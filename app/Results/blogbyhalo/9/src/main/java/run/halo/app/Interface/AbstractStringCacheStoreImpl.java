package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.AbstractStringCacheStore;
public class AbstractStringCacheStoreImpl implements AbstractStringCacheStore{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void putAny(String key,T value,long timeout,TimeUnit timeUnit){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/putAny"))
    .queryParam("key",key)
    .queryParam("value",value)
    .queryParam("timeout",timeout)
    .queryParam("timeUnit",timeUnit)
;
  restTemplate.put(builder.toUriString(), null);
}


public Optional<T> getAny(String key,Class<T> type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAny"))
    .queryParam("key",key)
    .queryParam("type",type)
;  Optional<T> aux = restTemplate.getForObject(builder.toUriString(), Optional<T>.class);

 return aux;
}


}