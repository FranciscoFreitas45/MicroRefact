package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.indto.CategoryInDTO;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.ICreateStandardCategoryController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class CreateStandardCategoryController implements ICreateStandardCategoryController{

@Autowired
 private  ICategoryService categoryService;


@PostMapping(value = "/categories/standard")
public ResponseEntity<Object> createStandardCategory(CategoryInDTO info){
    try {
        StandardCategoryOutDTO result;
        String parentID = info.getParentID();
        if (parentID == null) {
            result = this.categoryService.createRootStandardCategory(info.getDesignation());
        } else {
            result = this.categoryService.createChildStandardCategory(info.getDesignation(), info.getParentID());
        }
        Link linkToTree = linkTo(GetStandardCategoryTreeController.class).slash("categories").withRel("standard categories");
        result.add(linkToTree);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (DesignationNotPossibleException | ElementNotFoundException | IllegalArgumentException | NullPointerException exception) {
        String errorMessage = "Error: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


}