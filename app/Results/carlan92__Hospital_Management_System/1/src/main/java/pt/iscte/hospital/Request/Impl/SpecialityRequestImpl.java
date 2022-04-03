package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Speciality;
import pt.iscte.hospital.Request.SpecialityRequest;
public class SpecialityRequestImpl implements SpecialityRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Speciality getSpeciality(Long specialityId){
 Speciality aux = restTemplate.getForObject("http://0/Doctor/{id}/Speciality/getSpeciality",Speciality.class,specialityId);
return aux;
}


public void setSpeciality(Speciality speciality,Long specialityId){
 restTemplate.put("http://0/Doctor/{id}/Speciality/setSpeciality",speciality,specialityId);
 return ;
}


}