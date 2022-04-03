package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SimpleMailSenderController {

 private SimpleMailSender simplemailsender;


@PutMapping
("/send")
public void send(@RequestParam(name = "recipients") String[] recipients,@RequestParam(name = "subject") String subject,@RequestParam(name = "content") Object content,@RequestParam(name = "filePath") String filePath){
simplemailsender.send(recipients,subject,content,filePath);
}


}