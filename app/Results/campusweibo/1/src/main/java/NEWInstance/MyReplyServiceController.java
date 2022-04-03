package NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import java.util.List;
 import edu.*;

@RestController
@CrossOrigin
public class MyReplyServiceController {

 private MyReplyService myreplyservice;


@GetMapping
("/getAllReply")
public List<MyReply> getAllReply(@RequestParam(name = "id") Long id){
  return myreplyservice.getAllReply(id);
}


}