package com.hmm.system;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.SessionUtil;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
@RestController
public class LoginController {

 private  Logger logger;

@Autowired
 private  IdentityService identityService;

@Autowired
 private  EmployeeService employService;


@RequestMapping(value = "/checkUser")
@ResponseBody
public ExtAjaxResponse checkUser(HttpSession session){
    try {
        String userId = SessionUtil.getUserName(session);
        if (null != userId) {
            // 读取角色Group
            User user = identityService.createUserQuery().userId(userId).singleResult();
            List<Group> groupList = identityService.createGroupQuery().groupMember(user.getId()).list();
            SessionUtil.setGroupList(session, groupList);
            String[] groupNames = new String[groupList.size()];
            for (int i = 0; i < groupNames.length; i++) {
                groupNames[i] = groupList.get(i).getName();
            }
            // "groupNames"  : "admin,hrManager"
            SessionUtil.setGroupNames(session, ArrayUtils.toString(groupNames));
            String image = SessionUtil.getImage(session);
            Map<String, String> map = new HashMap<String, String>();
            String group = SessionUtil.getGroupNames(session).replace("{", "").replace("}", "");
            map.put("group", group);
            map.put("userName", userId);
            map.put("image", image);
            map.put("msg", "登录成功!");
            return new ExtAjaxResponse(true, map);
        } else {
            return new ExtAjaxResponse(false, "系统未登入!");
        }
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(false, "失败!");
    }
}


@RequestMapping(value = "/logout")
@ResponseBody
public ExtAjaxResponse logout(HttpSession session){
    try {
        SessionUtil.removeAttribute(session);
        return new ExtAjaxResponse(true, "登出成功!");
    } catch (Exception e) {
        return new ExtAjaxResponse(false, "登出失败!");
    }
}


@RequestMapping(value = "/editPassword")
@ResponseBody
public ExtAjaxResponse editPassword(String newpassword,String oldpassword,HttpSession session){
    try {
        String userId = SessionUtil.getUserName(session);
        boolean checkPassword = identityService.checkPassword(userId, oldpassword);
        if (checkPassword) {
            User user = identityService.createUserQuery().userId(userId).singleResult();
            user.setPassword(newpassword);
            identityService.saveUser(user);
            employService.updatePassword(newpassword, userId);
            return new ExtAjaxResponse(true, "修改成功");
        } else {
            return new ExtAjaxResponse(false, "失败!");
        }
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(false, "失败!");
    }
}


@RequestMapping(value = "/login")
@ResponseBody
public ExtAjaxResponse logon(String userName,String logintype,String password,HttpSession session){
    logger.debug("logon request: {userName={}, password={}}", userName, password);
    boolean checkPassword = identityService.checkPassword(userName, password);
    if (checkPassword) {
        // 查看用户是否存在
        User user = identityService.createUserQuery().userId(userName).singleResult();
        SessionUtil.setUser(session, user);
        Employee employee = employService.findByUserName(userName);
        String image = employee.getEmpImage();
        // 读取角色Group
        List<Group> groupList = identityService.createGroupQuery().groupMember(user.getId()).list();
        SessionUtil.setGroupList(session, groupList);
        String[] groupNames = new String[groupList.size()];
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = groupList.get(i).getName();
        }
        // "groupNames"  : "admin,hrManager"
        SessionUtil.setGroupNames(session, ArrayUtils.toString(groupNames));
        SessionUtil.setLogintype(session, logintype);
        SessionUtil.setIamge(session, image);
        Map<String, String> map = new HashMap<String, String>();
        String group = SessionUtil.getGroupNames(session).replace("{", "").replace("}", "");
        map.put("group", group);
        map.put("image", image);
        map.put("userName", userName);
        map.put("msg", "登录成功!");
        return new ExtAjaxResponse(true, map);
    } else {
        return new ExtAjaxResponse(false, "登录失败!帐号或者密码有误!请重新登录!");
    }
}


@RequestMapping(value = "/checkpwd")
@ResponseBody
public ExtAjaxResponse checkpwd(String oldpassword,HttpSession session){
    try {
        String userId = SessionUtil.getUserName(session);
        boolean checkPassword = identityService.checkPassword(userId, oldpassword);
        if (checkPassword) {
            return new ExtAjaxResponse(true, "成功");
        } else {
            return new ExtAjaxResponse(false, "失败!");
        }
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(false, "失败!");
    }
}


}