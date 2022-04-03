package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.ResourceService;
public class ResourceServiceImpl implements ResourceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Page<ResourceDTO> getOwnedResources(Pageable pageable,Specification<Resource> specification){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOwnedResources"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
;  Page<ResourceDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<ResourceDTO>.class);

 return aux;
}


public Page<ResourceDTO> getUsersResources(Pageable pageable,Specification<Resource> specification,Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUsersResources"))
    .queryParam("pageable",pageable)
    .queryParam("specification",specification)
    .queryParam("userId",userId)
;  Page<ResourceDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<ResourceDTO>.class);

 return aux;
}


}