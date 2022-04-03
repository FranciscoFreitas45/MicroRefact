package com.netease.controller.api;
 import java.math.BigInteger;
import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.netease.controller.base.BaseController;
import com.netease.domain.Basic;
import com.netease.domain.User;
import com.netease.service.BasicService;
import com.netease.service.UserService;
import com.netease.util.ValidateUtil;
import com.Interface.BasicService;
import com.DTO.Basic;
@Controller
public class BasicController extends BaseController{

 final  Logger logger;

@Autowired
 private BasicService basicService;

@Autowired
 private UserService userService;


@RequestMapping(value = "/test/forget_info", method = RequestMethod.GET)
public String testForget_info(){
    return "/test/forget_info";
}


@RequestMapping(value = "/test/signinfo", method = RequestMethod.GET)
public String testSigninfo(){
    return "test/signinfo";
}


@RequestMapping(value = "/test/signdo", method = RequestMethod.GET)
public String testSignDo(String name,String credential_number,String login_name,String password,String mobile_number,Model m){
    // 验证信息是否完整
    try {
        if ("".equals(name) || "".equals(credential_number) || "".equals(login_name) || "".equals(password) || "".equals(mobile_number)) {
            logger.info("信息不完整！");
            m.addAttribute("info", "信息不完整！");
            return "redirect:/test/signinfo";
        }
        User user = new User();
        user.setName(name);
        user.setCredential_type(1);
        // 验证身份证号是否二代身份证
        if (!ValidateUtil.validateCredential_number(credential_number)) {
            m.addAttribute("info", "无效的身份证号！");
            return "redirect:/test/signinfo";
        }
        user.setCredential_number(credential_number);
        // 验证登录名
        if (!ValidateUtil.validateLogin_name(login_name)) {
            m.addAttribute("info", "登录名不合法！请输入6-16位的字符数组-_/组合！");
            return "redirect:/test/signinfo";
        }
        user.setLoginName(login_name);
        // 验证密码
        if (!ValidateUtil.validatePassword(password)) {
            m.addAttribute("info", "密码不合法！请输入6-20位数字，字符组合！");
            return "redirect:/test/signinfo";
        }
        user.setPassword(password);
        // 验证邮箱
        user.setEmail("email");
        // 验证手机号
        BigInteger mobile;
        if (!ValidateUtil.validateMobile(mobile_number)) {
            m.addAttribute("info", "手机号错误");
            return "redirect:/test/signinfo";
        }
        mobile = BigInteger.valueOf(Long.valueOf(mobile_number));
        user.setMobileNumber(mobile);
        List<User> users = userService.findAll();
        for (User one : users) {
            if (credential_number.equals(one.getCredential_number()) || mobile.equals(one.getMobileNumber())) {
                logger.info("用户已注册！");
                m.addAttribute("info", "用户已注册");
                return "redirect:/test/signinfo";
            }
        }
        user.setSecurity_level(1);
        user.setIs_quick_query(1);
        user.setCreate_time(new DateTime());
        user.setUpdate_time(new DateTime());
        userService.save(user);
        logger.info(user.toString());
        m.addAttribute("user", user);
        return "test/sign_success";
    } catch (Exception e) {
        logger.debug("sign fail: {}", e);
        return "redirect:/test/sign";
    }
}


@RequestMapping(value = "/test/mobilelogin", method = RequestMethod.GET)
public String testMobileLogin(){
    return "test/mobilelogin";
}


@RequestMapping(value = "/test/mobileauth", method = RequestMethod.GET)
public String testMobileAuth(String mobile_number,Model m){
    // 验证手机号
    if (!ValidateUtil.validateMobile(mobile_number)) {
        m.addAttribute("info", "手机号无效");
        return "redirect:/test/mobilelogin";
    }
    // 生成一个验证码发送到手机，同时后台保存已验证
    return "/test/mobilelogin";
}


@RequestMapping(value = "/test/logindo", method = RequestMethod.GET)
public String testLoginDo(String username,String password,Model m){
    try {
        logger.info("username:" + username + "    password:" + password);
        // UsernamePasswordToken token = new UsernamePasswordToken(username,
        // password);
        // Subject subject = SecurityUtils.getSubject();
        // subject.login(token);
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getLoginName().equals(username) && user.getPassword().equals(password)) {
                return "test/login_success";
            }
        }
    } catch (Exception e) {
        logger.debug("Login fail: {}", e);
    }
    return "redirect:/test/login";
}


@RequestMapping(value = "/test/mobilelogindo", method = RequestMethod.GET)
public String testMobileLoginDo(String mobile_number,String authCode,Model m){
    if (!ValidateUtil.validateMobile(mobile_number)) {
        m.addAttribute("info", "手机号无效");
        return "redirect:/test/mobilelogin";
    }
    // 验证码验证
    // 检查该手机号是否已经注册
    List<User> users = userService.findAll();
    for (User user : users) {
        if (user.getMobileNumber().toString().equals(mobile_number)) {
            // 用户已经注册，进入对应管理页
            m.addAttribute("info", "用户已注册");
            return "test/login_success";
        }
    }
    // 为用户创建一个新的账户
    User user = new User();
    user.setName(mobile_number);
    user.setCredential_type(1);
    user.setCredential_number("0");
    user.setLoginName(mobile_number);
    user.setCreate_time(new DateTime());
    user.setUpdate_time(new DateTime());
    // 为用户生成一个密码
    String password = "xdghg23xMdf";
    user.setPassword(password);
    user.setMobileNumber(new BigInteger(mobile_number));
    userService.save(user);
    return "test/login_success";
}


@RequestMapping(value = "/admin/addbasic", method = RequestMethod.GET)
public String addBasic(String report_number,Model m){
    logger.info("reportnumber:" + report_number);
    Basic basic = new Basic();
    basic.setReportNumber(report_number);
    basic.setCreate_time(new DateTime());
    // basic.setName("test");
    // basic.setQuery_time(new DateTime());
    // basic.setCredential_type(0);
    // basic.setCredential_number("123456");
    // basic.setIs_married(false);
    basic.setUpdate_time(new DateTime());
    basicService.save(basic);
    logger.info(basic.toString());
    return "admin/basic";
}


@RequestMapping(value = "/test/sign", method = RequestMethod.GET)
public String testSign(){
    return "test/sign";
}


@RequestMapping(value = "/test/login", method = RequestMethod.GET)
public String testLogin(){
    // User user = userService.getByLoginName("geyule");
    // if (user != null)
    // logger.info(user.toString());
    return "test/login";
}


}