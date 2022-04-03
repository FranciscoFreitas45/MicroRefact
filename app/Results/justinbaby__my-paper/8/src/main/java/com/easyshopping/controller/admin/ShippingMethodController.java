package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.ShippingMethod;
import com.easyshopping.service.DeliveryCorpService;
import com.easyshopping.service.ShippingMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.DTO.Message;
@Controller("adminShippingMethodController")
@RequestMapping("/admin/shipping_method")
public class ShippingMethodController extends BaseController{

@Resource(name = "shippingMethodServiceImpl")
 private  ShippingMethodService shippingMethodService;

@Resource(name = "deliveryCorpServiceImpl")
 private  DeliveryCorpService deliveryCorpService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("deliveryCorps", deliveryCorpService.findAll());
    return "/admin/shipping_method/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("deliveryCorps", deliveryCorpService.findAll());
    model.addAttribute("shippingMethod", shippingMethodService.find(id));
    return "/admin/shipping_method/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(ShippingMethod shippingMethod,Long defaultDeliveryCorpId,RedirectAttributes redirectAttributes){
    shippingMethod.setDefaultDeliveryCorp(deliveryCorpService.find(defaultDeliveryCorpId));
    if (!isValid(shippingMethod)) {
        return ERROR_VIEW;
    }
    shippingMethod.setPaymentMethods(null);
    shippingMethod.setOrders(null);
    shippingMethodService.save(shippingMethod);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(ShippingMethod shippingMethod,Long defaultDeliveryCorpId,RedirectAttributes redirectAttributes){
    shippingMethod.setDefaultDeliveryCorp(deliveryCorpService.find(defaultDeliveryCorpId));
    if (!isValid(shippingMethod)) {
        return ERROR_VIEW;
    }
    shippingMethodService.update(shippingMethod, "paymentMethods", "orders");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", shippingMethodService.findPage(pageable));
    return "/admin/shipping_method/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids.length >= shippingMethodService.count()) {
        return Message.error("admin.common.deleteAllNotAllowed");
    }
    shippingMethodService.delete(ids);
    return SUCCESS_MESSAGE;
}


}