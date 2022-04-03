package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class INoticeServiceController {

 private INoticeService inoticeservice;


@GetMapping
("/list")
public List<Map<String,Object>> list(@RequestParam(name = "condition") String condition){
  return inoticeservice.list(condition);
}


}