import com.ats.hrmgt.model;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class AccessRoleRestController {

@Autowired
 private AccessRightModuleRepository accessRightModuleRepository;

@Autowired
 private AccessRightSubModuleRepository accessRightSubModuleRepository;

@Autowired
 private EmpTypeRepository empTypeRepository;

@Autowired
 private LoginResponseRepository loginResponseRepository;

@Autowired
 private GetAccessibleLocationeRepository getAccessibleLocationeRepository;


@RequestMapping(value = { "/getModuleAndSubModuleList" }, method = RequestMethod.GET)
@ResponseBody
public List<AccessRightModule> getModuleAndSubModuleList(){
    List<AccessRightModule> accessRightModuleList = new ArrayList<>();
    try {
        accessRightModuleList = accessRightModuleRepository.getModule();
        List<AccessRightSubModule> accessRightSubModuleList = accessRightSubModuleRepository.getSubModuleAll();
        for (int i = 0; i < accessRightModuleList.size(); i++) {
            List<AccessRightSubModule> list = new ArrayList<>();
            for (int j = 0; j < accessRightSubModuleList.size(); j++) {
                if (accessRightModuleList.get(i).getModuleId() == accessRightSubModuleList.get(j).getModuleId()) {
                    list.add(accessRightSubModuleList.get(j));
                }
            }
            accessRightModuleList.get(i).setAccessRightSubModuleList(list);
        }
    // System.out.println(accessRightModuleList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return accessRightModuleList;
}


@RequestMapping(value = { "/getUserForForgPass" }, method = RequestMethod.POST)
@ResponseBody
public LoginResponse getUserForForPass(String username){
    LoginResponse loginResponse = new LoginResponse();
    try {
        if (username.contains("@")) {
            System.err.println("Its Email");
            loginResponse = loginResponseRepository.CheckUserForPasswordByEmail(username);
        } else {
            System.err.println("Its Code ");
            loginResponse = loginResponseRepository.CheckUserForPasswordByUsername(username);
        }
        if (loginResponse == null) {
            loginResponse = new LoginResponse();
            loginResponse.setIsError(true);
        } else {
            loginResponse.setIsError(false);
        }
    } catch (Exception e) {
        loginResponse = new LoginResponse();
        loginResponse.setIsError(true);
        e.printStackTrace();
    }
    return loginResponse;
}


@RequestMapping(value = { "/deleteEmpType" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteEmpType(int empTypeId){
    Info info = new Info();
    try {
        int delete = empTypeRepository.deleteEmpType(empTypeId);
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


@RequestMapping(value = { "/loginProcess" }, method = RequestMethod.POST)
@ResponseBody
public LoginResponse loginProcess(String username,String password){
    LoginResponse loginResponse = new LoginResponse();
    try {
        /*
             * MessageDigest md = MessageDigest.getInstance("MD5"); byte[] messageDigest =
             * md.digest(password.getBytes()); BigInteger number = new BigInteger(1,
             * messageDigest); String hashtext = number.toString(16);
             */
        loginResponse = loginResponseRepository.loginProcessWebAppl(username, password);
        // System.out.println(loginResponse);
        if (loginResponse == null) {
            loginResponse = new LoginResponse();
            loginResponse.setIsError(true);
        } else {
            loginResponse.setIsError(false);
        }
    } catch (Exception e) {
        loginResponse = new LoginResponse();
        loginResponse.setIsError(true);
        e.printStackTrace();
    }
    return loginResponse;
}


@RequestMapping(value = { "/getAccessibleLocationAndPresentLocation" }, method = RequestMethod.POST)
@ResponseBody
public GetAccessibleLocation getAccessibleLocationAndPresentLocation(int empId){
    GetAccessibleLocation loginResponse = new GetAccessibleLocation();
    try {
        loginResponse = getAccessibleLocationeRepository.getAccessibleLocationAndPresentLocation(empId);
    } catch (Exception e) {
        loginResponse = new GetAccessibleLocation();
        loginResponse.setError(true);
        e.printStackTrace();
    }
    return loginResponse;
}


@RequestMapping(value = { "/saveEmpType" }, method = RequestMethod.POST)
@ResponseBody
public EmpType saveEmpType(EmpType empType){
    EmpType save = new EmpType();
    try {
        save = empTypeRepository.saveAndFlush(empType);
        if (save == null) {
            save = new EmpType();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
        save.setError(true);
    }
    return save;
}


@RequestMapping(value = { "/getEmpTypeList" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpType> getEmpTypeList(int compId){
    List<EmpType> list = new ArrayList<EmpType>();
    try {
        if (compId != 0) {
            list = empTypeRepository.findByDelStatusAndCompanyIdOrderByEmpTypeIdDesc(1, compId);
        } else {
            list = empTypeRepository.findByDelStatusOrderByEmpTypeIdDesc(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpTypeById" }, method = RequestMethod.POST)
@ResponseBody
public EmpType getEmpTypeById(int empTypeId){
    EmpType location = new EmpType();
    try {
        location = empTypeRepository.findByEmpTypeIdAndDelStatus(empTypeId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return location;
}


}