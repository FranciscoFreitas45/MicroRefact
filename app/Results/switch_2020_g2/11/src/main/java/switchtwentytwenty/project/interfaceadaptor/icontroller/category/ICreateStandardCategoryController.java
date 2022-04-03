package switchtwentytwenty.project.interfaceadaptor.icontroller.category;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.CategoryInDTO;
public interface ICreateStandardCategoryController {


public ResponseEntity<Object> createStandardCategory(CategoryInDTO info)
;

}