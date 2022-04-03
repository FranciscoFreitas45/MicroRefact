package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerIDGenerator;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import java.util.UUID;
@Service
@AllArgsConstructor
public class LedgerIDGenerator implements ILedgerIDGenerator{

@Autowired
 private ILedgerRepository ledgerRepository;


public LedgerID generate(){
    UUID id;
    LedgerID ledgerID;
    do {
        id = UUID.randomUUID();
        ledgerID = new LedgerID(id);
    } while (ledgerRepository.existsByID(ledgerID));
    return ledgerID;
}


}