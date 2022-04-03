package switchtwentytwenty.project.dto.outdto;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@NoArgsConstructor
@Getter
@Setter
public class LedgerMovementOutDTO extends RepresentationModel<LedgerMovementOutDTO>{

 private  String date;

 private  String movementType;

 private  double amount;

 private  String familyMember;

 private  String senderAccount;

 private  String receiverAccount;

 private  String description;

 private  String category;

 private  double balanceToThisDate;


@Override
public int hashCode(){
    return Objects.hash(date, movementType, amount, familyMember, senderAccount, receiverAccount, description, category, balanceToThisDate);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    LedgerMovementOutDTO that = (LedgerMovementOutDTO) o;
    return Double.compare(that.amount, amount) == 0 && Double.compare(that.balanceToThisDate, balanceToThisDate) == 0 && date.equals(that.date) && movementType.equals(that.movementType) && Objects.equals(familyMember, that.familyMember) && senderAccount.equals(that.senderAccount) && Objects.equals(receiverAccount, that.receiverAccount) && description.equals(that.description) && category.equals(that.category);
}


}