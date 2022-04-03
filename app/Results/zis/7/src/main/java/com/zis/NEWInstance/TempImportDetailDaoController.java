package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TempImportDetailDaoController {

 private TempImportDetailDao tempimportdetaildao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return tempimportdetaildao.findAll(Object);
}


}