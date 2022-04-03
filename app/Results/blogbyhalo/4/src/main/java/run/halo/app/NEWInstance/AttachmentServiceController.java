package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentServiceController {

 private AttachmentService attachmentservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return attachmentservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return attachmentservice.createInBatch(Object);
}


}