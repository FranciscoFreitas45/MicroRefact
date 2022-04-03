package switchtwentytwenty.project.dto.outdto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@AllArgsConstructor
public class PersonalCashAccountOutDTO extends RepresentationModel<PersonalCashAccountOutDTO>{

@Getter
 private String accountID;

@Getter
 private String accountDesignation;

@Getter
 private String balance;


@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), accountID, balance, accountDesignation);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof PersonalCashAccountOutDTO))
        return false;
    PersonalCashAccountOutDTO that = (PersonalCashAccountOutDTO) o;
    return Objects.equals(accountID, that.accountID) && Objects.equals(balance, that.balance) && Objects.equals(accountDesignation, that.accountDesignation);
}


}