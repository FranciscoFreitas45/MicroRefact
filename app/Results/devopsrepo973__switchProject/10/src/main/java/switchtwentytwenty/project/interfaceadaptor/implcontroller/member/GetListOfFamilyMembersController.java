package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IGetListOfFamilyMembersController;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetListOfFamilyMembersController implements IGetListOfFamilyMembersController{

@Autowired
 private  IPersonService personService;


@GetMapping(value = "/families/{familyID}/members")
public ResponseEntity<Object> getListOfFamilyMembers(String familyID){
    try {
        List<PersonOutDTO> familyMembers = personService.getListOfFamilyMembers(familyID);
        return new ResponseEntity<>(familyMembers, HttpStatus.OK);
    } catch (Exception exception) {
        String msg = exception.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}