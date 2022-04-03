package de.metas.ui.web.document.filter.sql;
 import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class SqlDocumentFilterConvertersListWithFallback implements SqlDocumentFilterConverter{

 private  SqlDocumentFilterConvertersList converters;

 private  SqlDocumentFilterConverter defaultConverter;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    // Find the effective converter to be used for given filter
    final String filterId = filter.getFilterId();
    final SqlDocumentFilterConverter effectiveConverter = converters.getConverterOrDefault(filterId, defaultConverter);
    // Convert the filter to SQL using the effective converter
    final String sqlFilter = effectiveConverter.getSql(sqlParamsOut, filter, sqlOpts, context);
    return sqlFilter;
}


@Override
public boolean canConvert(String filterId){
    return true;
}


public SqlDocumentFilterConvertersListWithFallback newInstance(SqlDocumentFilterConvertersList converters,SqlDocumentFilterConverter defaultConverter){
    return new SqlDocumentFilterConvertersListWithFallback(converters, defaultConverter);
}


}