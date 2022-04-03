package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IActivityStatisticsServiceController {

 private IActivityStatisticsService iactivitystatisticsservice;


@GetMapping
("/list")
public List<ActivityStatistics> list(@RequestParam(name = "deptid") Integer deptid){
  return iactivitystatisticsservice.list(deptid);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return iactivitystatisticsservice.deleteById(Object);
}


}