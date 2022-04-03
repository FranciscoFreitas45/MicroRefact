package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FunctionServiceController {

 private FunctionService functionservice;


@GetMapping
("/delFunction")
public AjaxJson delFunction(@RequestParam(name = "functionId") String functionId){
  return functionservice.delFunction(functionId);
}


}