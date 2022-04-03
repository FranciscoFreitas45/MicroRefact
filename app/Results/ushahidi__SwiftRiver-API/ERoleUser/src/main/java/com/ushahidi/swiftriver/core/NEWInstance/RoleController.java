package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleController {

 private JpaRoleDao jparoledao;


@GetMapping
("/equals/{id}")
public boolean equals(@PathVariable(name = "id") long id,@RequestParam(name = "obj") Object obj){
 return jparoledao.equals(id,obj);
}


}