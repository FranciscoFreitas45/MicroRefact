package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysMenuManagerController {

 private SysMenuManager sysmenumanager;


@GetMapping
("/getUserMenuJson")
public String getUserMenuJson(@RequestParam(name = "userId") Long userId){
  return sysmenumanager.getUserMenuJson(userId);
}


}