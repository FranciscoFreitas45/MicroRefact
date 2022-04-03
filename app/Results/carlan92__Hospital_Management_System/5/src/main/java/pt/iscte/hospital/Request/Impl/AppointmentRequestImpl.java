package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Appointment;
import pt.iscte.hospital.Request.AppointmentRequest;
public class AppointmentRequestImpl implements AppointmentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}