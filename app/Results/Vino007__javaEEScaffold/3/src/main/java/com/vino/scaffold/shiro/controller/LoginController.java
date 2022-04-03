package com.vino.scaffold.shiro.controller;
 import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vino.scaffold.controller.base.BaseController;
import com.vino.scaffold.entity.Constants;
@Controller
public class LoginController extends BaseController{


@RequestMapping(value = "/login")
public String showLoginForm(HttpServletRequest req,Model model){
    String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
    String error = null;
    if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
        error = "�û���/�������";
    } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
        error = "�û���/�������";
    } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
        error = "�˻�������������ϵ����Ա";
    } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
        error = "����������󳬹�" + Constants.PASSWORD_RETRY_MAX + "��,��ȴ�һСʱ�����³���";
    } else if (exceptionClassName != null) {
        error = "��������" + exceptionClassName;
    }
    model.addAttribute("loginError", error);
    return "login";
}


}