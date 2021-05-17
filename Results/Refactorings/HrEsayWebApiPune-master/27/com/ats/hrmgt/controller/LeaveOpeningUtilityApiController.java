import com.ats.hrmgt.model.OpeningPendingLeaveEmployeeList;
import com.ats.hrmgt.repository.OpeningPendingLeaveEmployeeListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class LeaveOpeningUtilityApiController {

@Autowired
 private OpeningPendingLeaveEmployeeListRepo openingPendingLeaveEmployeeListRepo;


@RequestMapping(value = { "/getEmplistForOpeningLeave" }, method = RequestMethod.POST)
@ResponseBody
public List<OpeningPendingLeaveEmployeeList> getEmplistForOpeningLeave(int locId,int yearId){
    List<OpeningPendingLeaveEmployeeList> list = new ArrayList<>();
    try {
        list = openingPendingLeaveEmployeeListRepo.getEmplistForOpeningLeave(locId, yearId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}