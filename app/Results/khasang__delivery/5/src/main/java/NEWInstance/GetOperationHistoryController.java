package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GetOperationHistoryController {

 private GetOperationHistory getoperationhistory;

 private GetOperationHistory getoperationhistory;


@PutMapping
("/setOperationHistoryRequest")
public void setOperationHistoryRequest(@RequestParam(name = "value") OperationHistoryRequest value){
getoperationhistory.setOperationHistoryRequest(value);
}


}