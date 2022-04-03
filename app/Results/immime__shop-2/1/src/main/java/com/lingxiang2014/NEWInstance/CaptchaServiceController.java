package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CaptchaServiceController {

 private CaptchaService captchaservice;


@GetMapping
("/buildImage")
public BufferedImage buildImage(@RequestParam(name = "captchaId") String captchaId){
  return captchaservice.buildImage(captchaId);
}


@GetMapping
("/isValid")
public boolean isValid(@RequestParam(name = "captchaType") CaptchaType captchaType,@RequestParam(name = "captchaId") String captchaId,@RequestParam(name = "captcha") String captcha){
  return captchaservice.isValid(captchaType,captchaId,captcha);
}


}