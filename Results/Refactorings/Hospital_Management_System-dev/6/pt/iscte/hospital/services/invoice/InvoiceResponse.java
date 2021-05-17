import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
@Component
@Getter
@Setter
@ToString
public class InvoiceResponse {

 private  String status;

 private  InvoiceApi invoice;


}