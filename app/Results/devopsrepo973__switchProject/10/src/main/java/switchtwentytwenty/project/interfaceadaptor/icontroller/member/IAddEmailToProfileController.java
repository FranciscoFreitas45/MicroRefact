package switchtwentytwenty.project.interfaceadaptor.icontroller.member;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.AddEmailInDTO;
public interface IAddEmailToProfileController {


public ResponseEntity<Object> addEmailToProfile(String personID,AddEmailInDTO emailToInput)
;

}