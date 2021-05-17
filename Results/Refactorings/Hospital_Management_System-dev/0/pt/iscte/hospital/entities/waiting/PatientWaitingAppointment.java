import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Patient;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class PatientWaitingAppointment implements Comparable<PatientWaitingAppointment>{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long patientWaitingAppointmentId;

@ManyToOne
@JoinColumn(name = "doctor_id")
 private  Doctor doctor;

@ManyToOne
@JoinColumn(name = "patient_id")
 private  Patient patient;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime date;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime limitDateToReply;

@Transient
 private  Slot slot;

 private  boolean closed;

 private  boolean slotAccepted;

 private  boolean repliedToOffer;

 private  Long position;

@Column(name = slotId)
 private Long slotId;

@Transient
 private SlotRequestImpl slotrequestimpl;


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
    return doctor;
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
    this.doctor = doctor;
}


public LocalDateTime getDate(){
    return date;
}


public Long getPatientWaitingAppointmentId(){
    return patientWaitingAppointmentId;
}


}