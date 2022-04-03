package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersListPatentServiceController {

 private ICreepersListPatentService icreeperslistpatentservice;


@GetMapping
("/queryListPatentList")
public Page<CreepersListPatentDTO> queryListPatentList(@RequestParam(name = "searchParams") Map<String,Object> searchParams,@RequestParam(name = "pageNumber") int pageNumber,@RequestParam(name = "pageSize") int pageSize,@RequestParam(name = "sortType") String sortType){
  return icreeperslistpatentservice.queryListPatentList(searchParams,pageNumber,pageSize,sortType);
}


}