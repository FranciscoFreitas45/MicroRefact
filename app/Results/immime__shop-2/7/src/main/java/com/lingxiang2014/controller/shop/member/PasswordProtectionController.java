package com.lingxiang2014.controller.shop.member;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.PasswordProtection;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.PasswordProtectionService;
import com.lingxiang2014.Interface.MemberService;
@Controller("shopMemberPasswordProtectionController")
@RequestMapping("/member/passwordProtection")
public class PasswordProtectionController extends BaseController{

@Resource(name = "passwordProtectionServiceImpl")
 private  PasswordProtectionService passwordProtectionService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    return "/shop/member/passwordProtection/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("passwordProtection", passwordProtectionService.find(id));
    return "/shop/member/passwordProtection/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(PasswordProtection passwordProtection,RedirectAttributes redirectAttributes){
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_VIEW;
    }
    passwordProtection.setMember(member);
    if (!isValid(passwordProtection)) {
        return ERROR_VIEW;
    }
    passwordProtectionService.save(passwordProtection);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(PasswordProtection passwordProtection,RedirectAttributes redirectAttributes){
    if (!isValid(passwordProtection)) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_VIEW;
    }
    passwordProtection.setMember(member);
    passwordProtectionService.update(passwordProtection);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    Member member = memberService.getCurrent();
    model.addAttribute("page", passwordProtectionService.findPage(pageable, member));
    return "/shop/member/passwordProtection/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    passwordProtectionService.delete(ids);
    return SUCCESS_MESSAGE;
}


}