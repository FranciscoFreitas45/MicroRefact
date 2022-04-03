package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.dto.outdto.LedgerMovementOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.transaction.IGetPersonLedgerMovementsController;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetPersonLedgerMovementsController implements IGetPersonLedgerMovementsController{

@Autowired
 private  ILedgerService ledgerService;


@GetMapping(value = "/users/{personID}/ledger")
public ResponseEntity<Object> getListOfPersonLedgerMovements(String personID){
    try {
        List<LedgerMovementOutDTO> result = ledgerService.getListOfPersonLedgerMovements(personID);
        return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception exception) {
        String error = exception.getMessage();
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}