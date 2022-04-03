package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PermissionRepositoryController {

 private PermissionRepository permissionrepository;


@GetMapping
("/findPermissionsByUsername")
public List<Permission> findPermissionsByUsername(@RequestParam(name = "username") String username){
  return permissionrepository.findPermissionsByUsername(username);
}


}