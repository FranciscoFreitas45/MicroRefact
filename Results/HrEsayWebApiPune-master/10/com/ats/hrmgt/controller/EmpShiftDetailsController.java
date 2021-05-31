import com.ats.hrmgt.model;
import com.ats.hrmgt.repository;
import com.ats.hrmgt.service.CommonFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
@RestController
public class EmpShiftDetailsController {

@Autowired
 private EmployeeMasterRepository empRepo;

@Autowired
 private WeeklyOffRepo weeklyOffRepo1;

@Autowired
 private CommonFunctionService commonFunctionService;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private ShiftMasterRepository shiftMasterRepository;

@Autowired
 private DailyAttendanceRepository dailyAttendanceRepository;

@Autowired
 private LeaveApplyRepository leaveApplyRepository;

@Autowired
 private HolidayRepo holidayRepo;

@Autowired
 private WeeklyOffShitRepository weeklyOffShitRepository;

@Autowired
 private EmpShiftDetailsRepo empShiftDetailsRepo;


@RequestMapping(value = { "/getEmpShiftDetails" }, method = RequestMethod.POST)
public List<EmpShiftDetails> getEmpShiftDetails(String nextMonthDay,String today,int companyId,String empRes,int daysNext,int daysToday){
    List<EmpShiftDetails> empShiftList = new ArrayList<EmpShiftDetails>();
    try {
        String[] a = today.split("-");
        String year = a[0];
        String month = a[1];
        if (month.length() == 1) {
            month = "0".concat(month);
        }
        String[] b = nextMonthDay.split("-");
        String year1 = b[0];
        String month1 = b[1];
        if (month1.length() == 1) {
            month1 = "0".concat(month1);
        }
        String fromDate = String.valueOf(year).concat("-").concat(month).concat("-").concat("01");
        String toDate1 = String.valueOf(year1).concat("-").concat(month1).concat("-").concat(String.valueOf(daysNext));
        List<ShiftMaster> shiftmList = shiftMasterRepository.findByStatus(1);
        List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();
        if (empRes.equals("-1")) {
            try {
                emplist = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
            } catch (Exception e) {
                System.err.println("Excep in getAllEmployee : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                emplist = empRepo.findByDelStatusAndEmpId(1, Integer.parseInt(empRes));
            } catch (Exception e) {
                System.err.println("Excep in getAllEmployee : " + e.getMessage());
                e.printStackTrace();
            }
        }
        // to get week_start_day
        Setting settingList = new Setting();
        settingList = settingRepo.findByKey("week_start_day");
        String startDay = settingList.getValue();
        for (int i = 0; i < emplist.size(); i++) {
            int empId = emplist.get(i).getEmpId();
            int locId = emplist.get(i).getLocationId();
            int currShiftId = 0;
            String currShiftName = null;
            DailyAttendance dailyRec = dailyAttendanceRepository.findLastMonthRecordOfEmp(empId);
            if (dailyRec != null) {
                currShiftId = dailyRec.getCurrentShiftid();
                currShiftName = dailyRec.getCurrentShiftname();
            } else {
                currShiftId = emplist.get(i).getCurrentShiftid();
                ShiftMaster shiftm = new ShiftMaster();
                for (int s = 0; s < shiftmList.size(); s++) {
                    if (shiftmList.get(s).getId() == currShiftId) {
                        shiftm = shiftmList.get(s);
                        break;
                    }
                }
                currShiftName = shiftm.getShiftname();
            }
            for (int j = 1; j <= daysToday; j++) {
                // System.out.println("emp "+empId+" day " + j);
                EmpShiftDetails empDet = new EmpShiftDetails();
                String day = null;
                if (String.valueOf(j).length() == 1) {
                    day = "0".concat(String.valueOf(j));
                } else {
                    day = String.valueOf(j);
                }
                String dateOfMonth = String.valueOf(year).concat("-").concat(month).concat("-").concat(day);
                Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);
                if (startDay.equals(dayOfWeek)) {
                    ShiftMaster shiftm = new ShiftMaster();
                    for (int s = 0; s < shiftmList.size(); s++) {
                        if (shiftmList.get(s).getId() == currShiftId) {
                            shiftm = shiftmList.get(s);
                            break;
                        }
                    }
                    if (shiftm.getChangeable() == 1) {
                        currShiftId = shiftm.getChangewith();
                        for (int s = 0; s < shiftmList.size(); s++) {
                            if (shiftmList.get(s).getId() == currShiftId) {
                                shiftm = shiftmList.get(s);
                                break;
                            }
                        }
                        currShiftName = shiftm.getShiftname();
                    }
                }
                empDet.setEmpId(empId);
                empDet.setShiftId(currShiftId);
                empDet.setShiftName(currShiftName);
                empDet.setDay(j);
                empDet.setDateOfMonth(dateOfMonth);
                empDet.setLocationId(locId);
                empDet.setMonth(Integer.parseInt(month));
                empDet.setYear(Integer.parseInt(year));
                empShiftList.add(empDet);
            }
            for (int j = 1; j <= daysNext; j++) {
                // System.out.println("emp "+empId+" day " + j);
                EmpShiftDetails empDet = new EmpShiftDetails();
                String day = null;
                if (String.valueOf(j).length() == 1) {
                    day = "0".concat(String.valueOf(j));
                } else {
                    day = String.valueOf(j);
                }
                String dateOfMonth = String.valueOf(year1).concat("-").concat(month1).concat("-").concat(day);
                Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);
                if (startDay.equals(dayOfWeek)) {
                    ShiftMaster shiftm = new ShiftMaster();
                    for (int s = 0; s < shiftmList.size(); s++) {
                        if (shiftmList.get(s).getId() == currShiftId) {
                            shiftm = shiftmList.get(s);
                            break;
                        }
                    }
                    if (shiftm.getChangeable() == 1) {
                        currShiftId = shiftm.getChangewith();
                        for (int s = 0; s < shiftmList.size(); s++) {
                            if (shiftmList.get(s).getId() == currShiftId) {
                                shiftm = shiftmList.get(s);
                                break;
                            }
                        }
                        currShiftName = shiftm.getShiftname();
                    }
                }
                empDet.setEmpId(empId);
                empDet.setShiftId(currShiftId);
                empDet.setShiftName(currShiftName);
                empDet.setDay(j);
                empDet.setDateOfMonth(dateOfMonth);
                empDet.setLocationId(locId);
                empDet.setMonth(Integer.parseInt(month1));
                empDet.setYear(Integer.parseInt(year1));
                empShiftList.add(empDet);
            }
        }
        // same ************************
        // to get weekoff of that month
        List<WeeklyOff> weeklyOfflist = weeklyOffRepo1.getWeeklyOffList();
        // to get leaves of that month
        List<LeaveApply> leavetList = leaveApplyRepository.getleavetList(fromDate, toDate1);
        // System.err.println("leavetList" + leavetList.toString());
        // to get holidey of that month
        List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate1);
        List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate1);
        List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.weeklyOffShitFromList(fromDate, toDate1);
        for (int m = 0; m < empShiftList.size(); m++) {
            int holidayCat = 0;
            int WeekoffCat = 0;
            int empIdNew = empShiftList.get(m).getEmpId();
            String calcDate = empShiftList.get(m).getDateOfMonth();
            int location = empShiftList.get(m).getLocationId();
            List<LeaveApply> leavetListMain = new ArrayList<LeaveApply>();
            for (int l = 0; l < emplist.size(); l++) {
                if (emplist.get(l).getEmpId() == empIdNew) {
                    holidayCat = emplist.get(l).getHolidayCategory();
                    WeekoffCat = emplist.get(l).getWeekendCategory();
                    break;
                }
            }
            if (leavetList != null) {
                for (int p = 0; p < leavetList.size(); p++) {
                    if (leavetList.get(p).getEmpId() == empIdNew) {
                        leavetListMain.add(leavetList.get(p));
                    }
                }
            }
            int weekEndStatus = commonFunctionService.findDateInWeekEnd(calcDate, calcDate, weeklyOfflist, weeklyOffShitList, empShiftList.get(m).getLocationId(), WeekoffCat, empIdNew, weeklyOffShitFromList);
            int holidayStatus = commonFunctionService.findDateInHoliday(calcDate, calcDate, holidayList, location, holidayCat);
            LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(calcDate, leavetListMain, empIdNew);
            if (holidayStatus == 3) {
                // System.err.println("holday"+calcDate);
                empShiftList.get(m).setShiftName("Holiday");
                empShiftList.get(m).setShiftId(-1);
            } else if (weekEndStatus == 1) {
                // System.err.println("holday"+calcDate);
                empShiftList.get(m).setShiftName("Weeklyoff");
                empShiftList.get(m).setShiftId(-2);
            } else if (stsInfo.getSts() == 5) {
                // System.err.println("Leave"+calcDate);
                empShiftList.get(m).setShiftName("Leave");
                empShiftList.get(m).setShiftId(-3);
            }
        }
    // System.err.println("daysList"+empShiftList.toString());
    } catch (Exception e) {
        System.err.println("Excep in getDesignationById : " + e.getMessage());
        e.printStackTrace();
    }
    return empShiftList;
}


@RequestMapping(value = { "/getAllEmp" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeeMaster> getLeaveTypeById(String empRes,int companyId){
    List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();
    if (empRes.equals("-1")) {
        try {
            emplist = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
        } catch (Exception e) {
            System.err.println("Excep in getAllEmployee : " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        try {
            emplist = empRepo.findByDelStatusAndEmpId(1, Integer.parseInt(empRes));
        } catch (Exception e) {
            System.err.println("Excep in getAllEmployee : " + e.getMessage());
            e.printStackTrace();
        }
    }
    return emplist;
}


}