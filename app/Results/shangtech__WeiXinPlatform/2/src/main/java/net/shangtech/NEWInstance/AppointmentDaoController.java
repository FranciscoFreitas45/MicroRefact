package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AppointmentDaoController {

 private AppointmentDao appointmentdao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return appointmentdao.find(Object);
}


}