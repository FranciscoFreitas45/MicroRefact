package com.project.stockexchangeappbackend.Interface;
public interface ResourceService {

   public Page<ResourceDTO> getOwnedResources(Pageable pageable,Specification<Resource> specification);
   public Page<ResourceDTO> getUsersResources(Pageable pageable,Specification<Resource> specification,Long userId);
}