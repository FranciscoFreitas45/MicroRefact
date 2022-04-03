package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@PutMapping
("/setUserNickname")
public void setUserNickname(@RequestParam(name = "userNickname") String userNickname){
user.setUserNickname(userNickname);
}


@PutMapping
("/setCompany")
public void setCompany(@RequestParam(name = "company") Company company){
user.setCompany(company);
}


@PutMapping
("/setUserPhone")
public void setUserPhone(@RequestParam(name = "userPhone") String userPhone){
user.setUserPhone(userPhone);
}


@PutMapping
("/setCompanyId")
public void setCompanyId(@RequestParam(name = "companyId") String companyId){
user.setCompanyId(companyId);
}


}