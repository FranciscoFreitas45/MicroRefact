import com.ats.hrmgt.model.DailyDaily;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.report.HodDashboard;
import com.ats.hrmgt.model.report.HodDeptDashb;
import com.ats.hrmgt.repo.DailyDailyRepo;
import com.ats.hrmgt.repo.report.HodDashboardRepo;
import com.ats.hrmgt.repo.report.HodDeptDashbRepo;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.SettingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class SachinReportApi {

@Autowired
 private HodDashboardRepo hodDashbRepo;

@Autowired
 private HodDeptDashbRepo hodDeptDbRepo;

@Autowired
 private DailyDailyRepo dailyDailyRepo;

@Autowired
 private DailyAttendanceRepository dailyrRepo;

@Autowired
 private SettingRepo settingRepo;


@RequestMapping(value = { "/getDailyDailyBetFdTdAndEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyDaily> getDailyDailyBetFdTdAndEmpId(String fromDate,String toDate,int empId){
    List<DailyDaily> dailyList = new ArrayList<>();
    try {
        dailyList = dailyDailyRepo.getData(fromDate, toDate, empId);
        System.err.println(" dailyList " + dailyList.toString());
        if (dailyList.isEmpty() || dailyList.equals(null)) {
            System.err.println("If null or empty  dailyList ");
            dailyList = new ArrayList<>();
        }
    } catch (Exception e) {
        dailyList = new ArrayList<>();
        e.printStackTrace();
    }
    return dailyList;
}


@RequestMapping(value = { "/getHodDeptDashb" }, method = RequestMethod.POST)
@ResponseBody
public List<HodDeptDashb> getHodDeptDashb(List<Integer> deptIdList,List<Integer> locIdList,String pmFromDate,String pmToDate){
    List<HodDeptDashb> hodDeptDashbList = new ArrayList<>();
    try {
        hodDeptDashbList = hodDeptDbRepo.getHodDeptDashb(deptIdList, locIdList, pmFromDate, pmToDate);
        System.err.println(" hodDeptDashbList " + hodDeptDashbList);
        if (hodDeptDashbList.isEmpty() || hodDeptDashbList.equals(null)) {
            System.err.println("If null or empty  hodDeptDashbList ");
            hodDeptDashbList = new ArrayList<>();
        }
    } catch (Exception e) {
        hodDeptDashbList = new ArrayList<>();
        e.printStackTrace();
    }
    return hodDeptDashbList;
}


@RequestMapping(value = { "/getHodDashboard" }, method = RequestMethod.POST)
@ResponseBody
public List<HodDashboard> getHodDashboard(List<Integer> deptIdList,List<Integer> locIdList,String cmFromDate,String cmToDate,String pmFromDate,String pmToDate){
    List<HodDashboard> dashBList = new ArrayList<>();
    try {
        dashBList = hodDashbRepo.getHodDashboardByDeptLocIds(deptIdList, locIdList, cmFromDate, cmToDate, pmFromDate, pmToDate);
        if (dashBList.isEmpty() || dashBList.equals(null)) {
            dashBList = new ArrayList<>();
        }
    } catch (Exception e) {
        dashBList = new ArrayList<>();
        e.printStackTrace();
    }
    return dashBList;
}


@RequestMapping(value = { "/saveDailyOTUpdate" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyDaily> saveDailyOTUpdate(List<DailyDaily> dailyList){
    List<DailyDaily> dailyListRes = new ArrayList<>();
    try {
        // dailyListRes = dailyDailyRepo.saveAll(dailyList);
        Setting auto_approve_ot_hr = settingRepo.findByKey("auto_approve_ot_hr");
        for (int x = 0; x < dailyList.size(); x++) {
            if (Integer.parseInt(auto_approve_ot_hr.getValue()) == 1) {
                int result = dailyrRepo.updateOTByIdAndApprove(dailyList.get(x).getOtHr(), dailyList.get(x).getId());
            } else {
                int result = dailyrRepo.updateOTById(dailyList.get(x).getOtHr(), dailyList.get(x).getId());
            }
        }
        System.err.println(" dailyListRes " + dailyListRes.toString());
    } catch (Exception e) {
        dailyListRes = new ArrayList<>();
        e.printStackTrace();
    }
    return dailyListRes;
}


}