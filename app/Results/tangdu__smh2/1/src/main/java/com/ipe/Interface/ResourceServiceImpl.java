package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.ResourceService;
public class ResourceServiceImpl implements ResourceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<Resource> getResources(String pid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getResources"))
    .queryParam("pid",pid)
;  List<Resource> aux = restTemplate.getForObject(builder.toUriString(), List<Resource>.class);

 return aux;
}


}