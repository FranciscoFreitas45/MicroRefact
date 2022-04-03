package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DaliyComputeSheduleController {

 private DaliyComputeShedule daliycomputeshedule;


@PutMapping
("/sendMail")
public void sendMail(){
daliycomputeshedule.sendMail();
}


@PutMapping
("/computeCount")
public void computeCount(){
daliycomputeshedule.computeCount();
}


}