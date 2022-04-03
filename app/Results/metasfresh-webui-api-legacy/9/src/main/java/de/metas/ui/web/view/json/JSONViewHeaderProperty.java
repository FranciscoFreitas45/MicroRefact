package de.metas.ui.web.view.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.view.ViewHeaderProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONViewHeaderProperty {

@JsonProperty("caption")
 private  String caption;

@JsonProperty("value")
 private  String value;


public JSONViewHeaderProperty of(ViewHeaderProperty property,String adLanguage){
    return JSONViewHeaderProperty.builder().caption(property.getCaption().translate(adLanguage)).value(property.getValue().translate(adLanguage)).build();
}


}