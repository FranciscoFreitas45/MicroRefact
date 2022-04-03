package lk.sliit.csse.procurementsystem.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import lk.sliit.csse.procurementsystem.Interface.SiteManager;
public class SiteManagerImpl implements SiteManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object setEnabled(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEnabled"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object setPassword(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPassword"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}