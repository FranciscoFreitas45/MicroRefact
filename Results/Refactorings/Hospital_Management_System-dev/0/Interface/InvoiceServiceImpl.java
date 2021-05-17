import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class InvoiceServiceImpl implements InvoiceService{

 private RestTemplate restTemplate;

  String url = "http://6";


public void createInvoice(Appointment appointment){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createInvoice"))
    .queryParam("appointment",appointment);

  restTemplate.put(builder.toUriString(), null)



}