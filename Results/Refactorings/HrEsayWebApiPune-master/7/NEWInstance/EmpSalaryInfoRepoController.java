import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmpSalaryInfoRepoController {

 private EmpSalaryInfoRepo empsalaryinforepo;


@GetMapping
("/updateLeaveDatainemployee")
public int updateLeaveDatainemployee(@RequestParam(name = "empId") int empId,@RequestParam(name = "leaveDate") String leaveDate,@RequestParam(name = "leaveReason") String leaveReason,@RequestParam(name = "lrEsic") String lrEsic,@RequestParam(name = "lrForPF") String lrForPF){
  return empsalaryinforepo.updateLeaveDatainemployee(empId,leaveDate,leaveReason,lrEsic,lrForPF);
}


}