package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.DoctorWaitingPatient;
import pt.iscte.hospital.Request.DoctorWaitingPatientRequest;
public class DoctorWaitingPatientRequestImpl implements DoctorWaitingPatientRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public DoctorWaitingPatient getDoctorWaitingPatient(Long doctorWaitingPatientId){
 DoctorWaitingPatient aux = restTemplate.getForObject("http://1/Appointment/{id}/DoctorWaitingPatient/getDoctorWaitingPatient",DoctorWaitingPatient.class,doctorWaitingPatientId);
return aux;
}


public void setDoctorWaitingPatient(DoctorWaitingPatient doctorWaitingPatient,Long doctorWaitingPatientId){
 restTemplate.put("http://1/Appointment/{id}/DoctorWaitingPatient/setDoctorWaitingPatient",doctorWaitingPatient,doctorWaitingPatientId);
 return ;
}


}