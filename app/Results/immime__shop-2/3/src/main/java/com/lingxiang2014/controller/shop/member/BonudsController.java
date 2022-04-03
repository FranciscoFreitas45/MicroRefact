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
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.Interface.MemberService;
@Controller("shoMemberBonudsController")
@RequestMapping("/member/bonuds")
public class BonudsController extends BaseController{

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("types", Type.values());
    return "/shop/member/bonuds/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("types", Type.values());
    model.addAttribute("bonuds", bonudsService.find(id));
    return "/shop/member/bonuds/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Bonuds bonuds,RedirectAttributes redirectAttributes){
    if (!isValid(bonuds)) {
        return ERROR_VIEW;
    }
    bonudsService.save(bonuds);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Bonuds bonuds,RedirectAttributes redirectAttributes){
    if (!isValid(bonuds)) {
        return ERROR_VIEW;
    }
    bonudsService.update(bonuds);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_VIEW;
    }
    model.addAttribute("page", bonudsService.findPage(member, pageable));
    return "/shop/member/bonuds/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    bonudsService.delete(ids);
    return SUCCESS_MESSAGE;
}


}