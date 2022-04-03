package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSOperationController {

 private TSOperation tsoperation;

 private TSOperation tsoperation;


@PutMapping
("/setOperationname")
public void setOperationname(@RequestParam(name = "operationname") String operationname){
tsoperation.setOperationname(operationname);
}


@PutMapping
("/setOperationcode")
public void setOperationcode(@RequestParam(name = "operationcode") String operationcode){
tsoperation.setOperationcode(operationcode);
}


@PutMapping
("/setOperationicon")
public void setOperationicon(@RequestParam(name = "operationicon") String operationicon){
tsoperation.setOperationicon(operationicon);
}


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") Short status){
tsoperation.setStatus(status);
}


@PutMapping
("/setTSFunction")
public void setTSFunction(@RequestParam(name = "tSFunction") TSFunction tSFunction){
tsoperation.setTSFunction(tSFunction);
}


@PutMapping
("/setTSIcon")
public void setTSIcon(@RequestParam(name = "tSIcon") TSIcon tSIcon){
tsoperation.setTSIcon(tSIcon);
}


}