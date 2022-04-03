package org.jugbd.mnet.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.Interface.PatientService;
public class PatientServiceImpl implements PatientService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public long count(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}