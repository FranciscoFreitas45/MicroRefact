package pt.iscte.hospital.DTO;
 import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.states.InvoiceState;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.services.invoice.InvoiceApi;
import javax.persistence;
import java.time.LocalDateTime;
public class Invoice {

 public  long NR_DAYS_TO_PAY_INVOICE;

 private  Long invoiceId;

 private  String invoiceApiId;

 private  Appointment appointment;

 private  LocalDateTime dueDate;

 private  LocalDateTime issuedDate;

 private  LocalDateTime paidDate;

 private  double value;

 private  int invoiceState;

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
    return appointment;
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


public Double getValue(){
    return value;
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