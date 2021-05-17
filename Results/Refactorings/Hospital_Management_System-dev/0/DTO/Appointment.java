import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.states.AppointmentState;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
public class Appointment implements Comparable<Appointment>{

 private  Long appointmentId;

 private  LocalDate date;

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;

 private  LocalTime timeOfArrival;

 private  Boolean hasChecked;

 private  String notes;

 private  Patient patient;

 private  Invoice invoice;

 private  Slot slot;

 private  int appointmentStatus;

 private  DoctorWaitingPatient doctorWaitingPatient;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public Boolean getHasChecked(){
    return hasChecked;
}


public String getAppointmentStatusStr(){
    return AppointmentState.searchState(appointmentStatus);
}


public DoctorWaitingPatient getDoctorWaitingPatient(){
    return doctorWaitingPatient;
}


public Doctor getDoctor(){
    return this.slot.getDoctor();
}


public String getNotes(){
    return notes;
}


public LocalDate getDate(){
    return date;
}


public LocalTime getTimeBegin(){
    return timeBegin;
}


public LocalTime getTimeOfArrival(){
    return timeOfArrival;
}


public LocalTime getTimeEnd(){
    return timeEnd;
}


public Long getAppointmentId(){
    return appointmentId;
}


public String getTimeOfArrivalStr(){
    if (timeOfArrival == null) {
        return "";
    }
    return timeOfArrival.format(Calendar.TIME_FORMATTER);
}


public Patient getPatient(){
    return patient;
}


public int getAppointmentStatus(){
    return appointmentStatus;
}


public Slot getSlot(){
    return slot;
}


public void setAppointmentStatus(int appointmentStatus){
    this.appointmentStatus = appointmentStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ appointmentId).concat("/setAppointmentStatus"));

.queryParam("appointmentStatus",appointmentStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setNotes(String notes){
    this.notes = notes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ appointmentId).concat("/setNotes"));

.queryParam("notes",notes);
restTemplate.put(builder.toUriString(),null);
}


public void setTimeBegin(LocalTime timeBegin){
    this.timeBegin = timeBegin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ appointmentId).concat("/setTimeBegin"));

.queryParam("timeBegin",timeBegin);
restTemplate.put(builder.toUriString(),null);
}


public void setDate(LocalDate date){
    this.date = date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ appointmentId).concat("/setDate"));

.queryParam("date",date);
restTemplate.put(builder.toUriString(),null);
}


public void setDoctorWaitingPatient(DoctorWaitingPatient doctorWaitingPatient){
    this.doctorWaitingPatient = doctorWaitingPatient;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ appointmentId).concat("/setDoctorWaitingPatient"));

.queryParam("doctorWaitingPatient",doctorWaitingPatient);
restTemplate.put(builder.toUriString(),null);
}


public void setTimeEnd(LocalTime timeEnd){
    this.timeEnd = timeEnd;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ appointmentId).concat("/setTimeEnd"));

.queryParam("timeEnd",timeEnd);
restTemplate.put(builder.toUriString(),null);
}


}