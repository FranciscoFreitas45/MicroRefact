package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrganizationRepositoryController {

 private OrganizationRepository organizationrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return organizationrepository.findById(Object);
}


@GetMapping
("/findAllDisabledDepartmentByPositionIdIn")
public List<OrganizationEntity> findAllDisabledDepartmentByPositionIdIn(@RequestParam(name = "ids") List<Long> ids){
  return organizationrepository.findAllDisabledDepartmentByPositionIdIn(ids);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return organizationrepository.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return organizationrepository.save(Object);
}


}