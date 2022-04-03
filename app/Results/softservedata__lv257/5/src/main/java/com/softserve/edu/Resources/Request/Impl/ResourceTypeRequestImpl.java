package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.ResourceType;
import com.softserve.edu.Resources.Request.ResourceTypeRequest;
public class ResourceTypeRequestImpl implements ResourceTypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceType getResourceType(Long id2JVR){
 ResourceType aux = restTemplate.getForObject("http://4/ResourceOwning/{id}/ResourceType/getResourceType",ResourceType.class,id2JVR);
return aux;
}


public void setResourceType(ResourceType resourceType,Long id2JVR){
 restTemplate.put("http://4/ResourceOwning/{id}/ResourceType/setResourceType",resourceType,id2JVR);
 return ;
}


}