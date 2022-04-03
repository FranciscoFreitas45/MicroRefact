package switchtwentytwenty.project.interfaceadaptor.icontroller.member;
 import org.springframework.http.ResponseEntity;
public interface IGetListOfFamilyMembersController {


public ResponseEntity<Object> getListOfFamilyMembers(String familyID)
;

}