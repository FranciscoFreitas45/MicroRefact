package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.ResourceService;
public class ResourceServiceImpl implements ResourceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findResourcesByResourceType"))
    .queryParam("genericResourceDTO",genericResourceDTO)
;  List<GenericResource> aux = restTemplate.getForObject(builder.toUriString(), List<GenericResource>.class);

 return aux;
}


public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findResourcesCountGroupedByResourceTypeForOwner"))
    .queryParam("ownerId",ownerId)
;  List<GroupedResourceCount> aux = restTemplate.getForObject(builder.toUriString(), List<GroupedResourceCount>.class);

 return aux;
}


public List<GenericResource> findResourcesByOwnerAndType(long ownerId,String resourceTypeName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findResourcesByOwnerAndType"))
    .queryParam("ownerId",ownerId)
    .queryParam("resourceTypeName",resourceTypeName)
;  List<GenericResource> aux = restTemplate.getForObject(builder.toUriString(), List<GenericResource>.class);

 return aux;
}


}