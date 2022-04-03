package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@AllArgsConstructor
public class FamilyOutDTO extends RepresentationModel{

@Getter
 private  String name;

@Getter
 private  String familyID;


@Override
public int hashCode(){
    return Objects.hash(name, familyID);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    FamilyOutDTO that = (FamilyOutDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(familyID, that.familyID);
}


}