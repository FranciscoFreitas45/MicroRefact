package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;
 import org.springframework.http.ResponseEntity;
public interface IGetPersonLedgerMovementsController {


public ResponseEntity<Object> getListOfPersonLedgerMovements(String personID)
;

}