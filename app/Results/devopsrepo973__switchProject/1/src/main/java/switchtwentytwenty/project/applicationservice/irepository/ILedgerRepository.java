package switchtwentytwenty.project.applicationservice.irepository;
 import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import java.text.ParseException;
public interface ILedgerRepository {


public boolean existsByID(LedgerID id)
;

public Ledger findByID(LedgerID id)
;

public void save(Ledger entity)
;

}