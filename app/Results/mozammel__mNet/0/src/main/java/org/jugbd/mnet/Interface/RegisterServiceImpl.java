package org.jugbd.mnet.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.Interface.RegisterService;
public class RegisterServiceImpl implements RegisterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Either<Register,OutdoorRegister> findRegisterEither(Long registerId,RegistrationType registrationType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRegisterEither"))
    .queryParam("registerId",registerId)
    .queryParam("registrationType",registrationType)
;  Either<Register,OutdoorRegister> aux = restTemplate.getForObject(builder.toUriString(), Either<Register,OutdoorRegister>.class);

 return aux;
}


public Register findOne(Long registerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("registerId",registerId)
;  Register aux = restTemplate.getForObject(builder.toUriString(), Register.class);

 return aux;
}


}