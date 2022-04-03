package switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
public class TransferInformationDTOBuilder {

 private  TransferInformationDTO transferInformationDTO;

public TransferInformationDTOBuilder() {
    transferInformationDTO = new TransferInformationDTO();
}
public TransferInformationDTOBuilder withOriginAccountID(AccountID originAccountID){
    transferInformationDTO.setOriginAccountID(originAccountID);
    return this;
}


public TransferInformationDTOBuilder withDate(TransactionDate date){
    transferInformationDTO.setDate(date);
    return this;
}


public TransferInformationDTOBuilder withDebit(MovementType debit){
    transferInformationDTO.setDebit(debit);
    return this;
}


public TransferInformationDTO build(){
    return this.transferInformationDTO;
}


public TransferInformationDTOBuilder withCategory(CategoryID categoryID){
    transferInformationDTO.setCategoryID(categoryID);
    return this;
}


public TransferInformationDTOBuilder withDestinationAccountID(AccountID destinationAccountID){
    transferInformationDTO.setDestinationAccountID(destinationAccountID);
    return this;
}


public TransferInformationDTOBuilder withDescription(TransactionDescription description){
    transferInformationDTO.setDescription(description);
    return this;
}


public TransferInformationDTOBuilder withAmount(MoneyValue amount){
    transferInformationDTO.setAmount(amount);
    return this;
}


public TransferInformationDTOBuilder withSystemDateEntry(SystemDateEntry systemDateEntry){
    transferInformationDTO.setSystemDateEntry(systemDateEntry);
    return this;
}


public TransferInformationDTOBuilder withCredit(MovementType credit){
    transferInformationDTO.setCredit(credit);
    return this;
}


}