package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.BasePostService;
public class BasePostServiceImpl implements BasePostService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void increaseVisit(Integer postId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/increaseVisit"))
    .queryParam("postId",postId)
;
  restTemplate.put(builder.toUriString(), null);
}


}