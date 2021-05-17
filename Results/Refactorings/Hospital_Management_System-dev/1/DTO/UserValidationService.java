import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.User;
public interface UserValidationService {

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public ModelMap getErrorModelMap()


public UserValidationService validLicenseNumber()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validLicenseNumber"));

UserValidationService aux = restTemplate.getForObject(builder.toUriString(),UserValidationService.class);
return aux;
}


public UserValidationService validSpeciality(String speciality)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validSpeciality"));

.queryParam("speciality",speciality);
UserValidationService aux = restTemplate.getForObject(builder.toUriString(),UserValidationService.class);
return aux;
}


public UserValidationService setUser(User user)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"));

.queryParam("user",user);
UserValidationService aux = restTemplate.getForObject(builder.toUriString(),UserValidationService.class);
return aux;
}


public UserValidationService validName()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validName"));

UserValidationService aux = restTemplate.getForObject(builder.toUriString(),UserValidationService.class);
return aux;
}


public UserValidationService validPassword()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validPassword"));

UserValidationService aux = restTemplate.getForObject(builder.toUriString(),UserValidationService.class);
return aux;
}


public UserValidationService samePassword(String passwordRetyped)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/samePassword"));

.queryParam("passwordRetyped",passwordRetyped);
UserValidationService aux = restTemplate.getForObject(builder.toUriString(),UserValidationService.class);
return aux;
}


}