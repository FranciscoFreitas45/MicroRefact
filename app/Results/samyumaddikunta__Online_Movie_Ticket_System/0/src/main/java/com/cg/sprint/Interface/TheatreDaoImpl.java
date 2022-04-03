package com.cg.sprint.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.sprint.Interface.TheatreDao;
public class TheatreDaoImpl implements TheatreDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Theatre> getTheatreList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTheatreList"))
;  List<Theatre> aux = restTemplate.getForObject(builder.toUriString(), List<Theatre>.class);

 return aux;
}


public Object existsById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}