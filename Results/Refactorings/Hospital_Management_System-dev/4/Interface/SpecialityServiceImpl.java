import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SpecialityServiceImpl implements SpecialityService{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<Speciality> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
  List<Speciality> aux = restTemplate.getForObject(builder.toUriString(), List<Speciality>.class)

 return aux;
}


}