import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PatientServiceImpl implements PatientService{

 private RestTemplate restTemplate;

  String url = "http://0";


public Patient findByUserId(Long patientId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserId"))
    .queryParam("patientId",patientId);
  Patient aux = restTemplate.getForObject(builder.toUriString(), Patient.class)

 return aux;
}


}