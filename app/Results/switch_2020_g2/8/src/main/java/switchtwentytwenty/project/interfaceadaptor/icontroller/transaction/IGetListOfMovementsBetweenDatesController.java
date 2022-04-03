package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;
 import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
public interface IGetListOfMovementsBetweenDatesController {


public ResponseEntity<Object> getListOfMovementsBetweenDates(HttpServletRequest request,String accountID,String startDate,String endDate)
;

}