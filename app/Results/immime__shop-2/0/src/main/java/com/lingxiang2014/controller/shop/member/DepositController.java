package com.lingxiang2014.controller.shop.member;
 import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.PluginService;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.PluginService;
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
    return "shop/member/deposit/recharge";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    try {
        Member member = memberService.getCurrent();
        Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
        model.addAttribute("page", depositService.findPage(member, pageable));
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
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
    data.put("message", SUCCESS_MESSAGE);
    return data;
}


}