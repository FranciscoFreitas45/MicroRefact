package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParamServiceController {

 private ParamService paramservice;


@GetMapping
("/getJsonByTypeId")
public String getJsonByTypeId(@RequestParam(name = "id") String id,@RequestParam(name = "type") String type){
  return paramservice.getJsonByTypeId(id,type);
}


@GetMapping
("/getListByTypeId")
public List<Param> getListByTypeId(@RequestParam(name = "id") String id,@RequestParam(name = "type") String type){
  return paramservice.getListByTypeId(id,type);
}


}