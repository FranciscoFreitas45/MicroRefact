package com.ipe.module.core.web.controller;
 import com.ipe.module.core.service.UserService;
import com.ipe.module.core.web.security.CustUsernamePasswordToken;
import com.ipe.module.core.web.util.WebUtil;
import org.apache.shiro.authc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLDecoder;
import org.apache.shiro.SecurityUtils.getSubject;
@Controller
public class IndexController extends AbstractController{

@Autowired
 private  UserService userService;

 private  Logger Logger;


@RequestMapping(value = "/reui/${url}", method = RequestMethod.GET)
public String reUI(String url){
    return url;
}


@RequestMapping(value = "/logout", method = RequestMethod.GET)
public String logout(HttpServletRequest request){
    request.getSession().removeAttribute("user");
    // TODO 上次登录时间
    request.getSession().removeAttribute("userPrvLoginTime");
    try {
        userService.logout();
        getSubject().logout();
    } catch (Exception e) {
        Logger.error("Exception {}", e);
    }
    return "signin";
}


public String getIpAddr(HttpServletRequest request){
    if (request == null) {
        return "unknown";
    }
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("X-Forwarded-For");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("X-Real-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }
    return ip;
}


@RequestMapping(value = "/signin", method = RequestMethod.GET)
public String signin(){
    return "signin";
}


@RequestMapping(value = "/", method = RequestMethod.GET)
public String index(){
    return "index";
}


@RequestMapping(value = { "/downFile" })
public void downlogs(String filePath,HttpServletResponse response){
    try {
        filePath = URLDecoder.decode(filePath, "UTF-8");
        File file = new File(filePath);
        if (file.canRead() && file.exists()) {
            super.downFile(file, response);
        } else {
            super.downFile(response);
        }
    } catch (Exception e) {
        Logger.error("del error", e);
        super.downFile(response);
    }
}


@RequestMapping(value = "/login", method = RequestMethod.POST)
public String login(String useraccount,String password,HttpServletRequest request,RedirectAttributes redirectAttributes){
    CustUsernamePasswordToken token = new CustUsernamePasswordToken(useraccount, password, WebUtil.getIpAddr(request), request.getMethod(), request.getParameter("captcha"), request.getRequestURL().toString());
    try {
        getSubject().login(token);
        return "redirect:/";
    } catch (UnknownAccountException uae) {
        redirectAttributes.addAttribute("errorMsg", "用户名不存在系统！");
    } catch (IncorrectCredentialsException ice) {
        redirectAttributes.addAttribute("errorMsg", "密码错误！");
    } catch (LockedAccountException lae) {
        redirectAttributes.addAttribute("errorMsg", "用户已经被锁定不能登录，请与管理员联系！");
    } catch (ExcessiveAttemptsException eae) {
        redirectAttributes.addAttribute("errorMsg", "错误次数过多！");
    } catch (AuthenticationException ae) {
        redirectAttributes.addAttribute("errorMsg", "其他的登录错误！");
    }
    return "redirect:signin";
}


}