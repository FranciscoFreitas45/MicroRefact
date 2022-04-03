package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
public class CustomCategoryOutDTO extends RepresentationModel<CustomCategoryOutDTO>{

@Getter
 private  String designation;

/**
 * Sole constructor.
 *
 * @param designation of the created category
 */
public CustomCategoryOutDTO(String designation) {
    this.designation = designation;
}
@Override
public int hashCode(){
    return Objects.hash(designation);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    CustomCategoryOutDTO that = (CustomCategoryOutDTO) o;
    return Objects.equals(designation, that.designation);
}


}