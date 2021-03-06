package com.circle.controller.login;
 import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.circle.constant.SystemDict;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.commission.CommissionHistory;
import com.circle.pojo.msg.MessageBean;
import com.circle.pojo.user.User;
import com.circle.service.circle.ICircleService;
import com.circle.service.commossion.ICommissionService;
import com.circle.service.login.ILoginService;
import com.circle.service.msg.IMsgService;
import com.circle.service.user.IUserService;
import com.circle.utils.MessageUtil;
import com.circle.utils.commission.CommissionComputeFactory;
import com.circle.utils.commission.compute.InviteCommissionCompute;
import com.circle.utils.msg.MsgQueue;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.ProUtil;
import com.Interface.IMsgService;
import com.Interface.ICircleService;
@Controller
@RequestMapping("login")
public class LoginController {

 private  Logger logger;

@Resource
 private IMsgService msgService;

@Resource
 private ILoginService loginService;

@Resource
 private ICircleService circleService;

@Resource
 private IUserService userService;

@Resource
 private ICommissionService commissionService;


public String generateInviteCode(User user){
    String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    String code = "";
    while (code.length() < 5) {
        if (Math.random() < 0.5)
            code += (int) (Math.random() * 10);
        else
            code += letter[(int) (Math.random() * 26)];
    }
    user = userService.getUserByInviteCode(code);
    if (user != null)
        generateInviteCode(user);
    return code;
}


@ResponseBody
@RequestMapping("registUser.action")
public String registUser(String mobilePhone,String userName,String password,String verificationCode,String inviteCode,HttpServletRequest request){
    request.setCharacterEncoding("utf-8");
    JSONObject josnResult = new JSONObject();
    String msg = "";
    String url = "";
    int result = 0;
    url = ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN);
    // ????????????
    boolean flag = false;
    User user = new User();
    user.setMobilePhone(mobilePhone);
    user.setPassword(password);
    user.setName(userName);
    user.setHeadImage(SystemDict.DEFAULT_USER_HEAD_PATH);
    String code = generateInviteCode(user);
    user.setInviteCode(code);
    // ??????????????????????????????
    User _user = userService.getUserByInviteCode(inviteCode);
    user.setInviteUserId(_user == null ? 0 : _user.getId());
    try {
        flag = userService.addUser(user);
        // ?????????????????? ????????????
        if (flag) {
            // ???????????????????????????
            if (user.getInviteUserId() != 0) {
                double money = CommissionComputeFactory.getCommissionCompute(SystemDict.COMMISSION_TYPE_INVITE).compute(user.getInviteUserId(), user.getId(), 0);
                CommissionHistory commissionHistory = new CommissionHistory(user.getInviteUserId(), user.getId(), money, Integer.parseInt(SystemDict.COMMISSION_TYPE_INVITE), 0, user.getId());
                commissionService.increaseUserCommission(commissionHistory);
            }
            result = 1;
            loginService.msgValidateOver(user.getMobilePhone(), verificationCode);
            msg = "????????????,??????????????????????????????????????????????????????????????????!";
            HttpSession session = request.getSession();
            user.setImagePath(user.getHeadImage());
            user.setHeadImage(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + user.getHeadImage());
            session.setAttribute(ConstantBusiKeys.SessionKey.SYS_USER, user);
            // ????????????????????????
            try {
                loginService.updateUserLastLoginTimer(mobilePhone);
            } catch (SPTException e) {
                logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
            }
        } else {
            result = 0;
            msg = "????????????????????????!";
        }
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        e.printStackTrace();
    }
    josnResult.put("msg", msg);
    josnResult.put("url", url);
    josnResult.put("result", result);
    return josnResult.toString();
}


@RequestMapping("logout.action")
public void logout(HttpServletRequest req,HttpServletResponse res){
    try {
        req.getSession().removeAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
        res.sendRedirect("../index.jsp");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


@ResponseBody
@RequestMapping("checkRegister.action")
public String checkRegister(String mobile,String code){
    String result = "success";
    try {
        if (!loginService.checkMsgCorrect(mobile, code)) {
            result = "2";
            return result;
        } else {
            // ???????????????
            result = loginService.checkVCodeIsOutTime(mobile, code) == 0 ? "1" : "success";
        }
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        e.printStackTrace();
    }
    return result;
}


public boolean sendSMS(String phone,String status){
    String s = "";
    while (s.length() < 6) {
        s += (int) (Math.random() * 10);
    }
    boolean flag = false;
    if (loginService.sendMsg(phone, s) > 0) {
        // ??????????????????????????????
        flag = true;
        if (status.equals(SystemDict.SEND_MSG_STATUS_REG))
            MessageUtil.sendRegisterCode(phone, s);
        else
            MessageUtil.sendUpdatePassCode(phone, s);
    }
    return flag;
}


@ResponseBody
@RequestMapping("sendValidateMsg.action")
public String sendValidateMsg(String mobilePhone,String status,HttpServletRequest request){
    String result = "";
    // ??????????????????
    try {
        if (status.equals(SystemDict.SEND_MSG_STATUS_REG)) {
            result = userService.checkUserIsExtists(mobilePhone) > 0 ? "0" : "";
            if (result.equals("0")) {
                return result;
            }
        } else {
            result = userService.checkUserIsExtists(mobilePhone) > 0 ? "" : "0";
            if (result.equals("0")) {
                return result;
            }
        }
        boolean flag = sendSMS(mobilePhone, status);
        if (!flag) {
            result = "1";
        }
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        e.printStackTrace();
    }
    return result;
}


@ResponseBody
@RequestMapping("login.action")
public String login(String mobilePhone,String password,String viewId,String circleId,HttpServletRequest request,HttpServletResponse response){
    viewId = viewId == null ? "" : viewId;
    JSONObject josnResult = new JSONObject();
    HttpSession session = request.getSession();
    String msg = "";
    String url = "";
    int result = 0;
    // ??????????????????
    User user = null;
    // ????????????
    if (!CommonUtil.isEmpty(mobilePhone) && !CommonUtil.isEmpty(password)) {
        try {
            user = loginService.login(mobilePhone, password);
        } catch (Exception e) {
            logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        }
        // ????????????
        if (null == user) {
            msg = "??????????????????????????????????????????!";
            result = 0;
        } else // ????????????
        {
            session.setAttribute(ConstantBusiKeys.SessionKey.SYS_USER, user);
            // ????????????????????????
            try {
                loginService.updateUserLastLoginTimer(mobilePhone);
            } catch (SPTException e) {
                logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
            }
            msg = "";
            result = 1;
        }
    }
    // ??????????????????
    if (viewId.equals("1")) {
        url = "/usercenter/toCreateCircle.action";
    } else // ??????????????????
    if (viewId.equals("2") && user != null) {
        try {
            Circle circle = circleService.queryCircleByCircleId(circleId);
            if (user.getId() == circle.getCreateUserId()) {
                msg = "???????????????,????????????!";
                josnResult.put("msg", msg);
                josnResult.put("url", url);
                josnResult.put("result", result);
                josnResult.put("viewId", viewId);
                return josnResult.toString();
            }
            // ?????? ?????? ????????????
            if (circle.getJoinType().equals(SystemDict.CIRCLE_AUTO_JOIN)) {
                msg = circleService.queryCircleMemberByCircleIdAndUserId(circleId, user.getId() + "") == 0 ? "" : "?????????????????????!";
                if (!msg.equals("")) {
                    josnResult.put("msg", msg);
                    josnResult.put("url", url);
                    josnResult.put("result", result);
                    josnResult.put("viewId", viewId);
                    return josnResult.toString();
                }
                msg = circleService.AddUserToCircle(user.getId(), circleId, SystemDict.NORMAL_USER) == true ? "success" : "????????????!";
                if (msg.equals("success")) {
                    List<Circle> myCircle = circleService.queryMyCircleList(user.getId());
                    request.getSession().setAttribute(ConstantBusiKeys.SessionKey.MY_CIRCLE, myCircle);
                    MessageBean msgBean = new MessageBean(SystemDict.MESSAGE_NORMAL, "?????????" + user.getName() + "???????????????[" + circle.getName() + "]??????!", user.getId() + "", circle.getCreateUserId() + "", "");
                    MsgQueue.GROUP_QUEUE.add(msgBean);
                }
            } else {
                JSONObject jsonResult = new JSONObject();
                jsonResult.put("userId", user.getId());
                jsonResult.put("circleId", circleId);
                jsonResult.put("createUserId", circle.getCreateUserId());
                jsonResult.put("userName", user.getName());
                jsonResult.put("circleName", circle.getName());
                // ?????????????????????
                if (circleService.queryCircleMemberAuditByCircleIdAndUserId(circleId, user.getId() + "") == 0) {
                    boolean flag = circleService.AddUserToCircleAudit(user.getId() + "", circleId, SystemDict.NORMAL_USER, SystemDict.EXAMINEING);
                    if (flag) {
                        // ????????????????????????????????????
                        if (msgService.queryCircleMsgCount(user.getId() + "", circle.getCreateUserId() + "") == 0) {
                            MessageBean msgBean = new MessageBean(SystemDict.MESSAGE_CIRCLE, "?????????" + user.getName() + "??????????????????[!" + circle.getName() + "]??????!", user.getId() + "", circle.getCreateUserId() + "", jsonResult.toString());
                            MsgQueue.GROUP_QUEUE.add(msgBean);
                        }
                        msg = "???????????????????????????????????????!";
                    } else {
                        msg = "??????????????????!";
                    }
                } else {
                    msg = "?????????????????????!";
                }
            }
        } catch (SPTException e) {
            e.printStackTrace();
            msg = "??????????????????!";
        }
    } else if (viewId.equals("3") && user != null) {
        // ????????????
        url = "";
        response.sendRedirect(request.getContextPath() + url);
        return null;
    }
    josnResult.put("msg", msg);
    josnResult.put("url", url);
    josnResult.put("result", result);
    josnResult.put("viewId", viewId);
    return josnResult.toString();
}


@ResponseBody
@RequestMapping("checkUserIsLogin.action")
public String checkUserIsLogin(HttpServletRequest request){
    String result = "0";
    User user = (User) request.getSession().getAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
    if (null == user)
        result = "0";
    else
        result = "1";
    return result;
}


}