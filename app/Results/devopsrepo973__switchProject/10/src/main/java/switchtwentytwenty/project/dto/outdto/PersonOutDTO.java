package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@AllArgsConstructor
public class PersonOutDTO extends RepresentationModel<PersonOutDTO>{

@Getter
 private  String name;

@Getter
 private  String mainEmail;

@Getter
 private  String familyID;


@Override
public int hashCode(){
    return Objects.hash(name, mainEmail);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof PersonOutDTO))
        return false;
    PersonOutDTO that = (PersonOutDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(mainEmail, that.mainEmail) && Objects.equals(familyID, that.familyID);
}


}