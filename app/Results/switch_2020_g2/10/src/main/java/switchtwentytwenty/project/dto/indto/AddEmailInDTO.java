package switchtwentytwenty.project.dto.indto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
public class AddEmailInDTO {

@Getter
@Setter
 private  String emailToAdd;


@Override
public int hashCode(){
    return Objects.hash(emailToAdd);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof AddEmailInDTO))
        return false;
    AddEmailInDTO that = (AddEmailInDTO) o;
    return Objects.equals(emailToAdd, that.emailToAdd);
}


}