package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttendceDaoController {

 private AttendceDao attendcedao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return attendcedao.save(Object);
}


@GetMapping
("/findlastest")
public Attends findlastest(@RequestParam(name = "date") String date,@RequestParam(name = "userid") long userid){
  return attendcedao.findlastest(date,userid);
}


}