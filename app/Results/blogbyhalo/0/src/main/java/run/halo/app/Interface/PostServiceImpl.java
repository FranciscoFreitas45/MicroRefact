package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.PostService;
public class PostServiceImpl implements PostService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object getById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object convertToMinimal(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertToMinimal"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}