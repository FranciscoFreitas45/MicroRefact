import org.springframework.web.client.RestTemplate;
public class DoctorRequestImpl implements DoctorRequest{

 private RestTemplate restTemplate;


public Doctor getDoctor(Long Long){
 Doctor aux = restTemplate.getForObject('http://0/Slot/{id}/Doctor/getDoctor',Doctor.class,Long);
return aux;
}


public void setDoctor(Doctor doctor,Long Long){
 restTemplate.put('http://0/Slot/{id}/Doctor/setDoctor',doctor,Long);
 return ;
}


}