package org.jugbd.mnet.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.Interface.RegisterService;
public class RegisterServiceImpl implements RegisterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Register> findAllRegisterByPatientId(Long patientId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllRegisterByPatientId"))
    .queryParam("patientId",patientId)
;  List<Register> aux = restTemplate.getForObject(builder.toUriString(), List<Register>.class);

 return aux;
}


public List<OutdoorRegister> findAllOutdoorRegisterByPatientId(Long patientId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllOutdoorRegisterByPatientId"))
    .queryParam("patientId",patientId)
;  List<OutdoorRegister> aux = restTemplate.getForObject(builder.toUriString(), List<OutdoorRegister>.class);

 return aux;
}


}