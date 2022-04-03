package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Doctor;
import pt.iscte.hospital.Request.DoctorRequest;
public class DoctorRequestImpl implements DoctorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Doctor> getDoctors(Long specialityId){
 List<Doctor> aux = restTemplate.getForObject("http://1/Speciality/{id}/Doctor/getDoctors",List<Doctor>.class,specialityId);
return aux;
}


public void setDoctors(List<Doctor> doctors,Long specialityId){
 restTemplate.put("http://1/Speciality/{id}/Doctor/setDoctors",doctors,specialityId);
 return ;
}


}