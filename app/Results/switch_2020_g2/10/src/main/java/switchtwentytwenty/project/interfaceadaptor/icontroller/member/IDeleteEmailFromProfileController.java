package switchtwentytwenty.project.interfaceadaptor.icontroller.member;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.DeleteEmailInDto;
public interface IDeleteEmailFromProfileController {


public ResponseEntity<Object> deleteEmailFromProfile(String personID,DeleteEmailInDto emailToDelete)
;

}