import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LeaveApplyRepositoryController {

 private LeaveApplyRepository leaveapplyrepository;


@GetMapping
("/findByCalYrIdAndDelStatusAndEmpId")
public List<LeaveApply> findByCalYrIdAndDelStatusAndEmpId(@RequestParam(name = "calYr") int calYr,@RequestParam(name = "i") int i,@RequestParam(name = "empId") int empId){
  return leaveapplyrepository.findByCalYrIdAndDelStatusAndEmpId(calYr,i,empId);
}


}