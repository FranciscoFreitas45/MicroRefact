package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.BankType;
import com.lingxiang2014.entity.BaseEntity.Save;
import com.lingxiang2014.service.BankTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminBankTypeController")
@RequestMapping("/admin/bankType")
public class BankTypeController extends BaseController{

@Resource(name = "bankTypeServiceImpl")
 private  BankTypeService bankTypeService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    return "/admin/bankType/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("bankType", bankTypeService.find(id));
    return "/admin/bankType/edit";
}


@RequestMapping(value = "/check_fullName", method = RequestMethod.GET)
@ResponseBody
public boolean checkUsername(String fullName){
    if (StringUtils.isEmpty(fullName)) {
        return false;
    }
    if (bankTypeService.usernameExists(fullName)) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(BankType bankType,RedirectAttributes redirectAttributes){
    if (!isValid(bankType, Save.class)) {
        return ERROR_VIEW;
    }
    if (bankTypeService.usernameExists(bankType.getFullName())) {
        return ERROR_VIEW;
    }
    bankTypeService.save(bankType);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(BankType bankType,Long[] roleIds,RedirectAttributes redirectAttributes){
    if (!isValid(bankType)) {
        return ERROR_VIEW;
    }
    bankTypeService.update(bankType);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", bankTypeService.findPage(pageable));
    return "/admin/bankType/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids.length >= bankTypeService.count()) {
        return Message.error("bankType.common.deleteAllNotAllowed");
    }
    bankTypeService.delete(ids);
    return SUCCESS_MESSAGE;
}


}