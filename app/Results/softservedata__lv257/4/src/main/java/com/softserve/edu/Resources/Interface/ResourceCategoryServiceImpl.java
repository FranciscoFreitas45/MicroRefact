package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.ResourceCategoryService;
public class ResourceCategoryServiceImpl implements ResourceCategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Optional<ResourceCategory> findCategoryById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCategoryById"))
    .queryParam("id",id)
;  Optional<ResourceCategory> aux = restTemplate.getForObject(builder.toUriString(), Optional<ResourceCategory>.class);

 return aux;
}


}