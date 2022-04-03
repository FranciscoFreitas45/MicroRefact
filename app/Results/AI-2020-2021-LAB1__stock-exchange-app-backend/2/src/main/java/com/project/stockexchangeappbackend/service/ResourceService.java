package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.MoveStockDTO;
import com.project.stockexchangeappbackend.dto.OwnerDTO;
import com.project.stockexchangeappbackend.dto.ResourceDTO;
import com.project.stockexchangeappbackend.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
public interface ResourceService {


public void moveStock(Long stockId,MoveStockDTO moveStock)
;

public Page<OwnerDTO> getStockOwners(Pageable pageable,Specification<Resource> specification,Long stockId)
;

public Page<ResourceDTO> getUsersResources(Pageable pageable,Specification<Resource> specification,Long userId)
;

public Page<ResourceDTO> getOwnedResources(Pageable pageable,Specification<Resource> specification)
;

}