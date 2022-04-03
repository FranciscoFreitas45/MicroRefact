package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
public class FamilyRelationOutDTO extends RepresentationModel<FamilyRelationOutDTO>{

@Getter
 private  String personID;

@Getter
 private  String kinID;

@Getter
 private  String relationType;

// Constructor Methods
public FamilyRelationOutDTO(String personID, String kinID, String relationType) {
    Objects.requireNonNull(personID);
    Objects.requireNonNull(kinID);
    Objects.requireNonNull(personID);
    this.personID = personID;
    this.kinID = kinID;
    this.relationType = relationType;
}
@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), personID, kinID, relationType);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null)
        return false;
    if (getClass() != o.getClass())
        return false;
    FamilyRelationOutDTO that = (FamilyRelationOutDTO) o;
    return Objects.equals(personID, that.personID) && Objects.equals(kinID, that.kinID);
}


}