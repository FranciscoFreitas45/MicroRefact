package DTO;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.util.time.SystemTime;
import lombok.Builder;
import lombok.ToString;
public class JSONUserSessionChangesEvent {

 private  String fullname;

 private  String email;

 private  String avatarId;

 private  JSONLookupValue language;

 private  String timestamp;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public boolean isEmpty(){
    return fullname == null && email == null && avatarId == null && language == null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}