package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.AdressService;
public class AdressServiceImpl implements AdressService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Adress> getAdress(String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAdress"))
    .queryParam("userId",userId)
;  List<Adress> aux = restTemplate.getForObject(builder.toUriString(), List<Adress>.class);

 return aux;
}


public void adressSave(AdressFrom adressFrom){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/adressSave"))
    .queryParam("adressFrom",adressFrom)
;
  restTemplate.put(builder.toUriString(), null);
}


}