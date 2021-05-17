import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GrupoRepImpl implements GrupoRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<Grupo> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAll"))
  List<Grupo> aux = restTemplate.getForObject(builder.toUriString(), List<Grupo>.class)

 return aux;
}


public Grupo findForClave(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findForClave"))
    .queryParam("clave",clave);
  Grupo aux = restTemplate.getForObject(builder.toUriString(), Grupo.class)

 return aux;
}


public Grupo findForClave(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findForClave"))
    .queryParam("clave",clave);
  Grupo aux = restTemplate.getForObject(builder.toUriString(), Grupo.class)

 return aux;
}


}