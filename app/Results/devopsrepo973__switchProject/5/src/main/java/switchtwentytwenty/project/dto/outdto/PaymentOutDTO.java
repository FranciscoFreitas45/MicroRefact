package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
@AllArgsConstructor
public class PaymentOutDTO extends RepresentationModel<PaymentOutDTO>{

@Getter
 private  String designation;

@Getter
 private  String accountID;

@Getter
 private  String categoryID;

@Getter
 private  double amount;

@Getter
 private  String date;


}