import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DoctorWaitingPatientServiceImpl implements DoctorWaitingPatientService{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<DoctorWaitingPatient> findAllByDate(LocalDate date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByDate"))
    .queryParam("date",date);
  List<DoctorWaitingPatient> aux = restTemplate.getForObject(builder.toUriString(), List<DoctorWaitingPatient>.class)

 return aux;
}


}