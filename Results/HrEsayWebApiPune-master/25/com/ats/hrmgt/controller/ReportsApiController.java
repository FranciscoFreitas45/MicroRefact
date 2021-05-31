import com.ats.hrmgt.model.EmpDetailForLetters;
import com.ats.hrmgt.model.graph.EmpDefaultSalaryGraph;
import com.ats.hrmgt.model.report.EmpLateMarkDetails;
import com.ats.hrmgt.model.report.EmpOtReg;
import com.ats.hrmgt.model.report.LoanStatementDetailsReport;
import com.ats.hrmgt.model.report.PendingLoanReport;
import com.ats.hrmgt.repo.EmpDetailForLettersRepo;
import com.ats.hrmgt.repo.report.EmpLateMarkDetailsRepo;
import com.ats.hrmgt.repo.report.EmpOtRegRepo;
import com.ats.hrmgt.repo.report.LoanStatementRepo;
import com.ats.hrmgt.repo.report.PendingLoanRepo;
import com.ats.hrmgt.repository.EmpDefaultSalaryGraphRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
@RestController
public class ReportsApiController {

@Autowired
 private PendingLoanRepo pendLoanRepo;

@Autowired
 private LoanStatementRepo loanStatRepo;

@Autowired
 private EmpOtRegRepo otRepo;

@Autowired
 private EmpLateMarkDetailsRepo empLateRepo;

@Autowired
 private SalaryCalcRepo salCalRepo;

@Autowired
 private EmpDetailForLettersRepo empDetailForLettersRepo;

@Autowired
 private EmpDefaultSalaryGraphRepo defSalRepo;


@RequestMapping(value = { "/getDefaultSalByEmpId" }, method = RequestMethod.POST)
public List<EmpDefaultSalaryGraph> getEmployeeById(int empId,int companyId,String fromDate,String toDate){
    List<EmpDefaultSalaryGraph> salCal = new ArrayList<EmpDefaultSalaryGraph>();
    try {
        String frmDate = fromDate;
        // System.out.println("From Date Before-----------"+frmDate);
        String[] parts = frmDate.split("-");
        String month = parts[0];
        String year = parts[1];
        String newfromDate = year + "-" + month + "-" + "01";
        // System.out.println("From Date After-----------"+newfromDate);
        String tDate = toDate;
        // System.out.println("To Date Before-----------"+toDate);
        String[] toparts = tDate.split("-");
        String tomonth = toparts[0];
        String toyear = toparts[1];
        String newToDate = toyear + "-" + tomonth + "-" + "01";
        // System.out.println("From Date After-----------"+newToDate);
        salCal = defSalRepo.getDefGrossSalByEmpId(newfromDate, newToDate, empId);
    } catch (Exception e) {
        System.err.println("Excep in getDefaultSalByEmpId : " + e.getMessage());
        e.printStackTrace();
    }
    return salCal;
}


@RequestMapping(value = { "/getEmpLateMarkSummary" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpLateMarkDetails> getEmpLateMarkSummary(int locId,String fromDate,String toDate){
    List<EmpLateMarkDetails> list = new ArrayList<EmpLateMarkDetails>();
    try {
        String frmDate = fromDate;
        // System.out.println("From Date Before-----------"+frmDate);
        String[] parts = frmDate.split("-");
        String date = parts[2];
        String month = parts[1];
        String year = parts[0];
        // System.out.println("From Date After-----------"+date+"/"+month+"/"+year);
        String tDate = toDate;
        // System.out.println("To Date Before-----------"+toDate);
        String[] toparts = tDate.split("-");
        String todate = toparts[2];
        String tomonth = toparts[1];
        String toyear = toparts[0];
        // System.out.println("To Date After-----------"+todate+"/"+tomonth+"/"+toyear);
        list = empLateRepo.getEmpLateMarkSummaryReport(locId, month, year, tomonth, toyear);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpLateMarkDetailsByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpLateMarkDetails> getEmpLateMarkDetailsByEmpId(int empId,String fromDate,String toDate){
    List<EmpLateMarkDetails> list = new ArrayList<EmpLateMarkDetails>();
    try {
        String frmDate = fromDate;
        System.out.println("From Date Before-----------" + frmDate);
        String[] parts = frmDate.split("-");
        String month = parts[0];
        String year = parts[1];
        System.out.println("From Date After-----------" + month + "/" + year);
        String tDate = toDate;
        System.out.println("To Date Before-----------" + toDate);
        String[] toparts = tDate.split("-");
        String tomonth = toparts[0];
        String toyear = toparts[1];
        System.out.println("To Date After-----------" + tomonth + "/" + toyear);
        // Get the number of days in that month
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(toyear), Integer.parseInt(tomonth));
        // 28
        int daysInMonth = yearMonthObject.lengthOfMonth();
        // System.out.println("Ttl Days----------"+daysInMonth);
        list = empLateRepo.getEmpLateMarkDetailReportByEmpId(month, year, tomonth, toyear, empId, daysInMonth);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getGrossSalByEmpId" }, method = RequestMethod.POST)
public List<EmpDefaultSalaryGraph> getGrossSalByEmpId(int empId,int companyId,String fromDate,String toDate){
    List<EmpDefaultSalaryGraph> salCal = new ArrayList<EmpDefaultSalaryGraph>();
    try {
        String frmDate = fromDate;
        // System.out.println("From Date Before-----------"+frmDate);
        String[] parts = frmDate.split("-");
        String month = parts[0];
        String year = parts[1];
        String newfromDate = year + "-" + month + "-" + "01";
        // System.out.println("From Date After-----------"+newfromDate);
        String tDate = toDate;
        // System.out.println("To Date Before-----------"+toDate);
        String[] toparts = tDate.split("-");
        String tomonth = toparts[0];
        String toyear = toparts[1];
        String newToDate = toyear + "-" + tomonth + "-" + "01";
        // System.out.println("From Date After-----------"+newToDate);
        salCal = defSalRepo.getGrossSalByEmpId(newfromDate, newToDate, empId);
    } catch (Exception e) {
        System.err.println("Excep in getDefaultSalByEmpId : " + e.getMessage());
        e.printStackTrace();
    }
    return salCal;
}


@RequestMapping(value = { "/getEmpPendingLoanReport" }, method = RequestMethod.POST)
@ResponseBody
public List<PendingLoanReport> getEmpPendingLoanReport(int locId,String fromDate,String toDate){
    List<PendingLoanReport> list = new ArrayList<PendingLoanReport>();
    try {
        list = pendLoanRepo.getEmpPendingLoanDetails(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLoanStatemnetReport" }, method = RequestMethod.POST)
@ResponseBody
public List<LoanStatementDetailsReport> getLoanStatemnetReport(int locId,String fromDate,String toDate){
    List<LoanStatementDetailsReport> list = new ArrayList<LoanStatementDetailsReport>();
    try {
        list = loanStatRepo.getEmpLoanStateDetails(locId, fromDate, toDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpDetailForGenrateLetters" }, method = RequestMethod.POST)
@ResponseBody
public EmpDetailForLetters getEmpDetailForGenrateLetters(int empId){
    EmpDetailForLetters empDetailForLetters = new EmpDetailForLetters();
    try {
        empDetailForLetters = empDetailForLettersRepo.getEmpPendingLoanDetails(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return empDetailForLetters;
}


@RequestMapping(value = { "/getEmpLateMarkDetails" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpLateMarkDetails> getEmpLateMarkDetails(int locId,String fromDate,String toDate){
    List<EmpLateMarkDetails> list = new ArrayList<EmpLateMarkDetails>();
    try {
        String frmDate = fromDate;
        // System.out.println("From Date Before-----------"+frmDate);
        String[] parts = frmDate.split("-");
        String date = parts[2];
        String month = parts[1];
        String year = parts[0];
        // System.out.println("From Date After-----------"+date+"/"+month+"/"+year);
        String tDate = toDate;
        // System.out.println("To Date Before-----------"+toDate);
        String[] toparts = tDate.split("-");
        String todate = toparts[2];
        String tomonth = toparts[1];
        String toyear = toparts[0];
        // System.out.println("To Date After-----------"+todate+"/"+tomonth+"/"+toyear);
        list = empLateRepo.getEmpLateMarkDetailReport(locId, fromDate, toDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpOtRegSummary" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpOtReg> getEmpOtRegSummary(int locId,String fromDate,String toDate){
    List<EmpOtReg> list = new ArrayList<EmpOtReg>();
    try {
        String frmDate = fromDate;
        // System.out.println("From Date Before-----------"+frmDate);
        String[] parts = frmDate.split("-");
        String year = parts[0];
        String month = parts[1];
        String date = parts[2];
        System.out.println("From Date After-----------" + year + "/" + month + "/" + date);
        String tDate = toDate;
        // System.out.println("To Date Before-----------"+toDate);
        String[] toparts = tDate.split("-");
        String toyear = toparts[0];
        String tomonth = toparts[1];
        String todate = toparts[2];
        System.out.println("To Date After-----------" + year + "/" + month + "/" + date);
        list = otRepo.getEmpOtSummary(locId, month, year, tomonth, toyear);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLoanStatemnetReportByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<LoanStatementDetailsReport> getLoanStatemnetReportByEmpId(int empId,String fromDate,String toDate){
    List<LoanStatementDetailsReport> list = new ArrayList<LoanStatementDetailsReport>();
    try {
        String frmDate = fromDate;
        // System.out.println("From Date Before-----------"+frmDate);
        String[] parts = frmDate.split("-");
        String month = parts[0];
        String year = parts[1];
        String newfromDate = year + "-" + month + "-" + "01";
        // System.out.println("From Date After-----------"+newfromDate);
        String tDate = toDate;
        // System.out.println("To Date Before-----------"+toDate);
        String[] toparts = tDate.split("-");
        String tomonth = toparts[0];
        String toyear = toparts[1];
        String newToDate = toyear + "-" + tomonth + "-" + "01";
        // System.out.println("From Date After-----------"+newToDate);
        list = loanStatRepo.getEmpLoanStateDetailsByEmpId(newfromDate, newToDate, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpOtRegDetails" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpOtReg> getEmpOtRegDetails(int locId,String fromDate,String toDate){
    List<EmpOtReg> list = new ArrayList<EmpOtReg>();
    try {
        list = otRepo.getEmpOtDetails(locId, fromDate, toDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}