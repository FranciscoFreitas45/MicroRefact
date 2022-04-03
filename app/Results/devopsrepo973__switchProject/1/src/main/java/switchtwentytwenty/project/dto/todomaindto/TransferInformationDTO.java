package switchtwentytwenty.project.dto.todomaindto;
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
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.CategoryID;
public class TransferInformationDTO {

@Getter
@Setter(AccessLevel.PRIVATE)
 private  AccountID originAccountID;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  AccountID destinationAccountID;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  CategoryID categoryID;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  MovementType credit;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  MovementType debit;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  MoneyValue amount;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  TransactionDate date;

@Getter
@Setter(AccessLevel.PRIVATE)
 private  TransactionDescription description;

@Getter
@Setter
 private  SystemDateEntry systemDateEntry;

 private  TransferInformationDTO transferInformationDTO;


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