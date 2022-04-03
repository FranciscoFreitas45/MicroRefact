package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BoxController {

 private Box box;

 private Box box;


@PutMapping
("/setIsSystem")
public void setIsSystem(@RequestParam(name = "isSystem") Boolean isSystem){
box.setIsSystem(isSystem);
}


@PutMapping
("/setMessages")
public void setMessages(@RequestParam(name = "messages") List<Message> messages){
box.setMessages(messages);
}


}