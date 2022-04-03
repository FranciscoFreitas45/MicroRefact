package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InStorageController {

 private InStorage instorage;

 private InStorage instorage;


@PutMapping
("/setProcessStatus")
public void setProcessStatus(@RequestParam(name = "processStatus") ProcessStatus processStatus){
instorage.setProcessStatus(processStatus);
}


@PutMapping
("/setEmployee")
public void setEmployee(@RequestParam(name = "employee") Employee employee){
instorage.setEmployee(employee);
}


}