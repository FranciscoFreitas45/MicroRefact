package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.AnchorRepository;
public class AnchorRepositoryImpl implements AnchorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Anchor findAnchorByUser(MobileUser user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAnchorByUser"))
    .queryParam("user",user)
;  Anchor aux = restTemplate.getForObject(builder.toUriString(), Anchor.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}