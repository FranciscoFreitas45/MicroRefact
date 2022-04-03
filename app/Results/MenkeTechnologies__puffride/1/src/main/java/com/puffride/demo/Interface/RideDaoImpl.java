package com.puffride.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.Interface.RideDao;
public class RideDaoImpl implements RideDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Ride> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Ride> aux = restTemplate.getForObject(builder.toUriString(), List<Ride>.class);

 return aux;
}


public Object stream(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object filter(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/filter"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}