package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GroupServiceController {

 private GroupService groupservice;


@GetMapping
("/getListByParent")
public List<Group> getListByParent(@RequestParam(name = "id") String id){
  return groupservice.getListByParent(id);
}


}