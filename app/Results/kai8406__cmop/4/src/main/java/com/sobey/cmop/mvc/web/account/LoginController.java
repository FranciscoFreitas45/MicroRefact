package com.sobey.cmop.mvc.web.account;
 import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.sobey.cmop.mvc.comm.BaseController;
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController{


@RequestMapping(method = RequestMethod.POST)
public String loginFail(String userName,Model model){
    model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
    return "account/signIn";
}


@RequestMapping
public String login(){
    return "account/login";
}


}