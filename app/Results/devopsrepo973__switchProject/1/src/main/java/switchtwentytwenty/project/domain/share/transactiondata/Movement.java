package switchtwentytwenty.project.domain.share.transactiondata;
 import lombok.AccessLevel;
import lombok.Getter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.id.AccountID;
import java.util.Objects;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.DTO.AccountID;
public class Movement {

@Getter
 private  AccountID accountID;

@Getter
 private  MovementType type;

@Getter(AccessLevel.PROTECTED)
 private  MoneyValue amount;

// Business Method
/**
 * Sole Constructor
 *
 * @param amount    - amount of the movement
 * @param accountID - account identifier
 * @param type      - type of movement: credit or debit
 */
public Movement(AccountID accountID, MovementType type, MoneyValue amount) {
    this.amount = amount;
    this.type = type;
    this.accountID = accountID;
}
@Override
public int hashCode(){
    return Objects.hash(accountID, type, amount);
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    Movement movement = (Movement) o;
    return Objects.equals(accountID, movement.accountID) && Objects.equals(type, movement.type) && Objects.equals(amount, movement.amount);
}


public boolean isFromAccount(AccountID accountID){
    return this.accountID.equals(accountID);
}


}