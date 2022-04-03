package de.metas.ui.web.window.datatypes.json;
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
@ApiModel("document-change-event")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentChangedEvent {

@JsonProperty("op")
 private  JSONOperation operation;

@JsonProperty("path")
 private  String path;

@JsonProperty("value")
 private  Object value;


public BigDecimal getValueAsBigDecimal(BigDecimal defaultValueIfNull){
    return value != null ? toBigDecimal(value) : defaultValueIfNull;
}


public void assertReplaceOperation(){
    if (!isReplace()) {
        throw new AdempiereException("Replace operation was expected for " + this);
    }
}


public boolean isReplace(){
    return operation == JSONOperation.replace;
}


public JSONDocumentChangedEvent replace(String fieldName,Object valueJson){
    return new JSONDocumentChangedEvent(JSONOperation.replace, fieldName, valueJson);
}


public T getValueAsId(IntFunction<T> mapper){
    final int repoId = getValueAsInteger(-1);
    return mapper.apply(repoId);
}


public LocalDate getValueAsLocalDate(){
    return DateTimeConverters.fromObjectToLocalDate(value);
}


public BigDecimal toBigDecimal(Object value){
    if (value == null) {
        return null;
    } else if (value instanceof BigDecimal) {
        return (BigDecimal) value;
    } else {
        final String valueStr = value.toString().trim();
        if (valueStr.isEmpty()) {
            return null;
        }
        return new BigDecimal(valueStr);
    }
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


@JsonCreator
public JSONDocumentChangedEvent of(JSONOperation operation,String path,Object value){
    return new JSONDocumentChangedEvent(operation, path, value);
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


}