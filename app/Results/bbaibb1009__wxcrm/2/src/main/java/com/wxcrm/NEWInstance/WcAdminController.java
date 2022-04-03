package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WcAdminController {

 private WcAdmin wcadmin;

 private WcAdmin wcadmin;


@PutMapping
("/setWadPwdMd5")
public void setWadPwdMd5(@RequestParam(name = "wadPwdMd5") String wadPwdMd5){
wcadmin.setWadPwdMd5(wadPwdMd5);
}


@PutMapping
("/setWadUsername")
public void setWadUsername(@RequestParam(name = "wadUsername") String wadUsername){
wcadmin.setWadUsername(wadUsername);
}


@PutMapping
("/setWadPwd")
public void setWadPwd(@RequestParam(name = "wadPwd") String wadPwd){
wcadmin.setWadPwd(wadPwd);
}


@GetMapping
("/isRemember")
public boolean isRemember(){
  return wcadmin.isRemember();
}


}