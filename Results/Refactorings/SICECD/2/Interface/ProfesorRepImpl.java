import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ProfesorRepImpl implements ProfesorRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Profesor findByCorreo(String correo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByCorreo"))
    .queryParam("correo",correo);
  Profesor aux = restTemplate.getForObject(builder.toUriString(), Profesor.class)

 return aux;
}


public List<Profesor> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAll"))
  List<Profesor> aux = restTemplate.getForObject(builder.toUriString(), List<Profesor>.class)

 return aux;
}


}