package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Patient;
import pt.iscte.hospital.Request.PatientRequest;
public class PatientRequestImpl implements PatientRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setPatient(Patient patient,Long userId){
 restTemplate.put("http://5/Appointment/{id}/Patient/setPatient",patient,userId);
 return ;
}


public Patient getPatient(Long userId){
 Patient aux = restTemplate.getForObject("http://5/Appointment/{id}/Patient/getPatient",Patient.class,userId);
return aux;
}


}