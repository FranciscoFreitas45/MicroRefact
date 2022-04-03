package com.gs.controller;
 import ch.qos.logback.classic.Logger;
import com.gs.bean.Appointment;
import com.gs.bean.Company;
import com.gs.bean.Complaint;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.AppointmentService;
import com.gs.service.CompanyService;
import com.gs.service.ComplaintService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.CompanyService;
@Controller
@RequestMapping("/")
public class UserIndexController {

 private  Subject subject;

 private  Logger logger;

@Resource
 private  AppointmentService appointmentService;

@Resource
 private  UserService userService;

@Resource
 private  CompanyService companyService;

@Resource
 private  ComplaintService complaintService;


@ResponseBody
@RequestMapping(value = "Userinsert", method = RequestMethod.POST)
public ControllerResult insert(HttpSession session,Complaint complaint){
    logger.info("投诉记录添加操作");
    User user = (User) session.getAttribute("frontUser");
    complaint.setUserId(user.getUserId());
    complaintService.insert(complaint);
    return ControllerResult.getSuccessResult("添加投诉信息成功");
}


@InitBinder
public void initBinder(WebDataBinder binder){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
}


@RequestMapping(value = "editpwd", method = RequestMethod.GET)
public String editpwd(){
    return "Frontpage/Personalcenter/AccountSettings/editpwd";
}


@RequestMapping(value = "appointmenting", method = RequestMethod.GET)
public String appointmenting(){
    return "Frontpage/Personalcenter/reservation/appointmenting";
}


@ResponseBody
@RequestMapping(value = "isOwnerLogin/{roles}", method = RequestMethod.POST)
public ControllerResult isLogin(String roles,HttpSession session){
    if (SessionUtil.isOwnerLogin(session)) {
        if (RoleUtil.checkRoles(roles)) {
            return ControllerResult.getSuccessResult("拥有角色");
        } else {
            logger.info("无进入方法角色");
            return ControllerResult.getNotRoleResult("权限不足");
        }
    } else {
        logger.info("请先登录");
        return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
    }
}


@RequestMapping(value = "companydeta", method = RequestMethod.GET)
public String companydeta(){
    return "Frontpage/Personalcenter/AccountSettings/companydetails";
}


@RequestMapping(value = "logout", method = RequestMethod.GET)
public String logout(HttpSession session){
    Subject currentUser = SecurityUtils.getSubject();
    if (SessionUtil.isLogin(session)) {
        User user = (User) session.getAttribute("user");
        user.setUserLoginedTime((Date) session.getAttribute("userLoginedTime"));
        userService.update(user);
    }
    currentUser.logout();
    return "Frontpage/registered";
}


@RequestMapping(value = "details", method = RequestMethod.GET)
public String details(){
    return "Frontpage/Personalcenter/maintain/details";
}


@RequestMapping(value = "welcome", method = RequestMethod.GET)
public ModelAndView welcome(HttpServletRequest request,HttpSession session){
    List<Company> companys = companyService.queryAll((User) session.getAttribute("frontUser"));
    System.out.print(companys);
    request.setAttribute("companys", companys);
    ModelAndView mav = new ModelAndView("Frontpage/Personalcenter/AccountSettings/welcome");
    return mav;
}


@RequestMapping(value = "conrecord", method = RequestMethod.GET)
public String conrecord(){
    return "Frontpage/Personalcenter/Consumptionstatistics/Consumptionrecord";
}


@ResponseBody
@RequestMapping(value = "appointmentAdd", method = RequestMethod.POST)
public ControllerResult add(Appointment appointment){
    logger.info("前台添加电话预约");
    if (appointment != null) {
        appointment.setCurrentStatus("已预约");
        appointmentService.insert(appointment);
        return ControllerResult.getSuccessResult("添加预约成功");
    } else {
        return ControllerResult.getFailResult("添加预约失败");
    }
}


@ResponseBody
@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
public ControllerResult updatePwd(String oldPwd,String newPwd,String conPwd,HttpSession session){
    User user = (User) session.getAttribute("frontUser");
    if (user != null && !user.equals("")) {
        if (user.getUserPwd().equals(EncryptUtil.md5Encrypt(oldPwd))) {
            if (newPwd != null && newPwd.equals(conPwd)) {
                user.setUserPwd(EncryptUtil.md5Encrypt(conPwd));
                userService.updatePwd(user);
                logger.info("用户更新密码成功");
                return ControllerResult.getSuccessResult("修改密码成功");
            } else {
                logger.info("两次密码输入不一致");
                return ControllerResult.getFailResult("两次密码输入不一致，请重新输入！");
            }
        } else {
            logger.info("旧密码输入有误！");
            return ControllerResult.getFailResult("更新密码失败，您的旧密码输入有误");
        }
    } else {
        logger.info("用户还未登陆");
        return ControllerResult.getFailResult("请登录");
    }
}


@RequestMapping(value = "changeaccount", method = RequestMethod.GET)
public String changeacc(HttpSession session){
    Subject currentUser = SecurityUtils.getSubject();
    if (SessionUtil.isOwnerLogin(session)) {
        User user = (User) session.getAttribute("frontUser");
        user.setUserLoginedTime((Date) session.getAttribute("userLoginedTime"));
        userService.update(user);
    }
    currentUser.logout();
    return "Frontpage/registered";
}


@RequestMapping(value = "cdocument", method = RequestMethod.GET)
public String cdocument(){
    return "Frontpage/Personalcenter/Consumptionstatistics/Chargedocuments";
}


@ResponseBody
@RequestMapping(value = "editinfomation", method = RequestMethod.POST)
public ControllerResult editinfomation(User user,HttpSession session,String province,String city,String area){
    if (user != null && !user.equals("")) {
        if (province != null && city != null || area != null) {
            user.setUserAddress(province + "-" + city + "-" + area);
            userService.update(user);
        } else {
            userService.update(user);
        }
        User u = userService.queryInfoById(user.getUserId());
        session.setAttribute("frontUser", u);
        return ControllerResult.getSuccessResult("修改成功");
    } else {
        return ControllerResult.getFailResult("修改失败");
    }
}


@RequestMapping(value = "myrese", method = RequestMethod.GET)
public String myrese(){
    return "Frontpage/Personalcenter/reservation/reservation";
}


@RequestMapping(value = "accountinfo", method = RequestMethod.GET)
public String accountinfo(){
    return "Frontpage/Personalcenter/AccountSettings/accountinformation";
}


@RequestMapping(value = "schedule", method = RequestMethod.GET)
public String schedule(){
    return "Frontpage/Personalcenter/maintain/schedule";
}


@RequestMapping(value = "editinfo", method = RequestMethod.GET)
public String editinfocon(){
    return "Frontpage/Personalcenter/AccountSettings/accountinformation";
}


@RequestMapping(value = "showeditpage", method = RequestMethod.GET)
public String showedotpage(){
    return "Frontpage/Personalcenter/AccountSettings/EditInfomation";
}


@ResponseBody
@RequestMapping(value = "addApp", method = RequestMethod.POST)
public ControllerResult addApp(HttpSession session,Appointment appointment){
    if (SessionUtil.isOwnerLogin(session)) {
        if (appointment != null && !appointment.equals("")) {
            appointmentService.insert(appointment);
            return ControllerResult.getSuccessResult("添加预约成功");
        } else {
            return ControllerResult.getFailResult("添加预约失败，请输入预约信息");
        }
    } else {
        logger.info("用户未登录");
        return ControllerResult.getFailResult("用户未登录");
    }
}


@RequestMapping(value = "userrese", method = RequestMethod.GET)
public String userrese(){
    return "Frontpage/Personalcenter/maintain/reserecording";
}


@RequestMapping(value = "mycomment", method = RequestMethod.GET)
public String myComment(){
    return "Frontpage/Personalcenter/Consumptionstatistics/mycomment";
}


@RequestMapping(value = "outusers", method = RequestMethod.GET)
public String outUser(HttpSession session){
    Subject currentUser = SecurityUtils.getSubject();
    if (SessionUtil.isOwnerLogin(session)) {
        User user = (User) session.getAttribute("frontUser");
        user.setUserLoginedTime((Date) session.getAttribute("userLoginedTime"));
        userService.update(user);
    }
    currentUser.logout();
    return "Frontpage/Frontindex";
}


@RequestMapping(value = "prompt", method = RequestMethod.GET)
public String prompt(){
    return "Frontpage/Personalcenter/maintain/prompt";
}


}