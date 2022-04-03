package de.metas.ui.web.document.filter.sql;
 import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class SqlDocumentFilterConverters {


public SqlDocumentFilterConvertersList.Builder listBuilder(){
    return SqlDocumentFilterConvertersList.builder();
}


public SqlDocumentFilterConverter createEntityBindingEffectiveConverter(SqlEntityBinding entityBinding){
    final SqlDocumentFilterConvertersList converters = entityBinding.getFilterConverters();
    final SqlDocumentFilterConverter fallBackConverter = SqlDefaultDocumentFilterConverter.newInstance(entityBinding);
    final // 
    SqlDocumentFilterConvertersListWithFallback sqlDocumentFilterConverter = SqlDocumentFilterConvertersListWithFallback.newInstance(converters, fallBackConverter);
    final SqlDocumentFilterConverterDecorator decoratorOrNull = entityBinding.getFilterConverterDecorator().orElse(null);
    if (decoratorOrNull == null) {
        return sqlDocumentFilterConverter;
    }
    return decoratorOrNull.decorate(sqlDocumentFilterConverter);
}


public SqlDocumentFilterConvertersList emptyList(){
    return SqlDocumentFilterConvertersList.EMPTY;
}


}