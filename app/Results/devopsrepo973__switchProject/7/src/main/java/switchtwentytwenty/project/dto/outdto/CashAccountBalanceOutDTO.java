package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
public class CashAccountBalanceOutDTO extends RepresentationModel<CashAccountBalanceOutDTO>{

@Getter
 private  double balance;

public CashAccountBalanceOutDTO(double balance) {
    this.balance = balance;
}
@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), balance);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof CashAccountBalanceOutDTO))
        return false;
    CashAccountBalanceOutDTO that = (CashAccountBalanceOutDTO) o;
    return Objects.equals(balance, that.balance);
}


}