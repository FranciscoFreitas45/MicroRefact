import com.ats.hrmgt.advance.repository.AdvanceRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.SalAllownceCal;
import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.graph.EmpAdvanceGraph;
import com.ats.hrmgt.model.graph.EmpDailyAttendanceGraph;
import com.ats.hrmgt.model.graph.EmpLoanGraph;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.SalAllownceCalRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class GraphApiController {

@Autowired
 private EmpSalaryInfoRepo empSalaryInfoRepo;

@Autowired
 private SalaryCalcRepo salaryCalcRepo;

@Autowired
 private SalAllownceCalRepo salAllownceCalRepo;

@Autowired
 private SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

@Autowired
 private AdvanceRepo advanceRepo;

@Autowired
 private LoanMainRepo loanMainRepo;


@RequestMapping(value = { "/getEmpAdvanceGraph" }, method = RequestMethod.POST)
public List<EmpAdvanceGraph> getEmpAdvanceGraph(int empId,int companyId){
    List<EmpAdvanceGraph> list = new ArrayList<EmpAdvanceGraph>();
    try {
        EmpSalaryInfo emp = empSalaryInfoRepo.findByEmpIdAndDelStatus(empId, 1);
        List<String> dateList = DateConvertor.getAllMonth(emp.getCmpJoiningDate());
        List<Advance> attList = advanceRepo.findByEmpIdAndDelStatus(empId, 1);
        // System.err.println(" attList" + attList.toString());
        for (int i = 0; i < dateList.size(); i++) {
            EmpAdvanceGraph dailyrec = new EmpAdvanceGraph();
            String[] a = dateList.get(i).split("-");
            int year = Integer.parseInt(a[1]);
            int month = Integer.parseInt(a[0]);
            double advanceAmt = 0;
            for (int j = 0; j < attList.size(); j++) {
                String[] mon = attList.get(j).getAdvDate().split("-");
                if (Integer.parseInt(mon[1]) == month && Integer.parseInt(mon[0]) == year) {
                    advanceAmt = attList.get(j).getAdvAmount();
                    break;
                }
            }
            dailyrec.setDate(dateList.get(i));
            dailyrec.setMonth(month);
            dailyrec.setYear(year);
            dailyrec.setAdvanceAmt(advanceAmt);
            list.add(dailyrec);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpAdvanceGraphNew" }, method = RequestMethod.POST)
public List<EmpAdvanceGraph> getEmpAdvanceGraphNew(int empId,int companyId,String fromDate,String toDate){
    List<EmpAdvanceGraph> list = new ArrayList<EmpAdvanceGraph>();
    try {
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
        List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);
        // System.err.println(" dateList" + dateList.toString());
        List<Advance> advanceList = advanceRepo.findByEmpIdAndDelStatus(empId, 1);
        // System.err.println(" advanceList" + advanceList.toString());
        for (int i = 0; i < dateList.size(); i++) {
            EmpAdvanceGraph dailyrec = new EmpAdvanceGraph();
            String[] a = dateList.get(i).split("-");
            int year = Integer.parseInt(a[1]);
            int month = Integer.parseInt(a[0]);
            double advanceAmt = 0;
            for (int j = 0; j < advanceList.size(); j++) {
                String[] mon = advanceList.get(j).getAdvDate().split("-");
                if (Integer.parseInt(mon[1]) == month && Integer.parseInt(mon[0]) == year) {
                    advanceAmt = advanceAmt + advanceList.get(j).getAdvAmount();
                // break;
                }
            }
            String c = Month.of(month).name();
            dailyrec.setDate(shortMonths[month - 1].concat("-").concat(String.valueOf(year)));
            dailyrec.setMonth(month);
            dailyrec.setYear(year);
            dailyrec.setAdvanceAmt(advanceAmt);
            list.add(dailyrec);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpLoanGraph" }, method = RequestMethod.POST)
public List<EmpLoanGraph> getEmpLoanGraph(int empId,int companyId,String fromDate,String toDate){
    List<EmpLoanGraph> list = new ArrayList<EmpLoanGraph>();
    String[] shortMonths = new DateFormatSymbols().getShortMonths();
    try {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
        List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);
        // System.err.println(" dateList" + dateList.toString());
        List<LoanMain> loanList = loanMainRepo.findByEmpIdAndDelStatus(empId, 1);
        // System.err.println(" loanList" + loanList.toString());
        for (int i = 0; i < dateList.size(); i++) {
            EmpLoanGraph dailyrec = new EmpLoanGraph();
            String[] a = dateList.get(i).split("-");
            int year = Integer.parseInt(a[1]);
            int month = Integer.parseInt(a[0]);
            double loanAmt = 0;
            for (int j = 0; j < loanList.size(); j++) {
                String[] mon = String.valueOf(loanList.get(j).getLoanRepayStart()).split("-");
                if (Integer.parseInt(mon[1]) == month && Integer.parseInt(mon[0]) == year) {
                    loanAmt = loanAmt + loanList.get(j).getLoanAmt();
                // break;
                }
            }
            dailyrec.setMonth(month);
            dailyrec.setYear(year);
            dailyrec.setLoanAmt(loanAmt);
            String c = Month.of(month).name();
            dailyrec.setDate(shortMonths[month - 1].concat("-").concat(String.valueOf(year)));
            list.add(dailyrec);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpAttendanceGraphNew" }, method = RequestMethod.POST)
public List<EmpDailyAttendanceGraph> getEmpAttendanceGraphNew(int empId,int companyId,String fromDate,String toDate){
    List<EmpDailyAttendanceGraph> list = new ArrayList<EmpDailyAttendanceGraph>();
    try {
        System.out.println("*********************" + fromDate + " " + toDate);
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
        List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);
        System.err.println("datelist " + dateList.toString());
        /*
             * List<SummaryDailyAttendance> attList = summaryDailyAttendanceRepository
             * .findAllByCompanyIdAndEmpId(companyId, empId);
             */
        String startDate = fromDate;
        String lastDate = toDate;
        String[] parts = startDate.split("-");
        String fmonth = parts[1];
        String fyear = parts[2];
        System.out.println("From-------" + fmonth + " " + fyear);
        String[] lastparts = lastDate.split("-");
        String lmonth = lastparts[1];
        String lyear = lastparts[2];
        System.out.println("To------------" + lmonth + " " + lyear);
        List<SummaryDailyAttendance> attList = summaryDailyAttendanceRepository.findAllByCompanyIdAndEmpId(companyId, empId, fmonth, fyear, lmonth, lyear);
        System.out.println("List:::::::::::" + attList);
        // System.err.println(" attList" + attList.toString());
        for (int i = 0; i < dateList.size(); i++) {
            EmpDailyAttendanceGraph dailyrec = new EmpDailyAttendanceGraph();
            double workingDays = 0;
            double presentdays = 0;
            double paidHoliday = 0;
            double unpaidHoliday = 0;
            double paidLeave = 0;
            double unpaidLeave = 0;
            double monthDays = 0;
            double payableDays = 0;
            int lateMarks = 0;
            String[] a = dateList.get(i).split("-");
            int year = Integer.parseInt(a[1]);
            int month = Integer.parseInt(a[0]);
            for (int j = 0; j < attList.size(); j++) {
                if (attList.get(j).getMonth() == month && attList.get(j).getYear() == year) {
                    workingDays = attList.get(j).getWorkingDays();
                    presentdays = attList.get(j).getPresentDays();
                    paidHoliday = attList.get(j).getPaidHoliday();
                    paidLeave = attList.get(j).getPaidLeave();
                    unpaidLeave = attList.get(j).getUnpaidLeave() + attList.get(j).getUnpaidHoliday() + attList.get(j).getAbsentDays();
                    monthDays = attList.get(j).getTotalDaysInmonth();
                    lateMarks = attList.get(j).getTotLate();
                    payableDays = attList.get(j).getPayableDays();
                    dailyrec.setMonthDays(monthDays);
                    dailyrec.setPaidHoliday(paidHoliday);
                    dailyrec.setPaidLeave(paidLeave);
                    dailyrec.setPresentdays(presentdays);
                    dailyrec.setUnpaidHoliday(unpaidHoliday);
                    dailyrec.setUnpaidLeave(unpaidLeave);
                    dailyrec.setWorkingDays(workingDays);
                    dailyrec.setLateMarks(lateMarks);
                    dailyrec.setPayableDaysDays(payableDays);
                    dailyrec.setWeekOff(attList.get(j).getWeeklyOff());
                    break;
                }
            }
            String c = Month.of(month).name();
            dailyrec.setDate(shortMonths[month - 1].concat("-").concat(String.valueOf(year)));
            dailyrec.setMonth(month);
            dailyrec.setYear(year);
            list.add(dailyrec);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpAttendanceSum" }, method = RequestMethod.POST)
public List<EmpDailyAttendanceGraph> getEmpAttendanceSum(int companyId,int locId,String fromDate,String toDate){
    List<EmpDailyAttendanceGraph> list = new ArrayList<EmpDailyAttendanceGraph>();
    try {
        // String[] shortMonths = new DateFormatSymbols().getShortMonths();
        // Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
        // Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
        // List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);
        // System.err.println("datelist " + dateList.toString());
        String startDate = fromDate;
        String lastDate = toDate;
        String[] parts = startDate.split("-");
        String fmonth = parts[1];
        String fyear = parts[2];
        // System.out.println("From-------"+fmonth+" "+fyear);
        String[] lastparts = lastDate.split("-");
        String lmonth = lastparts[1];
        String lyear = lastparts[2];
        // System.out.println("To------------"+lmonth+" "+lyear);
        List<SummaryDailyAttendance> attList = summaryDailyAttendanceRepository.findAllByCompanyId(locId, fmonth, fyear, lmonth, lyear);
        // System.out.println("List:::::::::::"+attList);
        // System.err.println(" attList" + attList.toString());
        double unpaidLeave = 0;
        double monthDays = 0;
        for (int j = 0; j < attList.size(); j++) {
            EmpDailyAttendanceGraph dailyrec = new EmpDailyAttendanceGraph();
            int month = attList.get(j).getMonth();
            int year = attList.get(j).getYear();
            unpaidLeave = attList.get(j).getUnpaidLeave() + attList.get(j).getUnpaidHoliday() + attList.get(j).getAbsentDays();
            monthDays = attList.get(j).getTotalDaysInmonth();
            dailyrec.setMonthDays(attList.get(j).getTotalDaysInmonth());
            dailyrec.setPaidHoliday(attList.get(j).getPaidHoliday());
            dailyrec.setPaidLeave(attList.get(j).getPaidLeave());
            dailyrec.setPresentdays(attList.get(j).getPresentDays());
            dailyrec.setUnpaidLeave(unpaidLeave);
            dailyrec.setWorkingDays(attList.get(j).getWorkingDays());
            dailyrec.setLateMarks(attList.get(j).getTotLate());
            dailyrec.setPayableDaysDays(attList.get(j).getPayableDays());
            dailyrec.setWeekOff(attList.get(j).getWeeklyOff());
            dailyrec.setEmpName(attList.get(j).getEmpName());
            dailyrec.setEmpCode(attList.get(j).getEmpCode());
            dailyrec.setDate(new DateFormatSymbols().getMonths()[month - 1].concat("-").concat(String.valueOf(year)));
            dailyrec.setMonth(month);
            dailyrec.setYear(year);
            list.add(dailyrec);
        }
    } catch (Exception e) {
        System.err.println("Excep in getEmpAttendanceSum : " + e.getMessage());
        e.printStackTrace();
    }
    // System.out.println("List:::::::::::"+list);
    return list;
}


@RequestMapping(value = { "/getEmpSalaryGraph" }, method = RequestMethod.POST)
public List<BonusCalc> getAllEmployeeDetailForBonus(int empId,int companyId){
    List<BonusCalc> list = new ArrayList<BonusCalc>();
    try {
        EmpSalaryInfo emp = empSalaryInfoRepo.findByEmpIdAndDelStatus(empId, 1);
        List<String> dateList = DateConvertor.getAllMonth(emp.getCmpJoiningDate());
        List<SalaryCalc> calcList = salaryCalcRepo.findAllByEmpId(empId);
        List<SalAllownceCal> allwList = salAllownceCalRepo.findByEmpId(empId);
    /*
             * for(int i=0;i<dateList.size();i++) {
             *
             * String a[]=dateList.get(i).split("-"); String year=a[1]; String month=a[0];
             *
             *
             *
             *
             *
             *
             *
             * }
             */
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


}