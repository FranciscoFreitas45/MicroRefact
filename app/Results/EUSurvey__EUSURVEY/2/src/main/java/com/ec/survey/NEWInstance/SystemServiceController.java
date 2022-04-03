package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SystemServiceController {

 private SystemService systemservice;


@PutMapping
("/sendUserErrorMessage")
public void sendUserErrorMessage(@RequestParam(name = "userid") int userid,@RequestParam(name = "message") String message){
systemservice.sendUserErrorMessage(userid,message);
}


@PutMapping
("/sendUserSuccessMessage")
public void sendUserSuccessMessage(@RequestParam(name = "userid") int userid,@RequestParam(name = "message") String message){
systemservice.sendUserSuccessMessage(userid,message);
}


@PutMapping
("/sendAdminErrorMessage")
public void sendAdminErrorMessage(@RequestParam(name = "message") String message){
systemservice.sendAdminErrorMessage(message);
}


}