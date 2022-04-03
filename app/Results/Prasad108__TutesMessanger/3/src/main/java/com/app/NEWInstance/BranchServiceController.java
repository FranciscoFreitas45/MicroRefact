package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BranchServiceController {

 private BranchService branchservice;


@GetMapping
("/getallOfParticularInstitute")
public List<Branch> getallOfParticularInstitute(@RequestParam(name = "id") Institute id){
  return branchservice.getallOfParticularInstitute(id);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "branch") Branch branch){
branchservice.create(branch);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "branch") Branch branch){
branchservice.update(branch);
}


@PutMapping
("/delet")
public void delet(@RequestParam(name = "id") int id){
branchservice.delet(id);
}


@GetMapping
("/find")
public Branch find(@RequestParam(name = "id") int id){
  return branchservice.find(id);
}


}