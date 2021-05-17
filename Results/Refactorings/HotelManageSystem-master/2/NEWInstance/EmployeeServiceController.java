import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmployeeServiceController {

 private EmployeeService employeeservice;


@GetMapping
("/findByUserName")
public Employee findByUserName(@RequestParam(name = "userName") String userName){
  return employeeservice.findByUserName(userName);
}


}