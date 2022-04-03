package com.lingxiang2014.controller.shop;
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
import com.lingxiang2014.service.BonudsService;
@Controller("shopBonudsController")
@RequestMapping("/shop/bonuds")
public class BonudsController extends BaseController{

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("types", Type.values());
    return "/shop/bonuds/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("types", Type.values());
    model.addAttribute("bonuds", bonudsService.find(id));
    return "/shop/bonuds/edit";
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
    model.addAttribute("page", bonudsService.findPage(pageable));
    return "/shop/bonuds/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    bonudsService.delete(ids);
    return SUCCESS_MESSAGE;
}


}