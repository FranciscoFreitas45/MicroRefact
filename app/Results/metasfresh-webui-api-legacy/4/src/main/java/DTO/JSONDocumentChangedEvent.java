package DTO;
 import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.util.lang.RepoIdAware;
import io.swagger.annotations.ApiModel;
import lombok.NonNull;
import lombok.Value;
public class JSONDocumentChangedEvent {

 private  JSONOperation operation;

 private  String path;

 private  Object value;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public BigDecimal getValueAsBigDecimal(BigDecimal defaultValueIfNull){
    return value != null ? toBigDecimal(value) : defaultValueIfNull;
}


public T getValueAsId(IntFunction<T> mapper){
    final int repoId = getValueAsInteger(-1);
    return mapper.apply(repoId);
}


public LocalDate getValueAsLocalDate(){
    return DateTimeConverters.fromObjectToLocalDate(value);
}


public String getValueAsString(String defaultValueIfNull){
    return value != null ? value.toString() : defaultValueIfNull;
}


public ZonedDateTime getValueAsZonedDateTime(){
    return DateTimeConverters.fromObjectToZonedDateTime(value);
}


public int getValueAsInteger(int defaultValueIfNull){
    final Integer valueInt = DataTypes.convertToInteger(value);
    return valueInt != null ? valueInt : defaultValueIfNull;
}


public IntegerLookupValue getValueAsIntegerLookupValue(){
    return DataTypes.convertToIntegerLookupValue(value);
}


public List<Integer> getValueAsIntegersList(){
    if (value == null) {
        return ImmutableList.of();
    }
    if (value instanceof List<?>) {
        @SuppressWarnings("unchecked")
        final List<Integer> intList = (List<Integer>) value;
        return intList;
    } else if (value instanceof String) {
        return ImmutableList.copyOf(DocumentIdsSelection.ofCommaSeparatedString(value.toString()).toIntSet());
    } else {
        throw new AdempiereException("Cannot convert value to int list").setParameter("event", this);
    }
}


public LookupValue getValueAsStringLookupValue(){
    if (value == null) {
        return null;
    } else if (value instanceof Map) {
        @SuppressWarnings("unchecked")
        final Map<String, Object> map = (Map<String, Object>) value;
        return JSONLookupValue.stringLookupValueFromJsonMap(map);
    } else if (value instanceof JSONLookupValue) {
        final JSONLookupValue json = (JSONLookupValue) value;
        if (json == null) {
            return null;
        }
        return json.toIntegerLookupValue();
    } else {
        throw new AdempiereException("Cannot convert value '" + value + "' (" + value.getClass() + ") to " + IntegerLookupValue.class);
    }
}


public Boolean getValueAsBoolean(Boolean defaultValue){
    return DisplayType.toBoolean(value, defaultValue);
}


public JSONDocumentChangedEvent replace(String fieldName,Object valueJson){
    return new JSONDocumentChangedEvent(JSONOperation.replace, fieldName, valueJson);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/replace"))

.queryParam("fieldName",fieldName);
.queryParam("valueJson",valueJson);
JSONDocumentChangedEvent aux = restTemplate.getForObject(builder.toUriString(),JSONDocumentChangedEvent.class);
return aux;
}


}