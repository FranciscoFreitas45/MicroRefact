package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.ID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Payment;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.domain.share.transactiondata.Transfer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Ledger implements AggregateRoot<Ledger, ID>{

 private  LedgerID id;

 private  TransactionList transactions;

 private  List<Transaction> list;

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param id identification
 */
protected Ledger(LedgerID id) {
    this.id = id;
    this.transactions = new TransactionList();
}
public List<Transaction> getTransactionList(){
    return new ArrayList<>(this.list);
}


public List<Transfer> getTransferList(){
    List<Transfer> transferList = new ArrayList<>();
    for (Transaction transaction : list) {
        if (transaction.isTransfer()) {
            transferList.add((Transfer) transaction);
        }
    }
    return transferList;
}


@Override
public LedgerID getID(){
    return this.id;
}


public List<Transaction> getListOfMovementsBetweenDates(AccountID accountID,TransactionDate startDate,TransactionDate endDate){
    List<Transaction> transactionsBetweenDates = new ArrayList<>();
    for (Transaction transaction : this.list) {
        if (transaction.isFromAccount(accountID) && transaction.isBetweenDates(startDate, endDate)) {
            transactionsBetweenDates.add(transaction);
        }
    }
    return transactionsBetweenDates;
}


public List<Payment> getPaymentList(){
    List<Payment> paymentList = new ArrayList<>();
    for (Transaction transaction : list) {
        if (transaction.isPayment()) {
            paymentList.add((Payment) transaction);
        }
    }
    return paymentList;
}


}