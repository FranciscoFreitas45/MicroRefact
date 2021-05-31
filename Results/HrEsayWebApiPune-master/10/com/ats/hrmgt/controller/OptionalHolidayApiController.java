import com.ats.hrmgt.model;
import com.ats.hrmgt.repository.EmpDetailForOptionalHolidayRepository;
import com.ats.hrmgt.repository.EmpListForHolidayApproveRepo;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.OptionalHolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class OptionalHolidayApiController {

@Autowired
 private EmpDetailForOptionalHolidayRepository empDetailForOptionalHolidayRepository;

@Autowired
 private HolidayRepo holidayRepo;

@Autowired
 private OptionalHolidayRepo optionalHolidayRepo;

@Autowired
 private EmpListForHolidayApproveRepo empListForHolidayApproveRepo;


@RequestMapping(value = { "/updateStsOfOptionalHoliday" }, method = RequestMethod.POST)
@ResponseBody
public Info updateStsOfOptionalHoliday(String date,int sts,int userId,List<Integer> ids){
    Info info = new Info();
    try {
        int update = optionalHolidayRepo.updateStsOfOptionalHoliday(date, sts, userId, ids);
        info.setError(false);
        info.setMsg("suucess");
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/getHistoryOptionalHoliday" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpListForHolidayApprove> getHistoryOptionalHoliday(int empId,int yearId,List<Integer> sts){
    List<EmpListForHolidayApprove> list = new ArrayList<>();
    try {
        list = empListForHolidayApproveRepo.getHistoryOptionalHoliday(empId, yearId, sts);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getOptionalHolidayApprovalList" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(int locId,List<Integer> sts){
    List<EmpListForHolidayApprove> list = new ArrayList<>();
    try {
        list = empListForHolidayApproveRepo.getOptionalHolidayApprovalList(locId, sts);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpInfoForOptionalHoliday" }, method = RequestMethod.POST)
@ResponseBody
public EmpDetailForOptionalHoliday getEmpInfoForOptionalHoliday(int empId,int yearId){
    EmpDetailForOptionalHoliday empDetailForOptionalHoliday = new EmpDetailForOptionalHoliday();
    try {
        empDetailForOptionalHoliday = empDetailForOptionalHolidayRepository.getEmpInfoForOptionalHoliday(empId, yearId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return empDetailForOptionalHoliday;
}


@RequestMapping(value = { "/getHolidayListforoptionalHoliday" }, method = RequestMethod.POST)
@ResponseBody
public List<Holiday> getHolidayListforoptionalHoliday(String date,int yearId,int catId,int empId){
    List<Holiday> list = new ArrayList<>();
    try {
        list = holidayRepo.getHolidayListforoptionalHoliday(date, yearId, catId, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveApplyHoliday" }, method = RequestMethod.POST)
@ResponseBody
public OptionalHoliday saveApplyHoliday(OptionalHoliday optionalHoliday){
    OptionalHoliday save = new OptionalHoliday();
    try {
        save = optionalHolidayRepo.save(optionalHoliday);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


}