package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminServiceController {

 private AdminService adminservice;


@GetMapping
("/getCurrent")
public Admin getCurrent(){
  return adminservice.getCurrent();
}


@GetMapping
("/getCurrentUsername")
public String getCurrentUsername(){
  return adminservice.getCurrentUsername();
}


}