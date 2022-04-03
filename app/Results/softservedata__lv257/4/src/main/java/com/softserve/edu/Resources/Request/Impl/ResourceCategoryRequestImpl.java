package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.ResourceCategory;
import com.softserve.edu.Resources.Request.ResourceCategoryRequest;
public class ResourceCategoryRequestImpl implements ResourceCategoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceCategory getCategory(Long idWR9B){
 ResourceCategory aux = restTemplate.getForObject("http://6/ResourceType/{id}/ResourceCategory/getCategory",ResourceCategory.class,idWR9B);
return aux;
}


public ResourceType setCategory(ResourceCategory category,Long idWR9B){
 ResourceType aux = restTemplate.getForObject("http://6/ResourceType/{id}/ResourceCategory/setCategory?'category'=category&'idWR9B'=idWR9B',",ResourceType.class,idWR9B);
return aux;
}


}