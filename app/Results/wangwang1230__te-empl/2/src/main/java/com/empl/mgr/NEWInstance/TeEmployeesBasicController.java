package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeEmployeesBasicController {

 private TeEmployeesBasic teemployeesbasic;

 private TeEmployeesBasic teemployeesbasic;


@PutMapping
("/setEmDeparemtn")
public void setEmDeparemtn(@RequestParam(name = "emDeparemtn") long emDeparemtn){
teemployeesbasic.setEmDeparemtn(emDeparemtn);
}


@PutMapping
("/setEmPolitics")
public void setEmPolitics(@RequestParam(name = "emPolitics") long emPolitics){
teemployeesbasic.setEmPolitics(emPolitics);
}


}