import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class Usuario_sysRepImpl implements Usuario_sysRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findById"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public List<Usuario_sys> findByRfc(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByRfc"))
    .queryParam("name",name);
  List<Usuario_sys> aux = restTemplate.getForObject(builder.toUriString(), List<Usuario_sys>.class)

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findById"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public List<Usuario_sys> findByRfc(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByRfc"))
    .queryParam("name",name);
  List<Usuario_sys> aux = restTemplate.getForObject(builder.toUriString(), List<Usuario_sys>.class)

 return aux;
}


}