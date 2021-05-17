import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LoggingController {

 private Logging logging;


@PutMapping
("/setTrace")
public void setTrace(@RequestParam(name = "action") String action,@RequestParam(name = "comments") String comments){
logging.setTrace(action,comments);
}


@PutMapping
("/setTrace")
public void setTrace(@RequestParam(name = "action") String action,@RequestParam(name = "comments") String comments){
logging.setTrace(action,comments);
}


@PutMapping
("/setTrace")
public void setTrace(@RequestParam(name = "action") String action,@RequestParam(name = "comments") String comments){
logging.setTrace(action,comments);
}


@PutMapping
("/setTrace")
public void setTrace(@RequestParam(name = "action") String action,@RequestParam(name = "comments") String comments){
logging.setTrace(action,comments);
}


}