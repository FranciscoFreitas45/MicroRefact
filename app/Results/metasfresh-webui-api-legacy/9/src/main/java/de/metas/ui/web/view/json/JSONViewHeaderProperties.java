package de.metas.ui.web.view.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewHeaderProperties;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewHeaderProperties {

 private  JSONViewHeaderProperties EMPTY;

@JsonProperty("entries")
 private  ImmutableList<JSONViewHeaderProperty> entries;


public JSONViewHeaderProperties of(ViewHeaderProperties properties,String adLanguage){
    if (properties.getEntries().isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONViewHeaderProperty> jsonEntries = properties.getEntries().stream().map(entry -> JSONViewHeaderProperty.of(entry, adLanguage)).collect(ImmutableList.toImmutableList());
    return new JSONViewHeaderProperties(jsonEntries);
}


}