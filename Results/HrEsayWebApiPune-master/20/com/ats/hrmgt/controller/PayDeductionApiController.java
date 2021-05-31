import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.PayDeduction;
import com.ats.hrmgt.model.PayDeductionDetailList;
import com.ats.hrmgt.model.PayDeductionDetails;
import com.ats.hrmgt.repository.PayDeductionDetailListRepo;
import com.ats.hrmgt.repository.PayDeductionDetailsRepo;
import com.ats.hrmgt.repository.PayDeductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class PayDeductionApiController {

@Autowired
 private PayDeductionRepo payDeductRepo;

@Autowired
 private PayDeductionDetailsRepo detailRepo;

@Autowired
 private PayDeductionDetailListRepo deductDetailRepo;


@RequestMapping(value = { "/savePayDeductnDetail" }, method = RequestMethod.POST)
public PayDeductionDetails saveDeductnPaymentType(PayDeductionDetails pay){
    PayDeductionDetails payDeductDetail = new PayDeductionDetails();
    try {
        payDeductDetail = detailRepo.save(pay);
    } catch (Exception e) {
        System.err.println("Excep in /savePayDeductnDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return payDeductDetail;
}


@RequestMapping(value = { "/getAllEmpPayDeductDetail" }, method = RequestMethod.GET)
public List<PayDeductionDetailList> getAllEmpPayDeductDetail(){
    List<PayDeductionDetailList> list = new ArrayList<PayDeductionDetailList>();
    try {
        list = deductDetailRepo.getEmpPayDeductDetail();
    } catch (Exception e) {
        System.err.println("Excep in getAllEmpPayDeductDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmpPayDeductDetailByEmpId" }, method = RequestMethod.POST)
public List<PayDeductionDetailList> getAllEmpPayDeductDetailByEmpId(int empId){
    List<PayDeductionDetailList> list = new ArrayList<PayDeductionDetailList>();
    try {
        list = deductDetailRepo.getAllEmpPayDeductDetailByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllPayDeduction" }, method = RequestMethod.GET)
public List<PayDeduction> getAllPayDecuvtion(){
    List<PayDeduction> list = new ArrayList<PayDeduction>();
    try {
        list = payDeductRepo.findByDelStatusOrderByDedTypeIdDesc(1);
    } catch (Exception e) {
        System.err.println("Excep in getAllPayDecuvtion : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpPayDeductionById" }, method = RequestMethod.POST)
public PayDeductionDetailList getEmpPayDeductionById(int dedId){
    PayDeductionDetailList deduct = new PayDeductionDetailList();
    try {
        deduct = deductDetailRepo.getEmpPayDeductionById(dedId);
    } catch (Exception e) {
        System.err.println("Excep in getEmpPayDeductionById : " + e.getMessage());
        e.printStackTrace();
    }
    return deduct;
}


@RequestMapping(value = { "/getPayDeductionById" }, method = RequestMethod.POST)
public PayDeduction getPayDeductionById(int typeId){
    PayDeduction pay = new PayDeduction();
    try {
        pay = payDeductRepo.findByDedTypeIdAndDelStatus(typeId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getPayDeductionById : " + e.getMessage());
        e.printStackTrace();
    }
    return pay;
}


@RequestMapping(value = { "/deletePayDeductionDetailById" }, method = RequestMethod.POST)
public Info deletePayDeductionDetailById(int dedId){
    Info info = new Info();
    try {
        int res = detailRepo.deletePayDeductnDetailById(dedId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deletePayDeductionDetailById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/deletePayDeduction" }, method = RequestMethod.POST)
public Info deleteEmployee(int typeId){
    Info info = new Info();
    try {
        int res = payDeductRepo.deleteDeductnTypeById(typeId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deletePayDeduction : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAllEmpPayDeductDetailLocId" }, method = RequestMethod.POST)
public List<PayDeductionDetailList> getAllEmpPayDeductDetailLocId(List<Integer> locId){
    List<PayDeductionDetailList> list = new ArrayList<PayDeductionDetailList>();
    try {
        list = deductDetailRepo.getAllEmpPayDeductDetailLocId(locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}