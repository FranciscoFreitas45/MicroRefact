import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import pt.iscte.hospital.objects.utils.Calendar;
public class Slot implements Comparable<Slot>{

 private  Long slotId;

 private  LocalDate date;

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;

 private  Set<Appointment> appointments;

 private  Doctor doctor;

 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

 private  boolean isAvailable;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public LocalTime getTimeEnd(){
    return timeEnd;
}


public String getTimeBeginStr(){
    return timeBegin.format(TIME_FORMATTER);
}


public String getTimeEndStr(){
    return timeEnd.format(TIME_FORMATTER);
}


public Doctor getDoctor(){
    return doctor;
}


public Long getSlotId(){
    return slotId;
}


public String getDateStr(){
    return date.format(FORMATTER);
}


public LocalDate getDate(){
    return date;
}


public LocalTime getTimeBegin(){
    return timeBegin;
}


public void setAvailable(boolean available){
    isAvailable = available;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ slotId).concat("/setAvailable"));

.queryParam("available",available);
restTemplate.put(builder.toUriString(),null);
}


}