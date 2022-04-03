package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TenantDataSourceProviderController {

 private TenantDataSourceProvider tenantdatasourceprovider;


@GetMapping
("/getProcessEngine")
public ProcessEngine getProcessEngine(){
  return tenantdatasourceprovider.getProcessEngine();
}


@GetMapping
("/getRepositoryService")
public Object getRepositoryService(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.getRepositoryService(Object);
}


@GetMapping
("/createDeployment")
public Object createDeployment(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.createDeployment(Object);
}


@GetMapping
("/name")
public Object name(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.name(Object);
}


@GetMapping
("/addClasspathResource")
public Object addClasspathResource(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.addClasspathResource(Object);
}


@GetMapping
("/deploy")
public Object deploy(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.deploy(Object);
}


@GetMapping
("/createProcessDefinitionQuery")
public Object createProcessDefinitionQuery(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.createProcessDefinitionQuery(Object);
}


@GetMapping
("/orderByProcessDefinitionVersion")
public Object orderByProcessDefinitionVersion(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.orderByProcessDefinitionVersion(Object);
}


@GetMapping
("/asc")
public Object asc(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.asc(Object);
}


@GetMapping
("/list")
public Object list(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.list(Object);
}


@GetMapping
("/getRuntimeService")
public Object getRuntimeService(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.getRuntimeService(Object);
}


@GetMapping
("/startProcessInstanceByKey")
public Object startProcessInstanceByKey(@RequestParam(name = "Object") Object Object){
  return tenantdatasourceprovider.startProcessInstanceByKey(Object);
}


}