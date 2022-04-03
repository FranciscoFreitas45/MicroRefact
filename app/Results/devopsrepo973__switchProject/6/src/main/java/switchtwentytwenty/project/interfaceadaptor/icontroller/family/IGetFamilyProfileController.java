package switchtwentytwenty.project.interfaceadaptor.icontroller.family;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
public interface IGetFamilyProfileController {


public ResponseEntity<Object> getFamilyProfile(HttpServletRequest request,String familyID)
;

}