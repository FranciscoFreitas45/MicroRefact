package switchtwentytwenty.project.interfaceadaptor.icontroller.account;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.InvalidAccountOwner;
public interface IGetCashAccountBalanceController {


public ResponseEntity<Object> getCashAccountBalance(String accountHolderId,String cashAccountId)
;

}