package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.service.PluginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller("adminPaymentPluginController")
@RequestMapping("/admin/payment_plugin")
public class PaymentPluginController extends BaseController{

@Resource(name = "pluginServiceImpl")
 private  PluginService pluginService;


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(ModelMap model){
    model.addAttribute("paymentPlugins", pluginService.getPaymentPlugins());
    return "/admin/payment_plugin/list";
}


}