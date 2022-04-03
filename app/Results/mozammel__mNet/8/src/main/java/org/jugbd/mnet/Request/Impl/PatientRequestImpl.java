package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Patient;
import org.jugbd.mnet.Request.PatientRequest;
public class PatientRequestImpl implements PatientRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Vital setPatient(Patient patient,Long id){
 Vital aux = restTemplate.getForObject("http://9/Vital/{id}/Patient/setPatient?'patient'=patient&'id'=id',",Vital.class,id);
return aux;
}


public Patient getPatient(Long id){
 Patient aux = restTemplate.getForObject("http://9/Vital/{id}/Patient/getPatient",Patient.class,id);
return aux;
}


}