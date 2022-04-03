package org.jugbd.mnet.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.Interface.PatientService;
public class PatientServiceImpl implements PatientService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Patient findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  Patient aux = restTemplate.getForObject(builder.toUriString(), Patient.class);

 return aux;
}


}