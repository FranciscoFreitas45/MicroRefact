package switchtwentytwenty.project.interfaceadaptor.icontroller.account;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.PersonalCashAccountInDTO;
public interface ICreatePersonalCashAccountController {


public ResponseEntity<Object> createPersonalCashAccount(String personId,PersonalCashAccountInDTO info)
;

}