package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CaptchaService;
public class CaptchaServiceImpl implements CaptchaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public boolean isValid(CaptchaType captchaType,String captchaId,String captcha){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isValid"))
    .queryParam("captchaType",captchaType)
    .queryParam("captchaId",captchaId)
    .queryParam("captcha",captcha)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}