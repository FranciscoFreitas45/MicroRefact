package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersShixinServiceController {

 private ICreepersShixinService icreepersshixinservice;


@PutMapping
("/processByMerName")
public void processByMerName(@RequestParam(name = "merName") String merName){
icreepersshixinservice.processByMerName(merName);
}


@GetMapping
("/findListByMerName")
public List<TCreepersShixin> findListByMerName(@RequestParam(name = "merName") String merName){
  return icreepersshixinservice.findListByMerName(merName);
}


@GetMapping
("/findList")
public Page<CreepersShixinDTO> findList(@RequestParam(name = "searchParams") Map<String,Object> searchParams,@RequestParam(name = "pageNumber") int pageNumber,@RequestParam(name = "pageSize") int pageSize,@RequestParam(name = "sortType") String sortType){
  return icreepersshixinservice.findList(searchParams,pageNumber,pageSize,sortType);
}


}