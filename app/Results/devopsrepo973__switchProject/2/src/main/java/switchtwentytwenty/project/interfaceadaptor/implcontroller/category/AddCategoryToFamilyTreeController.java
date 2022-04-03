package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.dto.indto.CustomCategoryInDTO;
import switchtwentytwenty.project.dto.outdto.CustomCategoryOutDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTOMapper;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.IAddCategoryToFamilyTreeController;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class AddCategoryToFamilyTreeController implements IAddCategoryToFamilyTreeController{

@Autowired
 private  ICategoryService categoryService;


@PostMapping(value = "/categories/custom/{familyID}")
public ResponseEntity<Object> addCategoryToFamilyTree(CustomCategoryInDTO info,String familyID){
    try {
        CustomCategoryDTO customCategoryDTO = CustomCategoryDTOMapper.toDTO(info, familyID);
        CustomCategoryOutDTO result;
        String parentID = info.getParentID();
        if (parentID == null) {
            result = this.categoryService.createRootCustomCategory(customCategoryDTO);
        } else {
            result = this.categoryService.createChildCustomCategory(customCategoryDTO);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (IllegalArgumentException | NullPointerException | DesignationNotPossibleException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}