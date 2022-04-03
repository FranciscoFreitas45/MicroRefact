package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysCodeManagerController {

 private SysCodeManager syscodemanager;


@GetMapping
("/getCodeDetailByCode")
public SysCodeDetail getCodeDetailByCode(@RequestParam(name = "mainCode") String mainCode,@RequestParam(name = "detailCode") String detailCode){
  return syscodemanager.getCodeDetailByCode(mainCode,detailCode);
}


}