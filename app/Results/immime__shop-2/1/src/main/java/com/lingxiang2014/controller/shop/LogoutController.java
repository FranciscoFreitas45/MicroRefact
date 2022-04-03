package com.lingxiang2014.controller.shop;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.util.WebUtils;
@Controller("shopLogoutController")
public class LogoutController extends BaseController{


@RequestMapping(value = "/logout", method = RequestMethod.GET)
public String execute(HttpServletRequest request,HttpServletResponse response,HttpSession session){
    session.removeAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
    WebUtils.removeCookie(request, response, Member.USERNAME_COOKIE_NAME);
    return "redirect:/";
}


}