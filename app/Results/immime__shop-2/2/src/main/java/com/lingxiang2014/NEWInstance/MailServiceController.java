package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailServiceController {

 private MailService mailservice;


@PutMapping
("/sendFindPasswordMail")
public void sendFindPasswordMail(@RequestParam(name = "toMail") String toMail,@RequestParam(name = "username") String username,@RequestParam(name = "safeKey") SafeKey safeKey){
mailservice.sendFindPasswordMail(toMail,username,safeKey);
}


}