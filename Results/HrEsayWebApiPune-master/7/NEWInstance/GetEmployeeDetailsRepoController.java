import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetEmployeeDetailsRepoController {

 private GetEmployeeDetailsRepo getemployeedetailsrepo;


@GetMapping
("/getEmpListByLocId")
public List<GetEmployeeDetails> getEmpListByLocId(@RequestParam(name = "locId") int locId){
  return getemployeedetailsrepo.getEmpListByLocId(locId);
}


}