import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetEmpInfoRepoController {

 private GetEmpInfoRepo getempinforepo;


@GetMapping
("/getEmpListByCompanyId")
public List<GetEmployeeInfo> getEmpListByCompanyId(@RequestParam(name = "companyId") int companyId){
  return getempinforepo.getEmpListByCompanyId(companyId);
}


@GetMapping
("/getEmpIdListByCompanyId")
public List<GetEmployeeInfo> getEmpIdListByCompanyId(@RequestParam(name = "companyId") int companyId,@RequestParam(name = "empIdList") List<GetEmployeeAuthorityWise> empIdList){
  return getempinforepo.getEmpIdListByCompanyId(companyId,empIdList);
}


@GetMapping
("/getEmpListByCompanyIdForAuth")
public List<GetEmployeeInfo> getEmpListByCompanyIdForAuth(@RequestParam(name = "companyId") int companyId,@RequestParam(name = "locIdList") List<Integer> locIdList){
  return getempinforepo.getEmpListByCompanyIdForAuth(companyId,locIdList);
}


@GetMapping
("/getEmpListByCompanyIdForAuthClaim")
public List<GetEmployeeInfo> getEmpListByCompanyIdForAuthClaim(@RequestParam(name = "companyId") int companyId,@RequestParam(name = "locIdList") List<Integer> locIdList){
  return getempinforepo.getEmpListByCompanyIdForAuthClaim(companyId,locIdList);
}


@GetMapping
("/getEmpIdListByCompanyIdForClaim")
public List<GetEmployeeInfo> getEmpIdListByCompanyIdForClaim(@RequestParam(name = "companyId") int companyId,@RequestParam(name = "empIdList") List<GetEmployeeAuthorityWise> empIdList){
  return getempinforepo.getEmpIdListByCompanyIdForClaim(companyId,empIdList);
}


@GetMapping
("/getEmpByEmpId")
public GetEmployeeInfo getEmpByEmpId(@RequestParam(name = "empId") int empId){
  return getempinforepo.getEmpByEmpId(empId);
}


}