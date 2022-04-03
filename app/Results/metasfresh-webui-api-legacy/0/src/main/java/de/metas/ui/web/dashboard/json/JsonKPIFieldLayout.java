package de.metas.ui.web.dashboard.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.dashboard.KPIField;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JsonKPIFieldLayout {

@JsonProperty("caption")
 private  String caption;

@JsonProperty("description")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String description;

@JsonProperty("unit")
 private  String unit;

@JsonProperty("fieldName")
 private  String fieldName;

@JsonProperty("dataType")
 private  String dataType;

@JsonProperty("color")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String color;


public JsonKPIFieldLayout field(KPIField kpiField,JSONOptions jsonOpts){
    final boolean isOffsetField = false;
    return new JsonKPIFieldLayout(kpiField, isOffsetField, jsonOpts);
}


public JsonKPIFieldLayout offsetField(KPIField kpiField,JSONOptions jsonOpts){
    final boolean isOffsetField = true;
    return new JsonKPIFieldLayout(kpiField, isOffsetField, jsonOpts);
}


}