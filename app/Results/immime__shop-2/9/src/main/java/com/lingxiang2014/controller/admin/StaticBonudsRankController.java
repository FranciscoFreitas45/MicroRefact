package com.lingxiang2014.controller.admin;
 import java.math.BigDecimal;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.StaticBonudsRank;
import com.lingxiang2014.service.StaticBonudsRankService;
import com.lingxiang2014.DTO.Message;
@Controller("adminStaticBonudsRankRankController")
@RequestMapping("/admin/staticBonudsRank")
public class StaticBonudsRankController extends BaseController{

@Resource(name = "staticBonudsRankServiceImpl")
 private  StaticBonudsRankService staticBonudsRankService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    return "/admin/staticBonudsRank/add";
}


@RequestMapping(value = "/check_amount", method = RequestMethod.GET)
@ResponseBody
public boolean checkAmount(BigDecimal previousAmount,BigDecimal amount){
    if (amount == null) {
        return false;
    }
    if (staticBonudsRankService.amountUnique(previousAmount, amount)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("staticBonudsRank", staticBonudsRankService.find(id));
    return "/admin/staticBonudsRank/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(StaticBonudsRank staticBonudsRank,RedirectAttributes redirectAttributes){
    if (!isValid(staticBonudsRank)) {
        return ERROR_VIEW;
    }
    if (staticBonudsRankService.nameExists(staticBonudsRank.getName())) {
        return ERROR_VIEW;
    }
    staticBonudsRankService.save(staticBonudsRank);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(StaticBonudsRank staticBonudsRank,RedirectAttributes redirectAttributes){
    if (!isValid(staticBonudsRank)) {
        return ERROR_VIEW;
    }
    StaticBonudsRank pStaticBonudsRank = staticBonudsRankService.find(staticBonudsRank.getId());
    if (pStaticBonudsRank == null) {
        return ERROR_VIEW;
    }
    if (!staticBonudsRankService.nameUnique(pStaticBonudsRank.getName(), staticBonudsRank.getName())) {
        return ERROR_VIEW;
    }
    if (pStaticBonudsRank.getIsDefault()) {
        staticBonudsRank.setIsDefault(true);
    }
    staticBonudsRankService.update(staticBonudsRank);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/check_name", method = RequestMethod.GET)
@ResponseBody
public boolean checkName(String previousName,String name){
    if (StringUtils.isEmpty(name)) {
        return false;
    }
    if (staticBonudsRankService.nameUnique(previousName, name)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", staticBonudsRankService.findPage(pageable));
    return "/admin/staticBonudsRank/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids != null) {
        long totalCount = staticBonudsRankService.count();
        if (ids.length >= totalCount) {
            return Message.error("admin.common.deleteAllNotAllowed");
        }
        staticBonudsRankService.delete(ids);
    }
    return SUCCESS_MESSAGE;
}


}