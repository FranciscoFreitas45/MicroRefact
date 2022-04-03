package switchtwentytwenty.project.interfaceadaptor.icontroller.category;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.ElementNotFoundException;
public interface IGetListOfFamilyCategoriesController {


public ResponseEntity<Object> getListOfFamilyCategoriesController(String familyID)
;

}