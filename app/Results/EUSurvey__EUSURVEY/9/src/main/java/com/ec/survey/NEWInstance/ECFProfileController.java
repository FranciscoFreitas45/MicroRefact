package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ECFProfileController {

 private ECFProfile ecfprofile;

 private ECFProfile ecfprofile;


@PutMapping
("/setOrderNumber")
public void setOrderNumber(@RequestParam(name = "orderNumber") Integer orderNumber){
ecfprofile.setOrderNumber(orderNumber);
}


}