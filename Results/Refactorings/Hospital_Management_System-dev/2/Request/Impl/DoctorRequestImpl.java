import org.springframework.web.client.RestTemplate;
public class DoctorRequestImpl implements DoctorRequest{

 private RestTemplate restTemplate;


public List<Doctor> getDoctors(Long specialityId){
 List<Doctor> aux = restTemplate.getForObject('http://0/Speciality/{id}/Doctor/getDoctors',List<Doctor>.class,specialityId);
return aux;
}


public void setDoctors(List<Doctor> doctors,Long specialityId){
 restTemplate.put('http://0/Speciality/{id}/Doctor/setDoctors',doctors,specialityId);
 return ;
}


}