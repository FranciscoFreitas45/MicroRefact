package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageIoBatchDaoController {

 private StorageIoBatchDao storageiobatchdao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return storageiobatchdao.findOne(Object);
}


}