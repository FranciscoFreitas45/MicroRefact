package pt.iscte.hospital.DTO;
 import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Patient;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDateTime;
import pt.iscte.hospital.Request.DoctorRequest;
import pt.iscte.hospital.Request.Impl.DoctorRequestImpl;
import pt.iscte.hospital.DTO.Doctor;
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

 private Long userId;

 private DoctorRequest doctorrequest = new DoctorRequestImpl();;

 private Long slotId;

public PatientWaitingAppointment() {
}public PatientWaitingAppointment(LocalDateTime date, Doctor doctor, Patient patient) {
    this.date = date;
    this.doctor = doctor;
    this.patient = patient;
}
public String getDateTimeStr(){
    return date.format(Calendar.DATE_TIME_FORMATTER);
}


public void setPatientWaitingAppointmentId(Long patientWaitingAppointmentId){
    this.patientWaitingAppointmentId = patientWaitingAppointmentId;
}


@Override
public int compareTo(PatientWaitingAppointment o){
    return date.compareTo(o.date);
}


public void setClosed(boolean closed){
    this.closed = closed;
}


public Patient getPatient(){
    return patient;
}


public void setPosition(Long position){
    this.position = position;
}


public void setPatient(Patient patient){
    this.patient = patient;
}


public Doctor getDoctor(){
  this.doctor = doctorrequest.getDoctor(this.userId);
return this.doctor;
}


public Long getPosition(){
    return position;
}


public boolean isClosed(){
    return closed;
}


public void setDate(LocalDateTime date){
    this.date = date;
}


public void setDoctor(Doctor doctor){
 doctorrequest.setDoctor(doctor,this.userId);
}



public LocalDateTime getDate(){
    return date;
}


public Long getPatientWaitingAppointmentId(){
    return patientWaitingAppointmentId;
}


}