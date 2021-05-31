import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LoanMainRepoController {

 private LoanMainRepo loanmainrepo;


@GetMapping
("/findByEmpIdAndDelStatus")
public List<LoanMain> findByEmpIdAndDelStatus(@RequestParam(name = "empId") int empId,@RequestParam(name = "i") int i){
  return loanmainrepo.findByEmpIdAndDelStatus(empId,i);
}


}