import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmployeeDaoController {

 private EmployeeDao employeedao;


@GetMapping
("/findByUserName")
public Employee findByUserName(@RequestParam(name = "userName") String userName){
  return employeedao.findByUserName(userName);
}


}