import com.ats.hrmgt.model;
import com.ats.hrmgt.repo.FullAndFinalRepo;
import com.ats.hrmgt.repo.GetDetailForBonusRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.GetAdvanceListRepo;
import com.ats.hrmgt.repository.GetClaimListRepo;
import com.ats.hrmgt.repository.GetPayDedListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
public class FullAndFinalApiController {

@Autowired
 private GetAdvanceListRepo getAdvanceListRepo;

@Autowired
 private GetClaimListRepo getClaimListRepo;

@Autowired
 private GetPayDedListRepo getPayDedListRepo;

@Autowired
 private GetDetailForBonusRepo getDetailForBonusRepo;

@Autowired
 private FullAndFinalRepo fullAndFinalRepo;

@Autowired
 private EmpSalaryInfoRepo empSalRepo;


@RequestMapping(value = { "/getAllAmountDeductionSectionListForFullnFinal" }, method = RequestMethod.POST)
public AdvanceAndLoanInfo getAllAmountDeductionSectionListForFullnFinal(List<Integer> empIds){
    AdvanceAndLoanInfo info = new AdvanceAndLoanInfo();
    try {
        GetAdvanceList getAdvanceList = getAdvanceListRepo.getAdvanceListForFullFinal(empIds);
        GetPayDedList getLoanList = getPayDedListRepo.getLoanListForFullFinal(empIds);
        if (getAdvanceList != null) {
            info.setAdvanceAmt(getAdvanceList.getAdvAmount());
        } else {
            info.setAdvanceAmt(0);
        }
        if (getLoanList != null) {
            info.setLoanAmt(getLoanList.getAmt());
        } else {
            info.setLoanAmt(0);
        }
    // List<GetClaimList> getClaimList = getClaimListRepo.getClaimList(empIds);
    // List<GetPayDedList> getPayDedList = getPayDedListRepo.getPayDedList(empIds);
    // List<GetPayDedList> getRewardList = getPayDedListRepo.getBonusList(empIds);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/insertfullandfinalrecord" }, method = RequestMethod.POST)
public FullAndFinal insertfullandfinalrecord(FullAndFinal fullAndFinal){
    FullAndFinal save = new FullAndFinal();
    try {
        save = fullAndFinalRepo.save(fullAndFinal);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/updateLeaveDatainemployee" }, method = RequestMethod.POST)
public Info updateLeaveDatainemployee(int empId,String leaveDate,String leaveReason,String lrEsic,String lrForPF){
    Info info = new Info();
    try {
        int res = empSalRepo.updateLeaveDatainemployee(empId, leaveDate, leaveReason, lrEsic, lrForPF);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getbonuscalDetails" }, method = RequestMethod.POST)
public GetDetailForBonus getbonuscalDetails(int empId,int fromMonth,int toMonth,int fromYear,int toYear){
    GetDetailForBonus info = new GetDetailForBonus();
    try {
        info = getDetailForBonusRepo.getbonuscalDetails(empId, fromMonth, toMonth, fromYear, toYear);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


}