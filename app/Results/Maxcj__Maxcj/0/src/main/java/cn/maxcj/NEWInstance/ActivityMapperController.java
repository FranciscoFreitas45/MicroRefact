package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActivityMapperController {

 private ActivityMapper activitymapper;


@GetMapping
("/getActivityName")
public String getActivityName(@RequestParam(name = "activity_id") Integer activity_id){
  return activitymapper.getActivityName(activity_id);
}


}