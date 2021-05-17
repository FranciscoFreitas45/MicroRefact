import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
@Component
@Getter
@Setter
@ToString
public class InvoiceItem {

 private  String description;

 private  double value;


}