package com.softserve.edu.Resources.Request;
import com.softserve.edu.Resources.DTO.ResourceType;
public interface ResourceTypeRequest {

   public ResourceType getResourceType(Long id2JVR);
   public void setResourceType(ResourceType resourceType,Long id2JVR);
}