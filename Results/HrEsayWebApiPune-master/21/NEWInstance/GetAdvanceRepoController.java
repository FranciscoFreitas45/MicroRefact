import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetAdvanceRepoController {

 private GetAdvanceRepo getadvancerepo;


@GetMapping
("/getSpecEmpAdvForReport")
public List<GetAdvance> getSpecEmpAdvForReport(@RequestParam(name = "companyId") int companyId,@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "locId") int locId){
  return getadvancerepo.getSpecEmpAdvForReport(companyId,month,year,locId);
}


}