import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmpSalAllowanceRepoController {

 private EmpSalAllowanceRepo empsalallowancerepo;


@GetMapping
("/findByDelStatus")
public List<EmpSalAllowance> findByDelStatus(@RequestParam(name = "i") int i){
  return empsalallowancerepo.findByDelStatus(i);
}


@GetMapping
("/findByDelStatusAndEmpId")
public List<EmpSalAllowance> findByDelStatusAndEmpId(@RequestParam(name = "delStatus") int delStatus,@RequestParam(name = "empIds") List<Integer> empIds){
  return empsalallowancerepo.findByDelStatusAndEmpId(delStatus,empIds);
}


}