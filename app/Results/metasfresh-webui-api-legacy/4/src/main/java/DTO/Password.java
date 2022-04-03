package DTO;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
public class Password {

 public  String OBFUSCATE_STRING;

 private  String password;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String getAsString(){
    return password;
}


public Password cast(Object value){
    return (Password) value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("value",value);
Password aux = restTemplate.getForObject(builder.toUriString(),Password.class);
return aux;
}


}