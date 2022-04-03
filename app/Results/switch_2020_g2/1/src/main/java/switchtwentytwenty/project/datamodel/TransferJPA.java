package switchtwentytwenty.project.datamodel;
 import lombok.Getter;
import javax.persistence;
@Entity
@Table(name = "Transfer")
public class TransferJPA {

@EmbeddedId
@Getter
 private  TransferIDJPA id;

@Getter
@ManyToOne()
@JoinColumn(name = "ledger_id")
 private  LedgerJPA ledgerJPA;

 private  TransferJPA transferJPA;

// Constructor method
/**
 * Sole constructor
 */
protected TransferJPA() {
    this.id = new TransferIDJPA();
}
public TransferJPABuilder withOriginAccountID(String originAccountID){
    this.transferJPA.id.setOriginAccountID(originAccountID);
    return this;
}


public TransferJPABuilder withDate(String date){
    this.transferJPA.id.setDate(date);
    return this;
}


public TransferJPABuilder withLedgerJPA(LedgerJPA ledgerJPA){
    this.transferJPA.ledgerJPA = ledgerJPA;
    return this;
}


public TransferJPA build(){
    return this.transferJPA;
}


public TransferJPABuilder withCategoryID(String categoryID){
    this.transferJPA.id.setCategoryID(categoryID);
    return this;
}


public TransferJPABuilder withDestinationAccountID(String destinationAccountID){
    this.transferJPA.id.setDestinationAccountID(destinationAccountID);
    return this;
}


public TransferJPABuilder withDescription(String description){
    this.transferJPA.id.setDescription(description);
    return this;
}


public TransferJPABuilder withBalance(double balance){
    this.transferJPA.id.setBalance(balance);
    return this;
}


public TransferJPABuilder withAmount(double amount){
    this.transferJPA.id.setAmount(amount);
    return this;
}


public TransferJPABuilder withSystemDateEntry(String systemDateEntry){
    this.transferJPA.id.setSystemDateEntry(systemDateEntry);
    return this;
}


}