package com.softserve.edu.Resources.Interface;
public interface ResourceTypeDAO {

   public ResourceType findWithPropertiesByID(Long resourceTypeID);
   public Optional<ResourceType> findByTypeName(String typeName);
   public Optional<ResourceType> findById(Long id,boolean doFetch);
}