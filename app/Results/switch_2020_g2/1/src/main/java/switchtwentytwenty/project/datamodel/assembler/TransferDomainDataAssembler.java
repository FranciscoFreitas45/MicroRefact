package switchtwentytwenty.project.datamodel.assembler;
 import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.LedgerJPA;
import switchtwentytwenty.project.datamodel.TransferJPA;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.transactiondata;
import switchtwentytwenty.project.dto.todomaindto.TransferAssemblerDTO;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
@Service
public class TransferDomainDataAssembler {


public TransferJPA toData(Transaction transfer,LedgerJPA ledgerJPA){
    String originAccountID = transfer.getOriginAccountID().toString();
    String destinationAccountID = transfer.getDestinationAccountID().toString();
    String categoryID = transfer.getCategoryID().toString();
    String description = transfer.getDescription().toString();
    String date = transfer.getDate().toString();
    String systemDateEntry = transfer.getSystemDateEntry().toString();
    double balance = transfer.getBalanceToThisDate().toDouble();
    double amount = transfer.getAmount().toDouble();
    return new TransferJPA.TransferJPABuilder().withOriginAccountID(originAccountID).withDestinationAccountID(destinationAccountID).withCategoryID(categoryID).withDescription(description).withDate(date).withSystemDateEntry(systemDateEntry).withBalance(balance).withAmount(amount).withLedgerJPA(ledgerJPA).build();
}


public Transaction toDomain(TransferJPA transferJPA){
    AccountID originAccountID = new AccountID(UUID.fromString(transferJPA.getId().getOriginAccountID()));
    AccountID destinationAccountID = new AccountID(UUID.fromString(transferJPA.getId().getDestinationAccountID()));
    CategoryID categoryID = new CategoryID(transferJPA.getId().getCategoryID());
    TransactionDate transactionDate = new TransactionDate(transferJPA.getId().getDate());
    MoneyValue amount = new MoneyValue(BigDecimal.valueOf(transferJPA.getId().getAmount()));
    MoneyValue balance = new MoneyValue(BigDecimal.valueOf(transferJPA.getId().getBalance()));
    SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse(transferJPA.getId().getSystemDateEntry()));
    TransactionDescription description = new TransactionDescription(transferJPA.getId().getDescription());
    TransferAssemblerDTO transferDTO = new TransferAssemblerDTO.TransferAssemblerDTOBuilder().withOriginAccountID(originAccountID).withDestinationAccountID(destinationAccountID).withCategoryID(categoryID).withDate(transactionDate).withCredit(new MovementType(Constants.CREDIT)).withDebit(new MovementType(Constants.DEBIT)).withDescription(description).withAmount(amount).withSystemDateEntry(systemDateEntry).build();
    return new Transfer(transferDTO, balance);
}


}