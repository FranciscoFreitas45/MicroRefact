package switchtwentytwenty.project.datamodel.PaymentJPA;
 import lombok.Getter;
import javax.persistence;
public class PaymentJPABuilder {

 private  PaymentJPA paymentJPA;

// Constructor methods
/**
 * sole constructor
 */
public PaymentJPABuilder() {
    this.paymentJPA = new PaymentJPA();
}
public PaymentJPABuilder withDate(String date){
    this.paymentJPA.id.setDate(date);
    return this;
}


public PaymentJPABuilder withSystemEntryDate(String systemEntryDate){
    this.paymentJPA.id.setSystemDateEntry(systemEntryDate);
    return this;
}


public PaymentJPABuilder withAccountID(String accountID){
    this.paymentJPA.id.setAccountID(accountID);
    return this;
}


public PaymentJPABuilder withLedgerJPA(LedgerJPA ledgerJPA){
    this.paymentJPA.ledgerJPA = ledgerJPA;
    return this;
}


public PaymentJPA build(){
    return this.paymentJPA;
}


public PaymentJPABuilder withCategoryID(String categoryID){
    this.paymentJPA.id.setCategoryID(categoryID);
    return this;
}


public PaymentJPABuilder withDescription(String description){
    this.paymentJPA.id.setDescription(description);
    return this;
}


public PaymentJPABuilder withBalance(double balance){
    this.paymentJPA.id.setBalance(balance);
    return this;
}


public PaymentJPABuilder withAmount(double amount){
    this.paymentJPA.id.setAmount(amount);
    return this;
}


}