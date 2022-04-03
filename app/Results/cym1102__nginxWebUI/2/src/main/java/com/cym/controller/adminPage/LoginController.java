package com.cym.controller.adminPage;
 import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cym.config.VersionConfig;
import com.cym.model.Admin;
import com.cym.model.Remote;
import com.cym.service.AdminService;
import com.cym.service.CreditService;
import com.cym.service.SettingService;
import com.cym.utils.AuthUtils;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import com.cym.utils.PwdCheckUtil;
import com.cym.utils.SystemTool;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.StrUtil;
import com.cym.Interface.VersionConfig;
import com.cym.Interface.SettingService;
@RequestMapping("/adminPage/login")
@Controller
public class LoginController extends BaseController{

@Autowired
 private AdminService adminService;

@Autowired
 private CreditService creditService;

@Autowired
 private VersionConfig versionConfig;

@Autowired
 private AuthUtils authUtils;

@Value("${project.version}")
 private String currentVersion;

@Autowired
 private SettingService settingService;


@RequestMapping("login")
@ResponseBody
public JsonResult submitLogin(String name,String pass,String code,String authCode,String remember,HttpSession httpSession){
    // 验证码
    String imgCode = (String) httpSession.getAttribute("imgCode");
    if (StrUtil.isEmpty(imgCode) || StrUtil.isNotEmpty(imgCode) && !imgCode.equalsIgnoreCase(code)) {
        // 销毁验证码
        httpSession.removeAttribute("imgCode");
        // 验证码不正确
        return renderError(m.get("loginStr.backError1"));
    }
    // 用户名密码
    Admin admin = adminService.login(name, pass);
    if (admin == null) {
        // 用户名密码错误
        return renderError(m.get("loginStr.backError2"));
    }
    // 两步验证
    if (admin.getAuth() && !authUtils.testKey(admin.getKey(), authCode)) {
        // 身份码不正确
        return renderError(m.get("loginStr.backError6"));
    }
    // 登录成功
    httpSession.setAttribute("localType", "local");
    httpSession.setAttribute("isLogin", true);
    httpSession.setAttribute("admin", admin);
    // 立刻销毁验证码
    httpSession.removeAttribute("imgCode");
    // 检查更新
    versionConfig.getNewVersion();
    return renderSuccess(admin);
}


@ResponseBody
@RequestMapping("getAuth")
public JsonResult getAuth(String name,String pass,String code,Integer remote,HttpSession httpSession){
    // 验证码
    if (remote == null) {
        String imgCode = (String) httpSession.getAttribute("imgCode");
        if (StrUtil.isEmpty(imgCode) || StrUtil.isNotEmpty(imgCode) && !imgCode.equalsIgnoreCase(code)) {
            // 销毁验证码
            httpSession.removeAttribute("imgCode");
            // 验证码不正确
            return renderError(m.get("loginStr.backError1"));
        }
    }
    // 用户名密码
    Admin admin = adminService.login(name, pass);
    if (admin == null) {
        // 用户名密码错误
        return renderError(m.get("loginStr.backError2"));
    }
    Admin ad = new Admin();
    ad.setAuth(admin.getAuth());
    ad.setKey(admin.getKey());
    return renderSuccess(ad);
}


@ResponseBody
@RequestMapping("getCredit")
public JsonResult getCredit(String name,String pass,String code,String auth){
    // 用户名密码
    Admin admin = adminService.login(name, pass);
    if (admin == null) {
        // 用户名密码错误
        return renderError(m.get("loginStr.backError2"));
    }
    if (!admin.getAuth()) {
        String imgCode = settingService.get("remoteCode");
        if (StrUtil.isEmpty(imgCode) || StrUtil.isNotEmpty(imgCode) && !imgCode.equalsIgnoreCase(code)) {
            // 验证码不正确
            return renderError(m.get("loginStr.backError1"));
        }
    } else {
        if (!authUtils.testKey(admin.getKey(), auth)) {
            // 身份码不正确
            return renderError(m.get("loginStr.backError6"));
        }
    }
    // 立刻销毁验证码
    settingService.remove("remoteCode");
    Map<String, String> map = new HashMap<String, String>();
    map.put("creditKey", creditService.make(admin.getId()));
    map.put("system", SystemTool.getSystem());
    return renderSuccess(map);
}


@RequestMapping("/getRemoteCode")
public void getRemoteCode(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
    ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 40);
    captcha.setGenerator(new RandomGenerator("0123456789", 4));
    String createText = captcha.getCode();
    settingService.set("remoteCode", createText);
    captcha.write(httpServletResponse.getOutputStream());
}


@RequestMapping("loginOut")
public ModelAndView loginOut(ModelAndView modelAndView,HttpSession httpSession,HttpServletRequest request){
    httpSession.removeAttribute("isLogin");
    modelAndView.setViewName("/adminPage/index");
    return modelAndView;
}


@RequestMapping("addAdmin")
@ResponseBody
public JsonResult addAdmin(String name,String pass){
    Long adminCount = sqlHelper.findAllCount(Admin.class);
    if (adminCount > 0) {
        return renderError(m.get("loginStr.backError4"));
    }
    if (!(PwdCheckUtil.checkContainUpperCase(pass) && PwdCheckUtil.checkContainLowerCase(pass) && PwdCheckUtil.checkContainDigit(pass) && PwdCheckUtil.checkPasswordLength(pass, "8", "100"))) {
        return renderError(m.get("loginStr.tips"));
    }
    Admin admin = new Admin();
    admin.setName(name);
    admin.setPass(pass);
    admin.setAuth(false);
    admin.setType(0);
    sqlHelper.insert(admin);
    return renderSuccess();
}


@ResponseBody
@RequestMapping("/changeLang")
public JsonResult changeLang(){
    Long adminCount = sqlHelper.findAllCount(Admin.class);
    if (adminCount == 0) {
        // 只有初始化时允许修改语言
        if (settingService.get("lang") != null && settingService.get("lang").equals("en_US")) {
            settingService.set("lang", "");
        } else {
            settingService.set("lang", "en_US");
        }
    }
    return renderSuccess();
}


@RequestMapping("noServer")
public ModelAndView noServer(ModelAndView modelAndView){
    modelAndView.setViewName("/adminPage/login/noServer");
    return modelAndView;
}


@ResponseBody
@RequestMapping("getLocalType")
public JsonResult getLocalType(HttpSession httpSession){
    String localType = (String) httpSession.getAttribute("localType");
    if (StrUtil.isNotEmpty(localType)) {
        if ("local".equals(localType)) {
            return renderSuccess(m.get("remoteStr.local"));
        } else {
            Remote remote = (Remote) httpSession.getAttribute("remote");
            if (StrUtil.isNotEmpty(remote.getDescr())) {
                return renderSuccess(remote.getDescr());
            }
            return renderSuccess(remote.getIp() + ":" + remote.getPort());
        }
    }
    return renderSuccess("");
}


@RequestMapping("")
public ModelAndView admin(ModelAndView modelAndView,HttpServletRequest request,HttpSession httpSession,String adminId){
    modelAndView.addObject("adminCount", sqlHelper.findAllCount(Admin.class));
    modelAndView.setViewName("/adminPage/login/index");
    return modelAndView;
}


@RequestMapping("autoLogin")
@ResponseBody
public JsonResult autoLogin(String adminId,HttpSession httpSession){
    // 用户名密码
    Admin admin = sqlHelper.findById(adminId, Admin.class);
    if (admin != null) {
        // 登录成功
        httpSession.setAttribute("localType", "local");
        httpSession.setAttribute("isLogin", true);
        httpSession.setAttribute("admin", admin);
        // 立刻销毁验证码
        httpSession.removeAttribute("imgCode");
        // 检查更新
        versionConfig.getNewVersion();
        return renderSuccess(admin);
    } else {
        return renderError();
    }
}


@RequestMapping("/getCode")
public void getCode(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
    ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 40);
    captcha.setGenerator(new RandomGenerator("0123456789", 4));
    String createText = captcha.getCode();
    httpServletRequest.getSession().setAttribute("imgCode", createText);
    captcha.write(httpServletResponse.getOutputStream());
}


}