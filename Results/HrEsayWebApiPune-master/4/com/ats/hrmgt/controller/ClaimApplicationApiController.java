import com.ats.hrmgt.claim.repository;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetAuthorityIds;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.claim;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.SettingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util;
@RestController
public class ClaimApplicationApiController {

@Autowired
 private GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;

@Autowired
 private GetEmpInfoRepo getEmpInfo;

@Autowired
 private GetAuthorityIdsRepo getAuthorityIdsRepo;

@Autowired
 private ClaimApplyRepo claimApplyRepository;

@Autowired
 private ClaimTrailRepo claimTrailRepository;

@Autowired
 private ClaimDetailRepo claimDetailRepo;

@Autowired
 private GetClaimTrailStatusRepo getClaimTrailStatusRepo;

@Autowired
 private GetClaimHeadRepo getClaimHeadRepo;

@Autowired
 private ClaimHeaderRepo claimHeaderRepo;

@Autowired
 private ClaimStructureDetailRepo claimStructureDetailRepo;

 static  String senderEmail;

 static  String senderPassword;

 static  String mailsubject;

@Autowired
 private GetClaimApplyAuthwiseRepo getClaimApplyAuthwiseRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private EmployeeMasterRepository employeeInfoRepository;

@Autowired
 private GetEmployeeClaimStrudtRepo getEmployeeClaimStrudtRepo;

@Autowired
 private EmployeeMasterRepository employeeMasterRepository;


@RequestMapping(value = { "/getClaimApplyListForPendingForAdmin" }, method = RequestMethod.GET)
@ResponseBody
public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForAdmin(){
    List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
    try {
        list = getClaimApplyAuthwiseRepo.getClaimApplyListForPendingForAdmin();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getClaimTrailList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimTrailStatus> getClaimTrailList(int claimId){
    List<GetClaimTrailStatus> trailList = new ArrayList<GetClaimTrailStatus>();
    try {
        trailList = getClaimTrailStatusRepo.getClaimTrailByClaimId(claimId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return trailList;
}


@RequestMapping(value = { "/getClaimApplyDetailsByClaimId" }, method = RequestMethod.POST)
@ResponseBody
public GetClaimApplyAuthwise getLeaveApplyDetailsByLeaveId(int claimId){
    GetClaimApplyAuthwise list = new GetClaimApplyAuthwise();
    System.out.println("inside getLeaveApplyDetailsByLeaveId");
    try {
        list = getClaimApplyAuthwiseRepo.getClaimApplyDetails(claimId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoAuthWise" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeInfo> getEmpInfoAuthWise(int companyId,List<Integer> locIdList,int empId){
    List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
    System.err.println("empId" + empId);
    List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();
    empIdList = getEmployeeAuthorityWise.getEmpIdList(empId);
    System.err.println("empIdList" + empIdList.toString());
    if (empIdList.size() > 0) {
        try {
            list = getEmpInfo.getEmpIdListByCompanyId(companyId, empIdList);
            System.err.println("GetEmployeeAuthorityWise::::" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoListForClaimAuth" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeInfo> getEmpInfoListForClaimAuth(int companyId,List<Integer> locIdList){
    List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
    try {
        list = getEmpInfo.getEmpListByCompanyIdForAuthClaim(companyId, locIdList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoById" }, method = RequestMethod.POST)
@ResponseBody
public EmployeeMaster getEmpInfoById(int empId){
    EmployeeMaster company = new EmployeeMaster();
    try {
        System.err.println("empIDDDD list " + empId);
        company = employeeMasterRepository.findByEmpIdAndDelStatus(empId, 1);
        System.err.println("empIDDDD det " + company.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return company;
}


@RequestMapping(value = { "/updateClaimStatus" }, method = RequestMethod.POST)
@ResponseBody
public Info updateLeaveStatus(int month,int year,int claimId,int status){
    Info info = new Info();
    System.err.println("in updateLeaveStatus" + status + claimId);
    try {
        int delete = 0;
        if (status == 3) {
            delete = claimHeaderRepo.updateClaimStatusWithDate(claimId, status, month, year);
        } else {
            delete = claimHeaderRepo.updateClaimStatus(claimId, status);
        }
        if (delete > 0) {
            info.setError(false);
            info.setMsg("updated status");
            ClaimApplyHeader leaveApply = new ClaimApplyHeader();
            leaveApply = claimHeaderRepo.findByCaHeadIdAndDelStatus(claimId, 1);
            System.err.println("ClaimApplyHeader" + leaveApply.toString());
            int empId = leaveApply.getEmpId();
            // System.err.println("empId" +empId);
            EmployeeMaster emp = new EmployeeMaster();
            emp = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);
            // System.err.println("emp details" + emp.toString());
            GetAuthorityIds claimApply = new GetAuthorityIds();
            claimApply = getAuthorityIdsRepo.getClaimAuthIdsDict(empId);
            String empIds = claimApply.getRepToEmpIds();
            String[] values = empIds.split(",");
            // System.err.println("emp ids for notification are::" + empIds);
            List<String> al = new ArrayList<String>(Arrays.asList(values));
            Set<String> set = new HashSet<>(al);
            al.clear();
            al.addAll(set);
            // System.err.println("emp ids for notification are:--------------:" +
            // al.toString());
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
            String claimDate = "";
            String claimDate1 = "";
            String claimDate2 = "";
            try {
                Date d1 = sdf1.parse(leaveApply.getCafromDt());
                claimDate1 = sdf2.format(d1.getTime());
                Date d2 = sdf1.parse(leaveApply.getCaToDt());
                claimDate2 = sdf2.format(d2.getTime());
                claimDate = claimDate1 + "To" + claimDate2;
            } catch (Exception e) {
                e.printStackTrace();
            }
            String claimMsg = "";
            try {
                if (status == 2) {
                    claimMsg = emp.getFirstName() + " " + emp.getSurname() + " your Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Approved By Initial Authority";
                // Firebase.sendPushNotification(emp.getExVar1(), "HRMS", claimMsg, 2);
                } else if (status == 3) {
                    claimMsg = emp.getFirstName() + " " + emp.getSurname() + " your Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Approved By Final Authority";
                // Firebase.sendPushNotification(emp.getExVar1(), "HRMS", claimMsg, 2);
                } else if (status == 8) {
                    claimMsg = emp.getFirstName() + " " + emp.getSurname() + " your Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Rejected By Initial Authority";
                // Firebase.sendPushNotification(emp.getExVar1(), "HRMS", claimMsg, 2);
                } else if (status == 9) {
                    claimMsg = emp.getFirstName() + " " + emp.getSurname() + " your Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Rejected By Final Authority";
                // Firebase.sendPushNotification(emp.getExVar1(), "HRMS", claimMsg, 2);
                }
            /*
                     * Info emailRes1 = EmailUtility.sendEmail("atsinfosoft@gmail.com",
                     * "atsinfosoft@123", emp.getEmailId(), " HRMS Claim Application Status", "",
                     * claimMsg);
                     */
            } catch (Exception e) {
                e.printStackTrace();
            }
            String claimMsg1 = "";
            try {
                claimMsg1 = "";
                if (status == 2) {
                    claimMsg1 = emp.getFirstName() + " " + emp.getSurname() + " Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Approved By Initial Authority";
                } else if (status == 3) {
                    claimMsg1 = emp.getFirstName() + " " + emp.getSurname() + " Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Approved By Final Authority";
                } else if (status == 8) {
                    claimMsg1 = emp.getFirstName() + " " + emp.getSurname() + " Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Rejected By Initial Authority";
                } else if (status == 9) {
                    claimMsg1 = emp.getFirstName() + " " + emp.getSurname() + " Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Rejected By Final Authority";
                } else if (status == 7) {
                    claimMsg1 = emp.getFirstName() + " " + emp.getSurname() + " Claim for Rs. " + leaveApply.getClaimAmount() + " From " + claimDate + " Cancelled";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < al.size(); i++) {
                EmployeeMaster empInfo = new EmployeeMaster();
                empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)), 1);
            /*
                     * Info emailRes1 = EmailUtility.sendEmail("atsinfosoft@gmail.com",
                     * "atsinfosoft@123", empInfo.getEmailId(), " HRMS Claim Application Status",
                     * "", claimMsg1);
                     */
            // Firebase.sendPushNotification(empInfo.getExVar1(), "HRMS", claimMsg1, 2);
            }
            Setting setting = new Setting();
            setting = settingRepo.findByKey("hremail");
            String hrEmail = (setting.getValue());
            System.out.println(hrEmail);
        /*
                 * Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com",
                 * "atsinfosoft@123", hrEmail, " HRMS Claim Application Status", "", claimMsg1);
                 */
        } else {
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/getClaimStructureDetailByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimStructureDetail> getClaimStructureDetailByEmpId(int empId){
    List<ClaimStructureDetail> detailList = new ArrayList<>();
    try {
        detailList = claimStructureDetailRepo.getClaimStructureDetailByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return detailList;
}


@RequestMapping(value = { "/getEmpInfoListForLeaveAuth" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeInfo> getEmpInfoListForLeaveAuth(int companyId,List<Integer> locIdList){
    List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
    try {
        list = getEmpInfo.getEmpListByCompanyIdForAuth(companyId, locIdList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "getClaimApplyByClaimId" }, method = RequestMethod.POST)
@ResponseBody
public ClaimApply getClaimApplyByClaimId(int claimId){
    ClaimApply leaveApply = new ClaimApply();
    try {
        leaveApply = claimApplyRepository.findByClaimIdAndDelStatus(claimId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveApply;
}


@RequestMapping(value = { "/getClaimApplyListForPending" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimApplyAuthwise> getClaimApplyListForPending(int empId){
    List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
    try {
        list = getClaimApplyAuthwiseRepo.getClaimApplyList(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getClaimApplyListForPendingForManager" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForManager(int empId){
    List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
    try {
        list = getClaimApplyAuthwiseRepo.getClaimApplyListForPendingForManager(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoClaimAuthWise" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeInfo> getEmpInfoClaimAuthWise(int companyId,int empId){
    List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
    List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();
    empIdList = getEmployeeAuthorityWise.getEmpIdListInClaimAuth(empId);
    System.err.println("empIdList" + empIdList.size());
    try {
        list = getEmpInfo.getEmpIdListByCompanyIdForClaim(companyId, empIdList);
    // System.err.println("GetEmployeeAuthorityWise::::" + list.size());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveClaimTrail" }, method = RequestMethod.POST)
@ResponseBody
public ClaimTrail saveClaimTrail(ClaimTrail claim){
    ClaimTrail save = new ClaimTrail();
    try {
        save = claimTrailRepository.saveAndFlush(claim);
        if (save == null) {
            save = new ClaimTrail();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getClaimDetailListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<ClaimDetail> getClaimDetailListByEmpId(int claimId){
    List<ClaimDetail> list = new ArrayList<ClaimDetail>();
    try {
        list = claimDetailRepo.getClaimDetailList(claimId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getClaimHeadListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimHead> getClaimHeadListByEmpId(int empId){
    List<GetClaimHead> list = new ArrayList<GetClaimHead>();
    try {
        System.err.println("empId************************" + empId);
        list = getClaimHeadRepo.getClaimHeadByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpClaimInfoListByTrailEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimTrailStatus> getEmpClaimInfoListByTrailEmpId(int claimId){
    List<GetClaimTrailStatus> leaveStatus = new ArrayList<GetClaimTrailStatus>();
    try {
        leaveStatus = getClaimTrailStatusRepo.getClaimTrailByClaimId(claimId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveStatus;
}


@RequestMapping(value = { "/GetEmployeeInfo" }, method = RequestMethod.POST)
@ResponseBody
public GetEmployeeInfo getEmployeeInfo(int empId){
    GetEmployeeInfo company = new GetEmployeeInfo();
    try {
        company = getEmpInfo.getEmpByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return company;
}


@RequestMapping(value = { "/getEmpClaimStructure" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeClaimStrudt> getEmpClaimStructure(int empId){
    List<GetEmployeeClaimStrudt> list = new ArrayList<GetEmployeeClaimStrudt>();
    try {
        list = getEmployeeClaimStrudtRepo.getClaimApplyStructList(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "getClaimAuthIds" }, method = RequestMethod.POST)
@ResponseBody
public GetAuthorityIds getClaimAuthIds(int empId,int companyId){
    System.out.println("emp id is " + empId);
    GetAuthorityIds leaveApply = new GetAuthorityIds();
    try {
        leaveApply = getAuthorityIdsRepo.getClaimAuthIds(empId, companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveApply;
}


@RequestMapping(value = { "/getClaimApplyListForInformation" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimApplyAuthwise> getLeaveApplyListForInformation(int empId){
    List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
    try {
        list = getClaimApplyAuthwiseRepo.getClaimApplyList2(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpClaimStructureByClaimType" }, method = RequestMethod.POST)
@ResponseBody
public GetEmployeeClaimStrudt getEmpClaimStructureByClaimType(int empId,int typeId){
    GetEmployeeClaimStrudt list = new GetEmployeeClaimStrudt();
    try {
        list = getEmployeeClaimStrudtRepo.getClaimApplyStructListUnique(empId, typeId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}