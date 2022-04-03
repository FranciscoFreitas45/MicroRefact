package switchtwentytwenty.project.dto.todomaindto.TransferAssemblerDTO;
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
public class TransferAssemblerDTOBuilder {

 private  TransferAssemblerDTO transferAssemblerDTO;

/**
 * Sole constructor.
 */
public TransferAssemblerDTOBuilder() {
    transferAssemblerDTO = new TransferAssemblerDTO();
}
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