package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSFunctionController {

 private TSFunction tsfunction;

 private TSFunction tsfunction;


@PutMapping
("/setFunctionName")
public void setFunctionName(@RequestParam(name = "functionName") String functionName){
tsfunction.setFunctionName(functionName);
}


@PutMapping
("/setFunctionIframe")
public void setFunctionIframe(@RequestParam(name = "functionIframe") Short functionIframe){
tsfunction.setFunctionIframe(functionIframe);
}


@PutMapping
("/setFunctionLevel")
public void setFunctionLevel(@RequestParam(name = "functionLevel") Short functionLevel){
tsfunction.setFunctionLevel(functionLevel);
}


@PutMapping
("/setFunctionOrder")
public void setFunctionOrder(@RequestParam(name = "functionOrder") String functionOrder){
tsfunction.setFunctionOrder(functionOrder);
}


@PutMapping
("/setFunctionUrl")
public void setFunctionUrl(@RequestParam(name = "functionUrl") String functionUrl){
tsfunction.setFunctionUrl(functionUrl);
}


@PutMapping
("/setTSFunction")
public void setTSFunction(@RequestParam(name = "TSFunction") TSFunction TSFunction){
tsfunction.setTSFunction(TSFunction);
}


@PutMapping
("/setTSIcon")
public void setTSIcon(@RequestParam(name = "tSIcon") TSIcon tSIcon){
tsfunction.setTSIcon(tSIcon);
}


}