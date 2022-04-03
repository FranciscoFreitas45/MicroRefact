package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AppointmentServiceController {

 private AppointmentService appointmentservice;


@GetMapping
("/queryByCompanyId")
public List<Appointment> queryByCompanyId(@RequestParam(name = "companyId") String companyId){
  return appointmentservice.queryByCompanyId(companyId);
}


@PutMapping
("/updateCurrentById")
public void updateCurrentById(@RequestParam(name = "currentStatus") String currentStatus,@RequestParam(name = "appointmentId") String appointmentId){
appointmentservice.updateCurrentById(currentStatus,appointmentId);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return appointmentservice.insert(Object);
}


@GetMapping
("/queryByPhone")
public List<Appointment> queryByPhone(@RequestParam(name = "userPhone") String userPhone){
  return appointmentservice.queryByPhone(userPhone);
}


@PutMapping
("/updateUserIds")
public void updateUserIds(@RequestParam(name = "userId") String userId,@RequestParam(name = "appIds") String appIds){
appointmentservice.updateUserIds(userId,appIds);
}


}