package com.lingxiang2014.controller.shop;
 import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.Message;
import com.lingxiang2014.Principal;
import com.lingxiang2014.Setting;
import com.lingxiang2014.Setting.AccountLockType;
import com.lingxiang2014.Setting.CaptchaType;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.CaptchaService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.RSAService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.util.WebUtils;
@Controller("shopLoginController")
@RequestMapping("/login")
public class LoginController extends BaseController{

@Resource(name = "captchaServiceImpl")
 private  CaptchaService captchaService;

@Resource(name = "rsaServiceImpl")
 private  RSAService rsaService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@RequestMapping(value = "/submit", method = RequestMethod.POST)
@ResponseBody
public Message submit(String captchaId,String captcha,String number,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    String password = rsaService.decryptParameter("enPassword", request);
    rsaService.removePrivateKey(request);
    if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
        return Message.error("shop.captcha.invalid");
    }
    if (StringUtils.isEmpty(number) || StringUtils.isEmpty(number)) {
        return Message.error("shop.common.invalid");
    }
    Member member;
    Setting setting = SettingUtils.get();
    if (setting.getIsEmailLogin() && number.contains("@")) {
        List<Member> members = memberService.findListByNumber(number);
        if (members.isEmpty()) {
            member = null;
        } else if (members.size() == 1) {
            member = members.get(0);
        } else {
            return Message.error("shop.login.unsupportedAccount");
        }
    } else {
        member = memberService.findByNumber(number);
    }
    if (member == null) {
        return Message.error("shop.login.unknownAccount");
    }
    if (!member.getIsEnabled()) {
        return Message.error("shop.login.disabledAccount");
    }
    if (member.getIsLocked()) {
        if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member)) {
            int loginFailureLockTime = setting.getAccountLockTime();
            if (loginFailureLockTime == 0) {
                return Message.error("shop.login.lockedAccount");
            }
            Date lockedDate = member.getLockedDate();
            Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
            if (new Date().after(unlockDate)) {
                member.setLoginFailureCount(0);
                member.setIsLocked(false);
                member.setLockedDate(null);
                memberService.update(member);
            } else {
                return Message.error("shop.login.lockedAccount");
            }
        } else {
            member.setLoginFailureCount(0);
            member.setIsLocked(false);
            member.setLockedDate(null);
            memberService.update(member);
        }
    }
    if (!DigestUtils.md5Hex(password).equals(member.getPassword())) {
        int loginFailureCount = member.getLoginFailureCount() + 1;
        if (loginFailureCount >= setting.getAccountLockCount()) {
            member.setIsLocked(true);
            member.setLockedDate(new Date());
        }
        member.setLoginFailureCount(loginFailureCount);
        memberService.update(member);
        if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member)) {
            return Message.error("shop.login.accountLockCount", setting.getAccountLockCount());
        } else {
            return Message.error("shop.login.incorrectCredentials");
        }
    }
    member.setLoginIp(request.getRemoteAddr());
    member.setLoginDate(new Date());
    member.setLoginFailureCount(0);
    memberService.update(member);
    Map<String, Object> attributes = new HashMap<String, Object>();
    Enumeration<?> keys = session.getAttributeNames();
    while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        attributes.put(key, session.getAttribute(key));
    }
    session.invalidate();
    session = request.getSession();
    for (Entry<String, Object> entry : attributes.entrySet()) {
        session.setAttribute(entry.getKey(), entry.getValue());
    }
    session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), number));
    WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getNumber());
    if (!member.getIsComplate()) {
        return Message.warn("登陆成功，请立即完善资料！");
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/index1", method = RequestMethod.GET)
public String index1(String redirectUrl,HttpServletRequest request,ModelMap model){
    Setting setting = SettingUtils.get();
    if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(setting.getSiteUrl()) && !redirectUrl.startsWith(request.getContextPath() + "/") && !redirectUrl.startsWith(setting.getSiteUrl() + "/")) {
        redirectUrl = null;
    }
    model.addAttribute("redirectUrl", redirectUrl);
    model.addAttribute("captchaId", UUID.randomUUID().toString());
    return "/shop/login/index1";
}


@RequestMapping(method = RequestMethod.GET)
public String index(String redirectUrl,HttpServletRequest request,ModelMap model){
    Setting setting = SettingUtils.get();
    if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(setting.getSiteUrl()) && !redirectUrl.startsWith(request.getContextPath() + "/") && !redirectUrl.startsWith(setting.getSiteUrl() + "/")) {
        redirectUrl = null;
    }
    model.addAttribute("redirectUrl", redirectUrl);
    model.addAttribute("captchaId", UUID.randomUUID().toString());
    return "/shop/login/index";
}


@RequestMapping(value = "/check", method = RequestMethod.GET)
@ResponseBody
public Boolean check(){
    return memberService.isAuthenticated();
}


}