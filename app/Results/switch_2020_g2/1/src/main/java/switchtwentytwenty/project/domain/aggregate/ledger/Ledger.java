package switchtwentytwenty.project.domain.aggregate.ledger;
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


public boolean addAll(List<Transaction> transactionList){
    if (transactionList != null) {
        return this.list.addAll(transactionList);
    }
    return false;
}


@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    Ledger ledger = (Ledger) other;
    return Objects.equals(id, ledger.id);
}


@Override
public LedgerID getID(){
    return this.id;
}


public boolean addTransaction(Transaction transaction){
    if (transaction != null) {
        return this.list.add(transaction);
    }
    return false;
}


@Override
public boolean hasSameID(ID id){
    return this.id.equals(id);
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


@Override
public boolean sameValueAs(Ledger other){
    return this.id.equals(other.id) && this.transactions.equals(other.transactions);
}


public boolean addAllTransaction(List<Transaction> transactionList){
    return this.transactions.addAll(transactionList);
}


}