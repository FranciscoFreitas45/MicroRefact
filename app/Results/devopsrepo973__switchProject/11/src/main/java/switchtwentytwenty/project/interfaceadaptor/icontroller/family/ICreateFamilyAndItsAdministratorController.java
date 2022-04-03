package switchtwentytwenty.project.interfaceadaptor.icontroller.family;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.FamilyAndAdminInDTO;
public interface ICreateFamilyAndItsAdministratorController {


public ResponseEntity<Object> startFamily(FamilyAndAdminInDTO info)
;

}