package de.metas.ui.web.view.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.view.ViewResultColumn;
import de.metas.ui.web.window.datatypes.json.JSONLayoutWidgetType;
import lombok.NonNull;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONViewResultColumn {

@JsonProperty("fieldName")
 private  String fieldName;

@JsonProperty("widgetType")
 private  JSONLayoutWidgetType widgetType;

@JsonProperty("maxPrecision")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer maxPrecision;


public JSONViewResultColumn of(ViewResultColumn viewResultColumn){
    return new JSONViewResultColumn(viewResultColumn);
}


}