package switchtwentytwenty.project.interfaceadaptor.icontroller.account;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.FamilyCashAccountInDTO;
public interface ICreateFamilyCashAccountController {


public ResponseEntity<Object> createFamilyCashAccount(String familyID,FamilyCashAccountInDTO info)
;

}