package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymanski.sharelibrary.entity.User;
public class BaseUserResponse {

 private  Long id;

 private  String email;

 private  String username;

 private  String name;

 private  String surname;

 private  CoordinatesResponse coordinates;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public BaseUserResponse of(User user){
    return new BaseUserResponse(user.getId(), user.getEmail(), user.getUsername(), user.getName(), user.getSurname(), CoordinatesResponse.of(user.getCoordinates()));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("user",user)
;
BaseUserResponse aux = restTemplate.getForObject(builder.toUriString(),BaseUserResponse.class);
return aux;
}


}