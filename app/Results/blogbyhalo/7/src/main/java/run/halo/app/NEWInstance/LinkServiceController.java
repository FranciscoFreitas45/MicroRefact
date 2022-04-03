package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LinkServiceController {

 private LinkService linkservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return linkservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return linkservice.createInBatch(Object);
}


}