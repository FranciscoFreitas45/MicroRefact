package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.ResourceService;
public class ResourceServiceImpl implements ResourceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Resource saveResource(Resource resource){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveResource"))
    .queryParam("resource",resource)
;  Resource aux = restTemplate.getForObject(builder.toUriString(), Resource.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Resource> getResources(String pid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getResources"))
    .queryParam("pid",pid)
;  List<Resource> aux = restTemplate.getForObject(builder.toUriString(), List<Resource>.class);

 return aux;
}


}