import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.TreeNode;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.guest.dto.GuestDto;
@RestController
@RequestMapping("/menu")
public class MenuController {

@Autowired
 private  EmployeeService employeeServiceimpl;

@Autowired
 private  IdentityService identityService;


@RequestMapping("/findMenu")
public ExtAjaxResponse findMenu(String userName,String loginType){
    if (userName != null) {
        Employee employee = employeeServiceimpl.findByUserName(userName);
        if (null != employee) {
            Map<String, String> map = new HashMap<String, String>();
            Department department = employee.getDepartmentes();
            map.put("deptName", department.getDeptName());
            List<Group> groupList = identityService.createGroupQuery().groupMember(userName).list();
            String[] groupNames = new String[groupList.size()];
            for (int i = 0; i < groupNames.length; i++) {
                groupNames[i] = groupList.get(i).getName();
            }
            String groupName = ArrayUtils.toString(groupNames);
            map.put("groupName", groupName);
            return new ExtAjaxResponse(true, map);
        } else {
            return new ExtAjaxResponse(false, "用户不存在");
        }
    } else {
        return new ExtAjaxResponse(true, "用户不存在");
    }
}


}