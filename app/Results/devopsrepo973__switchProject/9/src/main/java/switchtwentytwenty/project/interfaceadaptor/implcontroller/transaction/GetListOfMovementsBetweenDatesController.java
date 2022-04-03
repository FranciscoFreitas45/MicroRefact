package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.transaction.IGetListOfMovementsBetweenDatesController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetListOfMovementsBetweenDatesController implements IGetListOfMovementsBetweenDatesController{

@Autowired
 private  ILedgerService ledgerService;


@GetMapping(value = "/accounts/{accountID}/movements")
public ResponseEntity<Object> getListOfMovementsBetweenDates(HttpServletRequest request,String accountID,String startDate,String endDate){
    try {
        String username = request.getUserPrincipal().getName();
        List<MovementOutDTO> dtoList = ledgerService.getListOfMovementsBetweenDates(username, accountID, startDate, endDate);
        if (dtoList.isEmpty()) {
            return new ResponseEntity<>(dtoList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    } catch (IllegalStateException | ParseException | InvalidEmailException | InvalidRelationTypeException | UserEmailNotFoundException exception) {
        String msg = exception.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}