package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import switchtwentytwenty.project.dto.outdto.visitor.IAddLinkVisitor;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import java.util.Objects;
public class CategoryOutDTO extends RepresentationModel<CategoryOutDTO>{

@Getter
 private  String id;

@Getter
 private  String designation;

@Getter
 private  String parentID;

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param id          - identifier
 * @param designation - category designation
 * @param parentID    -    parent ID
 */
public CategoryOutDTO(String id, String designation, String parentID) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(designation);
    this.id = id;
    this.designation = designation;
    this.parentID = parentID;
}
public Link accept(IAddLinkVisitor visitor){
    return visitor.visit(this);
}


}