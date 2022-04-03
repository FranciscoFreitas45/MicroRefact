package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OperationHistoryRequestController {

 private OperationHistoryRequest operationhistoryrequest;


@PutMapping
("/setLanguage")
public void setLanguage(@RequestParam(name = "value") String value){
operationhistoryrequest.setLanguage(value);
}


@PutMapping
("/setMessageType")
public void setMessageType(@RequestParam(name = "value") int value){
operationhistoryrequest.setMessageType(value);
}


}