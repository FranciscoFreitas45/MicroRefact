import com.ats.hrmgt.claim.repository.GetEmpInfoRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model;
import com.ats.hrmgt.model.claim.GetEmployeeInfo;
import com.ats.hrmgt.repo.SkillRatesRepo;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class MasterApiController {

@Autowired
 private LeaveTypeRepository leaveTypeRepository;

@Autowired
 private LeaveSummaryRepository leaveSummaryRepository;

@Autowired
 private LocationRepository locationRepository;

@Autowired
 private CalculateYearRepository calculateYearRepository;

@Autowired
 private EmployeeMasterRepository employeeMasterRepository;

@Autowired
 private ShiftMasterRepository shiftMasterRepository;

@Autowired
 private SelfGroupRepository selfGroupRepository;

@Autowired
 private HolidayCategoryRepo holidayCategoryRepo;

@Autowired
 private GetShiftDetailRepository getShiftDetailRepository;

@Autowired
 private LeaveStructureDetailsRepo leaveStructureDetailsRepo;

@Autowired
 private DesignationRepo designationRepo;

@Autowired
 private DepartmentRepo departmentRepo;

@Autowired
 private WeekoffCategoryRepo weekoffCategoryRepo;

@Autowired
 private EmployeeMasterRepository empRepo;

@Autowired
 private GetEmployeeDetailsRepo getEmployeeDetailsRepo;

@Autowired
 private GetEmpInfoRepo getEmpInfo;

@Autowired
 private ShiftMasterRepository ShiftMasterRepository;

@Autowired
 private HolidayRepo holidayRepo;

@Autowired
 private WeeklyOffRepo weeklyOffRepo;

@Autowired
 private SkillRatesRepo skillRatesRepo;

@Autowired
 private EmployeeMasterRepository EmployeeMasterRepository;


@RequestMapping(value = { "/getShiftListByShiftIdLpad" }, method = RequestMethod.POST)
@ResponseBody
public GetShiftDetail getShiftListByShiftIdLpad(int shiftId){
    GetShiftDetail shiftMaster = new GetShiftDetail();
    try {
        shiftMaster = getShiftDetailRepository.getShiftListByShiftIdLpad(shiftId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return shiftMaster;
}


@RequestMapping(value = { "/getWeekoffCategoryHoCatId" }, method = RequestMethod.POST)
@ResponseBody
public WeekoffCategory getWeekoffCategoryHoCatId(int woCatId){
    WeekoffCategory bous = new WeekoffCategory();
    try {
        bous = weekoffCategoryRepo.findByWoCatIdAndDelStatus(woCatId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bous;
}


@RequestMapping(value = { "/deleteLeaveType" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLeaveType(int lvTypeId){
    Info info = new Info();
    try {
        List<LeaveStructureDetails> lvsDet = leaveStructureDetailsRepo.findByLvTypeIdAndDelStatus(lvTypeId);
        if (lvsDet.size() <= 0) {
            int delete = leaveTypeRepository.deleteLeaveType(lvTypeId);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Leave Type Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Leave Type");
            }
        } else {
            info.setError(true);
            info.setMsg("Leave Type Can't be Deleted as it is Part of Leave Structure ");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Leave Type");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmpInfoListForLeaveAuthLocId" }, method = RequestMethod.GET)
@ResponseBody
public List<GetEmployeeDetails> getEmpInfoListForLeaveAuthLocId(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpListByCompanyIdForAuth();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getShiftListByGroupIdandlocId" }, method = RequestMethod.POST)
@ResponseBody
public List<ShiftMaster> getShiftListByGroupIdandlocId(int groupId){
    List<ShiftMaster> shiftList = new ArrayList<>();
    try {
        shiftList = shiftMasterRepository.getShiftListByGroupIdandlocId(groupId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return shiftList;
}


@RequestMapping(value = { "/getEmpInfoListForLeaveAuth" }, method = RequestMethod.GET)
@ResponseBody
public List<GetEmployeeDetails> getEmpInfoListForLeaveAuth(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpListByCompanyIdForAuth();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLocationsByIds" }, method = RequestMethod.POST)
@ResponseBody
public List<Location> getLocationsByIds(List<Location> locIds){
    List<Location> locs = new ArrayList<Location>();
    try {
        locs = locationRepository.getLocationsByIds(locIds);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return locs;
}


@RequestMapping(value = { "/getCalculateYearListIsCurrent" }, method = RequestMethod.GET)
@ResponseBody
public CalenderYear getCalculateYearListIsCurrent(){
    CalenderYear calendearYear = new CalenderYear();
    try {
        calendearYear = calculateYearRepository.findByIsCurrent(1);
        System.out.println(calendearYear);
        calendearYear.setCalYrFromDate(DateConvertor.convertToDMY(calendearYear.getCalYrFromDate()));
        calendearYear.setCalYrToDate(DateConvertor.convertToDMY(calendearYear.getCalYrToDate()));
        System.out.println(calendearYear);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return calendearYear;
}


@RequestMapping(value = { "/getHolidayCategoryHoCatId" }, method = RequestMethod.POST)
@ResponseBody
public HolidayCategory getHolidayCategoryHoCatId(int hoCatId){
    HolidayCategory bous = new HolidayCategory();
    try {
        bous = holidayCategoryRepo.findByHoCatIdAndDelStatus(hoCatId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bous;
}


@RequestMapping(value = { "/checkUniqueDeptDesgn" }, method = RequestMethod.POST)
@ResponseBody
public Info checkUniqueField(String inputValue,int valueType,int isEditCall,int primaryKey,int compId){
    Info info = new Info();
    // 1- dept,2-desgn
    List<Department> dept = new ArrayList<Department>();
    List<Designation> desgn = new ArrayList<Designation>();
    List<HolidayCategory> hoCatList = new ArrayList<HolidayCategory>();
    List<WeekoffCategory> woCatList = new ArrayList<WeekoffCategory>();
    if (valueType == 1) {
        // System.err.println("Its Dept check");
        if (isEditCall == 0) {
            // System.err.println("Its New Record Insert ");
            dept = departmentRepo.findByNameAndCompanyId(inputValue, compId);
        } else {
            // System.err.println("Its Edit Record ");
            dept = departmentRepo.findByNameAndCompanyIdAndDepartIdNot(inputValue.trim(), compId, primaryKey);
        }
        // System.err.println("****"+dept.toString());
        if (dept.size() <= 0) {
            info.setError(false);
            info.setMsg("0");
        } else {
            info.setError(true);
            info.setMsg("1");
        }
    } else if (valueType == 2) {
        // System.err.println("Its Desn check");
        if (isEditCall == 0) {
            // System.err.println("Its New Record Insert ");
            desgn = designationRepo.findByNameAndCompanyId(inputValue, compId);
        } else {
            // System.err.println("Its Edit Record ");
            desgn = designationRepo.findByNameAndCompanyIdAndDesigIdNot(inputValue.trim(), compId, primaryKey);
        }
        if (desgn.size() <= 0) {
            info.setError(false);
            info.setMsg("0");
        } else {
            info.setError(true);
            info.setMsg("1");
        }
    } else if (valueType == 3) {
        if (isEditCall == 0) {
            hoCatList = holidayCategoryRepo.findByHoCatNameAndCompanyId(inputValue, compId);
        } else {
            hoCatList = holidayCategoryRepo.findByHoCatNameAndCompanyIdAndHoCatIdNot(inputValue.trim(), compId, primaryKey);
        }
        if (hoCatList.size() <= 0) {
            info.setError(false);
            info.setMsg("0");
        } else {
            info.setError(true);
            info.setMsg("1");
        }
    } else if (valueType == 4) {
        System.err.println("Its Week cat check");
        if (isEditCall == 0) {
            System.err.println("Its New Record Insert ");
            woCatList = weekoffCategoryRepo.findByWoCatNameAndCompanyId(inputValue, compId);
        } else {
            System.err.println("Its Edit Record ");
            woCatList = weekoffCategoryRepo.findByWoCatNameAndCompanyIdAndWoCatIdNot(inputValue.trim(), compId, primaryKey);
        }
        if (woCatList.size() <= 0) {
            info.setError(false);
            info.setMsg("0");
        } else {
            info.setError(true);
            info.setMsg("1");
        }
    }
    return info;
}


@RequestMapping(value = { "/getLeaveTypeById" }, method = RequestMethod.POST)
@ResponseBody
public LeaveType getLeaveTypeById(int lvTypeId){
    LeaveType leaveType = new LeaveType();
    try {
        leaveType = leaveTypeRepository.findByLvTypeIdAndDelStatus(lvTypeId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveType;
}


@RequestMapping(value = { "/checkUniqueShortName" }, method = RequestMethod.POST)
@ResponseBody
public LeaveType checkUniqueShortName(String valueType,int compId){
    LeaveType leaveType = new LeaveType();
    try {
        leaveType = leaveTypeRepository.findByCompanyIdAndLvTitleShort(compId, valueType);
    /*
             * if (leaveType==null) { info.setError(false); info.setMsg("deleted"); } else {
             * info.setError(true); info.setMsg("failed");
             *
             * }
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveType;
}


@RequestMapping(value = { "/getLeaveTypeListIsStructure" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveType> getLeaveTypeListIsStructure(int companyId){
    List<LeaveType> list = new ArrayList<LeaveType>();
    try {
        list = leaveTypeRepository.findByDelStatusAndIsStructuredAndCompanyIdOrderByLvTypeIdDesc(1, 1, companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveLeaveType" }, method = RequestMethod.POST)
@ResponseBody
public LeaveType saveLeaveType(LeaveType leaveType){
    LeaveType save = new LeaveType();
    try {
        save = leaveTypeRepository.saveAndFlush(leaveType);
        if (save == null) {
            save = new LeaveType();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getEmplistForAssignAuthority" }, method = RequestMethod.GET)
@ResponseBody
public List<GetEmployeeDetails> getEmplistForAssignAuthority(){
    List<GetEmployeeDetails> list = new ArrayList<>();
    try {
        list = getEmployeeDetailsRepo.getEmplistForAssignAuthority();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmplistForAssignAuthorityAllByLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeeMaster> getEmplistForAssignAuthorityAllByLocId(List<Location> locId){
    List<EmployeeMaster> list = new ArrayList<>();
    try {
        list = employeeMasterRepository.getEmplistForAssignAuthorityAll(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/showShiftListByLocationIds" }, method = RequestMethod.POST)
@ResponseBody
public List<ShiftMaster> showShiftListByLocationIds(List<Integer> locationIds){
    List<ShiftMaster> shiftList = new ArrayList<>();
    try {
        shiftList = shiftMasterRepository.showShiftListByLocationIds(locationIds);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return shiftList;
}


@RequestMapping(value = { "/saveWeekoffCat" }, method = RequestMethod.POST)
@ResponseBody
public WeekoffCategory saveWeekoffCat(WeekoffCategory holiCat){
    WeekoffCategory save = new WeekoffCategory();
    try {
        save = weekoffCategoryRepo.saveAndFlush(holiCat);
        if (save == null) {
            save = new WeekoffCategory();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getSkillById" }, method = RequestMethod.POST)
@ResponseBody
public SkillRates getSkillById(int skillId){
    SkillRates skill = new SkillRates();
    try {
        skill = skillRatesRepo.findBySkillIdAndDelStatus(skillId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return skill;
}


@RequestMapping(value = { "/deleteShiftTime" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteShiftTime(int shiftId){
    Info info = new Info();
    try {
        int delete = shiftMasterRepository.deleteShiftTime(shiftId);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getSelfGroupById" }, method = RequestMethod.POST)
@ResponseBody
public SelfGroup getSelfGroupById(int selftGroupId){
    SelfGroup bous = new SelfGroup();
    try {
        bous = selfGroupRepository.findBySelftGroupIdAndDelStatus(selftGroupId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bous;
}


@RequestMapping(value = { "/getLeaveTypeList" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveType> getLeaveTypeList(){
    List<LeaveType> list = new ArrayList<LeaveType>();
    try {
        list = leaveTypeRepository.findByDelStatus(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLocationList" }, method = RequestMethod.POST)
@ResponseBody
public List<Location> getLocationList(int companyId){
    List<Location> list = new ArrayList<Location>();
    try {
        if (companyId != 0) {
            list = locationRepository.findByDelStatusAndCompIdOrderByLocIdDesc(1, companyId);
        } else {
            list = locationRepository.findByDelStatus(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAuthorityWiseEmpListByEmpIdForApp" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpIdForApp(int empId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAuthorityWiseEmpListByEmpIdForApp(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getShiftListByLpadForShiftAllocation" }, method = RequestMethod.POST)
@ResponseBody
public List<ShiftMaster> getShiftListByLpadForShiftAllocation(){
    List<ShiftMaster> shiftList = new ArrayList<>();
    try {
        shiftList = shiftMasterRepository.getShiftListByLpadForShiftAllocation();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return shiftList;
}


@RequestMapping(value = { "/getLeaveSummaryList" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveSummary> getLeaveSummaryList(int compId){
    List<LeaveSummary> list = new ArrayList<LeaveSummary>();
    try {
        if (compId != 0) {
            list = leaveSummaryRepository.findByDelStatusAndCompanyId(1, compId);
        } else {
            list = leaveSummaryRepository.findByDelStatus(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpListForClaimAuthByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeDetails> getEmpListForClaimAuthByEmpId(int empId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getEmpListForClaimAuthByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveHolidayCat" }, method = RequestMethod.POST)
@ResponseBody
public HolidayCategory saveHolidayCat(HolidayCategory holiCat){
    HolidayCategory save = new HolidayCategory();
    try {
        save = holidayCategoryRepo.saveAndFlush(holiCat);
        if (save == null) {
            save = new HolidayCategory();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/saveShiftMaster" }, method = RequestMethod.POST)
@ResponseBody
public ShiftMaster getSelftGroupList(ShiftMaster shiftMaster){
    ShiftMaster save = new ShiftMaster();
    try {
        save = shiftMasterRepository.save(shiftMaster);
        if (shiftMaster.getChangewith() == 0) {
            save.setChangewith(save.getId());
            save = shiftMasterRepository.save(shiftMaster);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/deleteSelfGroup" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteSelfGroup(int bonusId){
    Info info = new Info();
    try {
        List<ShiftMaster> shftList = ShiftMasterRepository.findBySelfGroupIdAndStatus(bonusId, 1);
        if (shftList.size() <= 0) {
            int delete = selfGroupRepository.deleteSelfGroup(bonusId);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Self Group Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Self Group");
            }
        } else {
            info.setError(true);
            info.setMsg("Self Group Can't be Deleted as it is Asigned");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Self Group");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/saveLocation" }, method = RequestMethod.POST)
@ResponseBody
public Location saveLocation(Location location){
    Location save = new Location();
    try {
        save = locationRepository.saveAndFlush(location);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/deleteSkillRate" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteSkillRate(int skillId){
    Info info = new Info();
    try {
        List<EmployeeMaster> lvsDet = weeklyOffRepo.findByExInt2AndDelStatus(skillId, 1);
        if (lvsDet.size() <= 0) {
            int delete = skillRatesRepo.deleteSkillRate(skillId);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Skill Rate Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Skill Rate");
            }
        } else {
            info.setError(true);
            info.setMsg("Skill Rate Can't be Deleted as it is Assigned Employee ");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Skill Rate");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getLocationById" }, method = RequestMethod.POST)
@ResponseBody
public Location getLocationById(int locId){
    Location location = new Location();
    try {
        location = locationRepository.findByLocIdAndDelStatus(locId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return location;
}


@RequestMapping(value = { "/saveSelfGrp" }, method = RequestMethod.POST)
@ResponseBody
public SelfGroup saveSelfGrp(SelfGroup bonusMaster){
    SelfGroup save = new SelfGroup();
    try {
        save = selfGroupRepository.saveAndFlush(bonusMaster);
        if (save == null) {
            save = new SelfGroup();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/deleteLocation" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLocation(int locId){
    Info info = new Info();
    try {
        List<EmployeeMaster> empList = empRepo.findByLocationIdAndDelStatus(locId, 1);
        if (empList.size() <= 0) {
            int delete = locationRepository.deleteLocation(locId);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Location Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed to Delete Location");
            }
        } else {
            info.setError(true);
            info.setMsg("Location can't be Deleted as it is Assigned to Employee");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("Failed to Delete Location");
    }
    return info;
}


@RequestMapping(value = { "/getShiftListByLpad" }, method = RequestMethod.POST)
@ResponseBody
public List<ShiftMaster> getShiftListByLpad(){
    List<ShiftMaster> shiftList = new ArrayList<>();
    try {
        shiftList = shiftMasterRepository.getShiftListByLpad();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return shiftList;
}


@RequestMapping(value = { "/deleteWeekoffCategory" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteWeekoffCategory(String woCatId){
    Info info = new Info();
    try {
        List<WeeklyOff> lvsDet = weeklyOffRepo.findByExInt1AndDelStatus(Integer.parseInt(woCatId), 1);
        if (lvsDet.size() <= 0) {
            int delete = weekoffCategoryRepo.deleteWoCat(Integer.parseInt(woCatId));
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Weekly Off Category Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Weekly Off Category");
            }
        } else {
            info.setError(true);
            info.setMsg("Weekly Off Category Can't be Deleted as it is Assigned To Week Off ");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Weekly Off Category");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getWeekoffCategoryList" }, method = RequestMethod.GET)
@ResponseBody
public List<WeekoffCategory> getWeekoffCategoryList(){
    List<WeekoffCategory> list = new ArrayList<WeekoffCategory>();
    try {
        list = weekoffCategoryRepo.findByDelStatusOrderByWoCatIdDesc(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getcurrentyear" }, method = RequestMethod.GET)
@ResponseBody
public CalenderYear getcurrentyear(){
    CalenderYear calendearYear = new CalenderYear();
    try {
        calendearYear = calculateYearRepository.findByIsCurrent(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return calendearYear;
}


@RequestMapping(value = { "/getHolidayCategoryList" }, method = RequestMethod.POST)
@ResponseBody
public List<HolidayCategory> HolidayCategory(){
    List<HolidayCategory> list = new ArrayList<HolidayCategory>();
    try {
        list = holidayCategoryRepo.findByDelStatusOrderByHoCatIdDesc(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public EmployeeMaster getEmpInfoByEmpId(int empId){
    EmployeeMaster employeeMaster = new EmployeeMaster();
    try {
        employeeMaster = employeeMasterRepository.getEmpInfoByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employeeMaster;
}


@RequestMapping(value = { "/deleteHolidayCategory" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteHolidayCategory(String hoCatId){
    Info info = new Info();
    try {
        List<Holiday> lvsDet = holidayRepo.findByExInt1AndDelStatus(Integer.parseInt(hoCatId), 1);
        if (lvsDet.size() <= 0) {
            int delete = holidayCategoryRepo.deleteHoliCat(Integer.parseInt(hoCatId));
            if (delete > 0) {
                info.setError(false);
                info.setMsg("Holiday Category Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Holiday Category");
            }
        } else {
            info.setError(true);
            info.setMsg("Holiday Category Can't be Deleted as it is Assigned To Holiday ");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Holiday Category");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmpInfoListByEmpIdList1" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeInfo> getEmpInfoListByEmpIdList(int companyId,List<Integer> empIdList){
    List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
    try {
        list = getEmpInfo.getEmpListByCompanyIdByEmp(companyId, empIdList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAuthorityWiseEmpListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpId(int empId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        list = getEmployeeDetailsRepo.getAuthorityWiseEmpListByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveSkillRate" }, method = RequestMethod.POST)
@ResponseBody
public SkillRates saveSkillRate(SkillRates skill){
    SkillRates save = new SkillRates();
    try {
        save = skillRatesRepo.saveAndFlush(skill);
        if (save == null) {
            save = new SkillRates();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getSelftGroupListForAddShift" }, method = RequestMethod.GET)
@ResponseBody
public List<SelfGroup> getSelftGroupListForAddShift(){
    List<SelfGroup> selfGrouptList = new ArrayList<>();
    try {
        selfGrouptList = selfGroupRepository.getSelftGroupListForAddShift();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return selfGrouptList;
}


@RequestMapping(value = { "/getEmplistForAssignAuthorityAll" }, method = RequestMethod.GET)
@ResponseBody
public List<EmployeeMaster> getEmplistForAssignAuthorityAll(){
    List<EmployeeMaster> list = new ArrayList<>();
    try {
        list = employeeMasterRepository.getEmplistForAssignAuthorityAll();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getSkillRateList" }, method = RequestMethod.GET)
@ResponseBody
public List<SkillRates> getSkillRateList(){
    List<SkillRates> list = new ArrayList<SkillRates>();
    try {
        list = skillRatesRepo.findByDelStatusOrderBySkillIdDesc(1);
        System.err.println("SkillRates---" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}