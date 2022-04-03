package de.metas.ui.web.window.datatypes.json;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import io.swagger.annotations.ApiModel;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONPatchEvent {

@JsonProperty("op")
 private  JSONOperation op;

@JsonProperty("path")
 private  PathType path;

@JsonProperty("value")
 private  Object value;


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


public boolean isReplace(){
    return op == JSONOperation.replace;
}


public ET getValueAsEnum(Class<ET> enumType){
    if (value == null) {
        return null;
    }
    if (enumType.isAssignableFrom(value.getClass())) {
        @SuppressWarnings("unchecked")
        final ET valueConv = (ET) value;
        return valueConv;
    } else if (value instanceof String) {
        return Enum.valueOf(enumType, value.toString());
    } else {
        throw new AdempiereException("Cannot convert value " + value + " to " + enumType);
    }
}


public String getValueAsString(){
    return value != null ? value.toString() : null;
}


public Boolean getValueAsBoolean(Boolean defaultValue){
    return DisplayType.toBoolean(value, defaultValue);
}


public int getValueAsInteger(int defaultValueIfNull){
    if (value == null) {
        return defaultValueIfNull;
    } else if (value instanceof Number) {
        return ((Number) value).intValue();
    } else {
        return Integer.parseInt(value.toString());
    }
}


}