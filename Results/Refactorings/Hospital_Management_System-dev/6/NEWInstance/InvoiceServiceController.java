import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class InvoiceServiceController {

 private InvoiceService invoiceservice;


@PutMapping
("/createInvoice")
public void createInvoice(@RequestParam(name = "appointment") Appointment appointment){
invoiceservice.createInvoice(appointment);
}


}