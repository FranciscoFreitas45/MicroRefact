import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SpecialityRepositoryImpl implements SpecialityRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public Speciality findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name);
  Speciality aux = restTemplate.getForObject(builder.toUriString(), Speciality.class)

 return aux;
}


}