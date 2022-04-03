package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.project.stockexchangeappbackend.dto.ResourceDTO;
 import org.springframework.data.domain.*;
 import org.springframework.data.jpa.domain.Specification;
 import com.project.stockexchangeappbackend.DTO.Resource;
 import com.project.stockexchangeappbackend.service.ResourceService;
@RestController
@CrossOrigin
public class ResourceServiceController {

 private ResourceService resourceservice;


@GetMapping
("/getOwnedResources")
public Page<ResourceDTO> getOwnedResources(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<Resource> specification){
  return resourceservice.getOwnedResources(pageable,specification);
}


@GetMapping
("/getUsersResources")
public Page<ResourceDTO> getUsersResources(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<Resource> specification,@RequestParam(name = "userId") Long userId){
  return resourceservice.getUsersResources(pageable,specification,userId);
}


}