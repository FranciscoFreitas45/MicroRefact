package switchtwentytwenty.project.interfaceadaptor.icontroller.category;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.ElementNotFoundException;
public interface IGetCategoryByIDController {


public ResponseEntity<Object> getCategoryByID(String categoryID)
;

}