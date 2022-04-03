package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagServiceController {

 private TagService tagservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return tagservice.count(Object);
}


}