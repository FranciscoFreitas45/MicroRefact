import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SchedulEventServiceController {

 private SchedulEventService scheduleventservice;


@GetMapping
("/findattenceTotalTime")
public float findattenceTotalTime(@RequestParam(name = "userbname") String userbname){
  return scheduleventservice.findattenceTotalTime(userbname);
}


@GetMapping
("/findWorkTotalDay")
public int findWorkTotalDay(@RequestParam(name = "username") String username){
  return scheduleventservice.findWorkTotalDay(username);
}


@GetMapping
("/findTotalPerson")
public Integer findTotalPerson(){
  return scheduleventservice.findTotalPerson();
}


}