import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PatientWaitingAppointmentServiceImpl implements PatientWaitingAppointmentService{

 private RestTemplate restTemplate;

  String url = "http://0";


public void save(PatientWaitingAppointment patientWaitingAppointment){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("patientWaitingAppointment",patientWaitingAppointment);

  restTemplate.put(builder.toUriString(), null)



}