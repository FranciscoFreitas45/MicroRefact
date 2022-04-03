package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.ResourceType;
import com.softserve.edu.Resources.Request.ResourceTypeRequest;
public class ResourceTypeRequestImpl implements ResourceTypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceCategory setResourceTypes(Set<ResourceType> resourceTypes,Long id){
 ResourceCategory aux = restTemplate.getForObject("http://4/ResourceCategory/{id}/ResourceType/setResourceTypes?'resourceTypes'=resourceTypes&'id'=id',",ResourceCategory.class,id);
return aux;
}


public Set<ResourceType> getResourceTypes(Long id){
 Set<ResourceType> aux = restTemplate.getForObject("http://4/ResourceCategory/{id}/ResourceType/getResourceTypes",Set<ResourceType>.class,id);
return aux;
}


}