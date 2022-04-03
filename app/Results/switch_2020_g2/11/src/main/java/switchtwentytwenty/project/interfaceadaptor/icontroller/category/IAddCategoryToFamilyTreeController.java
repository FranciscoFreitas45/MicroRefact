package switchtwentytwenty.project.interfaceadaptor.icontroller.category;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.CustomCategoryInDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
public interface IAddCategoryToFamilyTreeController {


public ResponseEntity<Object> addCategoryToFamilyTree(CustomCategoryInDTO dto,String familyID)
;

}