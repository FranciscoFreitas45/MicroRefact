import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class MessageServiceController {

 private MessageService messageservice;


@PutMapping
("/save")
public void save(@RequestParam(name = "message") Message message){
messageservice.save(message);
}


}