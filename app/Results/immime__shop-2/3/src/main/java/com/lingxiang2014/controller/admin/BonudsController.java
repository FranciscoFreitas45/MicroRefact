package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.Interface.MemberService;
@Controller("adminBonudsController")
@RequestMapping("/admin/bonuds")
public class BonudsController extends BaseController{

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("types", Type.values());
    return "/admin/bonuds/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("types", Type.values());
    model.addAttribute("bonuds", bonudsService.find(id));
    return "/admin/bonuds/edit";
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
public String list(Pageable pageable,ModelMap model,Long memberId){
    model.addAttribute("memberId", memberId);
    Member member = memberService.find(memberId);
    if (member == null) {
        model.addAttribute("page", bonudsService.findPage(pageable));
    } else {
        model.addAttribute("memberId", memberId);
        model.addAttribute("page", bonudsService.findPage(member, pageable));
    }
    return "/admin/bonuds/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    bonudsService.delete(ids);
    return SUCCESS_MESSAGE;
}


}