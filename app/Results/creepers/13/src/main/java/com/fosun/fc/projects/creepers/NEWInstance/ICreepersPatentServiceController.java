package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersPatentServiceController {

 private ICreepersPatentService icreeperspatentservice;


@GetMapping
("/findByMerName")
public List<TCreepersPatent> findByMerName(@RequestParam(name = "rptNo") String rptNo){
  return icreeperspatentservice.findByMerName(rptNo);
}


@GetMapping
("/queryPatentList")
public Page<CreepersPatentDTO> queryPatentList(@RequestParam(name = "searchParams") Map<String,Object> searchParams,@RequestParam(name = "pageNumber") int pageNumber,@RequestParam(name = "pageSize") int pageSize,@RequestParam(name = "sortType") String sortType){
  return icreeperspatentservice.queryPatentList(searchParams,pageNumber,pageSize,sortType);
}


}