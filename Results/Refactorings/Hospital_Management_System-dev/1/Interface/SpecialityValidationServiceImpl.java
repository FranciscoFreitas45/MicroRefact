import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SpecialityValidationServiceImpl implements SpecialityValidationService{

 private RestTemplate restTemplate;

  String url = "http://2";


public SpecialityValidationService clear(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clear"))
  SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(), SpecialityValidationService.class)

 return aux;
}


public SpecialityValidationService setSpeciality(Speciality speciality){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSpeciality"))
    .queryParam("speciality",speciality);
  SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(), SpecialityValidationService.class)

 return aux;
}


public SpecialityValidationService validName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validName"))
  SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(), SpecialityValidationService.class)

 return aux;
}


public SpecialityValidationService validLength(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validLength"))
  SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(), SpecialityValidationService.class)

 return aux;
}


public SpecialityValidationService validPrice(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validPrice"))
  SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(), SpecialityValidationService.class)

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


}