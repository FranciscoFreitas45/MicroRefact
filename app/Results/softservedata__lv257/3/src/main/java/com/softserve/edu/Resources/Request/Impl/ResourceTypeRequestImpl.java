package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.ResourceType;
import com.softserve.edu.Resources.Request.ResourceTypeRequest;
public class ResourceTypeRequestImpl implements ResourceTypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceType getResourceType(Long idO93K){
 ResourceType aux = restTemplate.getForObject("http://4/ResourceRequest/{id}/ResourceType/getResourceType",ResourceType.class,idO93K);
return aux;
}


public ResourceRequest setResourceType(ResourceType resourceType,Long idO93K){
 ResourceRequest aux = restTemplate.getForObject("http://4/ResourceRequest/{id}/ResourceType/setResourceType?'resourceType'=resourceType&'idO93K'=idO93K',",ResourceRequest.class,idO93K);
return aux;
}


}