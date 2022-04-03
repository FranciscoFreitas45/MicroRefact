package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.PostCommentService;
public class PostCommentServiceImpl implements PostCommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object countByStatus(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByStatus"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}