package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
public class FamilyCashAccountOutDTO extends RepresentationModel<FamilyCashAccountOutDTO>{

@Getter
 private String accountDesignation;

@Getter
 private String balance;

public FamilyCashAccountOutDTO(String accountDesignation, String balance) {
    this.accountDesignation = accountDesignation;
    this.balance = balance;
}
@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), balance, accountDesignation);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof FamilyCashAccountOutDTO))
        return false;
    FamilyCashAccountOutDTO that = (FamilyCashAccountOutDTO) o;
    return Objects.equals(balance, that.balance) && Objects.equals(accountDesignation, that.accountDesignation);
}


}