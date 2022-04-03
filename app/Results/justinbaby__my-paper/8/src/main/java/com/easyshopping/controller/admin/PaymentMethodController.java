package com.easyshopping.controller.admin;
 import java.util.HashSet;
import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.PaymentMethod;
import com.easyshopping.entity.PaymentMethod.Method;
import com.easyshopping.entity.ShippingMethod;
import com.easyshopping.service.PaymentMethodService;
import com.easyshopping.service.ShippingMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.Interface.PaymentMethodService;
@Controller("adminPaymentMethodController")
@RequestMapping("/admin/payment_method")
public class PaymentMethodController extends BaseController{

@Resource(name = "paymentMethodServiceImpl")
 private  PaymentMethodService paymentMethodService;

@Resource(name = "shippingMethodServiceImpl")
 private  ShippingMethodService shippingMethodService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    model.addAttribute("methods", Method.values());
    model.addAttribute("shippingMethods", shippingMethodService.findAll());
    return "/admin/payment_method/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("methods", Method.values());
    model.addAttribute("shippingMethods", shippingMethodService.findAll());
    model.addAttribute("paymentMethod", paymentMethodService.find(id));
    return "/admin/payment_method/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(PaymentMethod paymentMethod,Long[] shippingMethodIds,RedirectAttributes redirectAttributes){
    paymentMethod.setShippingMethods(new HashSet<ShippingMethod>(shippingMethodService.findList(shippingMethodIds)));
    if (!isValid(paymentMethod)) {
        return ERROR_VIEW;
    }
    paymentMethod.setOrders(null);
    paymentMethodService.save(paymentMethod);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(PaymentMethod paymentMethod,Long[] shippingMethodIds,RedirectAttributes redirectAttributes){
    paymentMethod.setShippingMethods(new HashSet<ShippingMethod>(shippingMethodService.findList(shippingMethodIds)));
    if (!isValid(paymentMethod)) {
        return ERROR_VIEW;
    }
    paymentMethodService.update(paymentMethod, "orders");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", paymentMethodService.findPage(pageable));
    return "/admin/payment_method/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    if (ids.length >= paymentMethodService.count()) {
        return Message.error("admin.common.deleteAllNotAllowed");
    }
    paymentMethodService.delete(ids);
    return SUCCESS_MESSAGE;
}


}