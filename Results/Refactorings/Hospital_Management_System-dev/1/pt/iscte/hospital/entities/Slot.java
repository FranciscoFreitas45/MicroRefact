import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import pt.iscte.hospital.objects.utils.Calendar;
@Entity
public class Slot implements Comparable<Slot>{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "slot_id")
 private  Long slotId;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDate date;

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;

@Transient
 private  Set<Appointment> appointments;

@Transient
 private  Doctor doctor;

@Transient
 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

 private  boolean isAvailable;

@Transient
 private AppointmentRequestImpl appointmentrequestimpl;

@Column(name = userId)
 private Long userId;

@Transient
 private DoctorRequestImpl doctorrequestimpl;

@Transient
 private PatientWaitingAppointmentRequestImpl patientwaitingappointmentrequestimpl;


public void setTimeEnd(LocalTime timeEnd){
    this.timeEnd = timeEnd;
}


public boolean isAvailable(){
    return isAvailable;
}


public LocalTime getTimeEnd(){
    return timeEnd;
}


public void setAvailable(boolean available){
    isAvailable = available;
}


public void setTimeBegin(LocalTime timeBegin){
    this.timeBegin = timeBegin;
}


@Override
public int compareTo(Slot o){
    if (this.date.isBefore(o.date)) {
        return -1;
    } else if (this.date.isAfter(o.date)) {
        return 1;
    } else {
        // Same date
        if (this.timeBegin.isBefore(o.timeBegin)) {
            return -1;
        } else if (this.timeBegin.isAfter(o.timeBegin)) {
            return 1;
        } else {
            return 0;
        }
    }
}


public String getTimeBeginStr(){
    return timeBegin.format(TIME_FORMATTER);
}


public String getTimeEndStr(){
    return timeEnd.format(TIME_FORMATTER);
}


public Doctor getDoctor(){
  this.doctor = doctorrequestimpl.getDoctor(this.Long);
return this.doctor;
}


public void setSlotId(Long slotId){
    this.slotId = slotId;
}


public Long getSlotId(){
    return slotId;
}


public String getDateStr(){
    return date.format(FORMATTER);
}


public void setDate(LocalDate date){
    this.date = date;
}


public LocalDate getDate(){
    return date;
}


public void setDoctor(Doctor doctor){
 doctorrequestimpl.setDoctor(doctor,this.Long);



@Override
public String toString(){
    return "Slot{" + "slotId=" + slotId + ", date=" + date + ", timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + ", doctor= " + doctor.getFirstAndLastName() + '}';
}


public LocalTime getTimeBegin(){
    return timeBegin;
}


}