package switchtwentytwenty.project.dto.outdto;
 import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LedgerMovementOutMapper {


public LedgerMovementOutDTO toDTO(Transaction transaction,String familyMember,String senderAccount,String receiverAccount,String type,String category){
    LedgerMovementOutDTO dto = new LedgerMovementOutDTO();
    dto.setDate(transaction.getDate().toString());
    dto.setFamilyMember(familyMember);
    dto.setMovementType(type);
    dto.setAmount(transaction.getAmount().toDouble());
    dto.setSenderAccount(senderAccount);
    dto.setReceiverAccount(receiverAccount);
    dto.setDescription(transaction.getDescription().toString());
    dto.setCategory(category);
    dto.setBalanceToThisDate(transaction.getBalanceToThisDate().toDouble());
    return dto;
}


}