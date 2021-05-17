import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.entities.Invoice;
import pt.iscte.hospital.exceptions.PaymentException;
import java.time.LocalDateTime;
import java.util.List;
public interface InvoiceService {


public List<InvoiceApi> getListAPI(InvoiceFilter invoiceFilter)


public InvoiceApi getInvoiceInfoAPI(String invoiceId)


public boolean payInvoiceAPI(String invoiceId)


public String getInvoiceUrlAPI(String invoiceId)


public void payInvoice(Appointment appointment)


public InvoiceApi createInvoiceAPI(String name,String email,long nif,LocalDateTime dueDate,Double value,List<InvoiceItem> invoiceItems)


public void createInvoice(Appointment appointment)


public Invoice findByInvoiceId(Long invoiceId)


}