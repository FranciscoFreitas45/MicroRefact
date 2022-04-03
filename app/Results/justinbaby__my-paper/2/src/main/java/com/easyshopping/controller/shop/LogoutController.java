package com.easyshopping.controller.shop;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.easyshopping.entity.Member;
import com.easyshopping.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller("shopLogoutController")
public class LogoutController extends BaseController{


@RequestMapping(value = "/logout", method = RequestMethod.GET)
public String execute(HttpServletRequest request,HttpServletResponse response,HttpSession session){
    session.removeAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
    WebUtils.removeCookie(request, response, Member.USERNAME_COOKIE_NAME);
    return "redirect:/";
}


}