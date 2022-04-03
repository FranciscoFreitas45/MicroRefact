package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailController {

 private Mail mail;

 private Mail mail;


@PutMapping
("/setBccRecipients")
public void setBccRecipients(@RequestParam(name = "bccRecipients") String bccRecipients){
mail.setBccRecipients(bccRecipients);
}


@PutMapping
("/setMultinart")
public void setMultinart(@RequestParam(name = "multipart") Multipart multipart){
mail.setMultinart(multipart);
}


}