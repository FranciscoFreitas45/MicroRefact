package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StatusController {

 private Status status;

 private Status status;


@PutMapping
("/setDbversion")
public void setDbversion(@RequestParam(name = "dbversion") int dbversion){
status.setDbversion(dbversion);
}


@PutMapping
("/setUpdateDate")
public void setUpdateDate(@RequestParam(name = "updateDate") Date updateDate){
status.setUpdateDate(updateDate);
}


}