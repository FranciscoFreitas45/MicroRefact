package switchtwentytwenty.project.domain.aggregate.ledger;
 import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.dto.todomaindto.LedgerJpaToDomainDTO;
import java.util.List;
public class LedgerFactory {

// Constructor methods.
/**
 * Constructor.
 */
private LedgerFactory() {
}
public Ledger create(LedgerID ledgerID){
    return new Ledger(ledgerID);
}


}