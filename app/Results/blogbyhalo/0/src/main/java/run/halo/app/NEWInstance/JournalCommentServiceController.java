package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JournalCommentServiceController {

 private JournalCommentService journalcommentservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return journalcommentservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return journalcommentservice.createInBatch(Object);
}


@GetMapping
("/countByStatus")
public Object countByStatus(@RequestParam(name = "Object") Object Object){
  return journalcommentservice.countByStatus(Object);
}


}