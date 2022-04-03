package switchtwentytwenty.project.datamodel;
 import lombok.Getter;
import javax.persistence;
@Entity
@Table(name = "Payment")
public class PaymentJPA {

@EmbeddedId
@Getter
 private  PaymentIDJPA id;

@Getter
@ManyToOne()
@JoinColumn(name = "ledger_id")
 private  LedgerJPA ledgerJPA;

 private  PaymentJPA paymentJPA;

/**
 * Sole constructor
 */
protected PaymentJPA() {
    this.id = new PaymentIDJPA();
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