package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SafeKeyController {

 private SafeKey safekey;

 private SafeKey safekey;


@PutMapping
("/setValue")
public void setValue(@RequestParam(name = "value") String value){
safekey.setValue(value);
}


@PutMapping
("/setExpire")
public void setExpire(@RequestParam(name = "expire") Date expire){
safekey.setExpire(expire);
}


@GetMapping
("/hasExpired")
public boolean hasExpired(){
  return safekey.hasExpired();
}


}