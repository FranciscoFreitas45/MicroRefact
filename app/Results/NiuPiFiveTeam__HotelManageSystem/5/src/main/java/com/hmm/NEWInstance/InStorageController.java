package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InStorageController {

 private InStorageRepository instoragerepository;


@PutMapping
("/setInStorageId/{id}")
public void setInStorageId(@PathVariable(name = "id") String id,@RequestParam(name = "inStorageId") String inStorageId){
 instoragerepository.setInStorageId(id,inStorageId);
}


@PutMapping
("/setProcessStatus/{id}")
public void setProcessStatus(@PathVariable(name = "id") String id,@RequestParam(name = "processStatus") ProcessStatus processStatus){
 instoragerepository.setProcessStatus(id,processStatus);
}


@PutMapping
("/setEmployee/{id}")
public void setEmployee(@PathVariable(name = "id") String id,@RequestParam(name = "employee") Employee employee){
 instoragerepository.setEmployee(id,employee);
}


}