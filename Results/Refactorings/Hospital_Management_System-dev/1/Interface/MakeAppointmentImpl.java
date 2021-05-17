import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class MakeAppointmentImpl implements MakeAppointment{

 private RestTemplate restTemplate;

  String url = "http://0";


public Message mensagemConfirmacao(PatientWaitingAppointment patientWaiting,LocalDateTime dateTime){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/mensagemConfirmacao"))
    .queryParam("patientWaiting",patientWaiting)
    .queryParam("dateTime",dateTime);
  Message aux = restTemplate.getForObject(builder.toUriString(), Message.class)

 return aux;
}


}