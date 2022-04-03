package com.csquard.mregister.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AsmRepositoryController {

 private AsmRepository asmrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return asmrepository.findById(Object);
}


@GetMapping
("/existsBySalesRegionId")
public Boolean existsBySalesRegionId(@RequestParam(name = "salesRegionId") Long salesRegionId){
  return asmrepository.existsBySalesRegionId(salesRegionId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return asmrepository.save(Object);
}


}