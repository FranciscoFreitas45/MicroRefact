package org.jugbd.mnet.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.Interface.RegisterService;
public class RegisterServiceImpl implements RegisterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Register findOne(Long registerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("registerId",registerId)
;  Register aux = restTemplate.getForObject(builder.toUriString(), Register.class);

 return aux;
}


public OutdoorRegister save(OutdoorRegister register){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("register",register)
;  OutdoorRegister aux = restTemplate.getForObject(builder.toUriString(), OutdoorRegister.class);

 return aux;
}


public OutdoorRegister findOpdRegister(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOpdRegister"))
    .queryParam("id",id)
;  OutdoorRegister aux = restTemplate.getForObject(builder.toUriString(), OutdoorRegister.class);

 return aux;
}


}