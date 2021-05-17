import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ClaimStructureDetailRepoController {

 private ClaimStructureDetailRepo claimstructuredetailrepo;


@GetMapping
("/getClaimStructureDetailByEmpId")
public List<ClaimStructureDetail> getClaimStructureDetailByEmpId(@RequestParam(name = "empId") int empId){
  return claimstructuredetailrepo.getClaimStructureDetailByEmpId(empId);
}


}