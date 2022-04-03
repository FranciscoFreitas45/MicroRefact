package DTO;
 import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;
public class ViewProfileId {

 public  ViewProfileId NULL;

 private  String id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public boolean isNull(ViewProfileId profileId){
    return profileId == null || Objects.equals(profileId, NULL);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isNull"))

.queryParam("profileId",profileId);
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}