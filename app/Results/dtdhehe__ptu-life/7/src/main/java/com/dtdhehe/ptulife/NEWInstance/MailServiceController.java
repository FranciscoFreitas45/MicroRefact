package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailServiceController {

 private MailService mailservice;


@PutMapping
("/sendHtmlMail")
public void sendHtmlMail(@RequestParam(name = "to") String to,@RequestParam(name = "subject") String subject,@RequestParam(name = "content") String content){
mailservice.sendHtmlMail(to,subject,content);
}


}