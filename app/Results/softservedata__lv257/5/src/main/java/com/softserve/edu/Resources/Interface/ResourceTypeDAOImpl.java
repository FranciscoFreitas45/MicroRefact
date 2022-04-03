package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.ResourceTypeDAO;
public class ResourceTypeDAOImpl implements ResourceTypeDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ResourceType findWithPropertiesByID(Long resourceTypeID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findWithPropertiesByID"))
    .queryParam("resourceTypeID",resourceTypeID)
;  ResourceType aux = restTemplate.getForObject(builder.toUriString(), ResourceType.class);

 return aux;
}


public Optional<ResourceType> findByTypeName(String typeName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTypeName"))
    .queryParam("typeName",typeName)
;  Optional<ResourceType> aux = restTemplate.getForObject(builder.toUriString(), Optional<ResourceType>.class);

 return aux;
}


public Optional<ResourceType> findById(Long id,boolean doFetch){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
    .queryParam("doFetch",doFetch)
;  Optional<ResourceType> aux = restTemplate.getForObject(builder.toUriString(), Optional<ResourceType>.class);

 return aux;
}


}