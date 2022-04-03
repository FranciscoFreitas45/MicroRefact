package lk.sliit.csse.procurementsystem.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import lk.sliit.csse.procurementsystem.Interface.Supplier;
public class SupplierImpl implements Supplier{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object setBlackListed(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBlackListed"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object isBlackListed(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isBlackListed"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getName"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}