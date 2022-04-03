package com.gbcom.system.controller;
 import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.UserSessionManager;
import com.gbcom.system.utils.SecurityUtil;
import com.hc.core.controller.BaseCRUDActionController;
import org.hibernate.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class SysPasswordController extends BaseCRUDActionController{

@Autowired
 private  SysUserService sysUserService;

@Autowired
 private  UserSessionManager userSessionManager;


@RequestMapping
public String changePass(Model model){
    return "view/system/sysUser/pass_input";
}


@RequestMapping
@UserLog
public void savePass(Model model,HttpServletResponse response,HttpServletRequest request){
    try {
        SysUser loginedUser = userSessionManager.getLoginedUser();
        // SysUser loginedUser = sysUserService.get(1L);
        if (loginedUser != null) {
            String oldPass = request.getParameter("oldPass");
            String savedPass = loginedUser.getPassword();
            if (!StringHelper.isEmpty(savedPass)) {
                oldPass = SecurityUtil.MD5(oldPass.trim());
            }
            if (StringHelper.isEmpty(oldPass) && !StringHelper.isEmpty(savedPass)) {
                sendFailureJSON(response, "原密码输入不正确，请重新输入");
                return;
            }
            if (!StringHelper.isEmpty(oldPass) && !StringHelper.isEmpty(savedPass)) {
                if (!savedPass.equals(oldPass)) {
                    sendFailureJSON(response, "原密码输入不正确，请重新输入");
                    return;
                }
            }
            String newPass = request.getParameter("newPass");
            if (!StringHelper.isEmpty(newPass)) {
                loginedUser.setPassword(SecurityUtil.MD5(newPass));
            } else {
                loginedUser.setPassword(null);
            }
            sysUserService.save(loginedUser);
        } else {
            sendFailureJSON(response, "当前用户不存在");
            return;
        }
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


}