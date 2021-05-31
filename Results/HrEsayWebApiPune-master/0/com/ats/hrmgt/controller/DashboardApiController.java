import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model;
import com.ats.hrmgt.model.dashboard;
import com.ats.hrmgt.model.repo.dash;
import com.ats.hrmgt.repo.HolidayMasterRepo;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@RestController
public class DashboardApiController {

@Autowired
 private HolidayMasterRepo holidayMasterRepo;

@Autowired
 private GetBirthDaysForDashRepo getBirthDaysForDashRepo;

@Autowired
 private PerformanceProdDashRepo performanceProdDashRepo;

@Autowired
 private AgeDiversityDashRepo ageDiversityDashRepo;

@Autowired
 private IncentivesAmtDashRepo incentivesAmtDashRepo;

@Autowired
 private SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

@Autowired
 private EmpDeptWiseRepository empDeptWiseRepository;

@Autowired
 private SelfAttendanceDetailRepository selfAttendanceDetailRepository;

@Autowired
 private DashboardLeavePendingRepo dashboardLeavePendingRepo;

@Autowired
 private GetNewHiresDashRepo getNewHiresDashRepo;

@Autowired
 private LeavePenDashRepo leavePenDashRepo;

@Autowired
 private PreDayAttnDashRepo preDayAttnDashRepo;

@Autowired
 private DailyAttendanceRepository dailyAttendanceRepository;

@Autowired
 private DeptWiseWeekoffDashRepo deptWiseWeekoffDashRepo;

@Autowired
 private GetAllPendingMasterDetRepo getAllPendingMasterDetRepo;

@Autowired
 private PayRewardDedDashRepo payRewardDedDashRepo;

@Autowired
 private LoanAdvDashDetDashRepo loanAdvDashDetDashRepo;

@Autowired
 private GetLeaveHistForDashRepo getLeaveHistForDashRepo;

@Autowired
 private LeaveAuthorityRepository leaveAuthorityRepository;

@Autowired
 private EmpInfoForDashBoardRepository empInfoForDashBoardRepository;

@Autowired
 private EmpGraphDetailRepository empGraphDetailRepository;

@Autowired
 private TotalOTRepository totalOTRepository;

@Autowired
 private AdvaceAmtGraphRepository advaceAmtGraphRepository;

@Autowired
 private CountDataRepository countDataRepository;

@Autowired
 private PresentAttendaceListRepository presentAttendaceListRepository;


@RequestMapping(value = { "/getCommonDash" }, method = RequestMethod.POST)
@ResponseBody
public CommonDash getCommonDash(String fiterdate,int empId,int userType,int isAuth,int locId){
    CommonDash comDash = new CommonDash();
    BirthHoliDash birthHoliDash = new BirthHoliDash();
    List<HolidayMaster> holilist = new ArrayList<HolidayMaster>();
    List<GetBirthDaysForDash> birthListUpcoming = new ArrayList<GetBirthDaysForDash>();
    List<GetBirthDaysForDash> birthListToday = new ArrayList<GetBirthDaysForDash>();
    String[] temp = fiterdate.split("-");
    // 1 getBirthDayAndHolidayDash
    // for all
    try {
        GetBirthDaysForDash getBirthDaysForDash = getBirthDaysForDashRepo.loginUserBirthDay(fiterdate, empId);
        Date dt = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(getBirthDaysForDash.getDob());
        // 9 - October!!!
        int month2 = cal2.get(Calendar.MONTH) + 1;
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        if (month == month2 && day == day2) {
            birthHoliDash.setLoginUserBirthDay(1);
        } else {
            birthHoliDash.setLoginUserBirthDay(0);
        }
        if (userType == 2) {
            holilist = holidayMasterRepo.getHolidaysForDash(fiterdate);
            birthListToday = getBirthDaysForDashRepo.getTodaysBirth(fiterdate, locId);
            birthListUpcoming = getBirthDaysForDashRepo.getWeekBirth(fiterdate, locId);
        }
        birthHoliDash.setBirthListToday(birthListToday);
        birthHoliDash.setBirthListUpcoming(birthListUpcoming);
        birthHoliDash.setHoliList(holilist);
    } catch (Exception e) {
        e.printStackTrace();
    }
    comDash.setBirth(birthHoliDash);
    // 12 getLeaveHistForDash
    List<GetLeaveHistForDash> leaveHistForDashlist = new ArrayList<GetLeaveHistForDash>();
    try {
    // leaveHistForDashlist = getLeaveHistForDashRepo.getLeaveCnt(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    comDash.setLvApplList(leaveHistForDashlist);
    // getDashDeptWiseEmpCount
    if (userType == 2) {
        List<DeptWiseWeekoffDash> listEmpDiv = new ArrayList<DeptWiseWeekoffDash>();
        try {
        // listEmpDiv = deptWiseWeekoffDashRepo.getDeptWiseEmpDiversity(locId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setDeptWiseEmpCntList(listEmpDiv);
    }
    // getDedTypewiseDeduction
    List<DeptWiseWeekoffDash> listDedTypewiseAm = new ArrayList<DeptWiseWeekoffDash>();
    try {
    // listDedTypewiseAm = deptWiseWeekoffDashRepo.getDedTypewiseAmt(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    comDash.setDedWiseDedList(listDedTypewiseAm);
    // /getRewardWisewiseDeduction
    List<DeptWiseWeekoffDash> rewardwiseAmtlist = new ArrayList<DeptWiseWeekoffDash>();
    try {
        rewardwiseAmtlist = deptWiseWeekoffDashRepo.getRewardwiseAmt(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    comDash.setRewardWiseDedList(rewardwiseAmtlist);
    IncentivesAmtDash incent = new IncentivesAmtDash();
    try {
    // incent = incentivesAmtDashRepo.getWeekBirth(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    comDash.setIcent(incent);
    // getPerfProd
    PerformanceProdDash prod = new PerformanceProdDash();
    try {
    /*
             * prod = performanceProdDashRepo.getPerformanceDetails(temp[0], temp[1],
             * empId);
             *
             * comDash.setPerfList(prod); prod =
             * performanceProdDashRepo.getProdDetails(temp[0], temp[1], empId);
             *
             * comDash.setProdList(prod);
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
    // for HR only
    if (userType == 2) {
        // 2 getNewHireDash
        GetNewHiresDash hireDash = new GetNewHiresDash();
        try {
            hireDash = getNewHiresDashRepo.getTodaysHire(fiterdate, locId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setNewHire(hireDash);
        // 3 getLeaveCountDash
        LeavePenDash leavePenDash = new LeavePenDash();
        try {
            leavePenDash = leavePenDashRepo.getLeaveCnt(locId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setLvDet(leavePenDash);
        // 5 getAttnDash
        /*
             * PreDayAttnDash preDayAttn = new PreDayAttnDash(); DailyAttendance dailyAttn =
             * new DailyAttendance(); List<DailyAttendance> dailyAttnList = new
             * ArrayList<DailyAttendance>(); try {
             *
             * String fiterdateNew = new String(); dailyAttnList =
             * dailyAttendanceRepository.dailyAttendanceListRec(fiterdate); if
             * (dailyAttnList.size() == 0) { dailyAttn =
             * dailyAttendanceRepository.dailyAttendanceListLastRec(); fiterdateNew =
             * dailyAttn.getAttDate(); } else { fiterdateNew = fiterdate; }
             *
             * preDayAttn = preDayAttnDashRepo.getAttendance(fiterdateNew);
             *
             * preDayAttn.setAttnDate(DateConvertor.convertToDMY(fiterdateNew)); } catch
             * (Exception e) {
             *
             * e.printStackTrace(); } comDash.setAttnDet(preDayAttn);
             */
        // 7 getAllPendingMasterDet
        GetAllPendingMasterDet pendingMast = new GetAllPendingMasterDet();
        try {
            pendingMast = getAllPendingMasterDetRepo.getDet(locId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setMasterDet(pendingMast);
        // 9 getAdvLoanDash
        LoanAdvDashDet advDash = new LoanAdvDashDet();
        LoanAdvDashDet loanDash = new LoanAdvDashDet();
        try {
            advDash = loanAdvDashDetDashRepo.getAdvnceDetails(temp[0], temp[1], locId);
            comDash.setAdvDet(advDash);
            loanDash = loanAdvDashDetDashRepo.getLoanDetails(fiterdate, locId);
            comDash.setLoanDet(loanDash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 11 getDeptWisePerformanceBonus
        List<DeptWiseWeekoffDash> deptWisePerformanceBonusLidt = new ArrayList<DeptWiseWeekoffDash>();
        try {
        // deptWisePerformanceBonusLidt =
        // deptWiseWeekoffDashRepo.getDeptWisePerformanceBonus(temp[1], temp[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setPerfListDept(deptWisePerformanceBonusLidt);
        // getAgeDiversity
        GetNewHiresDash ageDiv = new GetNewHiresDash();
        try {
            ageDiv = getNewHiresDashRepo.getAgeDiversity(locId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setAgeDiv(ageDiv);
        // getAgeDiversityDetail
        AgeDiversityDash tempList = new AgeDiversityDash();
        try {
            tempList = ageDiversityDashRepo.getAttendance(fiterdate, locId);
            comDash.setAgeDiversity(tempList);
            tempList = ageDiversityDashRepo.getExperienceDiversity(fiterdate, locId);
            comDash.setExpDiversity(tempList);
            tempList = ageDiversityDashRepo.getSalaryDiversity(locId);
            comDash.setSalDiversity(tempList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // for HR and Authority
    if (userType == 2) {
        // 6 getDashDeptWiseWeekoff
        List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
        try {
        // list = deptWiseWeekoffDashRepo.getAttendance(fiterdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setDeptwiseWkoff(list);
        // 8 getRewardedDet
        PayRewardDedDash dedDet = new PayRewardDedDash();
        PayRewardDedDash rewardDet = new PayRewardDedDash();
        try {
            dedDet = payRewardDedDashRepo.getDedDetails(temp[0], temp[1], locId);
        // rewardDet = payRewardDedDashRepo.getRewardDetails(temp[0], temp[1], locId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setDedDet(dedDet);
        comDash.setRewardDet(rewardDet);
        // 10 getEmpAbsentLv
        List<DeptWiseWeekoffDash> list1 = new ArrayList<DeptWiseWeekoffDash>();
        try {
        // list1 = deptWiseWeekoffDashRepo.getLeavesAndAbsent(fiterdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setDeptWiseLvAbLList(list1);
        // getEmpLastMonthAttn
        SummaryDailyAttendance summlist = new SummaryDailyAttendance();
        try {
        // summlist =
        // summaryDailyAttendanceRepository.summaryDailyAttendanceList1(temp[1],
        // temp[0], empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comDash.setAttnLastMon(summlist);
    }
    return comDash;
}


@RequestMapping(value = { "/getEmpAbsentLv" }, method = RequestMethod.POST)
@ResponseBody
public List<DeptWiseWeekoffDash> getEmpAbsentLv(String fiterdate){
    List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
    try {
        list = deptWiseWeekoffDashRepo.getLeavesAndAbsent(fiterdate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/rewardAndClaimAmtMonthWise" }, method = RequestMethod.POST)
@ResponseBody
public List<MonthWiseDisbusedAmt> rewardAndClaimAmtMonthWise(int locId,int month,int year){
    // LineGraphData lineGraphData = new LineGraphData();
    List<MonthWiseDisbusedAmt> list = new ArrayList<>();
    try {
        YearMonth thisMonth = YearMonth.of(year, month);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");
        for (int i = 5; i >= 0; i--) {
            YearMonth lastMonth = thisMonth.minusMonths(i);
            MonthWiseDisbusedAmt monthWithOT = new MonthWiseDisbusedAmt();
            monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
            list.add(monthWithOT);
        }
        String defaltdt = year + "-" + month + "-01";
        List<AdvaceAmtGraph> rewardList = advaceAmtGraphRepository.getrewardMonthAmt(locId, defaltdt);
        List<AdvaceAmtGraph> claimList = advaceAmtGraphRepository.getClaimMonthAmt(locId, defaltdt);
        // System.out.println(advanceList);
        // SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
        for (int j = 0; j < list.size(); j++) {
            Date dt = sf.parse(list.get(j).getMonth());
            for (int i = 0; i < rewardList.size(); i++) {
                Date date = sf.parse(rewardList.get(i).getMonth());
                if (dt.compareTo(date) == 0) {
                    list.get(j).setAdvAmt(rewardList.get(i).getAdvTot());
                    break;
                }
            }
            for (int i = 0; i < claimList.size(); i++) {
                Date date = sf.parse(claimList.get(i).getMonth());
                if (dt.compareTo(date) == 0) {
                    list.get(j).setLoanAmt(claimList.get(i).getAdvTot());
                    break;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getRewardWisewiseDeduction" }, method = RequestMethod.POST)
@ResponseBody
public List<DeptWiseWeekoffDash> getRewardWisewiseDeduction(int empId){
    List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
    try {
        list = deptWiseWeekoffDashRepo.getRewardwiseAmt(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getUserApplicableHoliday" }, method = RequestMethod.POST)
@ResponseBody
public List<HolidayMaster> getUserApplicableHoliday(int empId,String date){
    List<HolidayMaster> list = new ArrayList<HolidayMaster>();
    try {
        list = holidayMasterRepo.getUserApplicableHoliday(date, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getPerfProd" }, method = RequestMethod.POST)
@ResponseBody
public List<PerformanceProdDash> getPerfProd(String fiterdate,int empId){
    List<PerformanceProdDash> list = new ArrayList<PerformanceProdDash>();
    PerformanceProdDash prod = new PerformanceProdDash();
    try {
        String[] temp = fiterdate.split("-");
        prod = performanceProdDashRepo.getPerformanceDetails(temp[0], temp[1], empId);
        list.add(prod);
        prod = performanceProdDashRepo.getProdDetails(temp[0], temp[1], empId);
        list.add(prod);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/chkIsAuth" }, method = RequestMethod.POST)
@ResponseBody
public Integer chkIsAuth(int empId){
    int n = 0;
    List<LeaveAuthority> list = new ArrayList<LeaveAuthority>();
    try {
        list = leaveAuthorityRepository.chkAuth(empId);
        n = list.size();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return n;
}


@RequestMapping(value = { "/getAttnDash" }, method = RequestMethod.POST)
@ResponseBody
public PreDayAttnDash getAttnDash(String fiterdate){
    PreDayAttnDash birthHoliDash = new PreDayAttnDash();
    DailyAttendance dailyAttn = new DailyAttendance();
    List<DailyAttendance> dailyAttnList = new ArrayList<DailyAttendance>();
    try {
        String fiterdateNew = "";
        dailyAttnList = dailyAttendanceRepository.dailyAttendanceListRec(fiterdate);
        if (dailyAttnList.size() == 0) {
            dailyAttn = dailyAttendanceRepository.dailyAttendanceListLastRec();
            fiterdateNew = dailyAttn.getAttDate();
        } else {
            fiterdateNew = fiterdate;
        }
        birthHoliDash = preDayAttnDashRepo.getAttendance(fiterdateNew);
        birthHoliDash.setAttnDate(DateConvertor.convertToDMY(fiterdateNew));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return birthHoliDash;
}


@RequestMapping(value = { "/getSelfAttendaceForDashboard" }, method = RequestMethod.POST)
@ResponseBody
public List<SelfAttendanceDetail> getSelfAttendaceForDashboard(String fromDate,int empId,String toDate){
    List<SelfAttendanceDetail> list = new ArrayList<>();
    try {
        list = selfAttendanceDetailRepository.getSelfAttendaceForDashboard(fromDate, empId, toDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpLastMonthAttn" }, method = RequestMethod.POST)
@ResponseBody
public SummaryDailyAttendance getEmpLastMonthRep(String fiterdate,int empId){
    SummaryDailyAttendance list = new SummaryDailyAttendance();
    try {
        String[] temp = fiterdate.split("-");
        list = summaryDailyAttendanceRepository.summaryDailyAttendanceList1(temp[1], temp[0], empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getDeparmentWiseEmpCount" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpDeptWise> getDeparmentWiseEmpCount(int locId){
    List<EmpDeptWise> emp = new ArrayList<EmpDeptWise>();
    try {
        emp = empDeptWiseRepository.getDeparmentWiseEmpCount(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getLeaveHistForDash" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveHistForDash> getLeaveHistForDash(int empId){
    List<GetLeaveHistForDash> list = new ArrayList<GetLeaveHistForDash>();
    try {
        list = getLeaveHistForDashRepo.getLeaveCnt(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/liveAttendaceDashboardData" }, method = RequestMethod.POST)
@ResponseBody
public AttendaceLiveData liveAttendaceDashboardData(int locId,String date){
    AttendaceLiveData attendaceLiveData = new AttendaceLiveData();
    try {
        CountData countData = countDataRepository.countData(locId, date);
        attendaceLiveData.setCountData(countData);
        List<PresentAttendaceList> presentList = presentAttendaceListRepository.presentList(locId, date);
        attendaceLiveData.setPresentList(presentList);
        List<PresentAttendaceList> lateList = presentAttendaceListRepository.lateListList(locId, date);
        attendaceLiveData.setLateList(lateList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return attendaceLiveData;
}


@RequestMapping(value = { "/getDeptWisePerformanceBonus" }, method = RequestMethod.POST)
@ResponseBody
public List<DeptWiseWeekoffDash> getDeptWisePerformanceBonus(String fiterdate){
    List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
    try {
        String[] temp = fiterdate.split("-");
        list = deptWiseWeekoffDashRepo.getDeptWisePerformanceBonus(temp[1], temp[0]);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoForModelGraph" }, method = RequestMethod.POST)
@ResponseBody
public EmpInfoForDashBoard getEmpInfoForModelGraph(int empId){
    EmpInfoForDashBoard emp = new EmpInfoForDashBoard();
    try {
        emp = empInfoForDashBoardRepository.getEmpInfoForModelGraph(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getLateMarkGraph" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpGraphDetail> getLateMarkGraph(int empId){
    List<EmpGraphDetail> emp = new ArrayList<EmpGraphDetail>();
    try {
        emp = empGraphDetailRepository.getLateMarkGraph(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getLeaveApprovalListForDashBoard" }, method = RequestMethod.POST)
@ResponseBody
public List<DashboardLeavePending> getLeaveApprovalListForDashBoard(int type,int locId){
    List<DashboardLeavePending> list = new ArrayList<DashboardLeavePending>();
    try {
        if (type == 1) {
            list = dashboardLeavePendingRepo.getLeaveIntialApprovalListForDashBoard(locId);
        } else if (type == 2) {
            list = dashboardLeavePendingRepo.getLeaveFinalApprovalListForDashBoard(locId);
        } else if (type == 3) {
            list = dashboardLeavePendingRepo.getOptionalHolidatApprovalListForDashBoard(locId);
        } else if (type == 4) {
            list = dashboardLeavePendingRepo.getClaimIntialApprovalListForDashBoard(locId);
        } else if (type == 5) {
            list = dashboardLeavePendingRepo.getClaimFinalApprovalListForDashBoard(locId);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/totalOtPrevioussixMonth" }, method = RequestMethod.POST)
@ResponseBody
public LineGraphData totalOtPrevioussixMonth(int locId,int month,int year){
    LineGraphData lineGraphData = new LineGraphData();
    List<MonthWithOT> list = new ArrayList<>();
    try {
        YearMonth thisMonth = YearMonth.of(year, month);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");
        for (int i = 5; i >= 0; i--) {
            YearMonth lastMonth = thisMonth.minusMonths(i);
            MonthWithOT monthWithOT = new MonthWithOT();
            monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
            list.add(monthWithOT);
        }
        String defaltdt = year + "-" + month + "-01";
        List<TotalOT> emp = totalOTRepository.totalOtPrevioussixMonth(locId, defaltdt);
        List<TotalOT> deptList = totalOTRepository.deptList(locId, defaltdt);
        for (int i = 0; i < deptList.size(); i++) {
            deptList.get(i).setId("0");
            deptList.get(i).setDateMo("-");
            deptList.get(i).setMonth("-");
            deptList.get(i).setOt(0);
        }
        SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
        for (int j = 0; j < list.size(); j++) {
            List<TotalOT> otlist = new ArrayList<>();
            int flag = 0;
            Date dt = sf.parse(list.get(j).getMonth());
            for (int i = 0; i < emp.size(); i++) {
                Date date = yy.parse(emp.get(i).getDateMo());
                if (dt.compareTo(date) == 0) {
                    otlist.add(emp.get(i));
                    flag = 1;
                }
            }
            if (flag == 0) {
                for (int i = 0; i < deptList.size(); i++) {
                    otlist.add(deptList.get(i));
                }
            }
            list.get(j).setOtlist(otlist);
        }
        lineGraphData.setList(list);
        lineGraphData.setDeptList(deptList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lineGraphData;
}


@RequestMapping(value = { "/getAttendaceByMonth" }, method = RequestMethod.POST)
@ResponseBody
public SummaryDailyAttendance getAttendaceByMonth(int empId,String month,String year){
    SummaryDailyAttendance summlist = new SummaryDailyAttendance();
    try {
        summlist = summaryDailyAttendanceRepository.summaryDailyAttendanceList1(month, year, empId);
        if (summlist == null) {
            summlist = new SummaryDailyAttendance();
        }
    } catch (Exception e) {
        summlist = new SummaryDailyAttendance();
        e.printStackTrace();
    }
    return summlist;
}


@RequestMapping(value = { "/getDashDeptWiseWeekoff" }, method = RequestMethod.POST)
@ResponseBody
public List<DeptWiseWeekoffDash> getDashDeptWiseWeekoff(String fiterdate){
    List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
    try {
        list = deptWiseWeekoffDashRepo.getAttendance(fiterdate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/disbusedAmtMonthWise" }, method = RequestMethod.POST)
@ResponseBody
public List<MonthWiseDisbusedAmt> disbusedAmtMonthWise(int locId,int month,int year){
    // LineGraphData lineGraphData = new LineGraphData();
    List<MonthWiseDisbusedAmt> list = new ArrayList<>();
    try {
        YearMonth thisMonth = YearMonth.of(year, month);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");
        for (int i = 5; i >= 0; i--) {
            YearMonth lastMonth = thisMonth.minusMonths(i);
            MonthWiseDisbusedAmt monthWithOT = new MonthWiseDisbusedAmt();
            monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
            list.add(monthWithOT);
        }
        String defaltdt = year + "-" + month + "-01";
        List<AdvaceAmtGraph> advanceList = advaceAmtGraphRepository.getAdvanceMonthAmt(locId, defaltdt);
        List<AdvaceAmtGraph> loanList = advaceAmtGraphRepository.getLoanMonthAmt(locId, defaltdt);
        // System.out.println(advanceList);
        // SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
        for (int j = 0; j < list.size(); j++) {
            Date dt = sf.parse(list.get(j).getMonth());
            for (int i = 0; i < advanceList.size(); i++) {
                Date date = sf.parse(advanceList.get(i).getMonth());
                if (dt.compareTo(date) == 0) {
                    list.get(j).setAdvAmt(advanceList.get(i).getAdvTot());
                    break;
                }
            }
            for (int i = 0; i < loanList.size(); i++) {
                Date date = sf.parse(loanList.get(i).getMonth());
                if (dt.compareTo(date) == 0) {
                    list.get(j).setLoanAmt(loanList.get(i).getAdvTot());
                    break;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getDedTypewiseDeduction" }, method = RequestMethod.POST)
@ResponseBody
public List<DeptWiseWeekoffDash> getDedTypewiseDeduction(int empId){
    List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
    try {
        list = deptWiseWeekoffDashRepo.getDedTypewiseAmt(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpTotAmtsDash" }, method = RequestMethod.POST)
@ResponseBody
public IncentivesAmtDash getEmpTotAmtsDash(int empId){
    IncentivesAmtDash list = new IncentivesAmtDash();
    try {
        list = incentivesAmtDashRepo.getWeekBirth(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}