package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.dto.outdto.FamilyOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.family.IGetListOfFamiliesController;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetListOfFamiliesController implements IGetListOfFamiliesController{

@Autowired
 private  IFamilyService familyService;


@GetMapping(value = "/families")
public ResponseEntity<Object> getListOfFamilies(){
    try {
        List<FamilyOutDTO> familyList = familyService.getListOfFamilies();
        return new ResponseEntity<>(familyList, HttpStatus.OK);
    } catch (Exception exception) {
        String msg = exception.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}