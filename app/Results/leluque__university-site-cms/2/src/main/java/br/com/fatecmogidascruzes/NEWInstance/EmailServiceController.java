package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmailServiceController {

 private EmailService emailservice;


@PutMapping
("/sendRecoveryPasswordEMail")
public void sendRecoveryPasswordEMail(@RequestParam(name = "to") String to,@RequestParam(name = "hash") String hash){
emailservice.sendRecoveryPasswordEMail(to,hash);
}


@PutMapping
("/sendRequestEMail")
public void sendRequestEMail(@RequestParam(name = "email") String email,@RequestParam(name = "name") String name,@RequestParam(name = "protocol") String protocol,@RequestParam(name = "requestSituation") String requestSituation,@RequestParam(name = "documentType") String documentType){
emailservice.sendRequestEMail(email,name,protocol,requestSituation,documentType);
}


}