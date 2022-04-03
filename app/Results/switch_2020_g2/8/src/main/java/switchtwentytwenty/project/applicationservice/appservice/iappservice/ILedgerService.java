package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.dto.outdto.LedgerMovementOutDTO;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
public interface ILedgerService {


public List<LedgerMovementOutDTO> getListOfFamilyLedgerMovements(String familyID)
;

public List<MovementOutDTO> getListOfMovementsBetweenDates(String personIDString,String accountIDString,String startDateString,String endDateString)
;

public List<LedgerMovementOutDTO> getListOfPersonLedgerMovements(String personID)
;

}