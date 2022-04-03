package com.easyshopping.plugin.alipayBank;
 import java.math.BigDecimal;
import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.controller.admin.BaseController;
import com.easyshopping.entity.PluginConfig;
import com.easyshopping.plugin.PaymentPlugin;
import com.easyshopping.plugin.PaymentPlugin.FeeType;
import com.easyshopping.service.PluginConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller("adminAlipayBankController")
@RequestMapping("/admin/payment_plugin/alipay_bank")
public class AlipayBankController extends BaseController{

@Resource(name = "alipayBankPlugin")
 private  AlipayBankPlugin alipayBankPlugin;

@Resource(name = "pluginConfigServiceImpl")
 private  PluginConfigService pluginConfigService;


@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
@ResponseBody
public Message uninstall(){
    if (alipayBankPlugin.getIsInstalled()) {
        PluginConfig pluginConfig = alipayBankPlugin.getPluginConfig();
        pluginConfigService.delete(pluginConfig);
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/install", method = RequestMethod.POST)
@ResponseBody
public Message install(){
    if (!alipayBankPlugin.getIsInstalled()) {
        PluginConfig pluginConfig = new PluginConfig();
        pluginConfig.setPluginId(alipayBankPlugin.getId());
        pluginConfig.setIsEnabled(false);
        pluginConfigService.save(pluginConfig);
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String paymentName,String partner,String key,FeeType feeType,BigDecimal fee,String logo,String description,Boolean isEnabled,Integer order,RedirectAttributes redirectAttributes){
    PluginConfig pluginConfig = alipayBankPlugin.getPluginConfig();
    pluginConfig.setAttribute(PaymentPlugin.PAYMENT_NAME_ATTRIBUTE_NAME, paymentName);
    pluginConfig.setAttribute("partner", partner);
    pluginConfig.setAttribute("key", key);
    pluginConfig.setAttribute(PaymentPlugin.FEE_TYPE_ATTRIBUTE_NAME, feeType.toString());
    pluginConfig.setAttribute(PaymentPlugin.FEE_ATTRIBUTE_NAME, fee.toString());
    pluginConfig.setAttribute(PaymentPlugin.LOGO_ATTRIBUTE_NAME, logo);
    pluginConfig.setAttribute(PaymentPlugin.DESCRIPTION_ATTRIBUTE_NAME, description);
    pluginConfig.setIsEnabled(isEnabled);
    pluginConfig.setOrder(order);
    pluginConfigService.update(pluginConfig);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:/admin/payment_plugin/list.jhtml";
}


@RequestMapping(value = "/setting", method = RequestMethod.GET)
public String setting(ModelMap model){
    PluginConfig pluginConfig = alipayBankPlugin.getPluginConfig();
    model.addAttribute("feeTypes", FeeType.values());
    model.addAttribute("pluginConfig", pluginConfig);
    return "/com/easyshopping/plugin/alipayBank/setting";
}


}