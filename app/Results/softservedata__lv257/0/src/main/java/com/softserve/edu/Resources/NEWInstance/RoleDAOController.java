package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleDAOController {

 private RoleDAO roledao;


@GetMapping
("/findByName")
public Role findByName(@RequestParam(name = "name") String name){
  return roledao.findByName(name);
}


}