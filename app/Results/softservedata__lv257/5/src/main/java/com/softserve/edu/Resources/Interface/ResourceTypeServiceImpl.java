package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.ResourceTypeService;
public class ResourceTypeServiceImpl implements ResourceTypeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ResourceType findWithPropertiesByID(Long ID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findWithPropertiesByID"))
    .queryParam("ID",ID)
;  ResourceType aux = restTemplate.getForObject(builder.toUriString(), ResourceType.class);

 return aux;
}


}