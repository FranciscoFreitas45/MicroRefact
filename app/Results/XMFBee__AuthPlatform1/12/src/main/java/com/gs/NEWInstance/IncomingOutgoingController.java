package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IncomingOutgoingController {

 private IncomingOutgoing incomingoutgoing;

 private IncomingOutgoing incomingoutgoing;


@PutMapping
("/setInOutCreatedUser")
public void setInOutCreatedUser(@RequestParam(name = "inOutCreatedUser") String inOutCreatedUser){
incomingoutgoing.setInOutCreatedUser(inOutCreatedUser);
}


@PutMapping
("/setOutTypeId")
public void setOutTypeId(@RequestParam(name = "outTypeId") String outTypeId){
incomingoutgoing.setOutTypeId(outTypeId);
}


@PutMapping
("/setCompanyId")
public void setCompanyId(@RequestParam(name = "companyId") String companyId){
incomingoutgoing.setCompanyId(companyId);
}


@PutMapping
("/setInTypeId")
public void setInTypeId(@RequestParam(name = "inTypeId") String inTypeId){
incomingoutgoing.setInTypeId(inTypeId);
}


@PutMapping
("/setInOutMoney")
public void setInOutMoney(@RequestParam(name = "inOutMoney") Double inOutMoney){
incomingoutgoing.setInOutMoney(inOutMoney);
}


}