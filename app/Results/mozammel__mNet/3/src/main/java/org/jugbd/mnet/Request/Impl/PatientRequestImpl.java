package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Patient;
import org.jugbd.mnet.Request.PatientRequest;
public class PatientRequestImpl implements PatientRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public OutdoorRegister setPatient(Patient patient,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://9/OutdoorRegister/{id}/Patient/setPatient?'patient'=patient&'id'=id',",OutdoorRegister.class,id);
return aux;
}


public Patient getPatient(Long id){
 Patient aux = restTemplate.getForObject("http://9/OutdoorRegister/{id}/Patient/getPatient",Patient.class,id);
return aux;
}


}