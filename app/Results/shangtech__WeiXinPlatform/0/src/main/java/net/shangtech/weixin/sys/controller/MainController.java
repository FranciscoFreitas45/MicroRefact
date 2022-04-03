package net.shangtech.weixin.sys.controller;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.util.EncoderUtils;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/manage")
public class MainController extends BaseController{

@Autowired
 private  SysUserService userService;


@RequestMapping("/prelogin")
public String loginForm(HttpServletRequest request){
    return "login";
}


@RequestMapping("/application")
public String application(HttpServletRequest request){
    return "user/application";
}


@RequestMapping("/service")
public String service(HttpServletRequest request){
    return "user/service";
}


@RequestMapping("/index")
public String index(HttpServletRequest request){
    return "user/index";
}


@RequestMapping("/login")
public String login(HttpServletRequest request,HttpServletResponse response){
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (StringUtils.isBlank(username)) {
        return failed(response, "请填写用户名");
    }
    if (StringUtils.isBlank(password)) {
        return failed(response, "请填写登录密码");
    }
    SysUser user = userService.findByUsername(username);
    if (user == null) {
        return failed(response, "用户名或密码错误");
    }
    // System.out.println(user.getPassword());
    // System.out.println(EncoderUtils.MD5(username+"@"+password));
    if (!user.getPassword().equals(EncoderUtils.MD5(EncoderUtils.MD5(username + "@" + password)))) {
        return failed(response, "用户名或密码错误");
    }
    request.getSession().setAttribute("user", user);
    return success(response);
}


}