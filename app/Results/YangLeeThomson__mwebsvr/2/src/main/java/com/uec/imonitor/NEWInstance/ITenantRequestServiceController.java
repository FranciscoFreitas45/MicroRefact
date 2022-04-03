package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ITenantRequestServiceController {

 private ITenantRequestService itenantrequestservice;


@GetMapping
("/findByTenantId")
public List<TenantRequestEntity> findByTenantId(@RequestParam(name = "tenantId") Integer tenantId){
  return itenantrequestservice.findByTenantId(tenantId);
}


@GetMapping
("/findById")
public TenantRequestEntity findById(@RequestParam(name = "id") int id){
  return itenantrequestservice.findById(id);
}


}