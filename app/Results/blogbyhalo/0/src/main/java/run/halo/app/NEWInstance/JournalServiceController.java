package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JournalServiceController {

 private JournalService journalservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return journalservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return journalservice.createInBatch(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return journalservice.count(Object);
}


}