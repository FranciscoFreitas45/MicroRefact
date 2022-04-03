package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.AbstractStringCacheStore;
public class AbstractStringCacheStoreImpl implements AbstractStringCacheStore{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object putIfAbsent(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/putIfAbsent"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}