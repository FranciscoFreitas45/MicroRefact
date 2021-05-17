import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.entities.Invoice;
import pt.iscte.hospital.entities.Patient;
import pt.iscte.hospital.entities.states.InvoiceState;
import pt.iscte.hospital.repositories.AppointmentRepository;
import pt.iscte.hospital.repositories.InvoiceRepository;
import pt.iscte.hospital.exceptions.PaymentException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util;
@Service
public class InvoiceServiceImpl implements InvoiceService,pt.iscte.hospital.services.invoice.InvoiceService{

 public  long COMPANY_NIF;

 public  String BASE_URL;

 public  String CREATE_URL;

 public  String GET_URL;

 public  String INFO_URL;

 public  String PAY_URL;

 public  String LIST_URL;

@Autowired
 private  AppointmentRepository appointmentRepository;

@Autowired
 private  InvoiceRepository invoiceRepository;


@Override
public List<InvoiceApi> getListAPI(InvoiceFilter invoiceFilter){
    // create an instance of RestTemplate
    RestTemplate restTemplate = new RestTemplate();
    // create headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String filters = invoiceFilter.filtros();
    // request url
    String requestUrl = BASE_URL + String.format(LIST_URL, COMPANY_NIF) + filters;
    // request
    InvoiceListResponse response;
    try {
        response = restTemplate.getForObject(requestUrl, InvoiceListResponse.class);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
        System.out.println(e.getStatusCode().value());
        System.out.println(e.getMessage());
        return new ArrayList<>();
    } catch (UnknownHttpStatusCodeException e) {
        return null;
    }
    if (response == null || response.getStatus().equals("error")) {
        return null;
    }
    return response.getInvoices();
}


@Override
public InvoiceApi getInvoiceInfoAPI(String invoiceId){
    // create an instance of RestTemplate
    RestTemplate restTemplate = new RestTemplate();
    // create headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // request url
    String requestUrl = BASE_URL + String.format(INFO_URL, COMPANY_NIF, invoiceId);
    // request
    InvoiceResponse response;
    try {
        response = restTemplate.getForObject(requestUrl, InvoiceResponse.class);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
        System.out.println(e.getStatusCode().value());
        System.out.println(e.getMessage());
        return null;
    } catch (UnknownHttpStatusCodeException e) {
        return null;
    }
    if (response == null || response.getStatus().equals("error")) {
        return null;
    }
    return response.getInvoice();
}


@Override
public boolean payInvoiceAPI(String invoiceId){
    // create an instance of RestTemplate
    RestTemplate restTemplate = new RestTemplate();
    // create headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // create body
    // request url
    String requestUrl = BASE_URL + String.format(PAY_URL, COMPANY_NIF, invoiceId);
    // request
    InvoiceResponse response;
    try {
        response = restTemplate.postForObject(requestUrl, new HttpEntity<>(headers), InvoiceResponse.class);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
        System.out.println(e.getStatusCode().value());
        System.out.println(e.getMessage());
        return false;
    } catch (UnknownHttpStatusCodeException e) {
        return false;
    }
    if (response == null || response.getStatus().equals("error")) {
        return false;
    }
    return true;
}


@Override
public String getInvoiceUrlAPI(String invoiceId){
    return BASE_URL + String.format(GET_URL, COMPANY_NIF, invoiceId);
}


@Override
public void payInvoice(Appointment appointment){
    // Pedir nº de factura
    if (appointment.getInvoice() != null) {
        Invoice invoice = appointment.getInvoice();
        String invoiceApiId = invoice.getInvoiceApiId();
        boolean paymentRegistered = payInvoiceAPI(invoiceApiId);
        if (paymentRegistered) {
            invoice.setPaidDate(LocalDateTime.now());
            invoice.setInvoiceState(InvoiceState.PAGA.getStateNr());
            invoiceRepository.save(invoice);
        } else {
            // Pagamento não fica registado no serviço da API
            throw new PaymentException();
        }
    }
}


@Override
public InvoiceApi createInvoiceAPI(String name,String email,long nif,LocalDateTime dueDate,Double value,List<InvoiceItem> invoiceItems){
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
    // create an instance of RestTemplate
    RestTemplate restTemplate = new RestTemplate();
    // create headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // create body
    JSONObject invoiceRequest = new JSONObject();
    invoiceRequest.put("name", name);
    invoiceRequest.put("email", email);
    invoiceRequest.put("nif", Long.toString(nif));
    invoiceRequest.put("duedate", df.format(dueDate));
    if (value != null) {
        invoiceRequest.put("value", Double.toString(value));
    } else {
        invoiceRequest.put("items", invoiceItems);
    }
    // request url
    String requestUrl = BASE_URL + String.format(CREATE_URL, COMPANY_NIF);
    // request
    InvoiceResponse response;
    try {
        response = restTemplate.postForObject(requestUrl, new HttpEntity<>(invoiceRequest.toString(), headers), InvoiceResponse.class);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
        System.out.println(e.getStatusCode().value());
        System.out.println(e.getMessage());
        return null;
    } catch (UnknownHttpStatusCodeException e) {
        return null;
    }
    if (response == null || response.getStatus().equals("error")) {
        return null;
    }
    return response.getInvoice();
}


@Override
public void createInvoice(Appointment appointment){
    // Pedir nº de factura
    if (appointment.getInvoice() == null) {
        Patient patient = appointment.getPatient();
        String name = patient.getName();
        String email = patient.getEmail();
        long nif = patient.getNif();
        LocalDateTime dueDate = LocalDateTime.now().plusDays(Invoice.NR_DAYS_TO_PAY_INVOICE);
        Double value = appointment.getDoctor().getSpeciality().getPrice();
        InvoiceApi invoiceApi = createInvoiceAPI(name, email, nif, dueDate, value, null);
        // se existir nº, adicionar o número ao appointment e salvar
        if (invoiceApi != null && invoiceApi.getId() != null) {
            Invoice invoice = new Invoice(appointment, invoiceApi);
            appointment.setInvoice(invoice);
            invoiceRepository.save(invoice);
            appointment.setInvoice(invoice);
            appointmentRepository.save(appointment);
        }
    }
}


@Override
public Invoice findByInvoiceId(Long invoiceId){
    return invoiceRepository.findByInvoiceId(invoiceId);
}


}