package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AppointmentController {

 private Appointment appointment;

 private Appointment appointment;


@PutMapping
("/setCurrentStatus")
public void setCurrentStatus(@RequestParam(name = "currentStatus") String currentStatus){
appointment.setCurrentStatus(currentStatus);
}


}