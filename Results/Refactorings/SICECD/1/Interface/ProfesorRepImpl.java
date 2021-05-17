import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ProfesorRepImpl implements ProfesorRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<Profesor> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAll"))
  List<Profesor> aux = restTemplate.getForObject(builder.toUriString(), List<Profesor>.class)

 return aux;
}


public Profesor findByRfc(String rfc){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByRfc"))
    .queryParam("rfc",rfc);
  Profesor aux = restTemplate.getForObject(builder.toUriString(), Profesor.class)

 return aux;
}


}