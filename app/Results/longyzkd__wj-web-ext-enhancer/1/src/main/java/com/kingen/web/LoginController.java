package com.kingen.web;
 import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.kingen.service.account.AccountService;
@Controller
@RequestMapping(value = "/login")
public class LoginController extends CommonController{

@Autowired
 private  AccountService service;


@RequestMapping(method = RequestMethod.POST)
@Deprecated
public String fail(String userName,Model model){
    model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
    return "account/login";
}


@RequestMapping(method = RequestMethod.GET)
public String login(HttpServletRequest request,Model model){
    if (request.getParameter("kickout") != null) {
        model.addAttribute("msg", "您的帐号在另一个地点登录，您已被踢出！");
    // out.println("{success:false,msg:'您的帐号在另一个地点登录，您已被踢出！'}");
    }
    if (request.getParameter("forceLogout") != null) {
        model.addAttribute("msg", "您已经被管理员强制退出，请重新登录！");
    // out.println("{success:false,msg:'您已经被管理员强制退出，请重新登录！'}");
    }
    return "account/login";
}


}