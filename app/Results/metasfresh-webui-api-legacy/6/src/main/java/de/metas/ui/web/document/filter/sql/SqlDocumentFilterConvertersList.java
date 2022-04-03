package de.metas.ui.web.document.filter.sql;
 import java.util.Collection;
import javax.annotation.concurrent.Immutable;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@Immutable
@ToString
@EqualsAndHashCode
public class SqlDocumentFilterConvertersList {

 static  SqlDocumentFilterConvertersList EMPTY;

 private  ImmutableList<SqlDocumentFilterConverter> converters;

 private  ImmutableList.Builder<SqlDocumentFilterConverter> converters;


public SqlDocumentFilterConverter getConverterOrDefault(String filterId,SqlDocumentFilterConverter defaultConverter){
    for (final SqlDocumentFilterConverter converter : converters) {
        if (converter.canConvert(filterId)) {
            return converter;
        }
    }
    return defaultConverter;
}


public SqlDocumentFilterConvertersList build(){
    if (converters == null) {
        return EMPTY;
    }
    final ImmutableList<SqlDocumentFilterConverter> converters = this.converters.build();
    if (converters.isEmpty()) {
        return EMPTY;
    }
    return new SqlDocumentFilterConvertersList(converters);
}


public Builder converter(SqlDocumentFilterConverter converter){
    if (converters == null) {
        converters = ImmutableList.builder();
    }
    converters.add(converter);
    return this;
}


public Builder builder(){
    return new Builder();
}


public Builder converters(Collection<SqlDocumentFilterConverter> converters){
    if (converters.isEmpty()) {
        return this;
    }
    if (this.converters == null) {
        this.converters = ImmutableList.builder();
    }
    this.converters.addAll(converters);
    return this;
}


}