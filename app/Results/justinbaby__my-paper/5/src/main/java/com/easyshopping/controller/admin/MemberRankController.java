package com.easyshopping.controller.admin;
 import java.math.BigDecimal;
import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.MemberRank;
import com.easyshopping.service.MemberRankService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.DTO.Message;
@Controller("adminMemberRankController")
@RequestMapping("/admin/member_rank")
public class MemberRankController extends BaseController{

@Resource(name = "memberRankServiceImpl")
 private  MemberRankService memberRankService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    return "/admin/member_rank/add";
}


@RequestMapping(value = "/check_amount", method = RequestMethod.GET)
@ResponseBody
public boolean checkAmount(BigDecimal previousAmount,BigDecimal amount){
    if (amount == null) {
        return false;
    }
    if (memberRankService.amountUnique(previousAmount, amount)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("memberRank", memberRankService.find(id));
    return "/admin/member_rank/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(MemberRank memberRank,RedirectAttributes redirectAttributes){
    if (!isValid(memberRank)) {
        return ERROR_VIEW;
    }
    if (memberRankService.nameExists(memberRank.getName())) {
        return ERROR_VIEW;
    }
    if (memberRank.getIsSpecial()) {
        memberRank.setAmount(null);
    } else if (memberRank.getAmount() == null || memberRankService.amountExists(memberRank.getAmount())) {
        return ERROR_VIEW;
    }
    memberRank.setMembers(null);
    memberRank.setPromotions(null);
    memberRankService.save(memberRank);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(MemberRank memberRank,RedirectAttributes redirectAttributes){
    if (!isValid(memberRank)) {
        return ERROR_VIEW;
    }
    MemberRank pMemberRank = memberRankService.find(memberRank.getId());
    if (pMemberRank == null) {
        return ERROR_VIEW;
    }
    if (!memberRankService.nameUnique(pMemberRank.getName(), memberRank.getName())) {
        return ERROR_VIEW;
    }
    if (pMemberRank.getIsDefault()) {
        memberRank.setIsDefault(true);
    }
    if (memberRank.getIsSpecial()) {
        memberRank.setAmount(null);
    } else if (memberRank.getAmount() == null || !memberRankService.amountUnique(pMemberRank.getAmount(), memberRank.getAmount())) {
        return ERROR_VIEW;
    }
    memberRankService.update(memberRank, "members", "promotions");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/check_name", method = RequestMethod.GET)
@ResponseBody
public boolean checkName(String previousName,String name){
    if (StringUtils.isEmpty(name)) {
        return false;
    }
    if (memberRankService.nameUnique(previousName, name)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", memberRankService.findPage(pageable));
    return "/admin/member_rank/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids != null) {
        for (Long id : ids) {
            MemberRank memberRank = memberRankService.find(id);
            if (memberRank != null && memberRank.getMembers() != null && !memberRank.getMembers().isEmpty()) {
                return Message.error("admin.memberRank.deleteExistNotAllowed", memberRank.getName());
            }
        }
        long totalCount = memberRankService.count();
        if (ids.length >= totalCount) {
            return Message.error("admin.common.deleteAllNotAllowed");
        }
        memberRankService.delete(ids);
    }
    return SUCCESS_MESSAGE;
}


}