import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetClaimApplyAuthwiseRepoController {

 private GetClaimApplyAuthwiseRepo getclaimapplyauthwiserepo;


@GetMapping
("/getClaimApplyList")
public List<GetClaimApplyAuthwise> getClaimApplyList(@RequestParam(name = "empId") int empId){
  return getclaimapplyauthwiserepo.getClaimApplyList(empId);
}


@GetMapping
("/getClaimApplyListForPendingForManager")
public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForManager(@RequestParam(name = "empId") int empId){
  return getclaimapplyauthwiserepo.getClaimApplyListForPendingForManager(empId);
}


@GetMapping
("/getClaimApplyListForPendingForAdmin")
public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForAdmin(){
  return getclaimapplyauthwiserepo.getClaimApplyListForPendingForAdmin();
}


@GetMapping
("/getClaimApplyList2")
public List<GetClaimApplyAuthwise> getClaimApplyList2(@RequestParam(name = "empId") int empId){
  return getclaimapplyauthwiserepo.getClaimApplyList2(empId);
}


@GetMapping
("/getClaimApplyDetails")
public GetClaimApplyAuthwise getClaimApplyDetails(@RequestParam(name = "claimId") int claimId){
  return getclaimapplyauthwiserepo.getClaimApplyDetails(claimId);
}


}