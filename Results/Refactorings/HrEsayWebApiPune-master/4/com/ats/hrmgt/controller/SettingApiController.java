import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class SettingApiController {

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private UserRepo userRepo;


@RequestMapping(value = { "/findUserInfoByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public User findUserInfoByEmpId(int EmpId){
    User save = new User();
    try {
        save = userRepo.findByEmpId(EmpId);
        if (save == null) {
            save = new User();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/updateSetting" }, method = RequestMethod.POST)
@ResponseBody
public Info updateSetting(String settingId,String val){
    Info info = new Info();
    try {
        int editSetting = settingRepo.settingUpdate(settingId, val);
        if (editSetting > 0) {
            info.setError(false);
            info.setMsg("Setting Label Updated Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Updated Setting Label");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/getSettingByKey" }, method = RequestMethod.POST)
@ResponseBody
public Setting getSettingByKey(String key){
    Setting setting = new Setting();
    try {
        setting = settingRepo.findByKey(key);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return setting;
}


@RequestMapping(value = { "/saveUserInfo" }, method = RequestMethod.POST)
@ResponseBody
public User saveUserInfo(User userInfo){
    User save = new User();
    try {
        save = userRepo.saveAndFlush(userInfo);
        if (save == null) {
            save = new User();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getAllSettingLabels" }, method = RequestMethod.GET)
public List<Setting> getAllSettingLabels(){
    List<Setting> list = new ArrayList<Setting>();
    try {
        list = settingRepo.findAllByEditableLabels();
    } catch (Exception e) {
        System.err.println("Excep in getAllSettingLabels : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getSettingById" }, method = RequestMethod.POST)
@ResponseBody
public Setting getSettingById(int settingId){
    Setting setting = new Setting();
    try {
        setting = settingRepo.findBySettingId(settingId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return setting;
}


@RequestMapping(value = { "/saveSetting" }, method = RequestMethod.POST)
@ResponseBody
public Setting saveSetting(Setting setting){
    Setting save = new Setting();
    try {
        save = settingRepo.saveAndFlush(setting);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


}