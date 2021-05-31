import com.ats.hrmgt.model.FreezeLogs;
import com.ats.hrmgt.repository.FreezeLogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
@RestController
public class LogsApiController {

@Autowired
 private FreezeLogsRepo freezeLogsRepoRepo;


@RequestMapping(value = { "/freezeUnfreezeLogs" }, method = RequestMethod.POST)
@ResponseBody
public FreezeLogs freezeUnfreezeLogs(FreezeLogs freezeLogs){
    FreezeLogs save = new FreezeLogs();
    try {
        save = freezeLogsRepoRepo.saveAndFlush(freezeLogs);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


}