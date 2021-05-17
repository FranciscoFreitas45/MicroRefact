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
@Entity
@Getter
@Setter
@ToString
public class Appointment implements Comparable<Appointment>{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "appointment_id")
 private  Long appointmentId;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDate date;

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;

 private  LocalTime timeOfArrival;

 private  Boolean hasChecked;

 private  String notes;

@Transient
 private  Patient patient;

@OneToOne(mappedBy = "appointment")
 private  Invoice invoice;

@Transient
 private  Slot slot;

 private  int appointmentStatus;

@OneToOne(mappedBy = "appointment")
 private  DoctorWaitingPatient doctorWaitingPatient;

@Column(name = userId)
 private Long userId;

@Transient
 private PatientRequestImpl patientrequestimpl;

@Column(name = slotId)
 private Long slotId;

@Transient
 private SlotRequestImpl slotrequestimpl;


public Boolean getHasChecked(){
    return hasChecked;
}


public String getAppointmentStatusStr(){
    return AppointmentState.searchState(appointmentStatus);
}


public void setTimeOfArrival(LocalTime timeOfArrival){
    this.timeOfArrival = timeOfArrival;
}


public DoctorWaitingPatient getDoctorWaitingPatient(){
    return doctorWaitingPatient;
}


public void setDoctorWaitingPatient(DoctorWaitingPatient doctorWaitingPatient){
    this.doctorWaitingPatient = doctorWaitingPatient;
}


public boolean hasInvoice(){
    return invoice != null;
}


public void setTimeBegin(LocalTime timeBegin){
    this.timeBegin = timeBegin;
}


@Override
public int compareTo(Appointment o){
    return this.getSlot().compareTo(o.getSlot());
}


public void setAppointmentStatus(int appointmentStatus){
    this.appointmentStatus = appointmentStatus;
}


public void setSlot(Slot slot){
 slotrequestimpl.setSlot(slot,this.Long);



public void setPatient(Patient patient){
 patientrequestimpl.setPatient(patient,this.Long);



public void setNotes(String notes){
    this.notes = notes;
}


public Doctor getDoctor(){
    return this.slot.getDoctor();
}


public String getNotes(){
    return notes;
}


public boolean foiRealizada(){
    return appointmentStatus == AppointmentState.REALIZADA.getStateNr();
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


public void setTimeEnd(LocalTime timeEnd){
    this.timeEnd = timeEnd;
}


public LocalTime getTimeEnd(){
    return timeEnd;
}


public Long getAppointmentId(){
    return appointmentId;
}


public void setHasChecked(Boolean hasChecked){
    this.hasChecked = hasChecked;
}


public String getTimeOfArrivalStr(){
    if (timeOfArrival == null) {
        return "";
    }
    return timeOfArrival.format(Calendar.TIME_FORMATTER);
}


public boolean missedAppointment(){
    int missedAppointmentNr = AppointmentState.NAO_REALIZADA.getStateNr();
    return appointmentStatus == missedAppointmentNr;
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


public void setDate(LocalDate date){
    this.date = date;
}


}