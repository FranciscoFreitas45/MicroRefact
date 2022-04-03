package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleDaoController {

 private RoleDao roledao;


@GetMapping
("/findByName")
public Role findByName(@RequestParam(name = "name") String name){
  return roledao.findByName(name);
}


}