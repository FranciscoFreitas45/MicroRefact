package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailServiceController {

 private MailService mailservice;


@PutMapping
("/sendTemplateMail")
public void sendTemplateMail(@RequestParam(name = "to") String to,@RequestParam(name = "subject") String subject,@RequestParam(name = "content") Map<String,Object> content,@RequestParam(name = "templateName") String templateName){
mailservice.sendTemplateMail(to,subject,content,templateName);
}


@PutMapping
("/sendTextMail")
public void sendTextMail(@RequestParam(name = "to") String to,@RequestParam(name = "subject") String subject,@RequestParam(name = "content") String content){
mailservice.sendTextMail(to,subject,content);
}


}