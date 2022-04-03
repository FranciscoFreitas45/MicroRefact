package com.softserve.edu.Resources.Request;
import com.softserve.edu.Resources.DTO.ResourceType;
public interface ResourceTypeRequest {

   public ResourceCategory setResourceTypes(Set<ResourceType> resourceTypes,Long id);
   public Set<ResourceType> getResourceTypes(Long id);
}