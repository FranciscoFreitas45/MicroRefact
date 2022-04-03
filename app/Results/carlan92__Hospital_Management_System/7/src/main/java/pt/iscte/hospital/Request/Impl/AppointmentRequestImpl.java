package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Appointment;
import pt.iscte.hospital.Request.AppointmentRequest;
public class AppointmentRequestImpl implements AppointmentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Appointment getAppointment(Long appointmentId){
 Appointment aux = restTemplate.getForObject("http://4/Invoice/{id}/Appointment/getAppointment",Appointment.class,appointmentId);
return aux;
}


}