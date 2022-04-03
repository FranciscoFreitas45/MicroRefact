package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSRoleFunctionController {

 private TSRoleFunction tsrolefunction;

 private TSRoleFunction tsrolefunction;


@PutMapping
("/setTSFunction")
public void setTSFunction(@RequestParam(name = "TSFunction") TSFunction TSFunction){
tsrolefunction.setTSFunction(TSFunction);
}


@PutMapping
("/setTSRole")
public void setTSRole(@RequestParam(name = "TSRole") TSRole TSRole){
tsrolefunction.setTSRole(TSRole);
}


}