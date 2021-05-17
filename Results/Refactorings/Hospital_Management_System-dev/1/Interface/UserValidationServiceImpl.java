import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UserValidationServiceImpl implements UserValidationService{

 private RestTemplate restTemplate;

  String url = "http://0";


public UserValidationService setUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"))
    .queryParam("user",user);
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


public UserValidationService validLicenseNumber(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validLicenseNumber"))
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


public UserValidationService validSpeciality(String speciality){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validSpeciality"))
    .queryParam("speciality",speciality);
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


public boolean isValid(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isValid"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


public ModelMap getErrorModelMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getErrorModelMap"))
  ModelMap aux = restTemplate.getForObject(builder.toUriString(), ModelMap.class)

 return aux;
}


public UserValidationService clear(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clear"))
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


public UserValidationService validName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validName"))
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


public UserValidationService validPassword(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validPassword"))
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


public UserValidationService samePassword(String passwordRetyped){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/samePassword"))
    .queryParam("passwordRetyped",passwordRetyped);
  UserValidationService aux = restTemplate.getForObject(builder.toUriString(), UserValidationService.class)

 return aux;
}


}