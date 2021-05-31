import com.ats.hrmgt.model;
import com.ats.hrmgt.repo.LeaveCashReportRepository;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class LeaveStructureApiController {

@Autowired
 private LeaveStructureDetailsRepo leaveStructureDetailsRepo;

@Autowired
 private LeaveStructureHeaderRepo leaveStructureHeaderRepo;

@Autowired
 private GetStructureAllotmentRepo getStructureAllotmentRepo;

@Autowired
 private GetLeaveAuthorityRepo getLeaveAuthorityRepo;

@Autowired
 private CalculateYearRepository calculateYearRepository;

@Autowired
 private LeaveBalanceCalRepo leaveBalanceCalRepo;

@Autowired
 private LeaveAllotmentRepository leaveAllotmentRepository;

@Autowired
 private EmployeeLeaveDetailRepo employeeLeaveDetailRepo;

@Autowired
 private GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;

@Autowired
 private GetLeaveStatusRepo getLeaveStatusRepo;

@Autowired
 private LeaveCashReportRepository leaveCashReportRepository;

@Autowired
 private LeavesAllotmentRepo leavesAllotmentRepo;


@RequestMapping(value = { "/getLeaveApplyDetailsByLeaveId" }, method = RequestMethod.POST)
@ResponseBody
public GetLeaveApplyAuthwise getLeaveApplyDetailsByLeaveId(int leaveId){
    GetLeaveApplyAuthwise list = new GetLeaveApplyAuthwise();
    try {
        list = getLeaveApplyAuthwiseRepo.getLeaveApplyDetails(leaveId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getPaidListOfleaveCash" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveCashReport> getPaidListOfleaveCash(int yearId){
    List<LeaveCashReport> list = new ArrayList<>();
    try {
        list = leaveCashReportRepository.getPaidListOfleaveCash(yearId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getStructureAllotmentList" }, method = RequestMethod.GET)
@ResponseBody
public List<GetStructureAllotment> getStructureAllotmentList(){
    List<GetStructureAllotment> list = new ArrayList<GetStructureAllotment>();
    try {
        CalenderYear calYear = new CalenderYear();
        calYear = calculateYearRepository.findByIsCurrent(1);
        list = getStructureAllotmentRepo.getStructureAllotment(calYear.getCalYrId());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveLeaveStruture" }, method = RequestMethod.POST)
@ResponseBody
public LeaveStructureHeader saveLeaveStruture(LeaveStructureHeader leaveStructureHeader){
    LeaveStructureHeader leaveHeader = new LeaveStructureHeader();
    try {
        leaveHeader = leaveStructureHeaderRepo.save(leaveStructureHeader);
        for (int i = 0; i < leaveStructureHeader.getDetailList().size(); i++) {
            leaveStructureHeader.getDetailList().get(i).setLvsId(leaveStructureHeader.getLvsId());
        }
        List<LeaveStructureDetails> docTermDetailsList = leaveStructureDetailsRepo.saveAll(leaveStructureHeader.getDetailList());
        leaveHeader.setDetailList(docTermDetailsList);
        if (leaveHeader != null) {
            leaveHeader.setError(false);
        } else {
            leaveHeader = new LeaveStructureHeader();
            leaveHeader.setError(true);
        }
    } catch (Exception e) {
        leaveHeader = new LeaveStructureHeader();
        leaveHeader.setError(true);
        e.printStackTrace();
    }
    return leaveHeader;
}


@RequestMapping(value = { "/saveLeaveBalanceCal" }, method = RequestMethod.POST)
@ResponseBody
public LeaveBalanceCal saveLeaveBalanceCal(LeaveBalanceCal leaveBalanceCal){
    LeaveBalanceCal save = new LeaveBalanceCal();
    try {
        save = leaveBalanceCalRepo.saveAndFlush(leaveBalanceCal);
        if (save != null) {
            save.setError(false);
        } else {
            save = new LeaveBalanceCal();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new LeaveBalanceCal();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getLeaveAllotmentByKey" }, method = RequestMethod.POST)
@ResponseBody
public LeavesAllotment getLeaveAllotmentByKey(int lvsaPkey){
    LeavesAllotment list = new LeavesAllotment();
    try {
        list = leaveAllotmentRepository.getLeaveAllotmentByKey(lvsaPkey);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getStructureByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public LeavesAllotment getStructureByEmpId(int empId,int currYrId){
    LeavesAllotment list = new LeavesAllotment();
    try {
        list = leavesAllotmentRepo.findByDelStatusAndEmpIdAndCalYrId(1, empId, currYrId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updateOpning" }, method = RequestMethod.POST)
@ResponseBody
public List<OpbalAndId> updateOpning(List<OpbalAndId> list){
    try {
        for (int i = 0; i < list.size(); i++) {
            int update = leaveBalanceCalRepo.updateOp(list.get(i).getBalId(), list.get(i).getOpBal());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveNewLeaveAllotment" }, method = RequestMethod.POST)
@ResponseBody
public LeavesAllotment saveNewLeaveAllotmentWith(LeavesAllotment leavesAllotment){
    LeavesAllotment save = new LeavesAllotment();
    try {
        save = leaveAllotmentRepository.saveAndFlush(leavesAllotment);
        if (save != null) {
            save.setError(false);
        } else {
            save = new LeavesAllotment();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new LeavesAllotment();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/saveNewLeaveAllotmentAll" }, method = RequestMethod.POST)
@ResponseBody
public List<LeavesAllotment> saveNewLeaveAllotmentAll(List<LeavesAllotment> leavesAllotment){
    List<LeavesAllotment> save = new ArrayList<>();
    try {
        save = leaveAllotmentRepository.saveAll(leavesAllotment);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/updateIsPaidIncash" }, method = RequestMethod.POST)
@ResponseBody
public Info updateIsPaidIncash(int yearId,List<Integer> empId,String date){
    Info info = new Info();
    try {
        int update = leaveBalanceCalRepo.updateIsPaidIncash(yearId, empId, date);
        info.setError(false);
        info.setMsg("update");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getLeaveAuthorityList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveAuthority> getLeaveAuthorityList(int companyId,List<Integer> locIdList){
    List<GetLeaveAuthority> list = new ArrayList<GetLeaveAuthority>();
    try {
        list = getLeaveAuthorityRepo.getLeaveAuth(locIdList);
    /*
             * for (int i = 0; i < list.size(); i++) {
             *
             * String[] reportIds = list.get(i).getRepToEmpIds().split(",");
             *
             * List<String> name = getLeaveAuthorityRepo.getEmpReportingName(reportIds);
             * list.get(i).setRePortingName(name); }
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveLeaveAllotment" }, method = RequestMethod.POST)
@ResponseBody
public LeavesAllotment saveLeaveAllotment(LeavesAllotment leavesAllotment){
    LeavesAllotment save = new LeavesAllotment();
    try {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String dateTime = dateFormat.format(now);
        save = leaveAllotmentRepository.saveAndFlush(leavesAllotment);
        if (save != null) {
            save.setError(false);
            List<LeaveStructureDetails> leaveStructureDetailsList = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(save.getLvsId(), 1);
            for (int j = 0; j < leaveStructureDetailsList.size(); j++) {
                LeaveBalanceCal leaveBalanceCal = new LeaveBalanceCal();
                leaveBalanceCal.setCalYrId(save.getCalYrId());
                leaveBalanceCal.setDelStatus(1);
                leaveBalanceCal.setEmpId(save.getEmpId());
                leaveBalanceCal.setIsActive(1);
                leaveBalanceCal.setLvAlloted(0);
                leaveBalanceCal.setLvbalId(0);
                leaveBalanceCal.setLvCarryFwd(0);
                leaveBalanceCal.setLvCarryFwdRemarks("Null");
                leaveBalanceCal.setLvEncash(0);
                leaveBalanceCal.setOpBal(0);
                leaveBalanceCal.setMakerUserId(1);
                leaveBalanceCal.setMakerEnterDatetime(dateTime);
                leaveBalanceCal.setLvEncashRemarks("Null");
                leaveBalanceCal.setLvTypeId(leaveStructureDetailsList.get(j).getLvTypeId());
                LeaveBalanceCal leaveBalanccRes = leaveBalanceCalRepo.saveAndFlush(leaveBalanceCal);
            }
        } else {
            save = new LeavesAllotment();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new LeavesAllotment();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "getLeaveAllotmentByCurrentCalender" }, method = RequestMethod.GET)
@ResponseBody
public List<LeavesAllotment> getLeaveAllotmentByCurrentCalender(){
    List<LeavesAllotment> leavesAllotment = new ArrayList<>();
    try {
        CalenderYear calYear = new CalenderYear();
        calYear = calculateYearRepository.findByIsCurrent(1);
        leavesAllotment = leaveAllotmentRepository.findByCalYrId(calYear.getCalYrId());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leavesAllotment;
}


@RequestMapping(value = { "/getStructureList" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveStructureHeader> getStructureList(int companyId){
    List<LeaveStructureHeader> list = new ArrayList<LeaveStructureHeader>();
    try {
        list = leaveStructureHeaderRepo.findByDelStatusAndCompanyIdOrderByLvsIdDesc(1, companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteLeaveStructure" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLeaveStructure(int lvsId){
    Info info = new Info();
    try {
        List<LeavesAllotment> lvsList = leavesAllotmentRepo.findByLvsIdAndDelStatus(lvsId, 1);
        if (lvsList.size() <= 0) {
            int delete = leaveStructureHeaderRepo.deleteLeaveStructure(lvsId);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Leave Structure Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Leave Structure");
            }
        } else {
            info.setError(true);
            info.setMsg("Leave Structure Can't be Deleted as it is Asigned to Employee");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Leave Structure");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getPendingListOfleaveCashLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveCashReport> getPendingListOfleaveCashLocId(int yearId,List<Integer> locId){
    List<LeaveCashReport> list = new ArrayList<>();
    try {
        list = leaveCashReportRepository.getPendingListOfleaveCashLocId(yearId, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoListByTrailEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveStatus> getEmpInfoListByTrailEmpId(int leaveId){
    List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
    try {
        leaveStatus = getLeaveStatusRepo.getEmpInfoByLeaveId(leaveId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveStatus;
}


@RequestMapping(value = { "/updateLeaveStructure" }, method = RequestMethod.POST)
@ResponseBody
public Info updateLeaveStructure(int lvsaPkey,int lvsId,int empId,int yearId){
    Info info = new Info();
    try {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String dateTime = dateFormat.format(now);
        int update = leaveAllotmentRepository.updateLeaveStructureBylvsaPkey(lvsId, lvsaPkey);
        List<LeaveStructureDetails> leaveStructureDetailsList = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId, 1);
        List<LeaveBalanceCal> balList = leaveBalanceCalRepo.findByCalYrIdAndEmpId(yearId, empId);
        for (int i = 0; i < leaveStructureDetailsList.size(); i++) {
            int flag = 0;
            for (int j = 0; j < balList.size(); j++) {
                if (balList.get(j).getLvTypeId() == leaveStructureDetailsList.get(i).getLvTypeId()) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                LeaveBalanceCal leaveBalanceCal = new LeaveBalanceCal();
                leaveBalanceCal.setCalYrId(yearId);
                leaveBalanceCal.setDelStatus(1);
                leaveBalanceCal.setEmpId(empId);
                leaveBalanceCal.setIsActive(1);
                leaveBalanceCal.setLvAlloted(0);
                leaveBalanceCal.setLvbalId(0);
                leaveBalanceCal.setLvCarryFwd(0);
                leaveBalanceCal.setLvCarryFwdRemarks("Null");
                leaveBalanceCal.setLvEncash(0);
                leaveBalanceCal.setOpBal(0);
                leaveBalanceCal.setMakerUserId(1);
                leaveBalanceCal.setMakerEnterDatetime(dateTime);
                leaveBalanceCal.setLvEncashRemarks("Null");
                leaveBalanceCal.setLvTypeId(leaveStructureDetailsList.get(i).getLvTypeId());
                balList.add(leaveBalanceCal);
            }
        }
        List<LeaveBalanceCal> leaveBalanccRes = leaveBalanceCalRepo.saveAll(balList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getStructureAllotmentListLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetStructureAllotment> getStructureAllotmentListLocId(List<Integer> locId){
    List<GetStructureAllotment> list = new ArrayList<GetStructureAllotment>();
    try {
        CalenderYear calYear = new CalenderYear();
        calYear = calculateYearRepository.findByIsCurrent(1);
        list = getStructureAllotmentRepo.getStructureAllotmentListLocId(calYear.getCalYrId(), locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "getLeaveListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeeLeaveDetail> getLeaveListByEmpId(int empId){
    List<EmployeeLeaveDetail> employeeInfo = new ArrayList<EmployeeLeaveDetail>();
    try {
        employeeInfo = employeeLeaveDetailRepo.getLeaveListByEmp(empId);
        System.out.println("info" + employeeInfo);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employeeInfo;
}


@RequestMapping(value = { "/getPendingListOfleaveCash" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveCashReport> getPendingListOfleaveCash(int yearId){
    List<LeaveCashReport> list = new ArrayList<>();
    try {
        list = leaveCashReportRepository.getPendingListOfleaveCash(yearId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getStatusForFreezeMonth" }, method = RequestMethod.POST)
@ResponseBody
public Info getStatusForFreezeMonth(int empId,String fromDate,String toDate){
    Info info = new Info();
    try {
        int count = getLeaveApplyAuthwiseRepo.getStatusForFreezeMonth(empId, fromDate, toDate);
        info.setMsg(String.valueOf(count));
        info.setError(false);
    } catch (Exception e) {
        info.setMsg("0");
        info.setError(true);
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getStructureById" }, method = RequestMethod.POST)
@ResponseBody
public LeaveStructureHeader getStructureById(int lvsId){
    LeaveStructureHeader lvs = new LeaveStructureHeader();
    try {
        lvs = leaveStructureHeaderRepo.findByLvsIdAndDelStatus(lvsId, 1);
        List<LeaveStructureDetails> detailList = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId, 1);
        lvs.setDetailList(detailList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lvs;
}


@RequestMapping(value = { "/getPaidListOfleaveCashLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveCashReport> getPaidListOfleaveCashLocId(int yearId,List<Integer> locId){
    List<LeaveCashReport> list = new ArrayList<>();
    try {
        list = leaveCashReportRepository.getPaidListOfleaveCashLocId(yearId, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}