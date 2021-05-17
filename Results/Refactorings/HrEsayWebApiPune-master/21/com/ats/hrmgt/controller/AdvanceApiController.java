import com.ats.hrmgt.advance.repository.AdvanceDetailsRepo;
import com.ats.hrmgt.advance.repository.AdvanceRepo;
import com.ats.hrmgt.advance.repository.GetAdvanceRepo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.advance.AdvanceDetails;
import com.ats.hrmgt.model.advance.GetAdvance;
import com.ats.hrmgt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class AdvanceApiController {

@Autowired
 private AdvanceRepo advanceRepo;

@Autowired
 private GetAdvanceRepo getAdvanceRepo;

@Autowired
 private AdvanceDetailsRepo advanceDetailsRepo;

@Autowired
 private UserRepo userRepo;


@RequestMapping(value = { "/getAdvanceDetailsByAdvanceIdId" }, method = RequestMethod.POST)
@ResponseBody
public List<AdvanceDetails> getAdvanceDetailsByAdvanceIdId(int advId){
    List<AdvanceDetails> list = new ArrayList<AdvanceDetails>();
    try {
        list = advanceDetailsRepo.findByAdvId(advId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updateSkipAdvance" }, method = RequestMethod.POST)
@ResponseBody
public Info updateSkipAdvance(String dateTimeUpdate,int userId,int advId,int dedMonth,int dedYear,int count,String skipStr){
    Info info = new Info();
    try {
        int delete = advanceRepo.skipAdvance(advId, dedYear, dedMonth, dateTimeUpdate, userId, count, skipStr);
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


@RequestMapping(value = { "/getPendingAdvance" }, method = RequestMethod.POST)
@ResponseBody
public List<GetAdvance> getPendingAdvance(int companyId){
    List<GetAdvance> list = new ArrayList<GetAdvance>();
    try {
        list = getAdvanceRepo.getSubModule(companyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAdvanceHistoryByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetAdvance> getAdvanceHistoryByEmpId(int empId){
    List<GetAdvance> list = new ArrayList<GetAdvance>();
    try {
        list = getAdvanceRepo.getAdvanceHistoryByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteAdvance" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLMstEmpType(int advId){
    Info info = new Info();
    try {
        int delete = advanceRepo.deleteAdvance(advId);
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


@RequestMapping(value = { "/getAdvanceHistory" }, method = RequestMethod.POST)
@ResponseBody
public List<GetAdvance> getAdvanceHistory(int empId,String calYrId,int companyId){
    List<GetAdvance> list = new ArrayList<GetAdvance>();
    try {
        if (empId == 0 && !calYrId.equals("0")) {
            list = getAdvanceRepo.getSpecYearAdv(companyId, calYrId);
        } else if (empId != 0 && calYrId.equals("0")) {
            list = getAdvanceRepo.getSpecEmpAdv(companyId, empId);
        } else if (empId != 0 && !calYrId.equals("0")) {
            list = getAdvanceRepo.getSpecAdv(companyId, empId, calYrId);
        } else {
            list = getAdvanceRepo.getAllAdv(companyId);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveAdvanceList" }, method = RequestMethod.POST)
@ResponseBody
public Info saveAdvanceList(List<Advance> advList){
    Info info = new Info();
    List<Advance> inf = new ArrayList<Advance>();
    try {
        inf = advanceRepo.saveAll(advList);
        if (inf == null) {
            info.setMsg("Failed");
            info.setError(true);
        } else {
            info.setMsg("Success");
            info.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAdvanceById" }, method = RequestMethod.POST)
@ResponseBody
public Advance getAdvanceById(int advId){
    Advance list = new Advance();
    try {
        list = advanceRepo.findById(advId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAdvanceDetails" }, method = RequestMethod.POST)
@ResponseBody
public List<AdvanceDetails> getAllAdvanceDetails(){
    List<AdvanceDetails> list = new ArrayList<AdvanceDetails>();
    try {
        list = advanceDetailsRepo.findAllByDelStatus();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/checkCustPhone" }, method = RequestMethod.POST)
@ResponseBody
public Info checkEmployeeEmail(String voucherNo,int locId){
    Info info = new Info();
    List<Advance> emp = new ArrayList<Advance>();
    try {
        emp = advanceRepo.findByVoucherNoAndDelStatus(voucherNo, 1, locId);
        info.setError(emp.size() > 0);
    } catch (Exception e) {
        System.err.println("Exce in checkEmployeeEmail  " + e.getMessage());
    }
    return info;
}


@RequestMapping(value = { "/updateUserPassAndExInt1" }, method = RequestMethod.POST)
@ResponseBody
public Info updateUserPassAndExInt1(int empId,String password,int isVisit){
    Info info = new Info();
    try {
        int delete = userRepo.updateUserPassAndExInt1(empId, password, isVisit);
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


@RequestMapping(value = { "/saveMstEmpAdvance" }, method = RequestMethod.POST)
@ResponseBody
public Advance saveMstEmpAdvance(Advance leaveType){
    Advance save = new Advance();
    try {
        save = advanceRepo.saveAndFlush(leaveType);
        if (save == null) {
            save = new Advance();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/saveAdvanceDetails" }, method = RequestMethod.POST)
@ResponseBody
public AdvanceDetails saveAdvanceDetails(AdvanceDetails leaveType){
    AdvanceDetails save = new AdvanceDetails();
    try {
        save = advanceDetailsRepo.saveAndFlush(leaveType);
        if (save == null) {
            save = new AdvanceDetails();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getUserInfoByEmpIdPass" }, method = RequestMethod.POST)
@ResponseBody
public User getUserInfoByEmpIdPass(int empId,String password){
    User user = new User();
    try {
        user = userRepo.getSpecificUserRecord(empId, password);
        if (user == null) {
            user = new User();
            user.setError(true);
        } else {
            user.setError(false);
        }
        System.out.println(user);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return user;
}


@RequestMapping(value = { "/getPendingAdvanceLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetAdvance> getPendingAdvanceLocId(int companyId,List<Integer> locId){
    List<GetAdvance> list = new ArrayList<GetAdvance>();
    try {
        list = getAdvanceRepo.getPendingAdvanceLocId(companyId, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updateUserPass" }, method = RequestMethod.POST)
@ResponseBody
public Info updateUserPass(int empId,String password){
    Info info = new Info();
    try {
        int delete = userRepo.updateUserPassword(empId, password);
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


}