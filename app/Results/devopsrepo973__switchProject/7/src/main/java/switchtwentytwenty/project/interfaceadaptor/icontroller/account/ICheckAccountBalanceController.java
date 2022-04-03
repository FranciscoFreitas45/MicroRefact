package switchtwentytwenty.project.interfaceadaptor.icontroller.account;
 import org.springframework.http.ResponseEntity;
public interface ICheckAccountBalanceController {


public ResponseEntity<Object> checkAccountBalance(String holderID,String accountID)
;

}