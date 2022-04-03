package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.ResourceRequest;
import com.softserve.edu.Resources.Request.ResourceRequestRequest;
public class ResourceRequestRequestImpl implements ResourceRequestRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceType setRequest(ResourceRequest request,long idW6RM){
 ResourceType aux = restTemplate.getForObject("http://3/ResourceType/{id}/ResourceRequest/setRequest?'request'=request&'idW6RM'=idW6RM',",ResourceType.class,idW6RM);
return aux;
}


public ResourceRequest getRequest(long idW6RM){
 ResourceRequest aux = restTemplate.getForObject("http://3/ResourceType/{id}/ResourceRequest/getRequest",ResourceRequest.class,idW6RM);
return aux;
}


}