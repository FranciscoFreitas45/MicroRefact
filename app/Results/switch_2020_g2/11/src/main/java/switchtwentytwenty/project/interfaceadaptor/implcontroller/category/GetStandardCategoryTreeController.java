package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.CategoryTreeOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.IGetStandardCategoryTreeController;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetStandardCategoryTreeController implements IGetStandardCategoryTreeController{

@Autowired
 private  ICategoryService categoryService;


@GetMapping("/categories/standard/tree")
public ResponseEntity<Object> getStandardCategoryTree(){
    List<CategoryTreeOutDTO> standardCategoryTreeOutDTO = categoryService.getStandardCategoriesTree();
    return new ResponseEntity<>(standardCategoryTreeOutDTO, HttpStatus.OK);
}


}