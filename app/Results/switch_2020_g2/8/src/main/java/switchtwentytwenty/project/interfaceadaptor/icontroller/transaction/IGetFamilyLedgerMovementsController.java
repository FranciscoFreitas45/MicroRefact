package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;
 import org.springframework.http.ResponseEntity;
public interface IGetFamilyLedgerMovementsController {


public ResponseEntity<Object> getListOfFamilyLedgerMovements(String personID)
;

}