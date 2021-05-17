import org.springframework.web.client.RestTemplate;
public class PatientRequestImpl implements PatientRequest{

 private RestTemplate restTemplate;


public void setPatient(Patient patient,Long Long){
 restTemplate.put('http://0/Appointment/{id}/Patient/setPatient',patient,Long);
 return ;
}


public Patient getPatient(Long Long){
 Patient aux = restTemplate.getForObject('http://0/Appointment/{id}/Patient/getPatient',Patient.class,Long);
return aux;
}


}