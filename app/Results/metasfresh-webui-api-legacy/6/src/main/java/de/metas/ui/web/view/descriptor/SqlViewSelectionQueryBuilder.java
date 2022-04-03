package de.metas.ui.web.view.descriptor;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.IStringExpressionWrapper;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.slf4j.Logger;
import de.metas.logging.LogManager;
import de.metas.security.IUserRolePermissions;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.security.permissions.Access;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelection;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelectionLine;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectDisplayValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlDocumentOrderByBuilder;
import de.metas.ui.web.window.model.sql.SqlDocumentOrderByBuilder.SqlOrderByBindings;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class SqlViewSelectionQueryBuilder {

 private  Logger logger;

 private  SqlViewBinding _viewBinding;

 private  boolean applySecurityRestrictions;

 private  SqlDocumentFilterConverter _sqlDocumentFieldConverter;

 private  SqlAndParams sqlCreateSelectionLines;

 private  SqlAndParams sqlCreateSelection;


public SqlAndParams buildSqlCount(String selectionId,DocumentIdsSelection rowIds){
    Check.assumeNotEmpty(selectionId, "selectionId is not empty");
    final SqlAndParams.Builder sql = SqlAndParams.builder().append("SELECT COUNT(1) FROM " + I_T_WEBUI_ViewSelection.Table_Name + " WHERE " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=?", selectionId);
    if (rowIds.isAll()) {
        return sql.build();
    } else if (rowIds.isEmpty()) {
        throw new IllegalArgumentException("empty rowIds is not allowed");
    } else {
        return sql.append(" AND (").append(getSqlViewKeyColumnNamesMap().prepareSqlFilterByRowIds().rowIds(rowIds).build()).append(")").build();
    }
}


public boolean isGroupBy(String fieldName){
    return _viewBinding.isGroupBy(fieldName);
}


public SqlAndParams buildSqlAddRowIdsFromSelection(String selectionId,DocumentId rowId){
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = getSqlViewKeyColumnNamesMap();
    // TODO: we should also validate if the rowId is allowed to be part of this selection (e.g. enforce entity binding's SQL where clause)
    return SqlAndParams.builder().append("INSERT INTO " + I_T_WEBUI_ViewSelection.Table_Name + " (" + " " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + ", " + I_T_WEBUI_ViewSelection.COLUMNNAME_Line + ", " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated() + ")" + " SELECT ").append(" ? as UUID ", // UUID
    selectionId).append(", (select coalesce(max(Line), 0) + 1 from T_WEBUI_ViewSelection z where z.UUID=?) as Line", // Line
    selectionId).append(", ").append(// keys
    keyColumnNamesMap.getSqlValuesCommaSeparated(rowId)).append(" WHERE ").append(" NOT EXISTS(select 1 from " + I_T_WEBUI_ViewSelection.Table_Name + " z where z.UUID=?", selectionId).append(" and ").append(keyColumnNamesMap.prepareSqlFilterByRowIds().sqlColumnPrefix("z.").rowIds(DocumentIdsSelection.fromNullable(rowId)).build()).append(")").build();
}


public SqlAndParams buildSqlCreateSelectionLines_WithGrouping(ViewEvaluationCtx viewEvalCtx,ViewId newViewId,DocumentFilterList filters,int queryLimit,SqlDocumentFilterConverterContext filterConverterCtx){
    final String sqlTableName = getTableName();
    final String sqlTableAlias = getTableAlias();
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = getSqlViewKeyColumnNamesMap();
    final String keyColumnName = keyColumnNamesMap.getSingleKeyColumnName();
    final Set<String> groupByFieldNames = getGroupByFieldNames();
    // shall not happen
    Check.assumeNotEmpty(groupByFieldNames, "groupByFieldNames is not empty");
    final CompositeStringExpression.Builder sqlBuilder = IStringExpression.composer();
    // 
    // INSERT INTO T_WEBUI_ViewSelectionLine (...)
    sqlBuilder.append("INSERT INTO " + I_T_WEBUI_ViewSelectionLine.Table_Name + " (" + " " + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + ", " + keyColumnNamesMap.getSingleWebuiSelectionColumnName() + ", " + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID + ")");
    // 
    // SELECT ... FROM ... WHERE 1=1
    final List<Object> sqlParams = new ArrayList<>();
    {
        final IStringExpression sqlRecordId = ConstantStringExpression.of("MIN(" + keyColumnName + ") OVER agg");
        final IStringExpression sqlLineId = ConstantStringExpression.of(keyColumnName);
        sqlBuilder.append(IStringExpression.composer().append("\n SELECT ").append(// UUID
        "\n  ?").append("\n, ").append(// Record_ID
        sqlRecordId).append("\n ,").append(// Line_ID
        sqlLineId).append("\n FROM ").append(sqlTableName).append(" ").append(sqlTableAlias).append("\n WHERE 1=1 ").wrap(// security
        securityRestrictionsWrapper(sqlTableAlias)));
        sqlParams.add(newViewId.getViewId());
    }
    // 
    // WHERE clause (from query)
    {
        final SqlParamsCollector sqlWhereClauseParams = SqlParamsCollector.newInstance();
        final IStringExpression sqlWhereClause = buildSqlWhereClause(sqlWhereClauseParams, filters, SqlOptions.usingTableAlias(sqlTableAlias), filterConverterCtx);
        if (sqlWhereClause != null && !sqlWhereClause.isNullExpression()) {
            sqlBuilder.append("\n AND (\n").append(sqlWhereClause).append("\n)");
            sqlParams.addAll(sqlWhereClauseParams.toList());
        }
    }
    // 
    // WINDOW "agg" definition
    {
        final IStringExpression sqlAggregateWindowDef = IStringExpression.composer().append("agg AS (PARTITION BY ").append(groupByFieldNames.stream().collect(Collectors.joining(", "))).append(")").build();
        sqlBuilder.append("\n WINDOW ").append(sqlAggregateWindowDef);
    }
    // 
    // Enforce a LIMIT, to not affect server performances on huge tables
    if (queryLimit > 0) {
        sqlBuilder.append("\n LIMIT ?");
        sqlParams.add(queryLimit);
    }
    // 
    // Evaluate the final SQL query
    final String sql = sqlBuilder.build().evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail);
    return SqlAndParams.of(sql, sqlParams);
}


@Builder(builderMethodName = "prepareSqlWhereClause", builderClassName = "SqlWhereClauseBuilder")
public String buildSqlWhereClause(String sqlTableAlias,SqlViewKeyColumnNamesMap keyColumnNamesMap,String selectionId,DocumentIdsSelection rowIds,SqlViewRowIdsConverter rowIdsConverter){
    if (rowIds.isEmpty()) {
        new AdempiereException("got empty rowIds").throwIfDeveloperModeOrLogWarningElse(logger);
        return "1=0";
    }
    final StringBuilder sqlWhereClause = new StringBuilder();
    sqlWhereClause.append("exists (select 1 from " + I_T_WEBUI_ViewSelection.Table_Name + " sel " + " where " + " " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=" + DB.TO_STRING(selectionId) + " and " + keyColumnNamesMap.getSqlJoinCondition(sqlTableAlias, "sel") + ")");
    if (!rowIds.isAll()) {
        sqlWhereClause.append(" AND (").append(keyColumnNamesMap.prepareSqlFilterByRowIds().sqlColumnPrefix(sqlTableAlias + ".").useKeyColumnName(true).rowIds(rowIds).rowIdsConverter(rowIdsConverter).embedSqlParams(true).build().getSql()).append(")");
    }
    return sqlWhereClause.toString();
}


public SqlAndParams buildSqlDeleteSelectionLines(Set<String> selectionIds){
    final ArrayList<Object> sqlParams = new ArrayList<>(selectionIds.size());
    final String sql = "DELETE FROM " + I_T_WEBUI_ViewSelectionLine.Table_Name + " WHERE " + DB.buildSqlList(I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID, selectionIds, sqlParams);
    return SqlAndParams.of(sql, sqlParams);
}


public SqlViewSelectionQueryBuilder applySecurityRestrictions(boolean applySecurityRestrictions){
    this.applySecurityRestrictions = applySecurityRestrictions;
    return this;
}


public boolean hasGroupingFields(){
    return _viewBinding.hasGroupingFields();
}


public boolean isAggregated(String fieldName){
    return _viewBinding.isAggregated(fieldName);
}


public SqlAndParams buildSqlCreateSelectionLinesFromSelectionLines(ViewEvaluationCtx viewEvalCtx,ViewId newViewId,String fromSelectionId){
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = getSqlViewKeyColumnNamesMap();
    // 
    // INSERT INTO T_WEBUI_ViewSelectionLine (UUID, keys, Line_ID)
    final StringBuilder sqlBuilder = new StringBuilder().append("INSERT INTO " + I_T_WEBUI_ViewSelectionLine.Table_Name + " (" + " " + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + ", " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated() + ", " + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID + ")");
    // 
    // SELECT ... FROM T_WEBUI_ViewSelectionLine sl INNER JOIN ourTable on (Line_ID) WHERE sel.UUID=[fromUUID]
    {
        sqlBuilder.append("\n SELECT ").append(// newUUID
        "\n  ?").append("\n, ").append(// keys
        keyColumnNamesMap.getWebuiSelectionColumnNameForKeyColumnName("sl")).append("\n, sl.").append(// Line_ID
        I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID).append("\n FROM ").append(I_T_WEBUI_ViewSelectionLine.Table_Name).append(" sl ").append("\n WHERE sl.").append(I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID).append(// fromUUID
        "=?");
    }
    // 
    final String sql = sqlBuilder.toString();
    return SqlAndParams.of(sql, newViewId.getViewId(), fromSelectionId);
}


public SqlAndParams buildSqlCreateSelectionFromSelection(ViewEvaluationCtx viewEvalCtx,ViewId newViewId,String fromSelectionId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,SqlDocumentFilterConverterContext filterConverterCtx){
    final String sqlTableAlias = getTableAlias();
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = getSqlViewKeyColumnNamesMap();
    final DocumentQueryOrderByList orderBysEffective = orderBys.stream().flatMap(this::flatMapEffectiveFieldNames).collect(DocumentQueryOrderByList.toDocumentQueryOrderByList());
    // 
    // Build the table we will join.
    final SqlAndParams sqlSourceTable;
    {
        final Set<String> addedFieldNames = new HashSet<>();
        final StringBuilder sqlKeyColumnNames;
        {
            sqlKeyColumnNames = new StringBuilder();
            for (final String keyColumnName : keyColumnNamesMap.getKeyColumnNames()) {
                if (!addedFieldNames.add(keyColumnName)) {
                    continue;
                }
                if (sqlKeyColumnNames.length() > 0) {
                    sqlKeyColumnNames.append("\n, ");
                }
                sqlKeyColumnNames.append(getSqlSelectValue(keyColumnName).withColumnNameAlias(keyColumnName).toSqlStringWithColumnNameAlias());
            }
        }
        final SqlAndParams.Builder sqlSourceTableBuilder = SqlAndParams.builder();
        sqlSourceTableBuilder.append("(SELECT ").append(sqlKeyColumnNames);
        for (final DocumentQueryOrderBy orderBy : orderBysEffective.toList()) {
            final String fieldName = orderBy.getFieldName();
            final SqlSelectDisplayValue sqlSelectDisplayValue = getSqlSelectDisplayValue(fieldName);
            if (sqlSelectDisplayValue != null && addedFieldNames.add(sqlSelectDisplayValue.getColumnNameAlias())) {
                sqlSourceTableBuilder.append("\n, ").append(sqlSelectDisplayValue.withJoinOnTableNameOrAlias(getTableName()).toSqlStringWithColumnNameAlias(viewEvalCtx.toEvaluatee()));
            }
            final SqlSelectValue sqlSelectValue = getSqlSelectValue(fieldName);
            if (sqlSelectValue != null && addedFieldNames.add(sqlSelectValue.getColumnNameAlias())) {
                sqlSourceTableBuilder.append("\n, ").append(sqlSelectValue.withJoinOnTableNameOrAlias(getTableName()).toSqlStringWithColumnNameAlias());
            }
        }
        sqlSourceTableBuilder.append("\n FROM ").append(getTableName());
        final SqlAndParams sqlFilters = buildSqlFiltersOrNull(filters, filterConverterCtx, SqlOptions.usingTableName(getTableName()));
        if (sqlFilters != null) {
            sqlSourceTableBuilder.append("\n WHERE ").append(sqlFilters);
        }
        sqlSourceTableBuilder.append(")");
        sqlSourceTable = sqlSourceTableBuilder.build();
    }
    // 
    // Order BY
    final String sqlOrderBys = SqlDocumentOrderByBuilder.newInstance(this::getFieldOrderBy).joinOnTableNameOrAlias(sqlTableAlias).useColumnNameAlias(true).buildSqlOrderBy(orderBysEffective).map(sqlOrderBysExpr -> sqlOrderBysExpr.evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail)).map(sql -> _viewBinding.replaceTableNameWithTableAlias(sql, sqlTableAlias)).orElse(null);
    // 
    final String sqlJoinCondition = keyColumnNamesMap.getSqlJoinCondition(sqlTableAlias, "sel");
    // 
    return SqlAndParams.builder().append("INSERT INTO " + I_T_WEBUI_ViewSelection.Table_Name + " (" + " " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + ", " + I_T_WEBUI_ViewSelection.COLUMNNAME_Line + ", " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated() + ")").append("\n SELECT ").append("\n  ?", // newUUID
    newViewId.getViewId()).append("\n, ").append("row_number() OVER (").append(sqlOrderBys != null ? "ORDER BY " + sqlOrderBys : "").append(// Line
    ")").append("\n, ").append(// keys
    keyColumnNamesMap.getKeyColumnNamesCommaSeparated()).append("\n FROM ").append(I_T_WEBUI_ViewSelection.Table_Name).append(" sel").append("\n INNER JOIN ").append(sqlSourceTable).append(" ").append(sqlTableAlias).append(" ON (").append(sqlJoinCondition).append(")").append("\n WHERE sel.").append(I_T_WEBUI_ViewSelection.COLUMNNAME_UUID).append("=?", // fromUUID
    fromSelectionId).build();
}


public SqlAndParams buildSqlCreateSelectionFromSelectionLines(ViewEvaluationCtx viewEvalCtx,ViewId newViewId,DocumentQueryOrderByList orderBys){
    final String lineTableName = getTableName();
    final String lineTableAlias = getTableAlias();
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = getSqlViewKeyColumnNamesMap();
    final String lineKeyColumnName = keyColumnNamesMap.getSingleKeyColumnName();
    final SqlOrderByBindings sqlOrderByBindings = fieldName -> {
        if (keyColumnNamesMap.isKeyPartFieldName(fieldName)) {
            return SqlOrderByValue.ofColumnName("sl", keyColumnNamesMap.getWebuiSelectionColumnNameForKeyColumnName(fieldName));
        } else if (isGroupBy(fieldName)) {
            return getFieldOrderBy(fieldName);
        } else if (isAggregated(fieldName)) {
            return SqlOrderByValue.builder().sqlSelectValue(getSqlAggregatedColumn(fieldName)).build();
        } else {
            // shall not happen
            return null;
        }
    };
    final DocumentQueryOrderByList orderBysEffective = orderBys.stream().flatMap(this::flatMapEffectiveFieldNames).filter(orderBy -> keyColumnNamesMap.isKeyPartFieldName(orderBy.getFieldName()) || isGroupBy(orderBy.getFieldName()) || isAggregated(orderBy.getFieldName())).collect(DocumentQueryOrderByList.toDocumentQueryOrderByList());
    final String sqlOrderBy = SqlDocumentOrderByBuilder.newInstance(sqlOrderByBindings).joinOnTableNameOrAlias(lineTableAlias).useColumnNameAlias(false).buildSqlOrderBy(orderBysEffective).map(sqlOrderByExpr -> sqlOrderByExpr.evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail)).orElseGet(() -> keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated("sl"));
    final String sqlFrom = "SELECT " + "\n sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "\n, " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated("sl") + "\n FROM " + I_T_WEBUI_ViewSelectionLine.Table_Name + " sl " + "\n INNER JOIN " + lineTableName + " " + lineTableAlias + " ON (" + lineTableAlias + "." + lineKeyColumnName + " = sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID + // join lines
    ")" + "\n WHERE " + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "=?" + "\n GROUP BY " + "\n sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "\n, " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated("sl") + "\n, " + getGroupByFieldNamesCommaSeparated() + "\n ORDER BY " + sqlOrderBy;
    final String sqlCreateSelectionFromLines = "INSERT INTO " + I_T_WEBUI_ViewSelection.Table_Name + "(" + "\n " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "\n, " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated() + "\n, " + // SeqNo
    I_T_WEBUI_ViewSelection.COLUMNNAME_Line + "\n)" + "\n SELECT " + "\n   sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "\n , " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated("sl") + // SeqNo
    "\n , row_number() OVER ()" + "\n FROM (" + sqlFrom + ") sl";
    final List<Object> sqlCreateSelectionFromLinesParams = Arrays.asList(newViewId.getViewId());
    return SqlAndParams.of(sqlCreateSelectionFromLines, sqlCreateSelectionFromLinesParams);
}


public IStringExpression getSqlWhereClause(){
    return _viewBinding.getSqlWhereClause();
}


public SqlAndParams buildSqlCreateSelection_WithoutGrouping(ViewEvaluationCtx viewEvalCtx,ViewId newViewId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,int queryLimit,SqlDocumentFilterConverterContext filterConverterCtx){
    final String sqlTableName = getTableName();
    final String sqlTableAlias = getTableAlias();
    final SqlViewKeyColumnNamesMap keyColumnNamesMap = getSqlViewKeyColumnNamesMap();
    // 
    // INSERT INTO T_WEBUI_ViewSelection[Line] (...)
    final CompositeStringExpression.Builder sqlBuilder = IStringExpression.composer();
    sqlBuilder.append("INSERT INTO " + I_T_WEBUI_ViewSelection.Table_Name + " (" + " " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + ", " + // SeqNo
    I_T_WEBUI_ViewSelection.COLUMNNAME_Line + ", " + // keys: IntKey1... StringKey1...
    keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated() + ")");
    // 
    // SELECT ... FROM ... WHERE 1=1
    final ArrayList<Object> sqlParams = new ArrayList<>();
    {
        final IStringExpression sqlOrderBy = SqlDocumentOrderByBuilder.newInstance(this::getFieldOrderBy).joinOnTableNameOrAlias(sqlTableAlias).useColumnNameAlias(false).buildSqlOrderBy(orderBys).orElseGet(() -> ConstantStringExpression.of(keyColumnNamesMap.getKeyColumnNamesCommaSeparated(sqlTableAlias)));
        final IStringExpression sqlSeqNo = IStringExpression.composer().append("row_number() OVER (ORDER BY ").append(sqlOrderBy).append(")").build();
        sqlBuilder.append(IStringExpression.composer().append("\n SELECT ").append(// UUID
        "\n  ?").append("\n, ").append(// Line/SeqNo
        sqlSeqNo).append("\n, ").append(// keys
        keyColumnNamesMap.getKeyColumnNamesCommaSeparated(sqlTableAlias)).append("\n FROM ").append(sqlTableName).append(" ").append(sqlTableAlias).append("\n WHERE 1=1 ").wrap(// security
        securityRestrictionsWrapper(sqlTableAlias)));
        sqlParams.add(newViewId.getViewId());
    }
    // 
    // WHERE clause (from query)
    {
        final SqlParamsCollector sqlWhereClauseParams = SqlParamsCollector.newInstance();
        final IStringExpression sqlWhereClause = buildSqlWhereClause(sqlWhereClauseParams, filters, SqlOptions.usingTableAlias(sqlTableAlias), filterConverterCtx);
        if (sqlWhereClause != null && !sqlWhereClause.isNullExpression()) {
            sqlBuilder.append("\n AND (\n").append(sqlWhereClause).append("\n)");
            sqlParams.addAll(sqlWhereClauseParams.toList());
        }
    }
    // 
    // Enforce a LIMIT, to not affect server performances on huge tables
    if (queryLimit > 0) {
        sqlBuilder.append("\n LIMIT ?");
        sqlParams.add(queryLimit);
    }
    // 
    // Evaluate the final SQL query
    final String sql = sqlBuilder.build().evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail);
    // 
    // 
    return SqlAndParams.of(sql, sqlParams);
}


public SqlAndParams buildSqlFiltersOrNull(DocumentFilterList filters,SqlDocumentFilterConverterContext context,SqlOptions sqlOpts){
    if (filters.isEmpty()) {
        return null;
    }
    final SqlParamsCollector sqlParamsOut = SqlParamsCollector.newInstance();
    final String sql = getSqlDocumentFilterConverter().getSql(sqlParamsOut, filters, sqlOpts, context);
    return Check.isNotBlank(sql) ? SqlAndParams.of(sql, sqlParamsOut.toList()) : null;
}


public String getTableName(){
    return _viewBinding.getTableName();
}


public SqlSelectValue getSqlSelectValue(String fieldName){
    return _viewBinding.getFieldByFieldName(fieldName).getSqlSelectValue();
}


public Stream<DocumentQueryOrderBy> flatMapEffectiveFieldNames(DocumentQueryOrderBy orderBy){
    return _viewBinding.flatMapEffectiveFieldNames(orderBy);
}


public SqlAndParams buildSqlDeleteSelection(Set<String> selectionIds){
    final ArrayList<Object> sqlParams = new ArrayList<>(selectionIds.size());
    final String sql = "DELETE FROM " + I_T_WEBUI_ViewSelection.Table_Name + " WHERE " + DB.buildSqlList(I_T_WEBUI_ViewSelection.COLUMNNAME_UUID, selectionIds, sqlParams);
    return SqlAndParams.of(sql, sqlParams);
}


public SqlViewSelectionQueryBuilder newInstance(SqlViewBinding viewBinding){
    return new SqlViewSelectionQueryBuilder(viewBinding);
}


public SqlAndParams buildSqlDeleteRowIdsFromSelection(String selectionId,DocumentIdsSelection rowIds){
    if (rowIds.isEmpty()) {
        return null;
    }
    final List<Object> sqlParams = new ArrayList<>();
    final StringBuilder sql = new StringBuilder();
    sql.append("DELETE FROM " + I_T_WEBUI_ViewSelection.Table_Name + " WHERE " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=?");
    sqlParams.add(selectionId);
    if (rowIds.isAll()) {
    // nothing
    } else {
        final SqlAndParams sqlFilterByRowIds = getSqlViewKeyColumnNamesMap().prepareSqlFilterByRowIds().rowIds(rowIds).build();
        sql.append("\n AND (").append(sqlFilterByRowIds.getSql()).append(")");
        sqlParams.addAll(sqlFilterByRowIds.getSqlParams());
    }
    return SqlAndParams.of(sql.toString(), sqlParams);
}


public SqlSelectDisplayValue getSqlSelectDisplayValue(String fieldName){
    return _viewBinding.getFieldByFieldName(fieldName).getSqlSelectDisplayValue();
}


public SqlViewKeyColumnNamesMap getSqlViewKeyColumnNamesMap(){
    return _viewBinding.getSqlViewKeyColumnNamesMap();
}


public String getGroupByFieldNamesCommaSeparated(){
    return getGroupByFieldNames().stream().collect(Collectors.joining(", "));
}


public SqlCreateSelection buildSqlCreateSelectionFrom(ViewEvaluationCtx viewEvalCtx,ViewId newViewId,DocumentFilterList filters,DocumentQueryOrderByList orderBys,int queryLimit,SqlDocumentFilterConverterContext filterConverterCtx){
    if (!hasGroupingFields()) {
        final SqlAndParams sqlCreateSelection = buildSqlCreateSelection_WithoutGrouping(viewEvalCtx, newViewId, filters, orderBys, queryLimit, filterConverterCtx);
        return SqlCreateSelection.builder().sqlCreateSelection(sqlCreateSelection).build();
    } else {
        final SqlAndParams sqlCreateSelectionLines = buildSqlCreateSelectionLines_WithGrouping(viewEvalCtx, newViewId, filters, queryLimit, filterConverterCtx);
        final SqlAndParams sqlCreateSelection = buildSqlCreateSelectionFromSelectionLines(viewEvalCtx, newViewId, orderBys);
        return SqlCreateSelection.builder().sqlCreateSelection(sqlCreateSelection).sqlCreateSelectionLines(sqlCreateSelectionLines).build();
    }
}


public String getTableAlias(){
    return _viewBinding.getTableAlias();
}


public SqlViewRowIdsConverter getRowIdsConverter(){
    return _viewBinding.getRowIdsConverter();
}


public SqlDocumentFilterConverter getSqlDocumentFilterConverter(){
    if (_sqlDocumentFieldConverter == null) {
        _sqlDocumentFieldConverter = SqlDocumentFilterConverters.createEntityBindingEffectiveConverter(_viewBinding);
    }
    return _sqlDocumentFieldConverter;
}


public SqlOrderByValue getFieldOrderBy(String fieldName){
    return _viewBinding.getFieldOrderBy(fieldName);
}


public Set<String> getGroupByFieldNames(){
    return _viewBinding.getGroupByFieldNames();
}


public SqlSelectValue getSqlAggregatedColumn(String fieldName){
    return _viewBinding.getSqlAggregatedColumn(fieldName);
}


public IStringExpressionWrapper securityRestrictionsWrapper(String sqlTableAlias){
    if (applySecurityRestrictions) {
        return AccessSqlStringExpression.wrapper(sqlTableAlias, IUserRolePermissions.SQL_FULLYQUALIFIED, Access.READ);
    } else {
        return expression -> expression;
    }
}


public SqlAndParams buildSqlRetrieveSize(String selectionId){
    Check.assumeNotEmpty(selectionId, "selectionId is not empty");
    return SqlAndParams.of("SELECT COUNT(1) FROM " + I_T_WEBUI_ViewSelection.Table_Name + " WHERE " + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=?", selectionId);
}


public SqlAndParams buildSqlSelectRowIdsForLineIds(SqlViewKeyColumnNamesMap keyColumnNamesMap,String selectionId,Collection<Integer> lineIds){
    Check.assumeNotEmpty(lineIds, "lineIds is not empty");
    final List<Object> sqlParams = new ArrayList<>();
    sqlParams.add(selectionId);
    final String sql = "SELECT " + keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated() + " FROM " + I_T_WEBUI_ViewSelectionLine.Table_Name + " WHERE " + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "=?" + " AND " + DB.buildSqlList(I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID, lineIds, sqlParams);
    return SqlAndParams.of(sql, sqlParams);
}


}