package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
public class LinkDaoImpl implements LinkDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Link findByHash(String hash){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByHash"))
    .queryParam("hash",hash)
;  Link aux = restTemplate.getForObject(builder.toUriString(), Link.class);

 return aux;
}


public Object create(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}