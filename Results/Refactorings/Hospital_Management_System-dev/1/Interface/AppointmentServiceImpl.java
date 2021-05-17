import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class AppointmentServiceImpl implements AppointmentService{

 private RestTemplate restTemplate;

  String url = "http://4";


public Appointment findByAppointmentId(Long appointmentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAppointmentId"))
    .queryParam("appointmentId",appointmentId);
  Appointment aux = restTemplate.getForObject(builder.toUriString(), Appointment.class)

 return aux;
}


public void saveAppointment(Appointment appointment){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAppointment"))
    .queryParam("appointment",appointment);

  restTemplate.put(builder.toUriString(), null)



}