package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaterialUseServiceController {

 private MaterialUseService materialuseservice;


@GetMapping
("/countNoUseRecord")
public int countNoUseRecord(@RequestParam(name = "companyId") String companyId){
  return materialuseservice.countNoUseRecord(companyId);
}


@GetMapping
("/queryNoUseRecord")
public List<RecordBaseView> queryNoUseRecord(@RequestParam(name = "companyId") String companyId,@RequestParam(name = "pager") Pager pager){
  return materialuseservice.queryNoUseRecord(companyId,pager);
}


@GetMapping
("/countHasUseRecord")
public int countHasUseRecord(@RequestParam(name = "companyId") String companyId){
  return materialuseservice.countHasUseRecord(companyId);
}


@GetMapping
("/queryHasUseRecord")
public List<RecordBaseView> queryHasUseRecord(@RequestParam(name = "companyId") String companyId,@RequestParam(name = "pager") Pager pager){
  return materialuseservice.queryHasUseRecord(companyId,pager);
}


@GetMapping
("/userWorksStatusByPager")
public List<WorkInfo> userWorksStatusByPager(@RequestParam(name = "user") User user,@RequestParam(name = "status") String status,@RequestParam(name = "pager") Pager pager){
  return materialuseservice.userWorksStatusByPager(user,status,pager);
}


@GetMapping
("/countUserWorksStatus")
public int countUserWorksStatus(@RequestParam(name = "user") User user,@RequestParam(name = "status") String status){
  return materialuseservice.countUserWorksStatus(user,status);
}


@GetMapping
("/queryDetailsByRecordId")
public List<DetailTemp> queryDetailsByRecordId(@RequestParam(name = "recordId") String recordId,@RequestParam(name = "companyId") String companyId,@RequestParam(name = "pager") Pager pager){
  return materialuseservice.queryDetailsByRecordId(recordId,companyId,pager);
}


@GetMapping
("/countDetailsByRecordId")
public int countDetailsByRecordId(@RequestParam(name = "recordId") String recordId,@RequestParam(name = "companyId") String companyId){
  return materialuseservice.countDetailsByRecordId(recordId,companyId);
}


@GetMapping
("/companyEmps")
public List<User> companyEmps(@RequestParam(name = "companyId") String companyId){
  return materialuseservice.companyEmps(companyId);
}


@GetMapping
("/recordIsDisp")
public boolean recordIsDisp(@RequestParam(name = "recordId") String recordId){
  return materialuseservice.recordIsDisp(recordId);
}


@GetMapping
("/insertWorkInfo")
public int insertWorkInfo(@RequestParam(name = "workInfo") WorkInfo workInfo){
  return materialuseservice.insertWorkInfo(workInfo);
}


@GetMapping
("/queryUserIdbyRecordId4workInfo")
public String queryUserIdbyRecordId4workInfo(@RequestParam(name = "recordId") String recordId){
  return materialuseservice.queryUserIdbyRecordId4workInfo(recordId);
}


@GetMapping
("/updRunProInstStartUser")
public int updRunProInstStartUser(@RequestParam(name = "newUserId") String newUserId,@RequestParam(name = "recordId") String recordId,@RequestParam(name = "flowName") String flowName){
  return materialuseservice.updRunProInstStartUser(newUserId,recordId,flowName);
}


@GetMapping
("/updWorkInfoUser")
public int updWorkInfoUser(@RequestParam(name = "recordId") String recordId,@RequestParam(name = "userId") String userId){
  return materialuseservice.updWorkInfoUser(recordId,userId);
}


}