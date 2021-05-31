import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmpListForHolidayApproveRepoController {

 private EmpListForHolidayApproveRepo emplistforholidayapproverepo;


@GetMapping
("/getOptionalHolidayApprovalList")
public List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(@RequestParam(name = "sts") int sts){
  return emplistforholidayapproverepo.getOptionalHolidayApprovalList(sts);
}


}