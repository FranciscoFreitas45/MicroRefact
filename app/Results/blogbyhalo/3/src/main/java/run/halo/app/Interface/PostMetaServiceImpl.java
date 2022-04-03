package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.PostMetaService;
public class PostMetaServiceImpl implements PostMetaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object listBy(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listBy"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object convertToMap(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertToMap"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}