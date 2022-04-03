package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AppointmentHistoryDaoController {

 private AppointmentHistoryDao appointmenthistorydao;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return appointmenthistorydao.insert(Object);
}


}