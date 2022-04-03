package de.metas.ui.web.window.model.sql;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.security.UserRolePermissionsKey;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlEntityFieldBinding;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentQuery;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.ui.web.window.model.lookup.LookupValueByIdSupplier;
import de.metas.util.Check;
import lombok.NonNull;
public class SqlDocumentQueryBuilder {

 private  Properties ctx;

 private  SqlDocumentEntityDataBindingDescriptor entityBinding;

 private  Evaluatee _evaluationContext;

 private  DocumentFilterList documentFilters;

 private  Document parentDocument;

 private  Set<DocumentId> recordIds;

 private  boolean noSorting;

 private  DocumentQueryOrderByList orderBys;

 private  int firstRow;

 private  int pageLength;

 private  IPair<IStringExpression,List<Object>> _sqlWhereAndParams;

 private  IPair<IStringExpression,List<Object>> _sqlAndParams;


public Evaluatee createEvaluationContext(){
    final Evaluatee evalCtx = Evaluatees.mapBuilder().put(Env.CTXNAME_AD_Language, getAD_Language()).put(AccessSqlStringExpression.PARAM_UserRolePermissionsKey.getName(), getPermissionsKey()).build();
    final Evaluatee parentEvalCtx;
    if (parentDocument != null) {
        parentEvalCtx = parentDocument.asEvaluatee();
    } else {
        final Properties ctx = getCtx();
        // TODO: get the proper windowNo
        final int windowNo = Env.WINDOW_MAIN;
        final boolean onlyWindow = false;
        parentEvalCtx = Evaluatees.ofCtx(ctx, windowNo, onlyWindow);
    }
    return Evaluatees.compose(evalCtx, parentEvalCtx);
}


public IStringExpression getSqlSelectFrom(){
    return entityBinding.getSqlSelectAllFrom();
}


public String getSqlWhere(List<Object> sqlParams){
    final IPair<IStringExpression, List<Object>> sqlWhereAndParams = getSqlWhereAndParams();
    final Evaluatee evalCtx = getEvaluationContext();
    final String sqlWhere = sqlWhereAndParams.getLeft().evaluate(evalCtx, OnVariableNotFound.Fail);
    sqlParams.addAll(sqlWhereAndParams.getRight());
    return sqlWhere;
}


public IPair<IStringExpression,List<Object>> buildSqlWhereClause(){
    final SqlParamsCollector sqlParams = SqlParamsCollector.newInstance();
    final CompositeStringExpression.Builder sqlWhereClauseBuilder = IStringExpression.composer();
    // 
    // Entity's WHERE clause
    {
        final IStringExpression entityWhereClauseExpression = entityBinding.getSqlWhereClause();
        if (!entityWhereClauseExpression.isNullExpression()) {
            sqlWhereClauseBuilder.appendIfNotEmpty("\n AND ");
            sqlWhereClauseBuilder.append(" /* entity where clause */ (").append(entityWhereClauseExpression).append(")");
        }
    }
    // 
    // Key column
    // FIXME: handle AD_Reference/AD_Ref_List(s). In that case the recordId will be AD_Ref_List.Value,
    // so the SQL where clause which is currently build is AD_Ref_List_ID=<the AD_Ref_List.Value>.
    // The build SQL where clause shall be something like AD_Reference_ID=<the reference, i think we shall fetch it somehow from Lookup> AND Value=<the value, which currently is the recordId>
    final Set<DocumentId> recordIds = getRecordIds();
    if (!recordIds.isEmpty()) {
        final List<SqlDocumentFieldDataBindingDescriptor> keyFields = entityBinding.getKeyFields();
        if (keyFields.isEmpty()) {
            throw new AdempiereException("Failed building where clause because there is no Key Column defined in " + entityBinding);
        }
        // Single primary key
        if (keyFields.size() == 1) {
            final String singleKeyColumnName = keyFields.get(0).getColumnName();
            final ImmutableSet<Integer> recordIdsIntSet = recordIds.stream().map(DocumentId::toInt).collect(ImmutableSet.toImmutableSet());
            sqlWhereClauseBuilder.appendIfNotEmpty("\n /* key */ AND ");
            sqlWhereClauseBuilder.append(DB.buildSqlList(singleKeyColumnName, recordIdsIntSet, sqlParams.toLiveList()));
        } else // Composed primary key
        {
            final boolean parenthesesRequired = !sqlWhereClauseBuilder.isEmpty();
            if (parenthesesRequired) {
                sqlWhereClauseBuilder.append(" AND ( ");
            }
            boolean firstRecord = true;
            final boolean appendParentheses = recordIds.size() > 1;
            for (final DocumentId recordId : recordIds) {
                if (!firstRecord) {
                    sqlWhereClauseBuilder.append("\n OR ");
                }
                if (appendParentheses) {
                    sqlWhereClauseBuilder.append("(");
                }
                final Map<String, Object> keyColumnName2value = extractComposedKey(recordId, keyFields);
                boolean firstKeyPart = true;
                for (final Map.Entry<String, Object> keyPart : keyColumnName2value.entrySet()) {
                    if (!firstKeyPart) {
                        sqlWhereClauseBuilder.append(" AND ");
                    }
                    final String keyColumnName = keyPart.getKey();
                    final Object value = keyPart.getValue();
                    sqlWhereClauseBuilder.append(" ").append(keyColumnName).append("=").append(sqlParams.placeholder(value));
                    firstKeyPart = false;
                }
                if (appendParentheses) {
                    sqlWhereClauseBuilder.append(")");
                }
                firstRecord = false;
            }
            if (parenthesesRequired) {
                sqlWhereClauseBuilder.append(")");
            }
        }
    }
    // 
    // Parent link where clause (if any)
    final Document parentDocument = getParentDocument();
    if (parentDocument != null) {
        final String parentLinkColumnName = entityBinding.getParentLinkColumnName();
        final String linkColumnName = entityBinding.getLinkColumnName();
        if (parentLinkColumnName != null && linkColumnName != null) {
            final IDocumentFieldView parentLinkField = parentDocument.getFieldView(parentLinkColumnName);
            final Object parentLinkValue = parentLinkField.getValue();
            final DocumentFieldWidgetType parentLinkWidgetType = parentLinkField.getWidgetType();
            final Class<?> targetClass = entityBinding.getFieldByFieldName(linkColumnName).getSqlValueClass();
            final Object sqlParentLinkValue = SqlDocumentsRepository.convertValueToPO(parentLinkValue, parentLinkColumnName, parentLinkWidgetType, targetClass);
            sqlWhereClauseBuilder.appendIfNotEmpty("\n AND ");
            sqlWhereClauseBuilder.append(" /* parent link */ ").append(linkColumnName).append("=").append(sqlParams.placeholder(sqlParentLinkValue));
        }
    }
    // 
    // Document filters
    {
        final SqlDocumentFilterConverterContext context = SqlDocumentFilterConverterContext.EMPTY;
        final String sqlFilters = SqlDocumentFilterConverters.createEntityBindingEffectiveConverter(entityBinding).getSql(sqlParams, getDocumentFilters(), SqlOptions.usingTableAlias(entityBinding.getTableAlias()), context);
        if (!Check.isEmpty(sqlFilters, true)) {
            sqlWhereClauseBuilder.appendIfNotEmpty("\n AND ");
            sqlWhereClauseBuilder.append(" /* filters */ (\n").append(sqlFilters).append(")\n");
        }
    }
    // 
    // Build the final SQL where clause
    return ImmutablePair.of(sqlWhereClauseBuilder.build(), Collections.unmodifiableList(sqlParams.toList()));
}


public IPair<IStringExpression,List<Object>> getSqlAndParams(){
    IPair<IStringExpression, List<Object>> sqlAndParams = _sqlAndParams;
    if (sqlAndParams == null) {
        sqlAndParams = buildSql();
        _sqlAndParams = sqlAndParams;
    }
    return sqlAndParams;
}


public String getSqlMaxLineNo(List<Object> outSqlParams){
    final StringBuilder sql = new StringBuilder("SELECT COALESCE(MAX(" + WindowConstants.FIELDNAME_Line + "), 0)").append(" FROM " + entityBinding.getTableName() + " " + entityBinding.getTableAlias());
    String sqlWhere = getSqlWhere(outSqlParams);
    if (!Check.isEmpty(sqlWhere, true)) {
        sql.append(" WHERE ").append(sqlWhere);
    }
    return sql.toString();
}


public String getSql(List<Object> outSqlParams){
    final Evaluatee evalCtx = getEvaluationContext();
    final IPair<IStringExpression, List<Object>> sqlAndParams = getSqlAndParams();
    final String sql = sqlAndParams.getLeft().evaluate(evalCtx, OnVariableNotFound.Fail);
    final List<Object> sqlParams = sqlAndParams.getRight();
    outSqlParams.addAll(sqlParams);
    return sql;
}


public int getFirstRow(){
    return firstRow;
}


public Set<DocumentId> getRecordIds(){
    return recordIds;
}


public SqlDocumentQueryBuilder noSorting(boolean noSorting){
    this.noSorting = noSorting;
    if (noSorting) {
        orderBys = null;
    }
    return this;
}


public SqlDocumentQueryBuilder of(DocumentQuery query){
    return new SqlDocumentQueryBuilder(query.getEntityDescriptor()).setDocumentFilters(query.getFilters()).setParentDocument(query.getParentDocument()).setRecordIds(query.getRecordIds()).noSorting(query.isNoSorting()).setOrderBys(query.getOrderBys()).setPage(query.getFirstRow(), query.getPageLength());
}


public IPair<IStringExpression,List<Object>> getSqlWhereAndParams(){
    IPair<IStringExpression, List<Object>> sqlWhereAndParams = _sqlWhereAndParams;
    if (sqlWhereAndParams == null) {
        sqlWhereAndParams = buildSqlWhereClause();
        _sqlWhereAndParams = sqlWhereAndParams;
    }
    return sqlWhereAndParams;
}


public SqlDocumentQueryBuilder setDocumentFilters(DocumentFilterList documentFilters){
    this.documentFilters = documentFilters;
    return this;
}


public DocumentFilterList getDocumentFilters(){
    return documentFilters;
}


public String getPermissionsKey(){
    // TODO: introduce permissionsKey as parameter
    return UserRolePermissionsKey.toPermissionsKeyString(getCtx());
}


public SqlDocumentQueryBuilder setRecordIds(Set<DocumentId> recordIds){
    this.recordIds = recordIds != null ? ImmutableSet.copyOf(recordIds) : ImmutableSet.of();
    return this;
}


public String extractSingleKeyColumnName(DocumentEntityDescriptor entityDescriptor){
    final DocumentFieldDescriptor idField = entityDescriptor.getSingleIdField();
    final SqlDocumentFieldDataBindingDescriptor idFieldBinding = SqlDocumentFieldDataBindingDescriptor.castOrNull(idField.getDataBinding());
    if (idFieldBinding == null) {
        throw new AdempiereException("Entity's ID field does not have SQL bindings: " + idField);
    }
    return idFieldBinding.getColumnName();
}


public Optional<IStringExpression> getSqlOrderByEffective(){
    final DocumentQueryOrderByList orderBys = getOrderBysEffective();
    return SqlDocumentOrderByBuilder.newInstance(entityBinding::getFieldOrderBy).joinOnTableNameOrAlias(entityBinding.getTableAlias()).buildSqlOrderBy(orderBys);
}


public SqlDocumentQueryBuilder setPage(int firstRow,int pageLength){
    this.firstRow = firstRow;
    this.pageLength = pageLength;
    return this;
}


public Evaluatee getEvaluationContext(){
    if (_evaluationContext == null) {
        _evaluationContext = createEvaluationContext();
    }
    return _evaluationContext;
}


public DocumentQueryOrderByList getOrderBysEffective(){
    if (noSorting) {
        return DocumentQueryOrderByList.EMPTY;
    }
    final DocumentQueryOrderByList queryOrderBys = getOrderBys();
    if (queryOrderBys != null && !queryOrderBys.isEmpty()) {
        return queryOrderBys;
    }
    return entityBinding.getDefaultOrderBys();
}


public SqlDocumentQueryBuilder newInstance(DocumentEntityDescriptor entityDescriptor){
    return new SqlDocumentQueryBuilder(entityDescriptor);
}


public boolean isNoSorting(){
    return noSorting;
}


public String getAD_Language(){
    // TODO: introduce AD_Language as parameter
    return Env.getAD_Language(getCtx());
}


public DocumentQueryOrderByList getOrderBys(){
    return orderBys;
}


public SqlDocumentQueryBuilder setOrderBys(DocumentQueryOrderByList orderBys){
    // Don't throw exception if noSorting is true. Just do nothing.
    // REASON: it gives us better flexibility when this builder is handled by different methods, each of them adding stuff to it
    // Check.assume(!noSorting, "sorting enabled for {}", this);
    if (noSorting) {
        return this;
    }
    this.orderBys = orderBys != null ? orderBys : DocumentQueryOrderByList.EMPTY;
    return this;
}


public Map<String,Object> extractComposedKey(DocumentId recordId,List<? extends SqlEntityFieldBinding> keyFields){
    final int count = keyFields.size();
    if (count < 1) {
        throw new AdempiereException("Invalid composed key: " + keyFields);
    }
    final List<Object> composedKeyParts = recordId.toComposedKeyParts();
    if (composedKeyParts.size() != count) {
        throw new AdempiereException("Invalid composed key '" + recordId + "'. Expected " + count + " parts but it has " + composedKeyParts.size());
    }
    final ImmutableMap.Builder<String, Object> composedKey = ImmutableMap.builder();
    for (int i = 0; i < count; i++) {
        final SqlEntityFieldBinding keyField = keyFields.get(i);
        final String keyColumnName = keyField.getColumnName();
        final Object valueObj = composedKeyParts.get(i);
        final Object valueConv = DataTypes.convertToValueClass(keyColumnName, valueObj, keyField.getWidgetType(), keyField.getSqlValueClass(), (LookupValueByIdSupplier) null);
        composedKey.put(keyColumnName, valueConv);
    }
    return composedKey.build();
}


public String getSqlSelectParentId(List<Object> outSqlParams,DocumentEntityDescriptor parentEntityDescriptor){
    final String linkColumnName = entityBinding.getLinkColumnName();
    final String parentLinkColumnName = entityBinding.getParentLinkColumnName();
    if (parentLinkColumnName == null || linkColumnName == null) {
        throw new AdempiereException("Selecting parent ID is not possible because this entity does not have a parent link").setParameter("linkColumnName", linkColumnName).setParameter("parentLinkColumnName", parentLinkColumnName).setParameter("entityBinding", entityBinding);
    }
    // 
    // SELECT linkColumnName from current(child) tableName
    final List<Object> sqlSelectLinkColumnNameParams = new ArrayList<>();
    final CompositeStringExpression.Builder sqlSelectLinkColumnName = IStringExpression.composer();
    {
        final IPair<IStringExpression, List<Object>> sqlWhereAndParams = getSqlWhereAndParams();
        final IStringExpression sqlWhere = sqlWhereAndParams.getLeft();
        final List<Object> sqlWhereParams = sqlWhereAndParams.getRight();
        sqlSelectLinkColumnName.append("SELECT " + linkColumnName).append(// NOTE: we need table alias because the where clause is using it
        " FROM " + entityBinding.getTableName() + " " + entityBinding.getTableAlias()).append("\n WHERE ").append(sqlWhere);
        sqlSelectLinkColumnNameParams.addAll(sqlWhereParams);
    }
    // 
    // 
    final String parentKeyColumnName = extractSingleKeyColumnName(parentEntityDescriptor);
    if (Objects.equals(parentKeyColumnName, parentLinkColumnName)) {
        final Evaluatee evalCtx = getEvaluationContext();
        final String sql = sqlSelectLinkColumnName.build().evaluate(evalCtx, OnVariableNotFound.Fail);
        outSqlParams.addAll(sqlSelectLinkColumnNameParams);
        return sql;
    } else {
        final Evaluatee evalCtx = getEvaluationContext();
        final String sql = IStringExpression.composer().append("SELECT " + parentKeyColumnName + " FROM " + parentEntityDescriptor.getTableName()).append("\n WHERE " + parentLinkColumnName + " IN (").append(sqlSelectLinkColumnName).append(")").build().evaluate(evalCtx, OnVariableNotFound.Fail);
        outSqlParams.addAll(sqlSelectLinkColumnNameParams);
        return sql;
    }
}


public Document getParentDocument(){
    return parentDocument;
}


public Properties getCtx(){
    return ctx;
}


public IPair<IStringExpression,List<Object>> buildSql(){
    final List<Object> sqlParams = new ArrayList<>();
    final CompositeStringExpression.Builder sqlBuilder = IStringExpression.composer();
    // 
    // SELECT ... FROM ...
    sqlBuilder.append(getSqlSelectFrom());
    // NOTE: no need to add security here because it was already embedded in SqlSelectFrom
    // 
    // WHERE
    {
        final IPair<IStringExpression, List<Object>> sqlWhereClauseAndParams = getSqlWhereAndParams();
        final IStringExpression sqlWhereClause = sqlWhereClauseAndParams.getLeft();
        if (!sqlWhereClause.isNullExpression()) {
            sqlBuilder.append("\n WHERE ").append(sqlWhereClause);
            sqlParams.addAll(sqlWhereClauseAndParams.getRight());
        }
    }
    // 
    // ORDER BY
    if (isSorting()) {
        final IStringExpression sqlOrderBy = getSqlOrderByEffective().orElse(null);
        if (sqlOrderBy != null) {
            sqlBuilder.append("\n ORDER BY ").append(sqlOrderBy);
        }
    }
    // 
    // LIMIT/OFFSET
    {
        final int firstRow = getFirstRow();
        if (firstRow > 0) {
            sqlBuilder.append("\n OFFSET ?");
            sqlParams.add(firstRow);
        }
        final int pageLength = getPageLength();
        if (pageLength > 0) {
            sqlBuilder.append("\n LIMIT ?");
            sqlParams.add(pageLength);
        }
    }
    // 
    // 
    return ImmutablePair.of(sqlBuilder.build(), Collections.unmodifiableList(sqlParams));
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("TableName", entityBinding.getTableName()).toString();
}


public int getPageLength(){
    return pageLength;
}


public SqlDocumentQueryBuilder setParentDocument(Document parentDocument){
    this.parentDocument = parentDocument;
    return this;
}


public boolean isSorting(){
    return !noSorting;
}


}