package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.sys.entity.Permission;
@RestController
@CrossOrigin
public class PermissionPageElementController {

@Autowired
 private PermissionPageElementService permissionpageelementservice;


@GetMapping
("/PageElement/{id}/Permission/getPermission")
public Permission getPermission(@PathVariable(name="id") String idIJY1){
return permissionpageelementservice.getPermission(idIJY1);
}


@PutMapping
("/PageElement/{id}/Permission/setPermission")
public void setPermission(@PathVariable(name="id") String idIJY1,@RequestBody Permission permission){
permissionpageelementservice.setPermission(idIJY1,permission);
}


}