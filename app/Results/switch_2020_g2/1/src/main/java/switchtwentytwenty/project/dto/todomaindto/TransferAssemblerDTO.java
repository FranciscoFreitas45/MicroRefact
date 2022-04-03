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
public class TransferAssemblerDTO {

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

 private  TransferAssemblerDTO transferAssemblerDTO;


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withOriginAccountID(AccountID originAccountID){
    transferAssemblerDTO.setOriginAccountID(originAccountID);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDate(TransactionDate date){
    transferAssemblerDTO.setDate(date);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDebit(MovementType debit){
    transferAssemblerDTO.setDebit(debit);
    return this;
}


public TransferAssemblerDTO build(){
    return this.transferAssemblerDTO;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withCategoryID(CategoryID categoryID){
    transferAssemblerDTO.setCategoryID(categoryID);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDestinationAccountID(AccountID destinationAccountID){
    transferAssemblerDTO.setDestinationAccountID(destinationAccountID);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withDescription(TransactionDescription description){
    transferAssemblerDTO.setDescription(description);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withAmount(MoneyValue amount){
    transferAssemblerDTO.setAmount(amount);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withSystemDateEntry(SystemDateEntry systemDateEntry){
    transferAssemblerDTO.setSystemDateEntry(systemDateEntry);
    return this;
}


public TransferAssemblerDTO.TransferAssemblerDTOBuilder withCredit(MovementType credit){
    transferAssemblerDTO.setCredit(credit);
    return this;
}


}