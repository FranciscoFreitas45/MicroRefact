package switchtwentytwenty.project.dto.outdto.MovementOutDTO;
 import lombok;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
public class OutMovementDTOBuilder {

 private  MovementOutDTO dto;

// Constructor method
/**
 * Sole constructor.
 */
public OutMovementDTOBuilder() {
    this.dto = new MovementOutDTO();
}
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


public OutMovementDTOBuilder withCategory(String category){
    dto.category = category;
    return this;
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