package com.easyshopping.controller.shop.member;
 import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.plugin.PaymentPlugin;
import com.easyshopping.service.DepositService;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.PluginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easyshopping.Interface.DepositService;
import com.easyshopping.Interface.PluginService;
@Controller("shopMemberDepositController")
@RequestMapping("/member/deposit")
public class DepositController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;

@Resource(name = "pluginServiceImpl")
 private  PluginService pluginService;


@RequestMapping(value = "/recharge", method = RequestMethod.GET)
public String recharge(ModelMap model){
    List<PaymentPlugin> paymentPlugins = pluginService.getPaymentPlugins(true);
    if (!paymentPlugins.isEmpty()) {
        model.addAttribute("defaultPaymentPlugin", paymentPlugins.get(0));
        model.addAttribute("paymentPlugins", paymentPlugins);
    }
    return "shop/member/deposit/recharge";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", depositService.findPage(member, pageable));
    return "shop/member/deposit/list";
}


@RequestMapping(value = "/check_balance", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> checkBalance(){
    Map<String, Object> data = new HashMap<String, Object>();
    Member member = memberService.getCurrent();
    data.put("balance", member.getBalance());
    return data;
}


@RequestMapping(value = "/calculate_fee", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> calculateFee(String paymentPluginId,BigDecimal amount){
    Map<String, Object> data = new HashMap<String, Object>();
    PaymentPlugin paymentPlugin = pluginService.getPaymentPlugin(paymentPluginId);
    if (paymentPlugin == null || !paymentPlugin.getIsEnabled() || amount == null || amount.compareTo(new BigDecimal(0)) < 0) {
        data.put("message", ERROR_MESSAGE);
        return data;
    }
    data.put("message", SUCCESS_MESSAGE);
    data.put("fee", paymentPlugin.calculateFee(amount));
    return data;
}


}