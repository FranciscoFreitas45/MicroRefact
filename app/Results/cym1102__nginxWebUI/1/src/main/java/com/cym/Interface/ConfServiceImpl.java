package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.ConfService;
public class ConfServiceImpl implements ConfService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public AsycPack getAsycPack(String[] asycData){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAsycPack"))
    .queryParam("asycData",asycData)
;  AsycPack aux = restTemplate.getForObject(builder.toUriString(), AsycPack.class);

 return aux;
}


public void setAsycPack(AsycPack asycPack,String adminName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAsycPack"))
    .queryParam("asycPack",asycPack)
    .queryParam("adminName",adminName)
;
  restTemplate.put(builder.toUriString(), null);
}


}