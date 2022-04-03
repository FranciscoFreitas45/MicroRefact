package edu.xr.campusweibo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.Interface.PersistentTokenRepository;
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<PersistentToken> findByUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUser"))
    .queryParam("user",user)
;  List<PersistentToken> aux = restTemplate.getForObject(builder.toUriString(), List<PersistentToken>.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}