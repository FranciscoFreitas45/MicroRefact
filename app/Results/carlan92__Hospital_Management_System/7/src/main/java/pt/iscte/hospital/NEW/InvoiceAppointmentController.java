package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Invoice;
@RestController
@CrossOrigin
public class InvoiceAppointmentController {

@Autowired
 private InvoiceAppointmentService invoiceappointmentservice;


}