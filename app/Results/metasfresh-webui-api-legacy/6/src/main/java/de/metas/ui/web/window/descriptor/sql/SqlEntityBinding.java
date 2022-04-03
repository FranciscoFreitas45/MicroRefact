package de.metas.ui.web.window.descriptor.sql;
 import java.util.Optional;
import java.util.regex.Pattern;
import org.adempiere.ad.expression.api.IStringExpression;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConvertersList;
import lombok.NonNull;
public interface SqlEntityBinding {


public IStringExpression getSqlWhereClause()
;

public String getTableAlias()
;

public String replaceTableNameWithTableAlias(String sql,String tableName,String tableAlias){
    if (sql == null || sql.isEmpty()) {
        return sql;
    }
    final String matchTableNameIgnoringCase = "(?i)" + Pattern.quote(tableName + ".");
    final String sqlFixed = sql.replaceAll(matchTableNameIgnoringCase, tableAlias + ".");
    return sqlFixed;
}
;

public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    throw new UnsupportedOperationException();
}
;

public String getTableName()
;

public Optional<SqlDocumentFilterConverterDecorator> getFilterConverterDecorator(){
    return Optional.empty();
}
;

public SqlOrderByValue getFieldOrderBy(String fieldName){
    return getFieldByFieldName(fieldName).getSqlOrderBy();
}
;

public SqlEntityFieldBinding getFieldByFieldName(String fieldName)
;

public SqlDocumentFilterConvertersList getFilterConverters(){
    return SqlDocumentFilterConverters.emptyList();
}
;

}