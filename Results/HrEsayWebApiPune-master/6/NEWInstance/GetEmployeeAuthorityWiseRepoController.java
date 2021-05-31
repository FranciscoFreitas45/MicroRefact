import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetEmployeeAuthorityWiseRepoController {

 private GetEmployeeAuthorityWiseRepo getemployeeauthoritywiserepo;


@GetMapping
("/getEmpIdList")
public List<GetEmployeeAuthorityWise> getEmpIdList(@RequestParam(name = "empId") int empId){
  return getemployeeauthoritywiserepo.getEmpIdList(empId);
}


@GetMapping
("/getEmpIdListInClaimAuth")
public List<GetEmployeeAuthorityWise> getEmpIdListInClaimAuth(@RequestParam(name = "empId") int empId){
  return getemployeeauthoritywiserepo.getEmpIdListInClaimAuth(empId);
}


}