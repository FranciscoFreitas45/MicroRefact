package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private UserRepository userrepository;


@PutMapping
("/setCredential_number/{id}")
public void setCredential_number(@PathVariable(name = "id") Integer id,@RequestParam(name = "credential_number") String credential_number){
 userrepository.setCredential_number(id,credential_number);
}


@PutMapping
("/setCredential_type/{id}")
public void setCredential_type(@PathVariable(name = "id") Integer id,@RequestParam(name = "credential_type") Integer credential_type){
 userrepository.setCredential_type(id,credential_type);
}


@PutMapping
("/setReport_status/{id}")
public void setReport_status(@PathVariable(name = "id") Integer id,@RequestParam(name = "report_status") Integer report_status){
 userrepository.setReport_status(id,report_status);
}


@PutMapping
("/setIs_married/{id}")
public void setIs_married(@PathVariable(name = "id") Integer id,@RequestParam(name = "is_married") Integer is_married){
 userrepository.setIs_married(id,is_married);
}


}