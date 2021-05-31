import com.ats.hrmgt.model;
import com.ats.hrmgt.repo.CountOfAssignPendingRepository;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class EmpShiftAssignApiController {

@Autowired
 private GetEmployeeDetailsRepo getEmployeeDetailsRepo;

@Autowired
 private CountOfAssignPendingRepository countOfAssignPendingRepository;

@Autowired
 private GetEmployeeDetailsForCarryFrwdLeaveRepo getEmployeeDetailsForCarryFrwdLeaveRepo;

@Autowired
 private ShiftMasterRepository shiftMasterRepository;

@Autowired
 private EmployeeMasterRepository employeeMasterRepository;

@Autowired
 private SalaryTypesMasterRepo salaryTypesMasterRepo;

@Autowired
 private EmpSalaryInfoRepo empSalaryInfoRepo;

@Autowired
 private UserRepo userRepo;

@Autowired
 private MstEmpTypeRepository mstEmpTypeRepository;

@Autowired
 private EmployeeMasterRepository empRepo;


@RequestMapping(value = { "/salStructAssignmentUpdate" }, method = RequestMethod.POST)
@ResponseBody
public Info salStructAssignmentUpdate(List<Integer> empIdList,String structId){
    Info info = new Info();
    try {
        int res = 0;
        res = empSalaryInfoRepo.assignsalStruct(empIdList, structId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("success");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        System.err.println("Exce in deleteService  " + e.getMessage());
        e.printStackTrace();
        info.setError(true);
        info.setMsg("excep");
    }
    return info;
}


@RequestMapping(value = { "/deleteLMstEmpType" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLMstEmpType(int empTypeId){
    Info info = new Info();
    try {
        List<EmployeeMaster> empList = empRepo.findByEmpTypeAndDelStatus(empTypeId, 1);
        if (empList.size() <= 0) {
            int delete = mstEmpTypeRepository.deleteMstType(empTypeId);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Employee Type Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed to Delete Employee Type");
            }
        } else {
            info.setError(true);
            info.setMsg("Employee Type Can't Be Deleted as it is Assigned to Employee");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("Failed to Delete Employee Type");
    }
    return info;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowAssignEmpTypeLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignEmpTypeLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignEmpTypeLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowEmpListToAssignSalStruct" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignSalStruct(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowEmpListToAssignSalStruct();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailshowEmpListToAssignSalStruct : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailAccesssRoleLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailAccesssRole(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailAccesssRoleLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignHolidayCategory" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailassignHolidayCategory(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignHolidayCategory();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignHolidayCategory : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpDetailByEmpIds" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getEmpDetailByEmpIds(List<String> empIds){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailListByCsEmpIds(empIds);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getSalryTypesMst" }, method = RequestMethod.GET)
@ResponseBody
public List<SalaryTypesMaster> getSalryTypesMst(){
    List<SalaryTypesMaster> list = new ArrayList<SalaryTypesMaster>();
    try {
        list = salaryTypesMasterRepo.findAllByDelStatus(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getMstEmpTypeList" }, method = RequestMethod.POST)
@ResponseBody
public List<MstEmpType> getLeaveTypeListIsStructure(int companyId){
    List<MstEmpType> list = new ArrayList<MstEmpType>();
    try {
        list = mstEmpTypeRepository.findByDelStatusAndCompanyIdOrderByEmpTypeIdDesc(1, companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailSkillRate" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailSkillRate(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        System.err.println("skill");
        list = getEmployeeDetailsRepo.getEmpDetailListForSkillRate();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveMstEmpType" }, method = RequestMethod.POST)
@ResponseBody
public MstEmpType saveLeaveType(MstEmpType leaveType){
    MstEmpType save = new MstEmpType();
    try {
        save = mstEmpTypeRepository.saveAndFlush(leaveType);
        if (save == null) {
            save = new MstEmpType();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/weekoffCatAssignmentUpdate" }, method = RequestMethod.POST)
@ResponseBody
public Info weekoffCatAssignmentUpdate(List<Integer> empIdList,String holiCatId){
    Info info = new Info();
    try {
        System.err.println("hii");
        int res = 0;
        res = employeeMasterRepository.weekHoliCat(empIdList, holiCatId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("success");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        System.err.println("Exce in deleteService  " + e.getMessage());
        e.printStackTrace();
        info.setError(true);
        info.setMsg("excep");
    }
    return info;
}


@RequestMapping(value = { "/assignEmpLoginType" }, method = RequestMethod.POST)
@ResponseBody
public Info assignEmpLoginType(List<Integer> empIdList,String loginType){
    Info info = new Info();
    try {
        int res = 0;
        res = userRepo.updateUserLoginType(empIdList, loginType);
        if (res > 0) {
            info.setError(false);
            info.setMsg("success");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        info = new Info();
        info.setError(true);
        info.setMsg("exception");
    }
    return info;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignSubCompanyLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailassignSubCompanyLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignSubCompanyLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailForCarryForwordLeave" }, method = RequestMethod.POST)
public List<GetEmployeeDetailsForCarryFrwdLeave> getAllEmployeeDetailForCarryForwordLeave(int locId){
    List<GetEmployeeDetailsForCarryFrwdLeave> list = new ArrayList<>();
    try {
        list = getEmployeeDetailsForCarryFrwdLeaveRepo.getEmpDetailListForCarryForwardLeave(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignSubCompany" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailassignSubCompany(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignSubCompany();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowAssignDesignation" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignDesignation(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignDesignation();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignWeekoffCategoryLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailassignWeekoffCategoryLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignWeekoffCategoryLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailBylocationId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailBylocationId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailBylocationId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getMstEmpTypeById" }, method = RequestMethod.POST)
@ResponseBody
public MstEmpType getLeaveTypeList(int empTypeId){
    MstEmpType list = new MstEmpType();
    try {
        list = mstEmpTypeRepository.findByDelStatusAndEmpTypeId(1, empTypeId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowAssignDesignationLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignDesignationLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignDesignationLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpsForLoanOrGuarantor" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getEmpsForLoanOrGuarantor(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpsForLoanOrGuarantor();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailForLoginTypeLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailForLoginTypeLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailForLoginTypeLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignHolidayCategoryLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailassignHolidayCategoryLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignHolidayCategoryLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getDetailForCarryForwordLeaveByEmpId" }, method = RequestMethod.POST)
public GetEmployeeDetailsForCarryFrwdLeave getDetailForCarryForwordLeaveByEmpId(int empId,int currYrId){
    GetEmployeeDetailsForCarryFrwdLeave res = new GetEmployeeDetailsForCarryFrwdLeave();
    try {
        res = getEmployeeDetailsForCarryFrwdLeaveRepo.getDetailForCarryForwordLeaveByEmpId(empId, currYrId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return res;
}


@RequestMapping(value = { "/getAllEmployeeDetailAccesibleLocBylocationId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailAccesibleLocBylocationId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailAccesibleLocBylocationId(locId);
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailShiftGroup" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailShiftGroup(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        System.err.println("skill");
        list = getEmployeeDetailsRepo.getAllEmployeeDetailShiftGroup();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignDept" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailassignDept(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignDept();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignDept : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getCountOfAssignForAttendance" }, method = RequestMethod.POST)
@ResponseBody
public CountOfAssignPending getCountOfAssignForAttendance(int locId){
    CountOfAssignPending count = new CountOfAssignPending();
    try {
        count = countOfAssignPendingRepository.getCountOfAssignForAttendance(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
}


@RequestMapping(value = { "/getAllEmployeeDetailForFullnFinalLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailForFullnFinalLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailForFullnFinalLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailSkillRateLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailSkillRateLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        System.err.println("skill");
        list = getEmployeeDetailsRepo.getAllEmployeeDetailSkillRateLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpDetailListforassignshiftbulk" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getEmpDetailListforassignshiftbulk(String date){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailListforassignshiftbulk(date);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowAssignLocation" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignLocation(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignLocation();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailshowAssignLocation : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getSalStructCountEmp" }, method = RequestMethod.POST)
@ResponseBody
public Integer getSalStructCountEmp(){
    List<EmployeeMaster> list = new ArrayList<>();
    int a = 0;
    try {
        list = employeeMasterRepository.getEmpSalAssign();
        a = list.size();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return a;
}


@RequestMapping(value = { "/getAllEmployeeDetailAccesibleLoc" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailAccesibleLoc(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailListForAccessibleLoc();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailForLoginType" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailForLoginType(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailForLoginType();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/empParamAssignmentUpdate" }, method = RequestMethod.POST)
@ResponseBody
public Info empParamAssignmentUpdate(List<Integer> empIdList,String upDateId,int flag){
    Info info = new Info();
    try {
        int res = 0;
        if (flag == 1) {
            res = employeeMasterRepository.assignHoliCat(empIdList, upDateId);
        } else if (flag == 2) {
            res = employeeMasterRepository.assignComapny(empIdList, upDateId);
        } else if (flag == 3) {
            res = employeeMasterRepository.assignDept(empIdList, upDateId);
        } else if (flag == 4) {
            res = employeeMasterRepository.assignDesignation(empIdList, upDateId);
        } else if (flag == 5) {
            res = employeeMasterRepository.assignEmpType(empIdList, upDateId);
        } else if (flag == 6) {
            res = employeeMasterRepository.assignLocation(empIdList, upDateId);
        } else if (flag == 7) {
            res = employeeMasterRepository.assignShift(empIdList, upDateId);
        } else if (flag == 8) {
            res = employeeMasterRepository.weekHoliCat(empIdList, upDateId);
        } else if (flag == 9) {
            res = userRepo.updateAccLoc(empIdList, upDateId);
        } else if (flag == 10) {
            res = employeeMasterRepository.empSkillUpdate(empIdList, upDateId);
        } else if (flag == 11) {
            res = employeeMasterRepository.empEmpCategoryUpdate(empIdList, upDateId);
        } else if (flag == 12) {
            res = employeeMasterRepository.empEmpShiftGroupUpdate(empIdList, upDateId);
        } else {
            res = 0;
        }
        if (res > 0) {
            info.setError(false);
            info.setMsg("success");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        System.err.println("Exce in deleteService  " + e.getMessage());
        e.printStackTrace();
        info.setError(true);
        info.setMsg("excep");
    }
    return info;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowEmpListToAssignSalStructLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignSalStructLocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowEmpListToAssignSalStructLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowEmpListToAssignShift" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignShift(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowEmpListToAssignShift();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllShiftList" }, method = RequestMethod.GET)
public List<ShiftMaster> getAllShiftList(){
    List<ShiftMaster> list = new ArrayList<ShiftMaster>();
    try {
        list = shiftMasterRepository.findAll();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignDeptlocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailassignDeptlocId(List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignDeptlocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpDetailListByLocId" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getEmpDetailListByLocId(List<Integer> locationIds){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailListByLocId(locationIds);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetail" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetail(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailList();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailForFullnFinal" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailForFullnFinal(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailForFullnFinal();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailshowAssignEmpType" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignEmpType(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignEmpType();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailByEmpId" }, method = RequestMethod.POST)
public GetEmployeeDetails getAllEmployeeDetailByEmpId(int empId){
    GetEmployeeDetails list = new GetEmployeeDetails();
    try {
        list = getEmployeeDetailsRepo.getEmpDetailList(empId);
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailByEmpId : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployeeDetailassignWeekoffCategory" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllEmployeeDetailassignWeekoffCategory(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAllEmployeeDetailassignWeekoffCategory();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetailassignWeekoffCategory : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


}