package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReCaptchaServiceController {

 private ReCaptchaService recaptchaservice;


@GetMapping
("/verifyRecaptcha")
public String verifyRecaptcha(@RequestParam(name = "ip") String ip,@RequestParam(name = "recaptchaResponse") String recaptchaResponse){
  return recaptchaservice.verifyRecaptcha(ip,recaptchaResponse);
}


}