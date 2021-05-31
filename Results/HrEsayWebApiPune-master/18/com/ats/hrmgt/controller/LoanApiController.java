import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.loan.GetLoan;
import com.ats.hrmgt.model.loan.LoanCalculation;
import com.ats.hrmgt.model.loan.LoanDetails;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.GetLoanRepo;
import com.ats.hrmgt.repo.loan.LoanDetailsRepo;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.SettingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class LoanApiController {

@Autowired
 private LoanMainRepo loanMainRepo;

@Autowired
 private LoanDetailsRepo loanDetailsRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private GetLoanRepo getLoanRepo;


@RequestMapping(value = { "/updateLoanMainAfterForeclose" }, method = RequestMethod.POST)
@ResponseBody
public Info updateLoanMainAfterForeclose(String dateTimeUpdate,int userId,int loanId,String closeDate,String currentTotpaid,String currentOut,String repayAmt){
    Info info = new Info();
    try {
        String status = null;
        int currentOutstanding1 = Integer.parseInt(currentOut);
        int repayAmt1 = Integer.parseInt(repayAmt);
        if (currentOutstanding1 == 0) {
            status = "Paid";
            int delete = loanMainRepo.forecloseLoan(loanId, userId, closeDate, currentTotpaid, currentOut, dateTimeUpdate, status);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("deleted");
            } else {
                info.setError(true);
                info.setMsg("failed");
            }
        } else {
            status = "Active";
            int delete = loanMainRepo.partialLoan(loanId, userId, currentTotpaid, currentOut, dateTimeUpdate);
            if (delete > 0) {
                info.setError(false);
                info.setMsg("deleted");
            } else {
                info.setError(true);
                info.setMsg("failed");
            }
        }
    /*
             * int delete = loanMainRepo.forecloseLoan(loanId, userId, closeDate,
             * currentTotpaid, currentOut, dateTimeUpdate, status);
             */
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/updateSkipLoan" }, method = RequestMethod.POST)
@ResponseBody
public Info updateSkipLoan(String dateTimeUpdate,int userId,String skipStr,int count,int advId,String repayEnd){
    Info info = new Info();
    try {
        int delete = loanMainRepo.skipLoan(advId, userId, count, skipStr, dateTimeUpdate, repayEnd);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("deleted");
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


@RequestMapping(value = { "/getAddLoanType" }, method = RequestMethod.GET)
@ResponseBody
public Setting getAddLoanType(){
    Setting setting = new Setting();
    String str = "";
    try {
        setting = settingRepo.findByKey("LoanCalculationType");
    } catch (Exception e) {
        e.getMessage();
    }
    return setting;
}


@RequestMapping(value = { "/getEmpLoanHistory" }, method = RequestMethod.POST)
@ResponseBody
public LoanMain getEmpLoanHistory(int empId){
    LoanMain list = new LoanMain();
    try {
        list = loanMainRepo.getEmpLoanDetail(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLoanHistoryEmpWiseDetail" }, method = RequestMethod.POST)
@ResponseBody
public List<LoanMain> getLoanHistoryEmpWiseDetail(String status,String calYrId,int companyId,int empId){
    List<LoanMain> list = new ArrayList<LoanMain>();
    try {
        list = loanMainRepo.getLoanHistoryDetail(companyId, status, calYrId, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLoanHistoryEmpWiseLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLoan> getLoanHistoryEmpWiseLocId(String status,String calYrId,int companyId,List<Integer> locId){
    List<GetLoan> list = new ArrayList<GetLoan>();
    try {
        list = getLoanRepo.getLoanHistoryEmpWiseLocId(companyId, status, calYrId, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveEmpLoan" }, method = RequestMethod.POST)
@ResponseBody
public LoanMain saveEmpLoan(LoanMain leaveType){
    LoanMain save = new LoanMain();
    try {
        save = loanMainRepo.saveAndFlush(leaveType);
        if (save == null) {
            save = new LoanMain();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/saveLoanDetail" }, method = RequestMethod.POST)
@ResponseBody
public LoanDetails saveLoanDetail(LoanDetails leaveType){
    LoanDetails save = new LoanDetails();
    try {
        save = loanDetailsRepo.saveAndFlush(leaveType);
        if (save == null) {
            save = new LoanDetails();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getLoanHistoryEmpWiseSpec" }, method = RequestMethod.POST)
@ResponseBody
public GetLoan getLoanHistoryEmpWise(String status,String calYrId,int companyId,int empId){
    GetLoan list = new GetLoan();
    try {
        list = getLoanRepo.getLoanHistoryEmpwiseSpec(companyId, status, calYrId, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLoanHistoryEmpWiseSpecForCompany" }, method = RequestMethod.POST)
@ResponseBody
public GetLoan getLoanHistoryEmpWiseSpecForCompany(int companyId,int empId){
    GetLoan list = new GetLoan();
    try {
        list = getLoanRepo.getLoanHistoryEmpwiseSpec(companyId, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpLoanDetailByMainId" }, method = RequestMethod.POST)
@ResponseBody
public List<LoanDetails> getEmpLoanDetailByMainId(int loanId){
    List<LoanDetails> list = new ArrayList<LoanDetails>();
    try {
        list = loanDetailsRepo.findByLoanMainIdAndDelStatus(loanId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLoanHistoryEmpWiseForCompanyLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLoan> getLoanHistoryEmpWiseForCompanyLocId(List<Integer> locId){
    List<GetLoan> list = new ArrayList<GetLoan>();
    try {
        list = getLoanRepo.getLoanHistoryEmpWiseForCompanyLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/calLoan" }, method = RequestMethod.POST)
@ResponseBody
public LoanCalculation calLoan(String roi,String tenure,String loanAmt,String startDate){
    LoanCalculation list = new LoanCalculation();
    try {
        Setting setting = new Setting();
        setting = settingRepo.findByKey("interest_cal_type");
        int type = Integer.parseInt(setting.getValue());
        float principle = Float.parseFloat(loanAmt);
        float rate = Float.parseFloat(roi);
        float period = Float.parseFloat(tenure);
        float si = 0;
        float emi = 0;
        float totalPayble = 0;
        LocalDate localDate = LocalDate.parse(startDate);
        // System.out.println("bef" + localDate);
        LocalDate oneMonthLater = localDate.plusMonths(Integer.parseInt(tenure) - 1);
        // System.out.println("aft" + oneMonthLater);
        list.setCalDate(String.valueOf(oneMonthLater));
        int insertVal = 0;
        try {
            setting = settingRepo.findByKey("ammount_format_Insert");
            insertVal = Integer.parseInt(setting.getValue());
        } catch (Exception e) {
            insertVal = 0;
        }
        if (type == 1) {
            si = (principle * (period / 12) * rate) / 100;
            System.err.println("si " + si);
            si = si + principle;
            // Sac si=(float) NumberFormatting.castNumber(si, 1);
            si = Math.round(si);
            // System.err.println("rounded "+emi);
            emi = Math.round(si / period);
        // emi=(float) NumberFormatting.castNumber(emi, 1);
        // System.err.println("rounded off "+emi);
        // Sac si = emi * period;
        // Sac si=(float) NumberFormatting.castNumber(si, 1);
        } else {
            si = 0;
            emi = 0;
        }
        list.setEmiAmt(emi);
        list.setRepayAmt(si);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/calDatePartialPay" }, method = RequestMethod.POST)
@ResponseBody
public Info calDatePartialPay(String currentOutstanding,String loanEmi,String partialAmt,String endDate,int loanId,int empId){
    Info info = new Info();
    LoanDetails dt = new LoanDetails();
    Date dateCal = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        /*
             * String day = null; Date date = new Date(); Date date1 = new
             * SimpleDateFormat("yyyy-MM-dd").parse(endDate); int x = date1.getDay();
             *
             * if (String.valueOf(x).length() == 1) { day = "0".concat(String.valueOf(x)); }
             * else { day = String.valueOf(x); }
             *
             * dt = loanDetailsRepo.getRecord(loanId); String month = null; String calDate =
             * null; if (dt != null) { if (String.valueOf(dt.getMonths()).length() == 1) {
             * month = "0".concat(String.valueOf(dt.getMonths())); } else { month =
             * String.valueOf(dt.getMonths()); } calDate =
             * String.valueOf(dt.getYears()).concat("-").concat(month).concat("-").concat(
             * day);
             *
             * } else { calDate = sf.format(date1); }
             *
             * // System.err.println("cal" + calDate); int currentOutstanding1 =
             * Integer.parseInt(currentOutstanding); int loanEmi1 =
             * Integer.parseInt(loanEmi); int partialAmt1 = Integer.parseInt(partialAmt);
             *
             * int n = currentOutstanding1 - partialAmt1; int y = n / loanEmi1; LocalDate
             * localDate = LocalDate.parse(calDate);
             *
             * // System.out.println("bef" + localDate); // System.out.println("y" + y);
             * LocalDate oneMonthLater = localDate.plusMonths(y); //
             * System.out.println("aft" + oneMonthLater); info.setError(false);
             * info.setMsg(String.valueOf(oneMonthLater));
             */
        String[] monthsplt = endDate.split("-");
        int count = loanDetailsRepo.getCount(monthsplt[0], monthsplt[1], empId);
        info.setError(count != 0);
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/getLoanById" }, method = RequestMethod.POST)
@ResponseBody
public LoanMain getLoanHistoryEmpWiseDetailComp(int id){
    LoanMain list = new LoanMain();
    try {
        list = loanMainRepo.findById(id);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updateGuarantor" }, method = RequestMethod.POST)
@ResponseBody
public Info updateGuarantor(int oldGuarantor,int newGuarantor,int loginName,String loginTime,int loanId){
    Info info = new Info();
    int result = 0;
    try {
        result = loanMainRepo.updateGuarantor1(loanId, loginName, loginTime, oldGuarantor, newGuarantor);
        System.err.println("G1 Updated" + result);
    } catch (Exception e) {
        result = 0;
    }
    if (result > 0) {
        info.setError(false);
        info.setMsg("Success");
    } else {
        result = loanMainRepo.updateGuarantor2(loanId, loginName, loginTime, oldGuarantor, newGuarantor);
        System.err.println("G2 Updated " + result);
    }
    if (result > 0) {
        info.setError(false);
        info.setMsg("Success");
    } else {
        info.setError(true);
        info.setMsg("Failed");
    }
    return info;
}


}