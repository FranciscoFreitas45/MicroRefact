package switchtwentytwenty.project.interfaceadaptor.icontroller.member;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.PersonInDTO;
public interface IAddFamilyMemberController {


public ResponseEntity<Object> addFamilyMember(PersonInDTO info)
;

}