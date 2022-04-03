package switchtwentytwenty.project.dto.outdto.visitor;
 import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.category.GetCategoryByIDController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@NoArgsConstructor
public class GetCategorySelfLinkVisitor implements IAddLinkVisitor{


@Override
public Link visit(CategoryOutDTO dto){
    return linkTo(methodOn(GetCategoryByIDController.class).getCategoryByID(dto.getId())).withRel("self link");
}


}