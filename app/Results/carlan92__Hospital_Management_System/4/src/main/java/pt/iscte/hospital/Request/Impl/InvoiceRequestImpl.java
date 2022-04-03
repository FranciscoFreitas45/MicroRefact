package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Invoice;
import pt.iscte.hospital.Request.InvoiceRequest;
public class InvoiceRequestImpl implements InvoiceRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}