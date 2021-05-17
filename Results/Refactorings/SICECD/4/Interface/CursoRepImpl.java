import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CursoRepImpl implements CursoRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Curso findByUniqueClaveCurso(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUniqueClaveCurso"))
    .queryParam("clave",clave);
  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class)

 return aux;
}


public void saveC(String clave,String nombre){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("saveC"))
    .queryParam("clave",clave)
    .queryParam("nombre",nombre);

  restTemplate.put(builder.toUriString(), null)



public Curso findByUniqueClaveCurso(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUniqueClaveCurso"))
    .queryParam("clave",clave);
  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class)

 return aux;
}


public Curso findByUniqueClaveCurso(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUniqueClaveCurso"))
    .queryParam("clave",clave);
  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class)

 return aux;
}


}