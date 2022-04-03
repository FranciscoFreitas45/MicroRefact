package de.metas.ui.web.view.descriptor;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelection;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelectionLine;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.sql.SqlSelectDisplayValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.util.Check;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class SqlViewSelectData {

 private  String COLUMNNAME_Paging_Prefix;

 private  String COLUMNNAME_Paging_UUID;

 public  String COLUMNNAME_Paging_SeqNo_OneBased;

 public  String COLUMNNAME_Paging_Parent_Prefix;

 public  String COLUMNNAME_IsRecordMissing;

 private  String sqlTableName;

 private  SqlViewKeyColumnNamesMap keyColumnNamesMap;

 private  ImmutableSet<String> displayFieldNames;

 private  ImmutableMap<String,SqlViewRowFieldBinding> fieldsByFieldName;

@Getter(AccessLevel.PRIVATE)
 private  IStringExpression sqlSelectByPage;

@Getter(AccessLevel.PRIVATE)
 private  IStringExpression sqlSelectRowIdsByPage;

@Getter(AccessLevel.PRIVATE)
 private  IStringExpression sqlSelectById;

 private  IStringExpression sqlSelectLines;


public List<SqlViewRowFieldBinding> extractKeyFields(Collection<SqlViewRowFieldBinding> allFields,SqlViewKeyColumnNamesMap keyColumnNamesMap){
    final List<String> keyColumnNames = keyColumnNamesMap.getKeyColumnNames();
    return allFields.stream().filter(field -> keyColumnNames.contains(field.getColumnName())).collect(ImmutableList.toImmutableList());
}


public IStringExpression buildSqlSelect_WithGrouping(String sqlTableName,String sqlTableAlias,SqlViewKeyColumnNamesMap keyColumnNamesMap,Collection<String> displayFieldNames,Collection<SqlViewRowFieldBinding> allFields,SqlViewGroupingBinding groupingBinding){
    final String sqlKeyColumnName = keyColumnNamesMap.getSingleKeyColumnName();
    final List<String> sqlSelectValuesList = new ArrayList<>();
    final List<IStringExpression> sqlSelectDisplayNamesList = new ArrayList<>();
    final List<String> sqlGroupBys = new ArrayList<>();
    allFields.forEach(field -> {
        final String fieldName = field.getFieldName();
        final boolean usingDisplayColumn = field.getSqlSelectDisplayValue() != null && displayFieldNames.contains(fieldName);
        // 
        if (keyColumnNamesMap.isKeyPartFieldName(field.getColumnName())) {
            sqlSelectValuesList.add("sel." + keyColumnNamesMap.getWebuiSelectionColumnNameForKeyColumnName(field.getColumnName()) + " AS " + field.getColumnName());
        } else if (groupingBinding.isGroupBy(fieldName)) {
            final SqlSelectValue sqlSelectValue = field.getSqlSelectValue();
            sqlSelectValuesList.add(sqlSelectValue.toSqlStringWithColumnNameAlias());
            sqlGroupBys.add(sqlSelectValue.toSqlString());
            if (usingDisplayColumn) {
                // TODO: introduce columnSql as parameter
                final SqlSelectDisplayValue sqlSelectDisplayValue = field.getSqlSelectDisplayValue();
                sqlSelectDisplayNamesList.add(sqlSelectDisplayValue.toStringExpressionWithColumnNameAlias());
            }
        } else {
            SqlSelectValue sqlSelectValueAgg = groupingBinding.getColumnSqlByFieldName(fieldName);
            if (sqlSelectValueAgg == null) {
                sqlSelectValueAgg = SqlSelectValue.builder().virtualColumnSql("NULL").columnNameAlias(field.getColumnName()).build();
            }
            sqlSelectValuesList.add(sqlSelectValueAgg.withColumnNameAlias(field.getColumnName()).toSqlStringWithColumnNameAlias());
        // FIXME: NOT supported atm
        // if (usingDisplayColumn)
        // {
        // sqlSelectDisplayValue = field.getSqlSelectDisplayValue(); // TODO: introduce columnSql as parameter
        // sqlSelectDisplayNamesList.add(sqlSelectDisplayValue);
        // }
        }
    });
    // NOTE: we don't need access SQL here because we assume the records were already filtered
    final CompositeStringExpression.Builder sql = IStringExpression.composer();
    sql.append("SELECT ").append("\n").append(sqlTableAlias).append(// Value fields
    ".*");
    if (!sqlSelectDisplayNamesList.isEmpty()) {
        // DisplayName fields
        sql.append(", \n").appendAllJoining("\n, ", sqlSelectDisplayNamesList);
    }
    sql.append("\n, ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated(columnName -> "null AS " + COLUMNNAME_Paging_Parent_Prefix + columnName));
    sql.append("\n FROM (").append("\n   SELECT ").append("\n   ").append(Joiner.on("\n   , ").join(sqlSelectValuesList)).append("\n , sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_Line + " AS " + COLUMNNAME_Paging_SeqNo_OneBased).append("\n , sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + " AS " + COLUMNNAME_Paging_UUID).append("\n , ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated(columnName -> "sel." + columnName + " AS " + COLUMNNAME_Paging_Prefix + columnName)).append("\n , (case when count(1) <= 0 then 'Y' else 'N' end) AS " + COLUMNNAME_IsRecordMissing).append("\n   FROM " + I_T_WEBUI_ViewSelection.Table_Name + " sel").append("\n   INNER JOIN " + I_T_WEBUI_ViewSelectionLine.Table_Name + " sl on (" + " sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "=sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + " and " + keyColumnNamesMap.prepareSqlJoinCondition().tableAlias1("sl").useKeyColumnNames1(false).tableAlias2("sel").useKeyColumnNames2(false).build() + ")").append("\n   LEFT OUTER JOIN " + sqlTableName + " ON (" + sqlTableName + "." + sqlKeyColumnName + " = sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID + ")").append("\n   WHERE sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=?").append("\n   GROUP BY ").append("\n   sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_Line).append("\n , sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID).append("\n , ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated("sel")).append("\n , " + Joiner.on("\n , ").join(sqlGroupBys)).append(// FROM
    "\n ) " + sqlTableAlias);
    return sql.build().caching();
}


public IStringExpression buildSqlSelectLines(String sqlTableName,String sqlTableAlias,SqlViewKeyColumnNamesMap keyColumnNamesMap,Collection<String> displayFieldNames,Collection<SqlViewRowFieldBinding> allFields){
    final List<String> sqlSelectValuesList = new ArrayList<>();
    final List<IStringExpression> sqlSelectDisplayNamesList = new ArrayList<>();
    allFields.forEach(field -> {
        // Collect the SQL select for internal value
        // NOTE: we need to collect all fields because, even if the field is not needed it might be present in some where clause
        sqlSelectValuesList.add(field.getSqlSelectValue().toSqlStringWithColumnNameAlias());
        // Collect the SQL select for displayed value,
        // * if there is one
        // * and if it was required by caller (i.e. present in fieldNames list)
        if (field.getSqlSelectDisplayValue() != null && displayFieldNames.contains(field.getFieldName())) {
            sqlSelectDisplayNamesList.add(field.getSqlSelectDisplayValue().toStringExpressionWithColumnNameAlias());
        }
    });
    // NOTE: we don't need access SQL here because we assume the records were already filtered
    final CompositeStringExpression.Builder sql = IStringExpression.composer();
    sql.append("SELECT ").append("\n").append(sqlTableAlias).append(// Value fields
    ".*");
    if (!sqlSelectDisplayNamesList.isEmpty()) {
        // DisplayName fields
        sql.append(", \n").appendAllJoining("\n, ", sqlSelectDisplayNamesList);
    }
    sql.append("\n, ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated(selColumnName -> COLUMNNAME_Paging_Prefix + selColumnName + " AS " + COLUMNNAME_Paging_Parent_Prefix + selColumnName));
    sql.append("\n FROM (").append("\n   SELECT ").append("\n   ").append(Joiner.on("\n   , ").join(sqlSelectValuesList)).append("\n , sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + " AS " + SqlViewSelectData.COLUMNNAME_Paging_UUID).append("\n , ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated(webuiSelectionColumnName -> "sl." + webuiSelectionColumnName + " AS " + COLUMNNAME_Paging_Prefix + webuiSelectionColumnName)).append("\n , ").append(keyColumnNamesMap.getSqlIsNullExpression(sqlTableName)).append(" AS ").append(COLUMNNAME_IsRecordMissing).append("\n   FROM " + I_T_WEBUI_ViewSelectionLine.Table_Name + " sl").append("\n   LEFT OUTER JOIN " + sqlTableName + " ON (" + sqlTableName + "." + keyColumnNamesMap.getSingleKeyColumnName() + " = sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_Line_ID + ")").append("\n   WHERE sl." + I_T_WEBUI_ViewSelectionLine.COLUMNNAME_UUID + "=?").append(// FROM
    "\n ) " + sqlTableAlias);
    return sql.build().caching();
}


@Builder(builderMethodName = "selectRowIdsByPage", builderClassName = "SelectRowIdsByPageBuilder")
public SqlAndParams selectRowIdsByIdPageBuilder(ViewEvaluationCtx viewEvalCtx,ViewId viewId,int firstRowZeroBased,int pageLength){
    Check.assume(firstRowZeroBased >= 0, "firstRow >= 0 but it was {}", firstRowZeroBased);
    Check.assume(pageLength > 0, "pageLength > 0 but it was {}", pageLength);
    final String viewSelectionId = viewId.getViewId();
    // NOTE: firstRow is 0-based while SeqNo are 1-based
    final int firstSeqNo = firstRowZeroBased + 1;
    final int lastSeqNo = firstRowZeroBased + pageLength;
    final IStringExpression sqlSelectRowIdsByPage = getSqlSelectRowIdsByPage();
    final String sql = sqlSelectRowIdsByPage.evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail);
    return SqlAndParams.of(sql, viewSelectionId, firstSeqNo, lastSeqNo);
}


@Builder(builderMethodName = "selectById", builderClassName = "SelectByIdBuilder")
public SqlAndParams selectByIdBuilder(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentId rowId){
    final String sql = getSqlSelectById().evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail);
    final ArrayList<Object> sqlParams = new ArrayList<>();
    sqlParams.add(viewId.getViewId());
    sqlParams.addAll(keyColumnNamesMap.getSqlValuesList(rowId));
    return SqlAndParams.of(sql, sqlParams);
}


public IStringExpression buildSqlSelect(String sqlTableName,String sqlTableAlias,SqlViewKeyColumnNamesMap keyColumnNamesMap,Collection<String> displayFieldNames,Collection<SqlViewRowFieldBinding> allFields,SqlViewGroupingBinding groupingBinding){
    if (groupingBinding == null) {
        return buildSqlSelect_WithoutGrouping(sqlTableName, sqlTableAlias, keyColumnNamesMap, displayFieldNames, allFields);
    } else {
        return buildSqlSelect_WithGrouping(sqlTableName, sqlTableAlias, keyColumnNamesMap, displayFieldNames, allFields, groupingBinding);
    }
}


public SqlAndParams selectFieldValues(ViewEvaluationCtx viewEvalCtx,String selectionId,String fieldName,int limit){
    Check.assumeGreaterThanZero(limit, "limit");
    final SqlViewRowFieldBinding field = fieldsByFieldName.get(fieldName);
    if (field == null) {
        throw new AdempiereException("Field `" + fieldName + "` not found. Available fields are: " + fieldsByFieldName.keySet());
    }
    final SqlSelectValue sqlValue = field.getSqlSelectValue();
    final SqlSelectDisplayValue sqlDisplayValue;
    if (field.getSqlSelectDisplayValue() != null && displayFieldNames.contains(fieldName)) {
        sqlDisplayValue = field.getSqlSelectDisplayValue();
    } else {
        sqlDisplayValue = null;
    }
    final CompositeStringExpression.Builder sqlExpression = IStringExpression.composer().append("SELECT DISTINCT ").append(sqlValue.getColumnNameAlias());
    if (sqlDisplayValue != null) {
        sqlExpression.append(", ").append(sqlDisplayValue.getColumnNameAlias());
    }
    sqlExpression.append("\n FROM (").append("\n SELECT ").append("\n ").append(sqlValue.withJoinOnTableNameOrAlias(sqlTableName).toSqlStringWithColumnNameAlias());
    if (sqlDisplayValue != null) {
        sqlExpression.append("\n, ").append(sqlDisplayValue.withJoinOnTableNameOrAlias(sqlTableName).toStringExpressionWithColumnNameAlias());
    }
    sqlExpression.append("\n FROM " + I_T_WEBUI_ViewSelection.Table_Name + " sel").append("\n INNER JOIN " + sqlTableName + " ON (" + keyColumnNamesMap.getSqlJoinCondition(sqlTableName, "sel") + ")").append("\n WHERE sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=?").append("\n ORDER BY sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_Line).append("\n) t").append("\n LIMIT ?");
    final String sql = sqlExpression.build().evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail);
    return SqlAndParams.of(sql, selectionId, limit);
}


@Builder(builderMethodName = "selectByPage", builderClassName = "SelectByPageBuilder")
public SqlAndParams selectByIdPageBuilder(ViewEvaluationCtx viewEvalCtx,ViewId viewId,int firstRowZeroBased,int pageLength){
    Check.assume(firstRowZeroBased >= 0, "firstRow >= 0 but it was {}", firstRowZeroBased);
    Check.assume(pageLength > 0, "pageLength > 0 but it was {}", pageLength);
    final String viewSelectionId = viewId.getViewId();
    // NOTE: firstRow is 0-based while SeqNo are 1-based
    final int firstSeqNo = firstRowZeroBased + 1;
    final int lastSeqNo = firstRowZeroBased + pageLength;
    final IStringExpression sqlSelectByPage = getSqlSelectByPage();
    final String sql = sqlSelectByPage.evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail);
    return SqlAndParams.of(sql, viewSelectionId, firstSeqNo, lastSeqNo);
}


public IStringExpression getSqlSelectLines(){
    Check.assumeNotNull(sqlSelectLines, "sqlSelectLines is not null (grouping not supported)");
    return sqlSelectLines;
}


public IStringExpression buildSqlSelect_WithoutGrouping(String sqlTableName,String sqlTableAlias,SqlViewKeyColumnNamesMap keyColumnNamesMap,Collection<String> displayFieldNames,Collection<SqlViewRowFieldBinding> allFields){
    final List<String> sqlSelectValuesList = new ArrayList<>();
    final List<IStringExpression> sqlSelectDisplayNamesList = new ArrayList<>();
    allFields.forEach(field -> {
        // Collect the SQL select for internal value
        // NOTE: we need to collect all fields because, even if the field is not needed it might be present in some where clause
        sqlSelectValuesList.add(field.getSqlSelectValue().toSqlStringWithColumnNameAlias());
        // Collect the SQL select for displayed value,
        // * if there is one
        // * and if it was required by caller (i.e. present in fieldNames list)
        if (field.getSqlSelectDisplayValue() != null && displayFieldNames.contains(field.getFieldName())) {
            sqlSelectDisplayNamesList.add(field.getSqlSelectDisplayValue().toStringExpressionWithColumnNameAlias());
        }
    });
    // NOTE: we don't need access SQL here because we assume the records were already filtered
    final CompositeStringExpression.Builder sql = IStringExpression.composer();
    sql.append("SELECT ").append("\n").append(sqlTableAlias).append(// Value fields
    ".*");
    if (!sqlSelectDisplayNamesList.isEmpty()) {
        // DisplayName fields
        sql.append("\n, ").appendAllJoining("\n, ", sqlSelectDisplayNamesList);
    }
    sql.append("\n, ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated(keyColumnName -> "null AS " + COLUMNNAME_Paging_Parent_Prefix + keyColumnName));
    sql.append("\n ," + COLUMNNAME_Paging_SeqNo_OneBased);
    sql.append("\n FROM (").append("\n   SELECT ").append("\n   ").append(Joiner.on("\n   , ").join(sqlSelectValuesList)).append("\n , sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_Line + " AS " + COLUMNNAME_Paging_SeqNo_OneBased).append("\n , sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + " AS " + COLUMNNAME_Paging_UUID).append("\n , ").append(keyColumnNamesMap.getWebuiSelectionColumnNamesCommaSeparated(columnName -> "sel." + columnName + " AS " + COLUMNNAME_Paging_Prefix + columnName)).append("\n , " + keyColumnNamesMap.getSqlIsNullExpression(sqlTableName) + " AS " + COLUMNNAME_IsRecordMissing).append("\n   FROM " + I_T_WEBUI_ViewSelection.Table_Name + " sel").append("\n   LEFT OUTER JOIN " + sqlTableName + " ON (" + keyColumnNamesMap.getSqlJoinCondition(sqlTableName, "sel") + ")").append("\n   WHERE sel." + I_T_WEBUI_ViewSelection.COLUMNNAME_UUID + "=?").append(// FROM
    "\n ) " + sqlTableAlias);
    return sql.build().caching();
}


@Builder(builderMethodName = "selectIncludedLines", builderClassName = "SelectIncludedLinesBuilder")
public SqlAndParams selectIncludedLinesBuilder(ViewEvaluationCtx viewEvalCtx,ViewId viewId,DocumentIdsSelection rowIds){
    final List<Object> sqlParams = new ArrayList<>();
    sqlParams.add(viewId.getViewId());
    final SqlAndParams sqlFilterByRowIds = keyColumnNamesMap.prepareSqlFilterByRowIds().sqlColumnPrefix(COLUMNNAME_Paging_Prefix).rowIds(rowIds).build();
    final String sql = new StringBuilder().append(getSqlSelectLines().evaluate(viewEvalCtx.toEvaluatee(), OnVariableNotFound.Fail)).append("\n WHERE ").append("\n").append(sqlFilterByRowIds.getSql()).toString();
    sqlParams.addAll(sqlFilterByRowIds.getSqlParams());
    return SqlAndParams.of(sql, sqlParams);
}


}