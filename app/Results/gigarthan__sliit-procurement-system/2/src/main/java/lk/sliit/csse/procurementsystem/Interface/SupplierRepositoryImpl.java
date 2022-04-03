package lk.sliit.csse.procurementsystem.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import lk.sliit.csse.procurementsystem.Interface.SupplierRepository;
public class SupplierRepositoryImpl implements SupplierRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Supplier> findByBlackListedFalse(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByBlackListedFalse"))
;  List<Supplier> aux = restTemplate.getForObject(builder.toUriString(), List<Supplier>.class);

 return aux;
}


public int setBlackListedFor(Boolean isBlacklisted,String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBlackListedFor"))
    .queryParam("isBlacklisted",isBlacklisted)
    .queryParam("name",name)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}