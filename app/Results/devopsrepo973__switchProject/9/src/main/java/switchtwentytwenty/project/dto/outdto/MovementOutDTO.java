package switchtwentytwenty.project.dto.outdto;
 import lombok;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovementOutDTO extends RepresentationModel<MovementOutDTO>{

@Getter
 private  double amount;

@Getter
 private  String accountID;

@Getter
 private  String movementType;

@Getter
 private  String date;

@Getter
 private  String category;

@Getter
 private  double balanceToThisDate;

@Getter
 private  String description;

 private  MovementOutDTO dto;


public OutMovementDTOBuilder withMovementType(String movementType){
    dto.movementType = movementType;
    return this;
}


public OutMovementDTOBuilder withDate(String date){
    dto.date = date;
    return this;
}


public OutMovementDTOBuilder withBalanceToThisDate(double balanceToThisDate){
    dto.balanceToThisDate = balanceToThisDate;
    return this;
}


public OutMovementDTOBuilder withAccountID(String accountID){
    dto.accountID = accountID;
    return this;
}


public MovementOutDTO build(){
    return this.dto;
}


@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), amount, accountID, movementType, date, category, balanceToThisDate, description);
}


public OutMovementDTOBuilder withCategory(String category){
    dto.category = category;
    return this;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    MovementOutDTO that = (MovementOutDTO) o;
    return Double.compare(that.amount, amount) == 0 && Double.compare(that.balanceToThisDate, balanceToThisDate) == 0 && Objects.equals(accountID, that.accountID) && Objects.equals(movementType, that.movementType) && Objects.equals(date, that.date) && Objects.equals(category, that.category) && Objects.equals(description, that.description);
}


public OutMovementDTOBuilder withDescription(String description){
    dto.description = description;
    return this;
}


public OutMovementDTOBuilder withAmount(double amount){
    dto.amount = amount;
    return this;
}


}