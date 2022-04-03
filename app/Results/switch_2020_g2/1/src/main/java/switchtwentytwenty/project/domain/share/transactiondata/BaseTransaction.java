package switchtwentytwenty.project.domain.share.transactiondata;
 import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import java.text.ParseException;
import java.util.Date;
import switchtwentytwenty.project.Interface.CategoryID;
public class BaseTransaction {

 protected  CategoryID categoryID;

 protected  TransactionDescription description;

 protected  TransactionDate date;

 protected  SystemDateEntry systemDateEntry;

 protected  MoneyValue balance;

// Constructor Method
/**
 * Sole constructor.
 *
 * @param categoryID      - category identifier
 * @param description     - transaction description
 * @param date            - transaction date
 * @param balance         - balance after transaction
 * @param systemDateEntry - transaction system entry date
 * @throws ParseException - read
 */
protected BaseTransaction(CategoryID categoryID, TransactionDescription description, TransactionDate date, MoneyValue balance, SystemDateEntry systemDateEntry) throws ParseException {
    this.categoryID = categoryID;
    this.balance = balance;
    this.date = date;
    if (systemDateEntry == null) {
        this.systemDateEntry = new SystemDateEntry(new Date(System.currentTimeMillis()));
    } else {
        this.systemDateEntry = systemDateEntry;
    }
    this.description = description;
}
public SystemDateEntry getSystemDateEntry(){
    return this.systemDateEntry;
}


public MoneyValue getBalance(){
    return this.balance;
}


public CategoryID getCategoryID(){
    return this.categoryID;
}


public boolean isBetweenDates(TransactionDate startDate,TransactionDate endDate){
    return this.date.isBetween(startDate, endDate);
}


public TransactionDate getDate(){
    return this.date;
}


public TransactionDescription getDescription(){
    return this.description;
}


}