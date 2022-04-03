package com.netease.controller.api;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.common.base.Optional;
import com.netease.domain.User;
import com.netease.dto.QuestionPageDTO;
import com.netease.enums.ReportStatusType;
import com.netease.service.LoginService;
import com.netease.service.RegisterFlowService;
import com.netease.service.ReportService;
import com.netease.service.SecurityUpgradeService;
import com.netease.service.UserService;
import com.netease.util.CommonServiceUtil;
import com.netease.util.ConstantUtil;
import com.netease.util.ParsePageUtil;
import com.Interface.ReportService;
@Controller
public class VerifyCodeController {

 final  Logger logger;

 static  boolean login_status;

@Autowired
 private LoginService loginService;

@Autowired
 private UserService userService;

@Autowired
 private ReportService reportService;

@Autowired
 private RegisterFlowService registerFLowService;

@Autowired
 private SecurityUpgradeService securityUpgradeService;


public String processUnapplyAndApplying(HttpServletRequest request,Model model,String loginName){
    // 跳转到填写验证码页面
    String responsePage = "";
    try {
        responsePage = loginService.queryReport();
    } catch (Exception e) {
        logger.error("获取报告失败", e);
        return "neteaseCredit/creditManager_gyl";
    }
    if (ConstantUtil.TRY_TO_GET_REPORT_ERROR.equals(responsePage)) {
        logger.error("尝试获取报告失败");
        return "neteaseCredit/creditManager_gyl";
    }
    // 查看返回结果是否包含“您的信用信息查询请求已提交，请在24小时后访问平台获取结果。
    // 为保障您的信息安全，您申请的信用信息将于7日后自动清理，请及时获取查询结果。”
    String errorMessage = ParsePageUtil.parseMessage(responsePage);
    String redioType = ParsePageUtil.parseRedioType(responsePage);
    logger.info("errorMessage: " + errorMessage + " redioType: " + redioType);
    model.addAttribute("login_name", loginName);
    if (errorMessage.contains(ConstantUtil.TWENTY_FOUR) && ConstantUtil.REDIO_VALUE.equals(redioType)) {
        // 报告已申请但是还没生成，则跳转到报告申请中页面
        // 更新用户报告状态为申请中
        userService.updateByLoginName(ReportStatusType.APPLYING.getValue(), loginName);
        return "neteaseCredit/process_report";
    } else if (ConstantUtil.NON_MESSAGE.equals(errorMessage) && ConstantUtil.REDIO_VALUE.equals(redioType)) {
        // 如果提示暂无信息，则表示没有提交申请，跳到回答问题页面
        // 更新用户报告状态为未申请
        userService.updateByLoginName(ReportStatusType.UNAPPLY.getValue(), loginName);
        return redirectToApplyingPage(request, model, loginName);
    } else if (ConstantUtil.RELOGIN.equals(errorMessage)) {
        return "neteaseCredit/userLoginInit";
    }
    // 报告已经生成，更新用户报告状态为报告已出,跳转到输入验证码页面，但是由于没有输入过验证码，因此状态还是申请中
    userService.updateByLoginName(ReportStatusType.APPLYING.getValue(), loginName);
    return "neteaseCredit/getReport";
}


@RequestMapping(value = "/Credit/registerverifycode", method = RequestMethod.GET)
public void genRegisterVerificationCode(HttpServletRequest request,HttpServletResponse response,Model m){
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setContentType("image/jpeg");
    CommonServiceUtil.getBaseURL(request, m);
    // 2.生成验证码图片
    BufferedImage bi = null;
    try {
        bi = registerFLowService.initAndGetImg();
    } catch (Exception e) {
        e.printStackTrace();
    }
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(bi, "jpg", out);
    try {
        out.flush();
    } finally {
        out.close();
    }
}


@RequestMapping(value = "/Credit/backCreditManager", method = RequestMethod.GET)
public String backCreditManager(HttpServletRequest request,HttpServletResponse response,Model m){
    CommonServiceUtil.getBaseURL(request, m);
    logger.info("返回征信管理首页");
    Subject subject = SecurityUtils.getSubject();
    // 获取当前登录用户
    String admin_userName = (String) subject.getPrincipal();
    logger.info("登录名：" + admin_userName);
    // web-todo
    // List<User> users = userService.findByCreateUser(admin_userName);
    List<User> users = userService.findByCreateUserAndLoginName(admin_userName, admin_userName);
    logger.info("共找到" + users.size() + "个用户");
    m.addAttribute("list", users);
    return "neteaseCredit/creditManager_gyl";
}


@RequestMapping(value = "/Credit/applyReport", method = RequestMethod.GET)
public String redirectToApplyingPage(HttpServletRequest request,Model model,String loginName){
    logger.info("跳转到回答问题页面");
    CommonServiceUtil.getBaseURL(request, model);
    // 如果检测到0，表明用户仅仅注册，需要回答问题才可以获取报告，回答问题之后用户的状态设置为1
    Map<String, List<String>> questions = loginService.extractQuestionPage();
    if (questions.size() == 0) {
        // 拿不到这个人的征信报告，返回到一个错误提示页
        return "neteaseCredit/noEnoughInfo";
    }
    model.addAttribute("login_name", loginName);
    model.addAttribute("questions", questions);
    logger.info("获取问题成功");
    return "neteaseCredit/apply_report";
}


@RequestMapping(value = "/Credit/getReport", method = RequestMethod.GET)
public String getReport(Model model,HttpServletRequest request,HttpServletResponse response,String loginName,Integer report_status){
    logger.info("进入VerifyCodeController#getReport方法");
    CommonServiceUtil.getBaseURL(request, model);
    if (report_status == ReportStatusType.REPORTFINISH.getValue()) {
        // 调用征信报告查询服务
        Map<String, Object> map = reportService.getPersonCreditReport(loginName);
        model.addAttribute("login_name", loginName);
        if (map.size() > 1) {
            // 说明本系统中已经存在报告，则直接跳转到显示报告页面中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
        } else {
            // 说明征信报告已经出，但是没有输入过身份码，跳转到输入身份码页面
            return "neteaseCredit/getReport";
        }
        logger.info("map.size(): " + map.size());
        // 跳转到征信报告展示jsp
        return "neteaseCredit/personalCreditReport_gyl";
    } else if (report_status == ReportStatusType.APPLYING.getValue() || report_status == ReportStatusType.UNAPPLY.getValue()) {
        // 先尝试获取报告，根据不同的响应结果进行相应处理
        return processUnapplyAndApplying(request, model, loginName);
    }
    return "neteaseCredit/creditManager_gyl";
}


@RequestMapping(value = "/Credit/getReportByVerifyCode", method = RequestMethod.POST)
public String getReportByVerifyCode(Model model,HttpServletRequest request,HttpServletResponse response,String loginName,String verifyCode){
    logger.info("getReportByVerifyCode");
    CommonServiceUtil.getBaseURL(request, model);
    try {
        // 从央行系统获取报告
        String reportResponse = loginService.getCreditContent(verifyCode);
        String errorMessage = ParsePageUtil.parseCredentialCodeErrorPage(reportResponse);
        if (!StringUtils.isEmpty(errorMessage)) {
            // 身份验证码错误或者身份码失效
            model.addAttribute("info", errorMessage + "请查看身份码是否已经过期。");
            model.addAttribute("login_name", loginName);
            return "neteaseCredit/getReport";
        }
        /**
         * 解析报告并保存，再修改用户的报告的状态
         */
        // 解析获取的报告
        reportService.parseAndSave(reportResponse, loginName);
        // 调用征信服务展示
        Map<String, Object> map = reportService.getPersonCreditReport(loginName);
        if (map.size() > 1) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
            // 更新用户报告状态
            userService.updateByLoginName(ReportStatusType.REPORTFINISH.getValue(), loginName);
            // 跳转到征信报告展示jsp
            return "neteaseCredit/personalCreditReport_gyl";
        }
    } catch (Exception e) {
        logger.error("获取征信报告遇到问题", e);
    }
    // 跳转到征信管理界面
    return "neteaseCredit/creditManager_gyl";
}


@RequestMapping("/neteaseCredit/success")
public String userLoginSuccess(){
    return "neteaseCredit/register_3";
}


@RequestMapping(value = "/Credit/userLoginInit", method = RequestMethod.GET)
public String userLogin(HttpServletRequest request,HttpServletResponse response,Model model){
    CommonServiceUtil.getBaseURL(request, model);
    login_status = false;
    return "neteaseCredit/login";
}


@Deprecated
@RequestMapping(value = "/Credit/userLogin", method = RequestMethod.POST)
public String getCreditReport(HttpServletRequest request,HttpServletResponse response,Model m,String loginname,String password,String mgrc){
    CommonServiceUtil.getBaseURL(request, m);
    logger.info("开始登录");
    try {
        User user = new User();
        // 简单的页面数据框校验
        if ("".equals(loginname) || loginname.trim() == null) {
            m.addAttribute("info", "登录名为空,请重新输入");
            m.addAttribute("loginname", loginname);
            m.addAttribute("password", password);
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        if ("".equals(mgrc) || mgrc.trim() == null) {
            m.addAttribute("info", "验证码为空,请重新输入");
            m.addAttribute("loginname", loginname);
            m.addAttribute("password", password);
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        if ("".equals(password) || password.trim() == null) {
            m.addAttribute("info", "密码为空,请重新输入");
            m.addAttribute("loginname", loginname);
            m.addAttribute("password", password);
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        // 用户接着登陆央行征信系统
        String result = loginService.exectLogin(loginname, password, mgrc);
        if (!"".equals(result)) {
            login_status = false;
            m.addAttribute("info", result);
            m.addAttribute("loginname", loginname);
            m.addAttribute("password", password);
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        // return "redirect:userLoginInit";
        }
        Subject subject = SecurityUtils.getSubject();
        String admin_userName = (String) subject.getPrincipal();
        login_status = true;
        logger.info("登录征信系统状态： " + login_status);
        // 只有当登录成功，才把正确的信息保存到数据库
        User user_1 = userService.getByLoginName(loginname);
        Optional<User> optional = Optional.fromNullable(user_1);
        if (!optional.isPresent()) {
            // 如果数据库查询为null，才把用户信息保存到数据库
            // 当用户在央行征信系统已经注册过，但是在网易征信系统没有注册过，这个时候先央行登录，然后这边保存，再做本系统的shiro安全登录
            logger.info("用户不存在，保存用户信息");
            String randomHex = new SecureRandomNumberGenerator().nextBytes().toHex();
            String passwordSha = new Sha256Hash(password, randomHex).toString();
            user.setLoginName(loginname);
            user.setCreateUser(admin_userName);
            user.setPassword(password);
            user.setPasswordsha(passwordSha);
            user.setSecurity_level(0);
            user.setIs_quick_query(0);
            user.setCreate_time(new DateTime());
            user.setUpdate_time(new DateTime());
            user.setEmail("");
            user.setSalt(randomHex);
            user.setReport_status(ReportStatusType.UNAPPLY.getValue());
            userService.save(user);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, password.toCharArray());
        if (StringUtils.isEmpty(admin_userName)) {
            subject = SecurityUtils.getSubject();
        }
        // 用户登陆网易征信系统
        subject.login(token);
        admin_userName = (String) subject.getPrincipal();
        logger.info("登录名：" + admin_userName);
        List<User> users = userService.findByCreateUserAndLoginName(admin_userName, loginname);
        logger.info("共找到" + users.size() + "个用户");
        m.addAttribute("list", users);
        return "neteaseCredit/creditManager_gyl";
    } catch (Exception e) {
        logger.error("登录系统内失败", e);
    }
    return "redirect:userLoginInit";
}


@RequestMapping(value = "/Credit/creditLogin", method = RequestMethod.POST)
public String loginCreditCenter(HttpServletRequest request,HttpServletResponse response,Model m,String loginName,String password,String mgrc){
    CommonServiceUtil.getBaseURL(request, m);
    logger.info("开始登录");
    try {
        User user = new User();
        m.addAttribute("loginname", loginName);
        // 简单的页面数据框校验
        if (StringUtils.isEmpty(loginName)) {
            m.addAttribute("info", "登录名为空,请重新输入");
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        if (StringUtils.isEmpty(mgrc)) {
            m.addAttribute("info", "验证码为空,请重新输入");
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        if (StringUtils.isEmpty(password)) {
            m.addAttribute("info", "密码为空,请重新输入");
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        // 1、登录征信平台
        String result = loginService.exectLogin(loginName, password, mgrc);
        if (!"".equals(result)) {
            login_status = false;
            m.addAttribute("info", result);
            logger.info("登陆央行系统失败 ");
            // 返回到原页面,该地方需要直接调用登录页的方法，不能用requestMap的重定向方法，因为redirect会导致数据模板里的数据格式发生乱码
            return userLogin(request, response, m);
        }
        Subject subject = SecurityUtils.getSubject();
        String createUser = (String) subject.getPrincipal();
        login_status = true;
        logger.info("登录征信系统状态： " + login_status);
        // 2、查看本地系统是否存在该用户
        User user_1 = userService.getByLoginName(loginName);
        // 判断是否第一次登录本系统，默认不是第一次登录
        boolean flag = false;
        Optional<User> optional = Optional.fromNullable(user_1);
        if (!optional.isPresent()) {
            // 如果数据库查询为null，才把用户信息保存到数据库
            // 当用户在央行征信系统已经注册过，但是在网易征信系统没有注册过，这个时候先央行登录，然后再在本地系统保存
            logger.info("本地系统用户不存在，保存用户信息");
            String randomHex = new SecureRandomNumberGenerator().nextBytes().toHex();
            String passwordSha = new Sha256Hash(password, randomHex).toString();
            user.setLoginName(loginName);
            user.setCreateUser(createUser);
            user.setPassword(password);
            user.setPasswordsha(passwordSha);
            user.setSecurity_level(0);
            user.setIs_quick_query(0);
            user.setCreate_time(new DateTime());
            user.setUpdate_time(new DateTime());
            user.setEmail("");
            user.setSalt(randomHex);
            user.setReport_status(ReportStatusType.UNAPPLY.getValue());
            userService.save(user);
            flag = true;
        }
        // 3、如果不是第一次登录本系统，则检查本地是否存在该用户对应的信用报告，如果存在则直接跳转到展现页面，否则继续步骤4
        // 问题：如果他的创建者不是当前登录人是否修改，他的创建者？？？？？？？？？？？？？？？
        if (!flag) {
            Map<String, Object> map = reportService.getPersonCreditReport(loginName);
            m.addAttribute("login_name", loginName);
            if (map.size() > 1) {
                // 说明本系统中已经存在报告，则直接跳转到显示报告页面中
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    m.addAttribute(entry.getKey(), entry.getValue());
                }
                // 更新用户报告状态
                userService.updateByLoginName(ReportStatusType.REPORTFINISH.getValue(), loginName);
                // 跳转到征信报告展示jsp
                return "neteaseCredit/personalCreditReport_gyl";
            }
        }
        // 4、访问征信平台的“信息服务 > 申请信用信息”页面，根据返回信息跳转到相应页面
        return redirectByReportStatus(request, response, m, loginName);
    } catch (Exception e) {
        logger.error("登录系统内失败", e);
    }
    return "redirect:userLoginInit";
}


@RequestMapping(value = "/Credit/verifycode", method = RequestMethod.GET)
public void genVerificationCode(HttpServletRequest request,HttpServletResponse response,Model m){
    // 设置响应头（防止页面缓存），内容类型返回jpeg
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setContentType("image/jpeg");
    CommonServiceUtil.getBaseURL(request, m);
    // 2.生成验证码图片
    BufferedImage bi = null;
    try {
        bi = loginService.getImage();
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 获得字节流----输出相应体内容（即把out中的内容返回页面）
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(bi, "jpg", out);
    try {
        out.flush();
    } finally {
        out.close();
    }
}


@RequestMapping(value = "/Credit/submitQuestion", method = RequestMethod.POST)
public String submitQuestion(HttpServletRequest request,Model model,String loginName,QuestionPageDTO pageDTO){
    logger.info("提交回答问题页面");
    CommonServiceUtil.getBaseURL(request, model);
    // 先获取之前的token，否则访问loginService.getReportStatus()后token被覆盖了，提交问题时出现302
    // 报告是否已经生成，默认为没有生成
    boolean flag = false;
    // String reportStatus = loginService.getReportStatus();
    // if (reportStatus.equals(ConstantUtil.GENERATED)) {
    // flag = true;
    // }
    String result = loginService.submitQuestionPage(pageDTO, flag);
    model.addAttribute("result", result);
    model.addAttribute("login_name", loginName);
    Integer updateResult = -1;
    try {
        updateResult = userService.updateByLoginName(ReportStatusType.APPLYING.getValue(), loginName);
    } catch (Exception e) {
        if (1 != updateResult) {
            // 更新失败，跳转到错误页面
            logger.error("更新用户报告状态失败", e);
            return "neteaseCredit/userLoginInit";
        }
    }
    return "neteaseCredit/process_report";
}


public String redirectByReportStatus(HttpServletRequest request,HttpServletResponse response,Model model,String loginName){
    // 暂时注释掉
    String result = loginService.getReportStatus();
    // String result = ConstantUtil.VERIFY_UNPASS;
    if (result.equals(ConstantUtil.BLANK_MESSAGE) || result.equals(ConstantUtil.VERIFY_UNPASS)) {
        // 空白或者“验证未通过”时，状态都为“未申请”跳转到，回答问题页面
        userService.updateByLoginName(ReportStatusType.UNAPPLY.getValue(), loginName);
        return redirectToApplyingPage(request, model, loginName);
    } else if (result.equals(ConstantUtil.HANDLERING)) {
        // 状态为“申请中”，然后跳转到申请中页面
        userService.updateByLoginName(ReportStatusType.APPLYING.getValue(), loginName);
        return "neteaseCredit/process_report";
    } else if (result.equals(ConstantUtil.GENERATED)) {
        // 报告已经生成，更新用户报告状态为报告已出,跳转到输入验证码页面，但是由于没有输入过验证码，因此状态还是申请中
        userService.updateByLoginName(ReportStatusType.APPLYING.getValue(), loginName);
        return "neteaseCredit/getReport";
    }
    return "redirect:userLoginInit";
}


}