package pt.iscte.hospital.DTO;
 import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import pt.iscte.hospital.Request.AppointmentRequest;
import pt.iscte.hospital.Request.Impl.AppointmentRequestImpl;
import pt.iscte.hospital.DTO.Appointment;
public class DoctorWaitingPatient implements Comparable<DoctorWaitingPatient>{

 private  Long doctorWaitingPatientId;

 private  LocalDate date;

 private  LocalTime timeFirstCall;

 private  LocalTime timeLatestCall;

 private  Appointment appointment;

 private Long appointmentId;

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


public String getTimeLatestCallStr(){
    return timeLatestCall.format(Calendar.TIME_FORMATTER);
}


public LocalDate getDate(){
    return date;
}


}