package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomMailerController {

 private CustomMailer custommailer;


@PutMapping
("/sendMail")
public void sendMail(@RequestParam(name = "recipients") Map<String,Map<String,String>> recipients,@RequestParam(name = "template") TemplateMail template){
custommailer.sendMail(recipients,template);
}


@GetMapping
("/extractCustomizations")
public Map<String,String> extractCustomizations(@RequestParam(name = "researcher") Object researcher){
  return custommailer.extractCustomizations(researcher);
}


}