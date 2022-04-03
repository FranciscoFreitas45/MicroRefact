package com.lingxiang2014.controller.shop.member;
 import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.Setting;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Bank;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Sn;
import com.lingxiang2014.entity.Withdraw;
import com.lingxiang2014.service.BankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.SnService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.BankService;
import com.lingxiang2014.DTO.Setting;
import com.lingxiang2014.DTO.Member;
@Controller("shopMemberWithdrawController")
@RequestMapping("/member/withdraw")
public class WithdrawController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;

@Resource(name = "bankServiceImpl")
 private  BankService bankService;

@Resource(name = "snServiceImpl")
 private  SnService snService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model,Integer flag){
    if (flag == null || flag == 0) {
        return "redirect:check.jhtml?flag=0";
    } else if (flag == 1) {
        Member membe = memberService.getCurrent();
        model.addAttribute("banks", bankService.findListByMember(membe));
        return "shop/member/withdraw/add";
    } else {
        return "redirect:check.jhtml?flag=0";
    }
}


@RequestMapping(value = "/check_money", method = RequestMethod.GET)
@ResponseBody
public boolean checkMoney(BigDecimal balance){
    if (balance == null) {
        return false;
    }
    Setting setting = SettingUtils.get();
    BigDecimal minMoney = setting.getWithrawMinMoney();
    if (minMoney.compareTo(balance) > 0) {
        return false;
    } else {
        return true;
    }
}


@RequestMapping(value = "/checkPassword2", method = RequestMethod.POST)
public String checkPassword2(String currentPassword,Integer flag){
    Member member = memberService.getCurrent();
    if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword2())) {
        flag = 1;
    } else {
        flag = 0;
    }
    return "redirect:add.jhtml?flag=" + flag;
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Withdraw withdraw,Long bankId,BigDecimal balance,HttpServletRequest request,HttpServletResponse response,ModelMap model){
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_VIEW;
    }
    Setting setting = SettingUtils.get();
    if (balance == null || balance.compareTo(new BigDecimal(0)) <= 0 || balance.precision() > 15 || balance.scale() > setting.getPriceScale()) {
        return ERROR_VIEW;
    }
    withdraw.setRealBalance(withdraw.getBalance().subtract(withdraw.getFee()));
    withdraw.setBank(bankService.find(bankId));
    withdraw.setNumber(snService.generate(Sn.Type.payment));
    withdraw.setMember(member);
    withdrawService.save(withdraw);
    member.setBalance(member.getBalance().subtract(withdraw.getBalance()));
    memberService.update(member);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/check", method = RequestMethod.GET)
public String check(ModelMap model,Integer flag){
    model.addAttribute("flag", flag);
    return "shop/member/withdraw/check";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", withdrawService.findPage(member, null, pageable));
    return "shop/member/withdraw/list";
}


@RequestMapping(value = "/check_balance", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> checkBalance(){
    Setting setting = SettingUtils.get();
    Map<String, Object> data = new HashMap<String, Object>();
    Member member = memberService.getCurrent();
    data.put("balance", member.getBalance());
    return data;
}


@RequestMapping(value = "/calculate_fee", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> calculateFee(Long bankId,BigDecimal balance){
    Map<String, Object> data = new HashMap<String, Object>();
    Bank bank = bankService.find(bankId);
    if (bank == null) {
        data.put("message", ERROR_MESSAGE);
    } else {
        data.put("message", SUCCESS_MESSAGE);
        data.put("fee", bank.getBankType().getFeeRate().multiply(balance));
        data.put("bankTrueName", bank.getBankTrueName());
        data.put("bankType", bank.getBankType());
        data.put("bankAccount", bank.getBankAccount());
    }
    return data;
}


}