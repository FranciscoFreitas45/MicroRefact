package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysVarCacheController {

 private SysVarCache sysvarcache;


@GetMapping
("/getSystemVar")
public Integer getSystemVar(@RequestParam(name = "key") String key){
  return sysvarcache.getSystemVar(key);
}


}