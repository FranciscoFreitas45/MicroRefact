package com.softserve.edu.Resources.Interface;
public interface ResourceService {

   public List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO);
   public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId);
   public List<GenericResource> findResourcesByOwnerAndType(long ownerId,String resourceTypeName);
}