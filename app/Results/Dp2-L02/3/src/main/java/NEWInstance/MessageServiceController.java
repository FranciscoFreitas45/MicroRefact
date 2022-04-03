package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageServiceController {

 private MessageService messageservice;


@PutMapping
("/sendNotificationDropOut")
public void sendNotificationDropOut(@RequestParam(name = "bro") Brotherhood bro){
messageservice.sendNotificationDropOut(bro);
}


}