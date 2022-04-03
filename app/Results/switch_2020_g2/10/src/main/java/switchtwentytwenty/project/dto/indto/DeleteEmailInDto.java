package switchtwentytwenty.project.dto.indto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmailInDto {

@Getter
@Setter
 private  String emailToDelete;


@Override
public int hashCode(){
    return Objects.hash(emailToDelete);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof DeleteEmailInDto))
        return false;
    DeleteEmailInDto that = (DeleteEmailInDto) o;
    return Objects.equals(emailToDelete, that.emailToDelete);
}


}