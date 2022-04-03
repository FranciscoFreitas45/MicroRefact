package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.IGetAllStandardCategoriesController;
import java.util.List;
@RestController
@AllArgsConstructor
public class GetAllStandardCategoriesController implements IGetAllStandardCategoriesController{

@Autowired
 private  ICategoryService categoryService;


@GetMapping("/categories/standard/list")
public ResponseEntity<Object> getAllStandardCategories(){
    List<StandardCategoryOutDTO> standardCategoriesDTOs = categoryService.getListOfAllStandardCategories();
    return new ResponseEntity<>(standardCategoriesDTOs, HttpStatus.OK);
}


}