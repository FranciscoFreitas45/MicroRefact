import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model;
import com.ats.hrmgt.repo.DailyRecordForCompOffRepository;
import com.ats.hrmgt.repo.LeaveEncashRepository;
import com.ats.hrmgt.repository;
import com.ats.hrmgt.service.CommonFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util;
@RestController
public class LeaveActionApiController {

@Autowired
 private LeaveApplyRepository leaveApplyRepository;

@Autowired
 private LeaveTrailRepository leaveTrailRepository;

@Autowired
 private LeaveHistoryRepo leaveHistoryRepo;

@Autowired
 private AuthorityInformationRepository authorityInformationRepository;

@Autowired
 private GetAuthorityIdsRepo getAuthorityIdsRepo;

@Autowired
 private GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;

@Autowired
 private CalculateYearRepository calculateYearRepository;

@Autowired
 private EmployeeMasterRepository employeeMasterRepository;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private PayableDayAndPresentDaysRepo payableDayAndPresentDaysRepo;

@Autowired
 private LeaveTypeWithLimitRepository leaveTypeWithLimitRepository;

@Autowired
 private EmpBasicAllownceForLeaveInCashRepo empBasicAllownceForLeaveInCashRepo;

@Autowired
 private MstEmpTypeRepository mstEmpTypeRepository;

@Autowired
 private DailyRecordForCompOffRepository dailyRecordForCompOffRepository;

@Autowired
 private GetDetailForGraduatyRepo getDetailForGraduatyRepo;

@Autowired
 private LeaveHistoryDetailForCarryRepo leaveHistoryDetailForCarryRepo;

@Autowired
 private LeaveEncashRepository leaveEncashRepository;

@Autowired
 private GetLeaveEncashDetailRepository getLeaveEncashDetailRepository;

@Autowired
 private EmpLeaveHistoryRepRepo empLeaveHistoryRepRepo;

@Autowired
 private CommonFunctionService commonFunctionService;

@Autowired
 private EmployeeListForInActiveRepository employeeListForInActiveRepository;

@Autowired
 private AttendanceApiControllerchange attendanceApiControllerchange;


@RequestMapping(value = { "/updateTrailId" }, method = RequestMethod.POST)
@ResponseBody
public Info updateTrailId(int leaveId,int trailId){
    Info info = new Info();
    try {
        int delete = leaveApplyRepository.updateLeaveApply(leaveId, trailId);
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


@RequestMapping(value = { "/updateLeaveStatus" }, method = RequestMethod.POST)
@ResponseBody
public Info updateLeaveStatus(int leaveId,int status){
    Info info = new Info();
    // System.err.println("in updateLeaveStatus" + status + leaveId);
    try {
        int update = leaveApplyRepository.updateLeaveStatus(leaveId, status);
        if (update > 0) {
            info.setError(false);
            info.setMsg("updated");
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


@RequestMapping(value = { "/getLeaveHistoryList" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveHistory> getLeaveHistoryList(int empId,int currYrId){
    List<LeaveHistory> list = new ArrayList<LeaveHistory>();
    try {
        list = leaveHistoryRepo.getLeaveHistoryByEmpId(empId, currYrId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/newLeaveEncash" }, method = RequestMethod.POST)
@ResponseBody
public Info newLeaveEncash(LeaveEncash leaveEncash){
    Info info = new Info();
    try {
        LeaveEncash res = leaveEncashRepository.save(leaveEncash);
        if (res != null) {
            info.setError(false);
            info.setMsg("save successfullyy");
        } else {
            info.setError(true);
            info.setMsg("error while encash leave");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("error while encash leave");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAuthorityInfoByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public AuthorityInformation getAuthorityInfoByEmpId(int empId){
    AuthorityInformation authorityInformation = new AuthorityInformation();
    try {
        authorityInformation = authorityInformationRepository.getAuthorityInfoByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return authorityInformation;
}


@RequestMapping(value = { "/saveCalYear" }, method = RequestMethod.POST)
@ResponseBody
public CalenderYear checkUniqueCalDates(CalenderYear year){
    CalenderYear calYear = new CalenderYear();
    try {
        calYear = calculateYearRepository.save(year);
        if (calYear.getIsCurrent() == 1) {
            int a = calculateYearRepository.updateOtherIds(calYear.getCalYrId());
        }
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
    return calYear;
}


@RequestMapping(value = { "/saveLeaveApply" }, method = RequestMethod.POST)
@ResponseBody
public LeaveApply saveLeaveApply(LeaveApply leave){
    LeaveApply save = new LeaveApply();
    try {
        save = leaveApplyRepository.saveAndFlush(leave);
        if (save == null) {
            save = new LeaveApply();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        save = new LeaveApply();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "getemplistwhichisnotyearend" }, method = RequestMethod.GET)
@ResponseBody
public List<EmployeeMaster> getemplistwhichisnotyearend(){
    List<EmployeeMaster> employeeInfo = new ArrayList<EmployeeMaster>();
    try {
        employeeInfo = employeeMasterRepository.getemplistwhichisnotyearend();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employeeInfo;
}


@RequestMapping(value = { "/getPreviousleaveHistory" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveHistory> getPreviousleaveHistory(int empId){
    List<LeaveHistory> list = new ArrayList<LeaveHistory>();
    try {
        list = leaveHistoryRepo.getPreviousleaveHistory(empId);
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLeaveEncashDetailByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveEncashDetail> getLeaveEncashDetailByEmpId(int empId,int yearId){
    List<GetLeaveEncashDetail> list = new ArrayList<>();
    try {
        list = getLeaveEncashDetailRepository.getLeaveEncashDetailByEmpId(empId, yearId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getCalculateYearList" }, method = RequestMethod.GET)
@ResponseBody
public List<CalenderYear> getCalculateYearList(){
    List<CalenderYear> list = new ArrayList<CalenderYear>();
    try {
        list = calculateYearRepository.getAllCalYearOrderByDesc();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCalYrFromDate(DateConvertor.convertToDMY(list.get(i).getCalYrFromDate()));
            list.get(i).setCalYrToDate(DateConvertor.convertToDMY(list.get(i).getCalYrToDate()));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "getemplistwhichisnotyearendByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeeMaster> getemplistwhichisnotyearendByEmpId(int locId){
    List<EmployeeMaster> employeeInfo = new ArrayList<EmployeeMaster>();
    try {
        employeeInfo = employeeMasterRepository.getemplistwhichisnotyearendByEmpId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employeeInfo;
}


@RequestMapping(value = { "/getLeaveHistoryRep" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpLeaveHistoryRep> getLeaveHistoryRep(int empId,int locId,int calYrId){
    List<EmpLeaveHistoryRep> list = new ArrayList<EmpLeaveHistoryRep>();
    try {
        if (empId == -1) {
            list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRepAll(calYrId, locId);
        } else {
            list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRep(empId, calYrId, locId);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getCountCalYear" }, method = RequestMethod.GET)
@ResponseBody
public Integer getCountCalYear(){
    int cntCalYear = 0;
    try {
        cntCalYear = calculateYearRepository.countCalyear();
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
    return cntCalYear;
}


@RequestMapping(value = { "/getCalYearById" }, method = RequestMethod.POST)
@ResponseBody
public CalenderYear getCalYearById(int calYearId){
    CalenderYear calYear = new CalenderYear();
    try {
        calYear = calculateYearRepository.findByCalYrId(calYearId);
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
    return calYear;
}


@RequestMapping(value = { "/getPayableDayandPresentDayByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public PayableDayAndPresentDays getPayableDayandPresentDayByEmpId(int empId,String fromDate,String toDate){
    PayableDayAndPresentDays payableDayAndPresentDays = new PayableDayAndPresentDays();
    try {
        payableDayAndPresentDays = payableDayAndPresentDaysRepo.getPayableDayandPresentDayByEmpId(empId, DateConvertor.convertToYMD(fromDate), DateConvertor.convertToYMD(toDate));
        if (payableDayAndPresentDays == null) {
            payableDayAndPresentDays = new PayableDayAndPresentDays();
            payableDayAndPresentDays.setError(true);
        } else {
            payableDayAndPresentDays.setError(false);
        }
    } catch (Exception e) {
        payableDayAndPresentDays = new PayableDayAndPresentDays();
        payableDayAndPresentDays.setError(true);
        e.printStackTrace();
    }
    return payableDayAndPresentDays;
}


@RequestMapping(value = { "/getEmployeeBasicAndAllownceValueByEmpIdAndStructId" }, method = RequestMethod.POST)
@ResponseBody
public EmpBasicAllownceForLeaveInCash getEmployeeBasicAndAllownceValueByEmpIdAndStructId(int empId,int lvsId){
    EmpBasicAllownceForLeaveInCash empBasicAllownceForLeaveInCash = new EmpBasicAllownceForLeaveInCash();
    try {
        empBasicAllownceForLeaveInCash = empBasicAllownceForLeaveInCashRepo.getEmployeeBasicAndAllownceValueByEmpIdAndStructId(empId, lvsId);
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return empBasicAllownceForLeaveInCash;
}


public InfoForCompOffList LeaveTypeValidation(int empId,int leaveTypeId,String shortName,float noOfDays){
    InfoForCompOffList info = new InfoForCompOffList();
    try {
        List<DailyRecordForCompOff> dailyrecordlistforcompoff = new ArrayList<>();
        info.setDailyrecordlistforcompoff(dailyrecordlistforcompoff);
        Setting TYPEVALIDATION = settingRepo.findByKey("TYPEVALIDATION");
        if (Integer.parseInt(TYPEVALIDATION.getValue()) == 1) {
            CalenderYear calendearYear = calculateYearRepository.findByIsCurrent(1);
            LeaveTypeWithLimit leaveTypeWithLimit = leaveTypeWithLimitRepository.LeaveTypeWithLimit(empId, leaveTypeId, calendearYear.getCalYrId());
            // System.out.println(leaveTypeWithLimit.getMaxNoDays() + " " + noOfDays);
            if (leaveTypeWithLimit.getMaxNoDays() != 0 && leaveTypeWithLimit.getMaxNoDays() < noOfDays) {
                info.setError(true);
                info.setMsg("Your Leave Limit is " + leaveTypeWithLimit.getMaxNoDays());
            } else {
                info.setError(false);
                info.setMsg("you can apply");
            }
        } else {
            info.setError(false);
            info.setMsg("you can apply");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "getAuthIdByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public GetAuthorityIds getAuthIdByEmpId(int empId){
    System.out.println("emp id is " + empId);
    GetAuthorityIds leaveApply = new GetAuthorityIds();
    try {
        leaveApply = getAuthorityIdsRepo.getAuthIds(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveApply;
}


@RequestMapping(value = { "/getdetailforgraduaty" }, method = RequestMethod.POST)
@ResponseBody
public GetDetailForGraduaty getdetailforgraduaty(int empId){
    GetDetailForGraduaty getDetailForGraduaty = new GetDetailForGraduaty();
    try {
        getDetailForGraduaty = getDetailForGraduatyRepo.getdetailforgraduaty(empId);
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return getDetailForGraduaty;
}


@RequestMapping(value = { "/checkDateForRepetedLeaveValidation" }, method = RequestMethod.POST)
@ResponseBody
public InfoForCompOffList checkDateForRepetedLeaveValidation(String fromDate,String toDate,int empId,int leaveTypeId,String shortName,float noOfDays){
    InfoForCompOffList info = new InfoForCompOffList();
    List<DailyRecordForCompOff> dailyrecordlistforcompoff = new ArrayList<>();
    info.setDailyrecordlistforcompoff(dailyrecordlistforcompoff);
    try {
        Setting setting = settingRepo.findByKey("CONTILEAVE");
        Setting CL_ID = settingRepo.findByKey("CL_ID");
        Setting PL_ID = settingRepo.findByKey("PL_ID");
        // Setting COMPOFFCONDITION = settingRepo.findByKey("COMPOFFCONDITION");
        List<LeaveApply> list = leaveApplyRepository.checkDateForRepetedLeaveValidation(fromDate, toDate, empId);
        if (list.size() > 0) {
            info.setError(true);
            info.setMsg("You Have Already Apply Leave on this Date.");
        } else {
            if (Integer.parseInt(setting.getValue()) == 1) {
                /*
                     * if (shortName.equalsIgnoreCase("LWP") || shortName.equalsIgnoreCase("SL")) {
                     *
                     * info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
                     *
                     * } else if (shortName.equalsIgnoreCase("Compoff")) {
                     *
                     * info = checkCompOff(empId, fromDate, toDate);
                     *
                     * } else {
                     *
                     * list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId,
                     * leaveTypeId); if (list.size() > 0) {
                     *
                     * info.setError(true);
                     * info.setMsg("You Cannot Apply Continue Leave As Diffrent Type. ");
                     *
                     * } else {
                     *
                     * info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays); } }
                     */
                if (shortName.equalsIgnoreCase("CL") || shortName.equalsIgnoreCase("PL")) {
                    if (shortName.equalsIgnoreCase("CL")) {
                        list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId, Integer.parseInt(PL_ID.getValue()));
                    } else {
                        list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId, Integer.parseInt(CL_ID.getValue()));
                    }
                    if (list.size() > 0) {
                        info.setError(true);
                        info.setMsg("You Cannot Apply Continue Leave CL and PL");
                    } else {
                        info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
                    }
                } else if (shortName.equalsIgnoreCase("Compoff")) {
                    // match compoffApplicabale in emptype
                    info = checkCompOff(empId, fromDate, toDate);
                } else {
                    info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
                }
            } else {
                if (shortName.equalsIgnoreCase("Compoff")) {
                    info = checkCompOff(empId, fromDate, toDate);
                } else {
                    info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getValidationOfFreezeMonth" }, method = RequestMethod.POST)
@ResponseBody
public Info getValidationOfFreezeMonth(String fromDate,String toDate,int empId){
    Info info = new Info();
    try {
        int count = getDetailForGraduatyRepo.getValidationOfFreezeMonth(fromDate, toDate, empId);
        info.setError(count > 0);
        info.setMsg(String.valueOf(count));
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getPreviousleaveHistoryForCarryFrwd" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveHistoryDetailForCarry> getPreviousleaveHistoryForCarryFrwd(int locId){
    List<LeaveHistoryDetailForCarry> list = new ArrayList<LeaveHistoryDetailForCarry>();
    try {
        list = leaveHistoryDetailForCarryRepo.getPreviousleaveHistoryForCarryFrwd(locId);
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


public InfoForCompOffList checkCompOff(int empId,String fromDate,String toDate){
    InfoForCompOffList info = new InfoForCompOffList();
    try {
        MstEmpType mstEmpType = mstEmpTypeRepository.getTypeByempId(empId);
        if (mstEmpType.getWhWork().equals("Compoff")) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sf.parse(fromDate);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(dt);
            cal.add(Calendar.DATE, -45);
            dt = cal.getTime();
            toDate = sf.format(dt);
            List<DailyRecordForCompOff> dailyrecordlistforcompoff = dailyRecordForCompOffRepository.dailyrecordlistforcompoff(toDate, fromDate, empId);
            if (dailyrecordlistforcompoff.size() > 0) {
                info.setError(false);
                info.setMsg("you can apply");
            } else {
                info.setError(true);
                info.setMsg("Insufficient compoff");
            }
            info.setDailyrecordlistforcompoff(dailyrecordlistforcompoff);
        } else {
            info.setError(true);
            info.setMsg("You cant take compoff");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getValidationOfFreezeMonthSalary" }, method = RequestMethod.POST)
@ResponseBody
public Info getValidationOfFreezeMonthSalary(String month,String year,int empId){
    Info info = new Info();
    try {
        int count = getDetailForGraduatyRepo.getValidationOfFreezeMonthSalary(month, year, empId);
        info.setError(count > 0);
        info.setMsg(String.valueOf(count));
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/saveLeaveApplyList" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveApply> saveLeaveApplyList(List<LeaveApply> leave){
    List<LeaveApply> save = new ArrayList<>();
    try {
        save = leaveApplyRepository.saveAll(leave);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getEmpTypeByempId" }, method = RequestMethod.POST)
@ResponseBody
public MstEmpType getEmpTypeByempId(int empId){
    MstEmpType mstEmpType = new MstEmpType();
    try {
        mstEmpType = mstEmpTypeRepository.getTypeByempId(empId);
    // System.err.println("LeaveHistory" + list.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return mstEmpType;
}


@RequestMapping(value = { "/saveLeaveTrail" }, method = RequestMethod.POST)
@ResponseBody
public LeaveTrail saveLeaveTrail(LeaveTrail leaveTrail){
    LeaveTrail save = new LeaveTrail();
    try {
        save = leaveTrailRepository.saveAndFlush(leaveTrail);
        if (save == null) {
            save = new LeaveTrail();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getLeaveApplyListForPending" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveApplyAuthwise> getLeaveApplyAuthwiseList(int empId,int currYrId){
    List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();
    try {
        list = getLeaveApplyAuthwiseRepo.getLeaveApplyList(empId, currYrId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteEncashLeave" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteEncashLeave(int id){
    Info info = new Info();
    try {
        int delete = leaveEncashRepository.deleteEncashLeave(id);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("delete successfullyy");
        } else {
            info.setError(true);
            info.setMsg("error while delete");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("error delete");
        e.printStackTrace();
    }
    return info;
}


@Scheduled(cron = "0 0 23 * * ? ")
public void applyLWPofInactiveEmployee(){
    try {
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List<LeaveApply> leavetList = leaveApplyRepository.getleavetListForAttndace(sf.format(dt), sf.format(dt));
        List<EmployeeListForInActive> empList = employeeListForInActiveRepository.getEmpInactiveList();
        CalenderYear calendearYear = calculateYearRepository.findByIsCurrent(1);
        for (int i = 0; i < empList.size(); i++) {
            int findLeave = 0;
            for (int j = 0; j < leavetList.size(); j++) {
                if (empList.get(i).getEmpId() == leavetList.get(j).getEmpId()) {
                    findLeave = 1;
                    break;
                }
            }
            if (findLeave == 0) {
                LeaveApply leaveSummary = new LeaveApply();
                leaveSummary.setCalYrId(calendearYear.getCalYrId());
                leaveSummary.setEmpId(empList.get(i).getEmpId());
                leaveSummary.setFinalStatus(1);
                leaveSummary.setLeaveNumDays(1);
                leaveSummary.setCirculatedTo("1");
                leaveSummary.setLeaveDuration("1");
                leaveSummary.setLeaveEmpReason("BY SYSTEM");
                leaveSummary.setLvTypeId(2);
                leaveSummary.setLeaveFromdt(sf.format(dt));
                leaveSummary.setLeaveTodt(sf.format(dt));
                leaveSummary.setExInt1(3);
                leaveSummary.setExInt2(1);
                leaveSummary.setExInt3(1);
                leaveSummary.setExVar1("NA");
                leaveSummary.setExVar2("0");
                /*
                     * leaveSummary.setExVar3("");
                     */
                leaveSummary.setIsActive(1);
                leaveSummary.setDelStatus(1);
                leaveSummary.setMakerUserId(1);
                leaveSummary.setMakerEnterDatetime(df.format(dt));
                LeaveApply save = leaveApplyRepository.saveAndFlush(leaveSummary);
                if (save != null) {
                    List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
                    FileUploadedData fileUploadedData = new FileUploadedData();
                    fileUploadedData.setEmpCode(empList.get(i).getEmpCode());
                    fileUploadedData.setEmpName("XYZ");
                    fileUploadedData.setLogDate(DateConvertor.convertToDMY(sf.format(dt)));
                    fileUploadedData.setInTime("00:00:00");
                    fileUploadedData.setOutTime("00:00:00");
                    fileUploadedDataList.add(fileUploadedData);
                    DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
                    dataForUpdateAttendance.setFromDate(sf.format(dt));
                    dataForUpdateAttendance.setToDate(sf.format(dt));
                    dataForUpdateAttendance.setMonth(0);
                    dataForUpdateAttendance.setYear(0);
                    dataForUpdateAttendance.setUserId(1);
                    dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
                    dataForUpdateAttendance.setEmpId(empList.get(i).getEmpId());
                    // System.out.println(dataForUpdateAttendance);
                    Info dailydailyinfo = attendanceApiControllerchange.getVariousListForUploadAttendace(dataForUpdateAttendance);
                }
            }
        }
    /*
             * LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(
             * sf.format(dt), leavetList, dailyAttendanceList.get(i).getEmpId());
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping(value = { "/getLeaveApplyListForInformation" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveApplyAuthwise> getLeaveApplyListForInformation(int empId,int currYrId){
    List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();
    try {
        list = getLeaveApplyAuthwiseRepo.getLeaveApplyList2(empId, currYrId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}