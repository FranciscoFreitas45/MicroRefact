package com.vino.scaffold.shiro.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceController {

 private Resource resource;

 private Resource resource;


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") String type){
resource.setType(type);
}


@PutMapping
("/setParentId")
public void setParentId(@RequestParam(name = "parentId") Long parentId){
resource.setParentId(parentId);
}


@PutMapping
("/setUrl")
public void setUrl(@RequestParam(name = "url") String url){
resource.setUrl(url);
}


@PutMapping
("/setPriority")
public void setPriority(@RequestParam(name = "priority") Integer priority){
resource.setPriority(priority);
}


@PutMapping
("/setAvailable")
public void setAvailable(@RequestParam(name = "available") Boolean available){
resource.setAvailable(available);
}


@PutMapping
("/setPermission")
public void setPermission(@RequestParam(name = "permission") String permission){
resource.setPermission(permission);
}


}