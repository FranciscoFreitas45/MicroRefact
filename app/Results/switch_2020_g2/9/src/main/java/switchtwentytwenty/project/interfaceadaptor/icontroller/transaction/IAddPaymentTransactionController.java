package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import switchtwentytwenty.project.dto.indto.PaymentInDTO;
import javax.servlet.http.HttpServletRequest;
public interface IAddPaymentTransactionController {


public ResponseEntity<Object> addPaymentTransaction(HttpServletRequest request,String accountID,PaymentInDTO dto)
;

}