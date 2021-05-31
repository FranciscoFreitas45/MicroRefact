import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetLeaveApplyAuthwiseRepoController {

 private GetLeaveApplyAuthwiseRepo getleaveapplyauthwiserepo;


@GetMapping
("/getLeaveApplyList")
public List<GetLeaveApplyAuthwise> getLeaveApplyList(@RequestParam(name = "empId") int empId,@RequestParam(name = "currYrId") int currYrId){
  return getleaveapplyauthwiserepo.getLeaveApplyList(empId,currYrId);
}


@GetMapping
("/getLeaveApplyList2")
public List<GetLeaveApplyAuthwise> getLeaveApplyList2(@RequestParam(name = "empId") int empId,@RequestParam(name = "currYrId") int currYrId){
  return getleaveapplyauthwiserepo.getLeaveApplyList2(empId,currYrId);
}


}