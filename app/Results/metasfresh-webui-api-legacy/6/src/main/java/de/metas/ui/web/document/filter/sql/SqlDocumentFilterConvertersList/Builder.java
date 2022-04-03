package de.metas.ui.web.document.filter.sql.SqlDocumentFilterConvertersList;
 import java.util.Collection;
import javax.annotation.concurrent.Immutable;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class Builder {

 private  ImmutableList.Builder<SqlDocumentFilterConverter> converters;


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