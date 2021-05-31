import com.ats.hrmgt.claim.repository.ClaimStructureAllotmentRepo;
import com.ats.hrmgt.claim.repository.ClaimStructureDetailRepo;
import com.ats.hrmgt.claim.repository.ClaimStructureHeaderRepo;
import com.ats.hrmgt.claim.repository.GetClaimStructureAllotmentRepo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.claim.ClaimStructureAllotment;
import com.ats.hrmgt.model.claim.ClaimStructureDetail;
import com.ats.hrmgt.model.claim.ClaimStructureHeader;
import com.ats.hrmgt.model.claim.GetClaimStructureAllotment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class ClaimStructureAndAllotApiController {

@Autowired
 private ClaimStructureDetailRepo claimStructureDetailRepo;

@Autowired
 private ClaimStructureHeaderRepo claimStructureHeaderRepo;

@Autowired
 private GetClaimStructureAllotmentRepo getClaimStructureAllotment;

@Autowired
 private ClaimStructureAllotmentRepo claimStructureAllotmentRepo;


@RequestMapping(value = { "/getClaimStructDetailList" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimStructureDetail> getClaimStructDetailList(int headId){
    Setting setting = new Setting();
    List<ClaimStructureDetail> list = new ArrayList<ClaimStructureDetail>();
    try {
        list = claimStructureDetailRepo.findByClmStructHeadIdAndDelStatus(headId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteClaimStructure" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLeaveStructure(int clmsId){
    Info info = new Info();
    try {
        int delete = claimStructureHeaderRepo.deleteClaimStructure(clmsId);
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


@RequestMapping(value = { "/getClaimStructureAllotmentList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimStructureAllotment> getStructureAllotmentList(int companyId,List<Integer> locIdList){
    List<GetClaimStructureAllotment> list = new ArrayList<GetClaimStructureAllotment>();
    try {
        list = getClaimStructureAllotment.getStructureAllotment(companyId, locIdList);
        System.out.println(list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveClaimStruture" }, method = RequestMethod.POST)
@ResponseBody
public ClaimStructureHeader saveClaimStruture(ClaimStructureHeader clStructureHeader){
    ClaimStructureHeader clmHeader = new ClaimStructureHeader();
    try {
        clmHeader = claimStructureHeaderRepo.save(clStructureHeader);
        for (int i = 0; i < clStructureHeader.getDetailList().size(); i++) {
            clStructureHeader.getDetailList().get(i).setClmStructHeadId(clStructureHeader.getClmStructHeadId());
        }
        List<ClaimStructureDetail> docTermDetailsList = claimStructureDetailRepo.saveAll(clStructureHeader.getDetailList());
        clmHeader.setDetailList(docTermDetailsList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return clmHeader;
}


@RequestMapping(value = { "/checkStructAlloted" }, method = RequestMethod.POST)
@ResponseBody
public ClaimStructureAllotment checkStructAlloted(int empId){
    ClaimStructureAllotment info = new ClaimStructureAllotment();
    try {
        info = claimStructureAllotmentRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/saveAllotmet" }, method = RequestMethod.POST)
@ResponseBody
public Info saveAllotmet(List<Integer> empList,int userId,String dateTime,int lvsId){
    Info info = new Info();
    try {
        for (int i = 0; i < empList.size(); i++) {
            ClaimStructureAllotment res = new ClaimStructureAllotment();
            System.err.println("empId" + empList.get(i));
            res = claimStructureAllotmentRepo.findByEmpIdAndDelStatus(empList.get(i), 1);
            if (res != null) {
                System.err.println("empId" + res.getClmsId());
                System.err.println("exists");
                int a = claimStructureAllotmentRepo.updateClaimStructure(empList.get(i), userId, dateTime, lvsId);
            } else {
                System.err.println("no");
                ClaimStructureAllotment save = new ClaimStructureAllotment();
                ClaimStructureAllotment temp = new ClaimStructureAllotment();
                temp.setDelStatus(1);
                temp.setEmpId(empList.get(i));
                temp.setExVar1("NA");
                temp.setExVar2("NA");
                temp.setExVar3("NA");
                temp.setIsActive(1);
                temp.setMakerUserId(userId);
                temp.setMakerEnterDatetime(dateTime);
                temp.setClmsId(lvsId);
                save = claimStructureAllotmentRepo.saveAndFlush(temp);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/saveClaimStructAllotment" }, method = RequestMethod.POST)
@ResponseBody
public ClaimStructureAllotment saveClaimStructAllotment(ClaimStructureAllotment leavesAllotment){
    ClaimStructureAllotment save = new ClaimStructureAllotment();
    try {
        save = claimStructureAllotmentRepo.saveAndFlush(leavesAllotment);
    } catch (Exception e) {
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getClaimStructHeadList" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimStructureHeader> getStructureList(int companyId){
    List<ClaimStructureHeader> list = new ArrayList<ClaimStructureHeader>();
    try {
        list = claimStructureHeaderRepo.findByDelStatusAndCompanyId(1, companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getClaimStructureById" }, method = RequestMethod.POST)
@ResponseBody
public ClaimStructureHeader getStructureById(int headId){
    ClaimStructureHeader clmss = new ClaimStructureHeader();
    try {
        clmss = claimStructureHeaderRepo.findByClmStructHeadIdAndDelStatus(headId, 1);
        List<ClaimStructureDetail> detailList = claimStructureDetailRepo.findByClmStructHeadIdAndDelStatus(headId, 1);
        clmss.setDetailList(detailList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return clmss;
}


}