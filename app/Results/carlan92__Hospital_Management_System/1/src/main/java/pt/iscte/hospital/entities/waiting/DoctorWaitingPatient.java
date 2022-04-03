package pt.iscte.hospital.entities.waiting;
 import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import pt.iscte.hospital.Request.AppointmentRequest;
import pt.iscte.hospital.Request.Impl.AppointmentRequestImpl;
import pt.iscte.hospital.DTO.Appointment;
@Entity
@Table(name = "doctor_waiting_patient")
public class DoctorWaitingPatient implements Comparable<DoctorWaitingPatient>{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "doctor_waiting_patient_id")
 private  Long doctorWaitingPatientId;

 private  LocalDate date;

 private  LocalTime timeFirstCall;

 private  LocalTime timeLatestCall;

@Transient
 private  Appointment appointment;

@Column(name = "appointmentId")
 private Long appointmentId;

@Transient
 private AppointmentRequest appointmentrequest = new AppointmentRequestImpl();;

// Constructors
public DoctorWaitingPatient() {
}public DoctorWaitingPatient(Appointment appointment) {
    this.date = LocalDate.now();
    this.timeFirstCall = LocalTime.now();
    this.timeLatestCall = LocalTime.now();
    this.appointment = appointment;
}
public Appointment getAppointment(){
  this.appointment = appointmentrequest.getAppointment(this.appointmentId);
return this.appointment;
}


public LocalTime getTimeLatestCall(){
    return timeLatestCall;
}


public LocalTime getTimeFirstCall(){
    return timeFirstCall;
}


public void setTimeLatestCall(LocalTime timeLatestCall){
    this.timeLatestCall = timeLatestCall;
}


public String getTimeLatestCallStr(){
    return timeLatestCall.format(Calendar.TIME_FORMATTER);
}


public LocalDate getDate(){
    return date;
}


@Override
public int compareTo(DoctorWaitingPatient o){
    if (this.date.isBefore(o.date)) {
        return -1;
    } else if (this.date.isAfter(o.date)) {
        return 1;
    } else {
        // Same date
        if (this.timeLatestCall.isBefore(o.timeLatestCall)) {
            return -1;
        } else if (this.timeLatestCall.isAfter(o.timeLatestCall)) {
            return 1;
        } else {
            return 0;
        }
    }
}


}