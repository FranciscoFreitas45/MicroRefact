package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.InvoiceRepository;
import pt.iscte.hospital.entities.Invoice;
@Service
public class InvoiceAppointmentService {

@Autowired
 private InvoiceRepository invoicerepository;


}