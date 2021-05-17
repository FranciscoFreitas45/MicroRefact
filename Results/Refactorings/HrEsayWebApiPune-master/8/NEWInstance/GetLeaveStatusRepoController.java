import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetLeaveStatusRepoController {

 private GetLeaveStatusRepo getleavestatusrepo;


@GetMapping
("/getLeaveTrailByLeaveId")
public List<GetLeaveStatus> getLeaveTrailByLeaveId(@RequestParam(name = "leaveId") int leaveId){
  return getleavestatusrepo.getLeaveTrailByLeaveId(leaveId);
}


}