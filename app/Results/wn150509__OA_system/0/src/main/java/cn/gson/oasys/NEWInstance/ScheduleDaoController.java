package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ScheduleDaoController {

 private ScheduleDao scheduledao;


@GetMapping
("/findstart")
public List<ScheduleList> findstart(@RequestParam(name = "userid") long userid){
  return scheduledao.findstart(userid);
}


}