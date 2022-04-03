package DTO;
 import java.util.Comparator;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewProfile;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.NonNull;
public class JSONViewProfilesList {

 private  JSONViewProfilesList EMPTY;

 private  List<JSONLookupValue> values;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public JSONViewProfilesList of(List<ViewProfile> viewProfiles,String adLanguage){
    if (viewProfiles.isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONLookupValue> jsonProfiles = viewProfiles.stream().map(viewProfile -> toJSONLookupValue(viewProfile, adLanguage)).sorted(Comparator.comparing(JSONLookupValue::getCaption)).collect(ImmutableList.toImmutableList());
    return new JSONViewProfilesList(jsonProfiles);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("viewProfiles",viewProfiles);
.queryParam("adLanguage",adLanguage);
JSONViewProfilesList aux = restTemplate.getForObject(builder.toUriString(),JSONViewProfilesList.class);
return aux;
}


}