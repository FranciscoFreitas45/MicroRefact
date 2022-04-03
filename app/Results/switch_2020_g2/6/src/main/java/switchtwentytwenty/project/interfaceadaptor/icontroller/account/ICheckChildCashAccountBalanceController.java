package switchtwentytwenty.project.interfaceadaptor.icontroller.account;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.exception.InvalidVATException;
import java.io.IOException;
public interface ICheckChildCashAccountBalanceController {


public ResponseEntity<Object> checkChildCashAccountBalance(String parentID,String childID,String accountID)
;

}