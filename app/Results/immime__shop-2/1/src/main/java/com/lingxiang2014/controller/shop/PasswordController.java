package com.lingxiang2014.controller.shop;
 import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.Message;
import com.lingxiang2014.Setting;
import com.lingxiang2014.Setting.CaptchaType;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.SafeKey;
import com.lingxiang2014.entity.BaseEntity.Save;
import com.lingxiang2014.service.CaptchaService;
import com.lingxiang2014.service.MailService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.MailService;
import com.lingxiang2014.DTO.SafeKey;
@Controller("shopPasswordController")
@RequestMapping("/password")
public class PasswordController extends BaseController{

@Resource(name = "captchaServiceImpl")
 private  CaptchaService captchaService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "mailServiceImpl")
 private  MailService mailService;


@RequestMapping(value = "/find", method = RequestMethod.POST)
@ResponseBody
public Message find(String captchaId,String captcha,String username,String email){
    if (!captchaService.isValid(CaptchaType.findPassword, captchaId, captcha)) {
        return Message.error("shop.captcha.invalid");
    }
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email)) {
        return Message.error("shop.common.invalid");
    }
    Member member = memberService.findByUsername(username);
    if (member == null) {
        return Message.error("shop.password.memberNotExist");
    }
    if (!member.getEmail().equalsIgnoreCase(email)) {
        return Message.error("shop.password.invalidEmail");
    }
    Setting setting = SettingUtils.get();
    SafeKey safeKey = new SafeKey();
    safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
    safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(), setting.getSafeKeyExpiryTime()) : null);
    member.setSafeKey(safeKey);
    memberService.update(member);
    mailService.sendFindPasswordMail(member.getEmail(), member.getUsername(), safeKey);
    return Message.success("shop.password.mailSuccess");
}


@RequestMapping(value = "reset", method = RequestMethod.POST)
@ResponseBody
public Message reset(String captchaId,String captcha,String username,String newPassword,String key){
    if (!captchaService.isValid(CaptchaType.resetPassword, captchaId, captcha)) {
        return Message.error("shop.captcha.invalid");
    }
    Member member = memberService.findByUsername(username);
    if (member == null) {
        return ERROR_MESSAGE;
    }
    if (!isValid(Member.class, "password", newPassword, Save.class)) {
        return Message.warn("shop.password.invalidPassword");
    }
    Setting setting = SettingUtils.get();
    if (newPassword.length() < setting.getPasswordMinLength() || newPassword.length() > setting.getPasswordMaxLength()) {
        return Message.warn("shop.password.invalidPassword");
    }
    SafeKey safeKey = member.getSafeKey();
    if (safeKey == null || safeKey.getValue() == null || !safeKey.getValue().equals(key)) {
        return ERROR_MESSAGE;
    }
    if (safeKey.hasExpired()) {
        return Message.error("shop.password.hasExpired");
    }
    member.setPassword(DigestUtils.md5Hex(newPassword));
    member.setPassword2(DigestUtils.md5Hex(newPassword));
    safeKey.setExpire(new Date());
    safeKey.setValue(null);
    memberService.update(member);
    return Message.success("shop.password.resetSuccess");
}


}