package switchtwentytwenty.project.dto.toservicedto;
 import switchtwentytwenty.project.domain.domaindto.TransferDomainDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.indto.TransferInDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.UUID;
public class TransferDTOMapper {

/**
 * Sole constructor
 */
private TransferDTOMapper() {
}
public TransferDomainDTO mapToTransferDomainDTO(TransferDTO transferDTO){
    String senderID = transferDTO.getSenderID();
    Email receiverID = new Email(transferDTO.getReceiverID());
    UUID originID = UUID.fromString(transferDTO.getOriginAccountID());
    AccountID senderAccountID = new AccountID(originID);
    UUID destinationID = UUID.fromString(transferDTO.getDestinationAccountID());
    AccountID receiverAccountID = new AccountID(destinationID);
    MoneyValue amount = new MoneyValue(BigDecimal.valueOf(transferDTO.getAmount()));
    CategoryID categoryID = new CategoryID(transferDTO.getCategoryID());
    TransactionDescription description = new TransactionDescription(transferDTO.getDescription());
    TransactionDate date = new TransactionDate(transferDTO.getDate());
    return new TransferDomainDTO.TransferBuilder().withSenderID(senderID).withReceiverID(receiverID).withOriginAccountID(senderAccountID).withDestinationAccountID(receiverAccountID).withAmount(amount).withDate(date).withCategoryID(categoryID).withDescription(description).build();
}


public TransferDTO mapToTransferDTO(TransferInDTO transferInDTO){
    return new TransferDTO.TransferDTOBuilder().senderId(transferInDTO.getSenderId()).receiverId(transferInDTO.getReceiverId()).originAccountId(transferInDTO.getOriginAccountId()).destinationAccountId(transferInDTO.getDestinationAccountId()).amount(transferInDTO.getAmount()).categoryId(transferInDTO.getCategoryId()).description(transferInDTO.getDescription()).date(transferInDTO.getDate()).build();
}


}