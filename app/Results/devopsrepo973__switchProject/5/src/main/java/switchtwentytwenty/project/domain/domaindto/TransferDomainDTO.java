package switchtwentytwenty.project.domain.domaindto;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.MoneyValue;
import switchtwentytwenty.project.Interface.CategoryID;
import switchtwentytwenty.project.Interface.TransactionDate;
import switchtwentytwenty.project.Interface.TransactionDescription;
@NoArgsConstructor
public class TransferDomainDTO {

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String senderID;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  Email receiverID;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  AccountID originAccountID;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  AccountID destinationAccountID;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  MoneyValue amount;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  CategoryID categoryID;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  TransactionDate date;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  TransactionDescription description;

 private  TransferDomainDTO transferDomainMemberToMemberDTO;


public TransferBuilder withOriginAccountID(AccountID originAccountID){
    transferDomainMemberToMemberDTO.setOriginAccountID(originAccountID);
    return this;
}


public TransferBuilder withDate(TransactionDate date){
    transferDomainMemberToMemberDTO.setDate(date);
    return this;
}


public TransferBuilder withSenderID(String senderID){
    transferDomainMemberToMemberDTO.setSenderID(senderID);
    return this;
}


public TransferDomainDTO build(){
    return transferDomainMemberToMemberDTO;
}


public TransferBuilder withCategoryID(CategoryID categoryID){
    transferDomainMemberToMemberDTO.setCategoryID(categoryID);
    return this;
}


public TransferBuilder withDestinationAccountID(AccountID destinationAccountID){
    transferDomainMemberToMemberDTO.setDestinationAccountID(destinationAccountID);
    return this;
}


public TransferBuilder withReceiverID(Email receiverID){
    transferDomainMemberToMemberDTO.setReceiverID(receiverID);
    return this;
}


public TransferBuilder withDescription(TransactionDescription description){
    transferDomainMemberToMemberDTO.setDescription(description);
    return this;
}


public TransferBuilder withAmount(MoneyValue amount){
    transferDomainMemberToMemberDTO.setAmount(amount);
    return this;
}


}