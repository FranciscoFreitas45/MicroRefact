package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SendMailUtilsController {

 private SendMailUtils sendmailutils;


@PutMapping
("/sendMailSmtp")
public void sendMailSmtp(@RequestParam(name = "to") String to,@RequestParam(name = "title") String title,@RequestParam(name = "msg") String msg){
sendmailutils.sendMailSmtp(to,title,msg);
}


}