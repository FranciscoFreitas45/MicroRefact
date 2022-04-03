package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.DeliveryTemplate;
import com.easyshopping.service.DeliveryTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminDeliveryTemplateController")
@RequestMapping("/admin/delivery_template")
public class DeliveryTemplateController extends BaseController{

@Resource(name = "deliveryTemplateServiceImpl")
 private  DeliveryTemplateService deliveryTemplateService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(Pageable pageable){
    return "/admin/delivery_template/add";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(DeliveryTemplate deliveryTemplate,RedirectAttributes redirectAttributes){
    if (!isValid(deliveryTemplate)) {
        return ERROR_VIEW;
    }
    deliveryTemplateService.save(deliveryTemplate);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String eidt(Long id,Model model){
    model.addAttribute("deliveryTemplate", deliveryTemplateService.find(id));
    return "/admin/delivery_template/edit";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,Model model){
    model.addAttribute("page", deliveryTemplateService.findPage(pageable));
    return "/admin/delivery_template/list";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String udpate(DeliveryTemplate deliveryTemplate,RedirectAttributes redirectAttributes){
    if (!isValid(deliveryTemplate)) {
        return ERROR_VIEW;
    }
    deliveryTemplateService.update(deliveryTemplate);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    deliveryTemplateService.delete(ids);
    return SUCCESS_MESSAGE;
}


}