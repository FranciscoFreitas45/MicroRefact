package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BranchController {

 private Branch branch;

 private Branch branch;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
branch.setName(name);
}


@PutMapping
("/setInstitute")
public void setInstitute(@RequestParam(name = "institute") Institute institute){
branch.setInstitute(institute);
}


}