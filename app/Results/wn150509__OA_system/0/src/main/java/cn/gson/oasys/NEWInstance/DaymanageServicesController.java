package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DaymanageServicesController {

 private DaymanageServices daymanageservices;


@GetMapping
("/aboutmeschedule")
public List<ScheduleList> aboutmeschedule(@RequestParam(name = "userId") Long userId){
  return daymanageservices.aboutmeschedule(userId);
}


}