package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.ConstrainedProperty;
public class ConstrainedPropertyImpl implements ConstrainedProperty{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean isRequired(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isRequired"))
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Object hashCode(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hashCode"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object equals(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public ResourceProperty getProperty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProperty"))
;  ResourceProperty aux = restTemplate.getForObject(builder.toUriString(), ResourceProperty.class);

 return aux;
}


}