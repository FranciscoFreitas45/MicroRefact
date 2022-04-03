package com.lingxiang2014.controller.shop.member;
 import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Setting;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.util.SettingUtils;
@Controller("shopMemberPasswordController")
@RequestMapping("/member/password")
public class PasswordController extends BaseController{

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(){
    return "shop/member/password/edit";
}


@RequestMapping(value = "/edit2", method = RequestMethod.GET)
public String edit2(){
    return "shop/member/password/edit2";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String currentPassword,String password,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (StringUtils.isEmpty(password) || StringUtils.isEmpty(currentPassword)) {
        return ERROR_VIEW;
    }
    if (!isValid(Member.class, "password", password)) {
        return ERROR_VIEW;
    }
    Setting setting = SettingUtils.get();
    if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    if (!StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword())) {
        return ERROR_VIEW;
    }
    member.setPassword(DigestUtils.md5Hex(password));
    memberService.update(member);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:edit.jhtml";
}


@RequestMapping(value = "/check_current_password2", method = RequestMethod.GET)
@ResponseBody
public boolean checkCurrentPassword2(String currentPassword){
    if (StringUtils.isEmpty(currentPassword)) {
        return false;
    }
    Member member = memberService.getCurrent();
    if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword2())) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/check_current_password", method = RequestMethod.GET)
@ResponseBody
public boolean checkCurrentPassword(String currentPassword){
    if (StringUtils.isEmpty(currentPassword)) {
        return false;
    }
    Member member = memberService.getCurrent();
    if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword())) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/update2", method = RequestMethod.POST)
public String update2(String currentPassword,String password,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (StringUtils.isEmpty(password) || StringUtils.isEmpty(currentPassword)) {
        return ERROR_VIEW;
    }
    if (!isValid(Member.class, "password2", password)) {
        return ERROR_VIEW;
    }
    Setting setting = SettingUtils.get();
    if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    if (!StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword2())) {
        return ERROR_VIEW;
    }
    member.setPassword2(DigestUtils.md5Hex(password));
    memberService.update(member);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:edit2.jhtml";
}


}