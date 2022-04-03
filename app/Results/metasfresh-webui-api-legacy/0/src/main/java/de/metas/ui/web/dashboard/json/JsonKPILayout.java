package de.metas.ui.web.dashboard.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.dashboard.KPI;
import de.metas.ui.web.dashboard.KPIField;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JsonKPILayout {

@JsonProperty("description")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String description;

@JsonProperty("chartType")
 private  String chartType;

@JsonProperty("pollIntervalSec")
 private  int pollIntervalSec;

@JsonProperty("groupByField")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JsonKPIFieldLayout groupByField;

@JsonProperty("fields")
 private  List<JsonKPIFieldLayout> fields;


public JsonKPILayout of(KPI kpi,JSONOptions jsonOpts){
    return new JsonKPILayout(kpi, jsonOpts);
}


}