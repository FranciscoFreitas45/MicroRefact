import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CursoRepImpl implements CursoRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<Curso> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAll"))
  List<Curso> aux = restTemplate.getForObject(builder.toUriString(), List<Curso>.class)

 return aux;
}


public Curso findForClave(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findForClave"))
    .queryParam("clave",clave);
  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class)

 return aux;
}


}