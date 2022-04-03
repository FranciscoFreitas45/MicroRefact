package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@AllArgsConstructor
public class AccountOutDTO extends RepresentationModel<AccountOutDTO>{

@Getter
 private String accountID;

@Getter
 private String designation;


@Override
public int hashCode(){
    return Objects.hash(accountID, designation);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof AccountOutDTO))
        return false;
    AccountOutDTO that = (AccountOutDTO) o;
    return Objects.equals(accountID, that.accountID) && Objects.equals(designation, that.designation);
}


}