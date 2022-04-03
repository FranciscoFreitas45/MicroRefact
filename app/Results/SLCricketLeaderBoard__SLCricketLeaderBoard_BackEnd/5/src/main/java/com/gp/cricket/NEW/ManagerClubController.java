package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Manager;
@RestController
@CrossOrigin
public class ManagerClubController {

@Autowired
 private ManagerClubService managerclubservice;


@GetMapping
("/Club/{id}/Manager/getManagerId")
public Manager getManagerId(@PathVariable(name="id") Integer managerIdv2){
return managerclubservice.getManagerId(managerIdv2);
}


@PutMapping
("/Club/{id}/Manager/setManagerId")
public void setManagerId(@PathVariable(name="id") Integer managerIdv2,@RequestBody Manager managerId){
managerclubservice.setManagerId(managerIdv2,managerId);
}


}