package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
public class AddEmailOutDTO extends RepresentationModel<AddEmailOutDTO>{

@Getter
@Setter
 private  String personID;


@Override
public int hashCode(){
    return Objects.hash(personID);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof AddEmailOutDTO))
        return false;
    AddEmailOutDTO that = (AddEmailOutDTO) o;
    return Objects.equals(personID, that.personID);
}


}