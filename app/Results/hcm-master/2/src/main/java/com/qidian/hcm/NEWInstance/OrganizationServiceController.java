package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrganizationServiceController {

 private OrganizationService organizationservice;


@GetMapping
("/listSelfAndChildrenIds")
public List<Long> listSelfAndChildrenIds(@RequestParam(name = "ids") List<Long> ids){
  return organizationservice.listSelfAndChildrenIds(ids);
}


@GetMapping
("/getIdEntityMap")
public Map<Long,OrganizationEntity> getIdEntityMap(){
  return organizationservice.getIdEntityMap();
}


@GetMapping
("/listAllPermissionOrgIds")
public List<Long> listAllPermissionOrgIds(@RequestParam(name = "platformType") PlatformType platformType,@RequestParam(name = "menuCode") MenuCode menuCode,@RequestParam(name = "actionType") ActionType actionType){
  return organizationservice.listAllPermissionOrgIds(platformType,menuCode,actionType);
}


@GetMapping
("/getIdNameMap")
public Map<Long,String> getIdNameMap(){
  return organizationservice.getIdNameMap();
}


@GetMapping
("/listOrganizationPathByIds")
public List<String> listOrganizationPathByIds(@RequestParam(name = "ids") List<Long> ids){
  return organizationservice.listOrganizationPathByIds(ids);
}


@PutMapping
("/createOrganizationPath")
public void createOrganizationPath(@RequestParam(name = "organization") OrganizationEntity organization){
organizationservice.createOrganizationPath(organization);
}


}