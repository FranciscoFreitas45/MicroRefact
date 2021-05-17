import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class MailServiceController {

 private MailService mailservice;


@PutMapping
("/sendEmail")
public void sendEmail(@RequestParam(name = "destination") String destination,@RequestParam(name = "subject") String subject,@RequestParam(name = "content") String content){
mailservice.sendEmail(destination,subject,content);
}


}