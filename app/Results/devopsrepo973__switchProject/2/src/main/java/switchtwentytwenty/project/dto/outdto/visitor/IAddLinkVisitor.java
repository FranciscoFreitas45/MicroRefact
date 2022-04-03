package switchtwentytwenty.project.dto.outdto.visitor;
 import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
public interface IAddLinkVisitor {


public Link visit(CategoryOutDTO dto)
;

}