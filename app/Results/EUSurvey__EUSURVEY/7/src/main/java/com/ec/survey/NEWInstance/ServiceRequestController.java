package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ServiceRequestController {

 private ServiceRequest servicerequest;

 private ServiceRequest servicerequest;


@PutMapping
("/setDate")
public void setDate(@RequestParam(name = "date") Date date){
servicerequest.setDate(date);
}


@PutMapping
("/setCounter")
public void setCounter(@RequestParam(name = "counter") Integer counter){
servicerequest.setCounter(counter);
}


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") int userId){
servicerequest.setUserId(userId);
}


}