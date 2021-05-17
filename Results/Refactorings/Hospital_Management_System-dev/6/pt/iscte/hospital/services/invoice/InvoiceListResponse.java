import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
@Getter
@Setter
@ToString
public class InvoiceListResponse {

 private  String status;

 private  List<InvoiceApi> invoices;


}