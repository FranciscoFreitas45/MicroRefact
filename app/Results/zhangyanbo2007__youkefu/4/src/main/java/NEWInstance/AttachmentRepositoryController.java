package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentRepositoryController {

 private AttachmentRepository attachmentrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return attachmentrepository.save(Object);
}


}