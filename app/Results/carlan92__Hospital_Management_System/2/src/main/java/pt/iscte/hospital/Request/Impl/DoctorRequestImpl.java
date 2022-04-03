package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Doctor;
import pt.iscte.hospital.Request.DoctorRequest;
public class DoctorRequestImpl implements DoctorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Doctor getDoctor(Long userId){
 Doctor aux = restTemplate.getForObject("http://1/Slot/{id}/Doctor/getDoctor",Doctor.class,userId);
return aux;
}


public void setDoctor(Doctor doctor,Long userId){
 restTemplate.put("http://1/Slot/{id}/Doctor/setDoctor",doctor,userId);
 return ;
}


}