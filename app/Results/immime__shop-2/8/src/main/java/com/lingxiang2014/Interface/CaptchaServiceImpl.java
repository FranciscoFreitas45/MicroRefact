package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.CaptchaService;
public class CaptchaServiceImpl implements CaptchaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public BufferedImage buildImage(String captchaId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildImage"))
    .queryParam("captchaId",captchaId)
;  BufferedImage aux = restTemplate.getForObject(builder.toUriString(), BufferedImage.class);

 return aux;
}


}