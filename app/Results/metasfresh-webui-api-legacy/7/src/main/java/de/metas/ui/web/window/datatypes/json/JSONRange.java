package de.metas.ui.web.window.datatypes.json;
 import java.time.LocalDate;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.DateRangeValue;
import lombok.Builder;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONRange {

@JsonProperty("value")
 private  String value;

@JsonProperty("valueTo")
 private  String valueTo;


public JSONRange of(DateRangeValue range){
    final LocalDate from = range.getFrom();
    final String jsonFrom = from != null ? DateTimeConverters.toJson(from) : null;
    final LocalDate to = range.getTo();
    final String jsonTo = to != null ? DateTimeConverters.toJson(to) : null;
    return new JSONRange(jsonFrom, jsonTo);
}


public DateRangeValue dateRangeFromJSONMap(Map<String,String> map){
    final String jsonFrom = map.get("value");
    final LocalDate from = DateTimeConverters.fromObjectToLocalDate(jsonFrom);
    final String jsonTo = map.get("valueTo");
    final LocalDate to = DateTimeConverters.fromObjectToLocalDate(jsonTo);
    return DateRangeValue.of(from, to);
}


}