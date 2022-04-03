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
import com.lingxiang2014.entity.Bank;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.AreaService;
import com.lingxiang2014.service.BankService;
import com.lingxiang2014.service.BankTypeService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.AreaService;
@Controller("shopMemberBankController")
@RequestMapping("/member/bank")
public class BankController extends BaseController{

@Resource(name = "bankServiceImpl")
 private  BankService bankService;

@Resource(name = "bankTypeServiceImpl")
 private  BankTypeService bankTypeService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "areaServiceImpl")
 private  AreaService areaService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("bankTypes", bankTypeService.findAll());
    return "/shop/member/bank/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("bank", bankService.find(id));
    model.addAttribute("bankTypes", bankTypeService.findAll());
    return "/shop/member/bank/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Bank bank,Long bankTypeId,Long areaId,RedirectAttributes redirectAttributes){
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_VIEW;
    }
    bank.setMember(member);
    bank.setBankType(bankTypeService.find(bankTypeId));
    bank.setArea(areaService.find(areaId));
    if (!isValid(bank)) {
        return ERROR_VIEW;
    }
    bankService.save(bank);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Bank bank,Long bankTypeId,Long areaId,RedirectAttributes redirectAttributes){
    bank.setBankType(bankTypeService.find(bankTypeId));
    if (!isValid(bank)) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_VIEW;
    }
    bank.setMember(member);
    bank.setArea(areaService.find(areaId));
    bankService.update(bank);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    Member member = memberService.getCurrent();
    model.addAttribute("page", bankService.findPage(pageable, member));
    return "/shop/member/bank/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    bankService.delete(ids);
    return SUCCESS_MESSAGE;
}


}