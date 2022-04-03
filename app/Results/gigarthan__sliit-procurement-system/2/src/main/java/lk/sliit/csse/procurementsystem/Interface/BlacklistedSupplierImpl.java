package lk.sliit.csse.procurementsystem.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplier;
public class BlacklistedSupplierImpl implements BlacklistedSupplier{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object setSupplier(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSupplier"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getSupplier(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSupplier"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getId(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}