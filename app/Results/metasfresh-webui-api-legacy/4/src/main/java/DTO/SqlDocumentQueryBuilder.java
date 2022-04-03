package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


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


public IPair<IStringExpression,List<Object>> getSqlWhereAndParams(){
    IPair<IStringExpression, List<Object>> sqlWhereAndParams = _sqlWhereAndParams;
    if (sqlWhereAndParams == null) {
        sqlWhereAndParams = buildSqlWhereClause();
        _sqlWhereAndParams = sqlWhereAndParams;
    }
    return sqlWhereAndParams;
}


public DocumentFilterList getDocumentFilters(){
    return documentFilters;
}


public String getPermissionsKey(){
    // TODO: introduce permissionsKey as parameter
    return UserRolePermissionsKey.toPermissionsKeyString(getCtx());
}


public Optional<IStringExpression> getSqlOrderByEffective(){
    final DocumentQueryOrderByList orderBys = getOrderBysEffective();
    return SqlDocumentOrderByBuilder.newInstance(entityBinding::getFieldOrderBy).joinOnTableNameOrAlias(entityBinding.getTableAlias()).buildSqlOrderBy(orderBys);
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


public String getAD_Language(){
    // TODO: introduce AD_Language as parameter
    return Env.getAD_Language(getCtx());
}


public DocumentQueryOrderByList getOrderBys(){
    return orderBys;
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


public int getPageLength(){
    return pageLength;
}


public SqlDocumentQueryBuilder of(DocumentQuery query){
    return new SqlDocumentQueryBuilder(query.getEntityDescriptor()).setDocumentFilters(query.getFilters()).setParentDocument(query.getParentDocument()).setRecordIds(query.getRecordIds()).noSorting(query.isNoSorting()).setOrderBys(query.getOrderBys()).setPage(query.getFirstRow(), query.getPageLength());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("query",query);
SqlDocumentQueryBuilder aux = restTemplate.getForObject(builder.toUriString(),SqlDocumentQueryBuilder.class);
return aux;
}


}