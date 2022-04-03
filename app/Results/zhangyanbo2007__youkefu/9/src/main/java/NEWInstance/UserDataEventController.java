package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDataEventController {

 private UserDataEvent userdataevent;

 private UserDataEvent userdataevent;


@PutMapping
("/setEvent")
public void setEvent(@RequestParam(name = "event") UserEvent event){
userdataevent.setEvent(event);
}


}