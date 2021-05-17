import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Patient;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDateTime;
public class PatientWaitingAppointment implements Comparable<PatientWaitingAppointment>{

 private  Long patientWaitingAppointmentId;

 private  Doctor doctor;

 private  Patient patient;

 private  LocalDateTime date;

 private  LocalDateTime limitDateToReply;

 private  Slot slot;

 private  boolean closed;

 private  boolean slotAccepted;

 private  boolean repliedToOffer;

 private  Long position;

 private Long slotId;

 private SlotRequestImpl slotrequestimpl;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getDateTimeStr(){
    return date.format(Calendar.DATE_TIME_FORMATTER);
}


public Patient getPatient(){
    return patient;
}


public Doctor getDoctor(){
    return doctor;
}


public Long getPosition(){
    return position;
}


public LocalDateTime getDate(){
    return date;
}


public Long getPatientWaitingAppointmentId(){
    return patientWaitingAppointmentId;
}


public void setClosed(boolean closed){
    this.closed = closed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ patientWaitingAppointmentId).concat("/setClosed"));

.queryParam("closed",closed);
restTemplate.put(builder.toUriString(),null);
}


public void setPosition(Long position){
    this.position = position;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ patientWaitingAppointmentId).concat("/setPosition"));

.queryParam("position",position);
restTemplate.put(builder.toUriString(),null);
}


}