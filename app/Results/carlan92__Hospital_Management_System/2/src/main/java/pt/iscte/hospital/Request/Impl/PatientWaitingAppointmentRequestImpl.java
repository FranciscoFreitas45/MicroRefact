package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.PatientWaitingAppointment;
import pt.iscte.hospital.Request.PatientWaitingAppointmentRequest;
public class PatientWaitingAppointmentRequestImpl implements PatientWaitingAppointmentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}