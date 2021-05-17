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

 private Long userId;

 private PatientRequestImpl patientrequestimpl;

 private Long slotId;

 private SlotRequestImpl slotrequestimpl;


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
  this.patient = patientrequestimpl.getPatient(this.Long);
return this.patient;
}


public int getAppointmentStatus(){
    return appointmentStatus;
}


public Slot getSlot(){
  this.slot = slotrequestimpl.getSlot(this.Long);
return this.slot;
}


}