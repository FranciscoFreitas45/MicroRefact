import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.states.InvoiceState;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.services.invoice.InvoiceApi;
import javax.persistence;
import java.time.LocalDateTime;
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

@OneToOne
@JoinColumn(name = "appointment_id")
 private  Appointment appointment;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime dueDate;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime issuedDate;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 private  LocalDateTime paidDate;

 private  double value;

 private  int invoiceState;


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