package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IndustrySMSController {

 private IndustrySMS industrysms;

 private IndustrySMS industrysms;


@PutMapping
("/execute")
public void execute(){
industrysms.execute();
}


}