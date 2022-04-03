package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkInfoController {

 private WorkInfo workinfo;


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") String userId){
workinfo.setUserId(userId);
}


@PutMapping
("/setWorkAssignTime")
public void setWorkAssignTime(@RequestParam(name = "workAssignTime") Date workAssignTime){
workinfo.setWorkAssignTime(workAssignTime);
}


}