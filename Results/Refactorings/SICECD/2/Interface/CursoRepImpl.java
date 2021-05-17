import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CursoRepImpl implements CursoRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Curso findByUniqueClave(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUniqueClave"))
    .queryParam("clave",clave);
  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class)

 return aux;
}


}