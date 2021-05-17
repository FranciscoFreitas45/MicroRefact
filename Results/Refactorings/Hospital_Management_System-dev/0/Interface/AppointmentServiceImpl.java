import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class AppointmentServiceImpl implements AppointmentService{

 private RestTemplate restTemplate;

  String url = "http://4";


public List<Appointment> findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusOrderBySlotTimeBeginAsc(Long userId,LocalDate date,int appointmentState){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusOrderBySlotTimeBeginAsc"))
    .queryParam("userId",userId)
    .queryParam("date",date)
    .queryParam("appointmentState",appointmentState);
  List<Appointment> aux = restTemplate.getForObject(builder.toUriString(), List<Appointment>.class)

 return aux;
}


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



public List<Appointment> findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasCheckedOrderBySlotTimeBeginAsc(Long userId,LocalDate date,int appointmentState,boolean hasChecked){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasCheckedOrderBySlotTimeBeginAsc"))
    .queryParam("userId",userId)
    .queryParam("date",date)
    .queryParam("appointmentState",appointmentState)
    .queryParam("hasChecked",hasChecked);
  List<Appointment> aux = restTemplate.getForObject(builder.toUriString(), List<Appointment>.class)

 return aux;
}


public long countBySlotDoctorUserIdAndPatientUserIdAndAppointmentStatus(Long doctorId,Long patientId,int appointmentState){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBySlotDoctorUserIdAndPatientUserIdAndAppointmentStatus"))
    .queryParam("doctorId",doctorId)
    .queryParam("patientId",patientId)
    .queryParam("appointmentState",appointmentState);
  long aux = restTemplate.getForObject(builder.toUriString(), long.class)

 return aux;
}


public long countBySlotDoctorUserIdAndSlotDateAndAppointmentStatus(long doctorId,LocalDate date,int appointmentState){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBySlotDoctorUserIdAndSlotDateAndAppointmentStatus"))
    .queryParam("doctorId",doctorId)
    .queryParam("date",date)
    .queryParam("appointmentState",appointmentState);
  long aux = restTemplate.getForObject(builder.toUriString(), long.class)

 return aux;
}


public long countBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasChecked(long doctorId,LocalDate date,int appointmentState,boolean hasChecked){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countBySlotDoctorUserIdAndSlotDateAndAppointmentStatusAndHasChecked"))
    .queryParam("doctorId",doctorId)
    .queryParam("date",date)
    .queryParam("appointmentState",appointmentState)
    .queryParam("hasChecked",hasChecked);
  long aux = restTemplate.getForObject(builder.toUriString(), long.class)

 return aux;
}


}