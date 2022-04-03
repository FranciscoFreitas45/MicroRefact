package switchtwentytwenty.project.dto.outdto.visitor;
 import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.category.GetListOfStandardCategoriesController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
public class GetCategoryStandardLinkVisitor implements IAddLinkVisitor{


@Override
public Link visit(CategoryOutDTO dto){
    return linkTo(methodOn(GetListOfStandardCategoriesController.class).getListOfStandardCategories()).withRel("system's standard categories");
}


}