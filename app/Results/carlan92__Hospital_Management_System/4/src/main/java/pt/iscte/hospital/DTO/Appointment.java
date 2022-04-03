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

 private PatientRequest patientrequest = new PatientRequestImpl();;

 private Long invoiceId;

 private InvoiceRequest invoicerequest = new InvoiceRequestImpl();;

 private Long slotId;

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
    this.slot = slot;
}


public void setPatient(Patient patient){
 patientrequest.setPatient(patient,this.userId);
}



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
  this.patient = patientrequest.getPatient(this.userId);
return this.patient;
}


public int getAppointmentStatus(){
    return appointmentStatus;
}


public Slot getSlot(){
    return slot;
}


public void setDate(LocalDate date){
    this.date = date;
}


}