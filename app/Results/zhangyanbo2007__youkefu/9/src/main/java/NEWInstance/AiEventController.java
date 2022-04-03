package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AiEventController {

 private AiEvent aievent;

 private AiEvent aievent;


@PutMapping
("/setEvent")
public void setEvent(@RequestParam(name = "event") UserEvent event){
aievent.setEvent(event);
}


}