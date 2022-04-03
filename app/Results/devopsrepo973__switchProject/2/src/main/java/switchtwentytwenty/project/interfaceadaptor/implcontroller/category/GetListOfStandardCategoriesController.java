package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.IGetListOfStandardCategoriesController;
import java.util.List;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetListOfStandardCategoriesController implements IGetListOfStandardCategoriesController{

@Autowired
 private  ICategoryService categoryService;


@GetMapping("/categories/standard")
public ResponseEntity<Object> getListOfStandardCategories(){
    List<StandardCategoryOutDTO> standardCategoriesDTOs = categoryService.getListOfStandardCategories();
    Link standardCategories = linkTo(methodOn(GetListOfStandardCategoriesController.class).getListOfStandardCategories()).withRel("View List of Standard Categories");
    for (StandardCategoryOutDTO standardCategoryOutDTO : standardCategoriesDTOs) {
        standardCategoryOutDTO.add(standardCategories);
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(GetCategoryByIDController.class).getCategoryByID(standardCategoryOutDTO.getId())).withRel("self link");
        standardCategoryOutDTO.add(selfLink);
    }
    return new ResponseEntity<>(standardCategoriesDTOs, HttpStatus.OK);
}


}