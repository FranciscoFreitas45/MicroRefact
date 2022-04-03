package switchtwentytwenty.project.datamodel.assembler;
 import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.LedgerJPA;
import switchtwentytwenty.project.datamodel.PaymentJPA;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.transactiondata.Payment;
import switchtwentytwenty.project.domain.share.transactiondata.SystemDateEntry;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
@Service
public class PaymentDomainDataAssembler {


public PaymentJPA toData(Transaction payment,LedgerJPA ledgerJPA){
    String categoryID = payment.getCategoryID().toString();
    String description = payment.getDescription().toString();
    String date = payment.getDate().toString();
    String systemEntryDate = payment.getSystemDateEntry().toString();
    double balance = payment.getBalanceToThisDate().toDouble();
    String accountID = payment.getAccountID().toString();
    double amount = payment.getAmount().toDouble();
    return new PaymentJPA.PaymentJPABuilder().withCategoryID(categoryID).withDescription(description).withDate(date).withSystemEntryDate(systemEntryDate).withBalance(balance).withAccountID(accountID).withAmount(amount).withLedgerJPA(ledgerJPA).build();
}


public Transaction toDomain(PaymentJPA paymentJPA){
    TransactionDescription transactionDescription = new TransactionDescription(paymentJPA.getId().getDescription());
    TransactionDate transactionDate = new TransactionDate(paymentJPA.getId().getDate());
    MoneyValue balance = new MoneyValue(BigDecimal.valueOf(paymentJPA.getId().getBalance()));
    MoneyValue amount = new MoneyValue(BigDecimal.valueOf(paymentJPA.getId().getAmount()));
    CategoryID categoryID = new CategoryID(paymentJPA.getId().getCategoryID());
    AccountID accountID = new AccountID(UUID.fromString(paymentJPA.getId().getAccountID()));
    SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse(paymentJPA.getId().getSystemDateEntry()));
    return new Payment.PaymentBuilder().withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry).withDebitMovement(accountID, amount).build();
}


}