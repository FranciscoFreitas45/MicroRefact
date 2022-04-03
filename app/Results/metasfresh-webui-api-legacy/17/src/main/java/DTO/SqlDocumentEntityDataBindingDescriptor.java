package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.adempiere.ad.expression.api.ICachedStringExpression;
import org.adempiere.ad.expression.api.IExpressionFactory;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.IPair;
import org.compiere.model.POInfo;
import org.compiere.util.DB;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.security.IUserRolePermissions;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.security.permissions.Access;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.sql.SqlDocumentQueryBuilder;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.ToString;
public class SqlDocumentEntityDataBindingDescriptor implements SqlEntityBinding,DocumentEntityDataBindingDescriptor{

 private  String TABLEALIAS_Master;

 public  String FIELDNAME_Version;

 private  DocumentsRepository documentsRepository;

 private  String sqlTableName;

 private  String sqlTableAlias;

 private  String sqlLinkColumnName;

 private  String sqlParentLinkColumnName;

 private  ICachedStringExpression sqlSelectAllFrom;

 private  ICachedStringExpression sqlWhereClause;

 private  DocumentQueryOrderByList defaultOrderBys;

 private  ImmutableMap<String,SqlDocumentFieldDataBindingDescriptor> _fieldsByFieldName;

 private  ImmutableList<SqlDocumentFieldDataBindingDescriptor> keyFields;

 private  Optional<String> sqlSelectVersionById;

 private  SqlDocumentEntityDataBindingDescriptor _built;

 private  DocumentsRepository documentsRepository;

 private  String _sqlTableName;

 private  String _tableAlias;

 private  String _sqlLinkColumnName;

 private  String _sqlParentLinkColumnName;

 private  String _sqlWhereClause;

 private  IStringExpression _sqlWhereClauseExpression;

 private  IStringExpression _sqlSelectAll;

 private  LinkedHashMap<String,SqlDocumentFieldDataBindingDescriptor> _fieldsByFieldName;


public String getSqlParentLinkColumnName(){
    return _sqlParentLinkColumnName;
}


public Map<String,SqlDocumentFieldDataBindingDescriptor> getFieldsByFieldName(){
    return _fieldsByFieldName;
}


public IStringExpression getSqlWhereClauseExpression(){
    if (_sqlWhereClauseExpression == null) {
        _sqlWhereClauseExpression = buildSqlWhereClause();
    }
    return _sqlWhereClauseExpression;
}


public DocumentsRepository getDocumentsRepository(){
    Check.assumeNotNull(documentsRepository, "Parameter documentsRepository is not null");
    return documentsRepository;
}


@Override
public SqlDocumentFieldDataBindingDescriptor getFieldByFieldName(String fieldName){
    final SqlDocumentFieldDataBindingDescriptor field = _fieldsByFieldName.get(fieldName);
    if (field == null) {
        throw new IllegalArgumentException("No field found for fieldName=" + fieldName + " in " + this);
    }
    return field;
}


public POInfo getPOInfo(){
    return POInfo.getPOInfo(getTableName());
}


public String getSingleKeyColumnName(){
    Check.assume(keyFields.size() == 1, "Single key field: {}", this);
    return keyFields.get(0).getColumnName();
}


public String getParentLinkColumnName(){
    return sqlParentLinkColumnName;
}


public IStringExpression getSqlSelectAll(){
    if (_sqlSelectAll == null) {
        buildSqlSelects();
    }
    return _sqlSelectAll;
}


@Override
public SqlDocumentEntityDataBindingDescriptor getOrBuild(){
    if (_built == null) {
        _built = new SqlDocumentEntityDataBindingDescriptor(this);
    }
    return _built;
}


public List<SqlDocumentFieldDataBindingDescriptor> getKeyFields(){
    return getFieldsByFieldName().values().stream().filter(SqlDocumentFieldDataBindingDescriptor::isKeyColumn).collect(ImmutableList.toImmutableList());
}


public String getSqlWhereClauseById(DocumentId documentId){
    if (documentId.isInt()) {
        return getSqlWhereClauseById(documentId.toInt());
    } else {
        final Map<String, Object> idPartsByFieldName = SqlDocumentQueryBuilder.extractComposedKey(documentId, getKeyFields());
        final StringBuilder sql = new StringBuilder();
        for (final Map.Entry<String, Object> keyFieldNameAndValue : idPartsByFieldName.entrySet()) {
            String keyFieldName = keyFieldNameAndValue.getKey();
            final Object idPart = keyFieldNameAndValue.getValue();
            if (sql.length() > 0) {
                sql.append(" AND ");
            }
            sql.append(sqlTableName).append(".").append(keyFieldName).append("=").append(DB.TO_SQL(idPart));
        }
        return sql.toString();
    }
}


public String getSqlLinkColumnName(){
    return _sqlLinkColumnName;
}


@Override
public IStringExpression getSqlWhereClause(){
    return sqlWhereClause;
}


public String getTableName(){
    return _sqlTableName;
}


public SqlDocumentFieldDataBindingDescriptor getField(String fieldName){
    final SqlDocumentFieldDataBindingDescriptor field = getFieldsByFieldName().get(fieldName);
    if (field == null) {
        throw new AdempiereException("Field " + fieldName + " not found in " + this);
    }
    return field;
}


public String getTableAlias(){
    return _tableAlias;
}


public DocumentQueryOrderByList getDefaultOrderBys(){
    // Build the ORDER BY from fields
    return getFieldsByFieldName().values().stream().filter(field -> field.isDefaultOrderBy()).sorted(Comparator.comparing(SqlDocumentFieldDataBindingDescriptor::getDefaultOrderByPriority)).map(field -> DocumentQueryOrderBy.byFieldName(field.getFieldName(), field.isDefaultOrderByAscending())).collect(DocumentQueryOrderByList.toDocumentQueryOrderByList());
}


public String getLinkColumnName(){
    return sqlLinkColumnName;
}


public Collection<SqlDocumentFieldDataBindingDescriptor> getFields(){
    return _fieldsByFieldName.values();
}


public IStringExpression getSqlSelectAllFrom(){
    return sqlSelectAllFrom;
}


public Optional<String> getSqlSelectVersionById(){
    if (getFieldsByFieldName().get(FIELDNAME_Version) == null) {
        return Optional.empty();
    }
    final List<SqlDocumentFieldDataBindingDescriptor> keyColumns = getKeyFields();
    if (keyColumns.size() != 1) {
        return Optional.empty();
    }
    final String keyColumnName = keyColumns.get(0).getColumnName();
    final String sql = "SELECT " + FIELDNAME_Version + " FROM " + getTableName() + " WHERE " + keyColumnName + "=?";
    return Optional.of(sql);
}


}