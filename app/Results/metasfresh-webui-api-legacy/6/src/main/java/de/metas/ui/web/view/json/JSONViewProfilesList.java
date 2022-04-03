package de.metas.ui.web.view.json;
 import java.util.Comparator;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewProfile;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewProfilesList {

 private  JSONViewProfilesList EMPTY;

@JsonProperty("values")
 private  List<JSONLookupValue> values;


public JSONLookupValue toJSONLookupValue(ViewProfile viewProfile,String adLanguage){
    return JSONLookupValue.of(viewProfile.getProfileId().toJson(), viewProfile.getCaption().translate(adLanguage));
}


public JSONViewProfilesList of(List<ViewProfile> viewProfiles,String adLanguage){
    if (viewProfiles.isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONLookupValue> jsonProfiles = viewProfiles.stream().map(viewProfile -> toJSONLookupValue(viewProfile, adLanguage)).sorted(Comparator.comparing(JSONLookupValue::getCaption)).collect(ImmutableList.toImmutableList());
    return new JSONViewProfilesList(jsonProfiles);
}


}