package DTO;
 import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
public class DocumentFilterParam {

 private  boolean joinAnd;

 private  String fieldName;

 private  Operator operator;

 private  Object value;

 private  Object valueTo;

 private  SqlAndParams sqlWhereClause;

 private  boolean joinAnd;

 private  String fieldName;

 private  Operator operator;

 private  Object value;

 private  Object valueTo;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public int getValueAsInt(int defaultValue){
    final Integer valueInt = convertToInt(value);
    return valueInt != null ? valueInt : defaultValue;
}


public Instant getValueAsInstant(){
    return value != null ? DateTimeConverters.fromObjectToInstant(value) : null;
}


public Instant getValueToAsInstant(){
    return valueTo != null ? DateTimeConverters.fromObjectToInstant(valueTo) : null;
}


public LocalDate getValueAsLocalDate(){
    return DateTimeConverters.fromObjectToLocalDate(value);
}


public String getValueAsString(){
    return value != null ? value.toString() : null;
}


public Collection<?> getValueAsCollection(){
    if (value == null) {
        throw new AdempiereException("Cannot convert null value to Collection<?>");
    } else if (value instanceof Collection) {
        return (Collection<?>) value;
    } else if (value instanceof LookupValuesList) {
        final Collection<LookupValue> lookupValues = ((LookupValuesList) value).getValues();
        return lookupValues;
    } else {
        return ImmutableList.of(value);
    // throw new AdempiereException("Cannot convert value to Collection<?>: " + value + " (" + value.getClass() + ")");
    }
}


public LocalDate getValueToAsLocalDateOr(LocalDate defaultValue){
    return valueTo != null ? DateTimeConverters.fromObjectToLocalDate(valueTo) : defaultValue;
}


public T getValueAsRepoIdOrNull(IntFunction<T> repoIdMapper){
    final int idInt = getValueAsInt(-1);
    if (idInt < 0) {
        return null;
    }
    return repoIdMapper.apply(idInt);
}


public List<T> getValueAsList(Function<Object,T> itemConverter){
    final Collection<?> valueAsCollection = getValueAsCollection();
    if (valueAsCollection == null) {
        throw new AdempiereException("Cannot convert null value to List<Integer>");
    }
    if (valueAsCollection.isEmpty()) {
        return ImmutableList.of();
    }
    return valueAsCollection.stream().map(itemConverter).collect(ImmutableList.toImmutableList());
}


public Boolean getValueAsBoolean(Boolean defaultValue){
    return DisplayType.toBoolean(value, defaultValue);
}


public List<Integer> getValueAsIntList(){
    return getValueAsList(itemObj -> convertToInt(itemObj));
}


public LocalDate getValueAsLocalDateOr(LocalDate defaultValue){
    return value != null ? DateTimeConverters.fromObjectToLocalDate(value) : defaultValue;
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public boolean isSqlFilter(){
    return getSqlWhereClause() != null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSqlFilter"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}