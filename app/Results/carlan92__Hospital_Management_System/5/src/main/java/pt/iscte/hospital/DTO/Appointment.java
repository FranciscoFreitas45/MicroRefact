package pt.iscte.hospital.DTO;
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
import pt.iscte.hospital.Request.PatientRequest;
import pt.iscte.hospital.Request.Impl.PatientRequestImpl;
import pt.iscte.hospital.DTO.Patient;
import pt.iscte.hospital.Request.InvoiceRequest;
import pt.iscte.hospital.Request.Impl.InvoiceRequestImpl;
import pt.iscte.hospital.DTO.Invoice;
import pt.iscte.hospital.Request.SlotRequest;
import pt.iscte.hospital.Request.Impl.SlotRequestImpl;
import pt.iscte.hospital.DTO.Slot;
import pt.iscte.hospital.Request.DoctorWaitingPatientRequest;
import pt.iscte.hospital.Request.Impl.DoctorWaitingPatientRequestImpl;
import pt.iscte.hospital.DTO.DoctorWaitingPatient;
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

 private Long invoiceId;

 private Long slotId;

 private Long doctorWaitingPatientId;

// Constructors
public Appointment() {
}public Appointment(Patient patient, Slot slot) {
    this.patient = patient;
    this.slot = slot;
    this.appointmentStatus = AppointmentState.MARCADA.getStateNr();
}public Appointment(LocalDate date, LocalTime timeBegin, LocalTime timeEnd, String notes) {
    this.date = date;
    this.timeBegin = timeBegin;
    this.timeEnd = timeEnd;
    this.notes = notes;
}
public Boolean getHasChecked(){
    return hasChecked;
}


public String getAppointmentStatusStr(){
    return AppointmentState.searchState(appointmentStatus);
}


public DoctorWaitingPatient getDoctorWaitingPatient(){
  this.doctorWaitingPatient = doctorwaitingpatientrequest.getDoctorWaitingPatient(this.doctorWaitingPatientId);
return this.doctorWaitingPatient;
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
  this.patient = patientrequest.getPatient(this.userId);
return this.patient;
}


public int getAppointmentStatus(){
    return appointmentStatus;
}


public Slot getSlot(){
  this.slot = slotrequest.getSlot(this.slotId);
return this.slot;
}


}