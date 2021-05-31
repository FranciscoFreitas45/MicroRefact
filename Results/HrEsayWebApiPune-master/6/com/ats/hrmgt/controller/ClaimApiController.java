import com.ats.hrmgt.claim.repository;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.claim;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.GetLeaveAuthorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class ClaimApiController {

@Autowired
 private ClaimRepository claimRepository;

@Autowired
 private ClaimAuthorityRepo claimAuthorityRepo;

@Autowired
 private GetClaimAuthorityRepo getClaimAuthorityRepo;

@Autowired
 private GetClaimApplyAuthRepo getClaimApplyAuthRepo;

@Autowired
 private GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;

@Autowired
 private GetAuthorityIdsRepo getAuthorityIdsRepo;

@Autowired
 private ClaimProofRepo claimProofRepo;

@Autowired
 private GetLeaveAuthorityRepo getLeaveAuthorityRepo;


@RequestMapping(value = { "/getClaimApplyListForAuth" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimApplyAuthWise> getClaimApplyListForAuth(int empId,List<Integer> statusList,int authTypeId){
    List<GetClaimApplyAuthWise> list = new ArrayList<GetClaimApplyAuthWise>();
    List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();
    if (authTypeId == 1) {
        empIdList = getEmployeeAuthorityWise.getClaimIdListForInitialAuth(empId);
        System.err.println("empIdList" + empIdList.size());
    } else {
        empIdList = getEmployeeAuthorityWise.getClaimIdListForFinalAuth(empId);
        System.err.println("empIdList" + empIdList.size());
    }
    try {
        list = getClaimApplyAuthRepo.getClaimApplyList(empIdList, statusList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveClaimAuthority" }, method = RequestMethod.POST)
@ResponseBody
public ClaimAuthority saveClaimAuthority(ClaimAuthority claimAuthority){
    ClaimAuthority save = new ClaimAuthority();
    try {
        save = claimAuthorityRepo.saveAndFlush(claimAuthority);
        if (save != null) {
            save.setError(false);
        } else {
            save = new ClaimAuthority();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new ClaimAuthority();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getClaimAuthorityListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public ClaimAuthority getLeaveAuthorityListByEmpId(int empId){
    ClaimAuthority list = new ClaimAuthority();
    try {
        list = claimAuthorityRepo.findByDelStatusAndEmpId(1, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteClaimType" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteClaimType(int claimTypeId){
    Info info = new Info();
    try {
        int delete = claimRepository.deleteClaimType(claimTypeId);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("deleted");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/deleteClaimProof" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteClaimProof(int cpId){
    Info info = new Info();
    try {
        int delete = claimProofRepo.deleteClaimProof(cpId);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("deleted");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/saveClaimType" }, method = RequestMethod.POST)
@ResponseBody
public ClaimType saveClaimType(ClaimType claimType){
    ClaimType save = new ClaimType();
    try {
        save = claimRepository.saveAndFlush(claimType);
        if (save != null) {
            save.setError(false);
        } else {
            save = new ClaimType();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new ClaimType();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getClaimListByCompanyId" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimType> getClaimListByCompanyId(int companyId){
    List<ClaimType> list = new ArrayList<ClaimType>();
    try {
        list = claimRepository.findByDelStatusAndCompanyIdOrderByCompanyIdDesc(1, companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getClaimList" }, method = RequestMethod.GET)
@ResponseBody
public List<ClaimType> getClaimList(){
    List<ClaimType> list = new ArrayList<ClaimType>();
    try {
        list = claimRepository.findByDelStatusOrderByClaimTypeIdDesc(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getClaimById" }, method = RequestMethod.POST)
@ResponseBody
public ClaimType getClaimById(int claimTypeId){
    ClaimType claimType = new ClaimType();
    try {
        claimType = claimRepository.findByClaimTypeIdAndDelStatus(claimTypeId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return claimType;
}


@RequestMapping(value = { "/getClaimAuthorityList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimAuthority> getClaimAuthorityList(int companyId,List<Integer> locIdList){
    List<GetClaimAuthority> list = new ArrayList<GetClaimAuthority>();
    try {
        list = getClaimAuthorityRepo.getClaimAuth(companyId, locIdList);
        for (int i = 0; i < list.size(); i++) {
            String[] reportIds = list.get(i).getCaRepToEmpIds().split(",");
            // System.err.println("rep to ids  "+reportIds.toString());
            List<String> name = getLeaveAuthorityRepo.getEmpReportingName(reportIds);
            list.get(i).setRePortingName(name);
        // System.err.println("rep to "+name.toString());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveSingleClaimProof" }, method = RequestMethod.POST)
@ResponseBody
public ClaimProof saveSingleClaimProof(ClaimProof claimProof){
    System.err.println("res1 claim is");
    ClaimProof save = new ClaimProof();
    try {
        save = claimProofRepo.saveAndFlush(claimProof);
        if (save != null) {
            save.setError(false);
        } else {
            save = new ClaimProof();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new ClaimProof();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/saveClaimProof" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimProof> saveClaimProof(List<ClaimProof> claimProof){
    System.err.println("res1 claim is");
    List<ClaimProof> save = new ArrayList<ClaimProof>();
    try {
        save = claimProofRepo.saveAll(claimProof);
    /*
             * if (save != null) { save.setError(false); } else {
             *
             * save = new ClaimProof(); save.setError(true); }
             */
    } catch (Exception e) {
        /*
             * save = new ClaimProof(); save.setError(true);
             */
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getClaimProofByClaimId" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimProof> getClaimProofByClaimId(int claimId){
    List<ClaimProof> list = new ArrayList<ClaimProof>();
    try {
        list = claimProofRepo.findByClaimIdAndDelStatus(claimId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}