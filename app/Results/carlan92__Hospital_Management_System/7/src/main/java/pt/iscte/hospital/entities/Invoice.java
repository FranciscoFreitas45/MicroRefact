package pt.iscte.hospital.entities;
 import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.states.InvoiceState;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.services.invoice.InvoiceApi;
import javax.persistence;
import java.time.LocalDateTime;
import pt.iscte.hospital.Request.AppointmentRequest;
import pt.iscte.hospital.Request.Impl.AppointmentRequestImpl;
import pt.iscte.hospital.DTO.Appointment;
@Entity
@Getter
@Setter
@ToString
public class Invoice {

 public  long NR_DAYS_TO_PAY_INVOICE;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long invoiceId;

 private  String invoiceApiId;

@Transient
 private  Appointment appointment;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime dueDate;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime issuedDate;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime paidDate;

 private  double value;

 private  int invoiceState;

@Column(name = "appointmentId")
 private Long appointmentId;

@Transient
 private AppointmentRequest appointmentrequest = new AppointmentRequestImpl();;

// Constructors
public Invoice() {
    this.invoiceState = InvoiceState.NAO_FACTURADA.getStateNr();
}public Invoice(Appointment appointment, InvoiceApi invoiceApi) {
    this.invoiceApiId = invoiceApi.getId();
    this.appointment = appointment;
    this.issuedDate = invoiceApi.getIssuedDate();
    this.dueDate = invoiceApi.getDueDate();
    this.value = invoiceApi.getValue();
    this.invoiceState = InvoiceState.NAO_FACTURADA.getStateNr();
}public Invoice(Long invoiceId) {
    this.invoiceId = invoiceId;
    this.invoiceState = InvoiceState.NAO_FACTURADA.getStateNr();
}
public Appointment getAppointment(){
  this.appointment = appointmentrequest.getAppointment(this.appointmentId);
return this.appointment;
}


public String getShortId(){
    return invoiceApiId.split("-")[0];
}


public String getDueDateStr(){
    return dueDate.format(Calendar.FORMATTER);
}


public Long getInvoiceId(){
    return invoiceId;
}


public String getIssuedDateStr(){
    return issuedDate.format(Calendar.FORMATTER);
}


public String getInvoiceStateStr(){
    return InvoiceState.searchState(invoiceState);
}


public void setInvoiceState(int invoiceState){
    this.invoiceState = invoiceState;
}


public Double getValue(){
    return value;
}


public boolean isPaid(){
    return invoiceState == InvoiceState.PAGA.getStateNr();
}


public void setValue(Double value){
    this.value = value;
}


public int getInvoiceState(){
    return invoiceState;
}


public String getInvoiceApiId(){
    return invoiceApiId;
}


public String getPaidDateStr(){
    return paidDate.format(Calendar.FORMATTER);
}


}