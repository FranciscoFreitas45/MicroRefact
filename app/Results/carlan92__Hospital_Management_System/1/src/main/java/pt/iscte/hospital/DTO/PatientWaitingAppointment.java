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

public PatientWaitingAppointment() {
}public PatientWaitingAppointment(LocalDateTime date, Doctor doctor, Patient patient) {
    this.date = date;
    this.doctor = doctor;
    this.patient = patient;
}
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


}