import com.ats.hrmgt.claim.repository.ClaimApplyRepo;
import com.ats.hrmgt.claim.repository.ClaimHeaderRepo;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetAuthorityIds;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.claim.ClaimApply;
import com.ats.hrmgt.model.claim.ClaimApplyHeader;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.SettingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util;
@RestController
public class ClaimHeaderDetailApiController {

@Autowired
 private EmployeeMasterRepository employeeInfoRepository;

@Autowired
 private GetAuthorityIdsRepo getAuthorityIdsRepo;

@Autowired
 private ClaimHeaderRepo claimHeaderRepo;

@Autowired
 private ClaimApplyRepo claimApplyRepo;

@Autowired
 private SettingRepo settingRepo;


@RequestMapping(value = { "/updateClaimTrailId" }, method = RequestMethod.POST)
@ResponseBody
public Info updateTrailId(int claimId,int trailId){
    Info info = new Info();
    try {
        int delete = claimHeaderRepo.updateTrailApply(claimId, trailId);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("updated status");
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


@RequestMapping(value = { "/saveClaimHeaderAndDetail" }, method = RequestMethod.POST)
@ResponseBody
public ClaimApplyHeader saveClaimHeaderAndDetail(ClaimApplyHeader claimHead){
    Info errorMessage = new Info();
    ClaimApplyHeader claimHeader = new ClaimApplyHeader();
    try {
        claimHeader = claimHeaderRepo.save(claimHead);
        for (int i = 0; i < claimHead.getDetailList().size(); i++) {
            claimHead.getDetailList().get(i).setExInt2(claimHeader.getCaHeadId());
        }
        List<ClaimApply> claimDetailsList = claimApplyRepo.saveAll(claimHead.getDetailList());
        claimHeader.setDetailList(claimDetailsList);
        if (claimDetailsList == null) {
            claimHeader = new ClaimApplyHeader();
            errorMessage.setError(true);
        } else {
            errorMessage.setError(false);
            int empId = claimHeader.getEmpId();
            EmployeeMaster empInfo1 = new EmployeeMaster();
            empInfo1 = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);
            String name = empInfo1.getFirstName() + " " + empInfo1.getSurname();
            GetAuthorityIds claimApply = new GetAuthorityIds();
            claimApply = getAuthorityIdsRepo.getClaimAuthIdsDict(empId);
            String empIds = claimApply.getRepToEmpIds();
            String[] values = empIds.split(",");
            System.err.println("emp ids for notification are::" + empIds);
            List<String> al = new ArrayList<String>(Arrays.asList(values));
            Set<String> set = new HashSet<>(al);
            al.clear();
            al.addAll(set);
            // System.err.println("emp ids for notification are:--------------:" +
            // al.toString());
            for (int i = 0; i < al.size(); i++) {
                EmployeeMaster empInfo = new EmployeeMaster();
                empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)), 1);
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                String claimDateFrom = "";
                String claimDateTo = "";
                try {
                    Date d1 = sdf1.parse(claimHeader.getCafromDt());
                    claimDateFrom = sdf2.format(d1.getTime());
                    Date d2 = sdf1.parse(claimHeader.getCaToDt());
                    claimDateTo = sdf2.format(d1.getTime());
                } catch (Exception e) {
                    claimDateFrom = claimHeader.getCafromDt();
                    claimDateTo = claimHeader.getCaToDt();
                    e.printStackTrace();
                }
            /*
                     * try {
                     *
                     * Firebase.sendPushNotification( empInfo.getExVar1(), "HRMS", " " + name +
                     * " has applied for Claim for Rs. " + claimHeader.getClaimAmount() + " From " +
                     * claimDateFrom + "To"+claimDateTo+", Please check for Approval", 21);
                     *
                     * Info emailRes1 = EmailUtility.sendEmail("atsinfosoft@gmail.com",
                     * "atsinfosoft@123", empInfo.getEmailId(), " HRMS Claim Application Status",
                     * "", name + " has applied for Claim for Rs. " + claimHeader.getClaimAmount() +
                     * " From " + claimDateFrom + "To"+claimDateTo+", Please check for Approval"); }
                     * catch (Exception e) { e.printStackTrace(); }
                     */
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        errorMessage.setError(true);
    }
    return claimHeader;
}


@RequestMapping(value = { "/getClaimHeaderToChangeDate" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimApplyHeader> getClaimHeaderToChangeDate(List<Integer> locId){
    List<ClaimApplyHeader> list = new ArrayList<ClaimApplyHeader>();
    try {
        list = claimHeaderRepo.getClaimHeaderListByCompanyIdlocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updateClmPaidDate" }, method = RequestMethod.POST)
@ResponseBody
public Info updateClmPaidDate(String dateTimeUpdate,int userId,int clmHeadId,int month,int year){
    Info info = new Info();
    try {
        int delete = claimHeaderRepo.updatePaid(dateTimeUpdate, userId, clmHeadId, month, year);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("updated status");
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


}