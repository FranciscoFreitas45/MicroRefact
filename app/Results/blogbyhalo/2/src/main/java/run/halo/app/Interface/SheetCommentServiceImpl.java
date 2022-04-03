package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.SheetCommentService;
public class SheetCommentServiceImpl implements SheetCommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object listAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object createInBatch(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createInBatch"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}