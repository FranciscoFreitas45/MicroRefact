package switchtwentytwenty.project.interfaceadaptor.icontroller.account;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.CreateAccountInDTO;
public interface IAddBankAccountController {


public ResponseEntity<Object> addBankAccount(CreateAccountInDTO info)
;

}