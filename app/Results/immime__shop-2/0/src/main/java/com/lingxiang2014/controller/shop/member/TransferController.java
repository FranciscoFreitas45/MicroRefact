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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.Setting;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Transfer;
import com.lingxiang2014.entity.Transfer.Method;
import com.lingxiang2014.entity.Transfer.Type;
import com.lingxiang2014.service.BankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.SnService;
import com.lingxiang2014.service.TransferService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.BankService;
import com.lingxiang2014.Interface.SnService;
import com.lingxiang2014.DTO.Member;
@Controller("shopMemberTransferController")
@RequestMapping("/member/transfer")
public class TransferController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "transferServiceImpl")
 private  TransferService transferService;

@Resource(name = "bankServiceImpl")
 private  BankService bankService;

@Resource(name = "snServiceImpl")
 private  SnService snService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model,Integer flag){
    if (flag == null) {
        return "redirect:check.jhtml";
    } else {
        model.addAttribute("setting", SettingUtils.get());
        return "shop/member/transfer/add";
    }
}


@RequestMapping(value = "/check_username", method = RequestMethod.GET)
@ResponseBody
public boolean checkUsername(String toMemberName){
    if (StringUtils.isEmpty(toMemberName)) {
        return false;
    }
    if (memberService.numberExists(toMemberName) || memberService.usernameExists(toMemberName)) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Transfer transfer,String toMemberName,BigDecimal balance,HttpServletRequest request,HttpServletResponse response,ModelMap model,RedirectAttributes redirectAttributes){
    Setting setting = SettingUtils.get();
    Member member = memberService.getCurrent();
    Member toMember = memberService.findByNumber(toMemberName);
    if (toMember == null) {
        toMember = memberService.findByUsername(toMemberName);
    }
    if (member == null || toMember == null) {
        addFlashMessage(redirectAttributes, ERROR_MESSAGE);
        return ERROR_VIEW;
    }
    if (toMember == member) {
        addFlashMessage(redirectAttributes, Message.error("??????????????????????????????????????????"));
        return "redirect:list.jhtml";
    }
    String memo = "?????????" + member.getNumber() + "???????????????" + toMember.getNumber();
    if (Type.xianjin.equals(transfer.getType())) {
        // ???????????????????????????
        transfer.setOldFromBalance(member.getBalance());
        transfer.setOldToBalance(toMember.getBalance());
        transfer.setBalance(balance);
        transfer.setFee(balance.multiply(setting.getXianjinRate()));
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        memo = memo + "?????????" + setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + "?????????" + member.getNumber() + "??????????????????????????????";
        memo = memo + setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + ",??????????????????????????????";
        member.setBalance(member.getBalance().subtract(transfer.getBalance()));
        memo = memo + setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + toMember.getNumber() + "??????????????????????????????" + setting.getCurrencySign() + toMember.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + ",??????????????????????????????";
        toMember.setBalance(toMember.getBalance().add(transfer.getBalance1()));
        memo = memo + setting.getCurrencySign() + toMember.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "???";
        transfer.setNewFromBalance(member.getBalance());
        transfer.setNewToBalance(toMember.getBalance());
    } else if (Type.baodan.equals(transfer.getType())) {
        // ???????????????????????????
        transfer.setOldFromBalance(member.getBalance1());
        transfer.setOldToBalance(toMember.getBalance1());
        transfer.setBalance(balance);
        transfer.setFee(balance.multiply(setting.getBaodanRate()));
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        memo = memo + "?????????" + setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + "?????????" + member.getNumber() + "??????????????????????????????";
        memo = memo + setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP) + ",??????????????????????????????";
        member.setBalance1(member.getBalance1().subtract(transfer.getBalance()));
        memo = memo + setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + toMember.getNumber() + "??????????????????????????????" + setting.getCurrencySign() + toMember.getBalance1() + ",??????????????????????????????";
        toMember.setBalance1(toMember.getBalance1().add(transfer.getBalance1()));
        memo = memo + setting.getCurrencySign() + toMember.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP) + "???";
        transfer.setNewFromBalance(member.getBalance1());
        transfer.setNewToBalance(toMember.getBalance1());
    } else if (Type.futou.equals(transfer.getType())) {
        // ???????????????????????????
        transfer.setOldFromBalance(member.getBalance2());
        transfer.setOldToBalance(toMember.getBalance2());
        transfer.setBalance(balance);
        transfer.setFee(balance.multiply(setting.getFutouRate()));
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        memo = memo + "???????????????" + setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + "?????????" + member.getNumber() + "??????????????????????????????";
        memo = memo + setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP) + ",??????????????????????????????";
        member.setBalance2(member.getBalance2().subtract(transfer.getBalance()));
        memo = memo + setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + toMember.getNumber() + "??????????????????????????????" + setting.getCurrencySign() + toMember.getBalance2() + ",??????????????????????????????";
        toMember.setBalance2(toMember.getBalance2().add(transfer.getBalance1()));
        memo = memo + setting.getCurrencySign() + toMember.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP) + "???";
        transfer.setNewFromBalance(member.getBalance2());
        transfer.setNewToBalance(toMember.getBalance2());
    } else if (Type.licai.equals(transfer.getType())) {
        // ???????????????????????????
        transfer.setOldFromBalance(member.getBalance3());
        transfer.setOldToBalance(toMember.getBalance3());
        transfer.setBalance(balance);
        transfer.setFee(balance.multiply(setting.getLicaiRate()));
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        memo = memo + "???????????????" + setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + "?????????" + member.getNumber() + "??????????????????????????????";
        memo = memo + member.getBalance2() + ",??????????????????????????????";
        member.setBalance2(member.getBalance2().subtract(transfer.getBalance()));
        memo = memo + setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP) + "????????????" + toMember.getNumber() + "??????????????????????????????" + setting.getCurrencySign() + toMember.getBalance2() + ",??????????????????????????????";
        toMember.setBalance2(toMember.getBalance2().add(transfer.getBalance1()));
        memo = memo + setting.getCurrencySign() + toMember.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP) + "???";
        transfer.setNewFromBalance(member.getBalance3());
        transfer.setNewToBalance(toMember.getBalance3());
    }
    transfer.setFromMember(member);
    transfer.setToMember(toMember);
    transfer.setMemo(memo);
    transfer.setMethod(Method.transform);
    transferService.save(transfer);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/check", method = RequestMethod.GET)
public String check(ModelMap model){
    return "shop/member/transfer/check";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", transferService.findPage(member, member, Method.transform, pageable));
    return "shop/member/transfer/list";
}


@RequestMapping(value = "/check_toMember", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> checkToMember(String toMemberUsername){
    Map<String, Object> data = new HashMap<String, Object>();
    Member member = memberService.findByNumber(toMemberUsername);
    if (member == null) {
        member = memberService.findByUsername(toMemberUsername);
    }
    data.put("member", member);
    return data;
}


@RequestMapping(value = "/list1", method = RequestMethod.GET)
public String list1(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", transferService.findPage(member, member, Method.exchange, pageable));
    return "shop/member/transfer/list1";
}


@RequestMapping(value = "/check1", method = RequestMethod.GET)
public String check1(ModelMap model){
    return "shop/member/transfer/check1";
}


@RequestMapping(value = "/checkPassword1", method = RequestMethod.POST)
public String checkPassword1(String currentPassword){
    Member member = memberService.getCurrent();
    Integer flag = null;
    System.out.println(DigestUtils.md5Hex(currentPassword) + "---" + member.getPassword2());
    if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword2())) {
        flag = 1;
    } else {
        flag = null;
    }
    if (flag != null) {
        return "redirect:add1.jhtml?flag=" + flag;
    } else {
        return "redirect:add1.jhtml";
    }
}


@RequestMapping(value = "/save1", method = RequestMethod.POST)
public String save1(Transfer transfer,BigDecimal balance,HttpServletRequest request,HttpServletResponse response,ModelMap model,RedirectAttributes redirectAttributes){
    Setting setting = SettingUtils.get();
    String argument_A = "";
    String argument_B = "";
    String argument_C = "";
    String balance_A = "";
    String balance_B = "";
    String balance_C = "";
    String balance_D = "";
    String balance_E = "";
    String balance_F = "";
    String fee = "";
    Member member = memberService.getCurrent();
    if (member == null) {
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return ERROR_VIEW;
    }
    String memo = "?????????" + member.getNumber() + "??????argument_A?????????????????????????????????argument_B?????????balance_A,???????????????argument_B???balance_B???????????????argument_C?????????balance_C,????????????argument_C?????????balance_D??????????????????balance_E???????????????fee??????????????????balance_F???";
    // ?????????????????????
    transfer.setBalance(balance);
    if (Type.xjTbd.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance());
        transfer.setOldToBalance(member.getBalance1());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance(member.getBalance().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance1().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance());
        transfer.setNewToBalance(member.getBalance1());
    } else if (Type.xjTft.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance());
        transfer.setOldToBalance(member.getBalance2());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance(member.getBalance().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance2(member.getBalance2().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance());
        transfer.setNewToBalance(member.getBalance2());
    } else if (Type.xjTlc.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance());
        transfer.setOldToBalance(member.getBalance3());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance(member.getBalance().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance3().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance());
        transfer.setNewToBalance(member.getBalance3());
    } else if (Type.bdTft.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance1());
        transfer.setOldToBalance(member.getBalance2());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance1().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance2(member.getBalance2().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance1());
        transfer.setNewToBalance(member.getBalance2());
    } else if (Type.bdTlc.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance1());
        transfer.setOldToBalance(member.getBalance3());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance1().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance3(member.getBalance3().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance1());
        transfer.setNewToBalance(member.getBalance3());
    } else if (Type.bfTli.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance2());
        transfer.setOldToBalance(member.getBalance3());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance2(member.getBalance2().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance3(member.getBalance3().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance2());
        transfer.setNewToBalance(member.getBalance3());
    } else if (Type.bdTxj.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance1());
        transfer.setOldToBalance(member.getBalance());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance1().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance(member.getBalance().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance1());
        transfer.setNewToBalance(member.getBalance());
    } else if (Type.ftTxj.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance2());
        transfer.setOldToBalance(member.getBalance());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance2(member.getBalance2().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance(member.getBalance().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance2());
        transfer.setNewToBalance(member.getBalance());
    } else if (Type.lcTxj.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance3());
        transfer.setOldToBalance(member.getBalance1());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance3(member.getBalance3().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance(member.getBalance().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance3());
        transfer.setNewToBalance(member.getBalance());
    } else if (Type.ftTbd.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance2());
        transfer.setOldToBalance(member.getBalance1());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance2(member.getBalance2().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance1().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance2());
        transfer.setNewToBalance(member.getBalance1());
    } else if (Type.lcTbd.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance3());
        transfer.setOldToBalance(member.getBalance1());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance3(member.getBalance3().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance1(member.getBalance1().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance3());
        transfer.setNewToBalance(member.getBalance1());
    } else if (Type.lcTft.equals(transfer.getType())) {
        // ?????????->?????????
        transfer.setOldFromBalance(member.getBalance3());
        transfer.setOldToBalance(member.getBalance2());
        // ?????????
        transfer.setFee(transfer.getBalance().multiply(setting.getXjTbdRate()));
        // ????????????
        transfer.setBalance1(transfer.getBalance().subtract(transfer.getFee()));
        argument_A = "?????????->?????????";
        argument_B = "?????????";
        argument_C = "?????????";
        balance_A = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance3(member.getBalance3().subtract(transfer.getBalance()));
        balance_B = setting.getCurrencySign() + member.getBalance3().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_C = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        member.setBalance2(member.getBalance2().add(transfer.getBalance1()));
        balance_D = setting.getCurrencySign() + member.getBalance2().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_E = setting.getCurrencySign() + transfer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
        fee = setting.getCurrencySign() + transfer.getFee().setScale(2, BigDecimal.ROUND_HALF_UP);
        balance_F = setting.getCurrencySign() + transfer.getBalance1().setScale(2, BigDecimal.ROUND_HALF_UP);
        transfer.setNewFromBalance(member.getBalance3());
        transfer.setNewToBalance(member.getBalance2());
    }
    memo = memo.replaceAll("argument_A", argument_A).replaceAll("argument_B", argument_B).replaceAll("argument_C", argument_C).replaceAll("balance_A", balance_A).replaceAll("balance_B", balance_B).replaceAll("balance_C", balance_C).replaceAll("balance_D", balance_D).replaceAll("balance_E", balance_E).replaceAll("balance_F", balance_F).replaceAll("fee", fee);
    transfer.setFromMember(member);
    transfer.setBalance(balance);
    transfer.setToMember(member);
    transfer.setMemo(memo);
    transfer.setMethod(Method.exchange);
    transferService.save(transfer);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list1.jhtml";
}


@RequestMapping(value = "/checkPassword2", method = RequestMethod.POST)
public String checkPassword2(String currentPassword){
    Member member = memberService.getCurrent();
    Integer flag = null;
    if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword2())) {
        flag = 1;
    } else {
        flag = null;
    }
    if (flag != null) {
        return "redirect:add.jhtml?flag=" + flag;
    } else {
        return "redirect:add.jhtml";
    }
}


@RequestMapping(value = "/add1", method = RequestMethod.GET)
public String add1(ModelMap model,Integer flag){
    if (flag == null) {
        return "redirect:check1.jhtml";
    } else {
        model.addAttribute("setting", SettingUtils.get());
        return "shop/member/transfer/add1";
    }
}


@RequestMapping(value = "/check_balance", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> checkBalance(String type){
    Map<String, Object> data = new HashMap<String, Object>();
    Member member = memberService.getCurrent();
    if ((Type.xianjin.name()).equals(type)) {
        // ???????????????????????????
        data.put("balance", member.getBalance());
    } else if ((Type.baodan.name()).equals(type)) {
        // ???????????????????????????
        data.put("balance", member.getBalance1());
    } else if ((Type.futou.name()).equals(type)) {
        // ???????????????????????????
        data.put("balance", member.getBalance2());
    } else if ((Type.licai.name()).equals(type)) {
        // ???????????????????????????
        data.put("balance", member.getBalance3());
    } else if ((Type.xjTbd.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance());
    } else if ((Type.xjTft.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance());
    } else if ((Type.xjTlc.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance());
    } else if ((Type.bdTft.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance1());
    } else if ((Type.bdTlc.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance1());
    } else if ((Type.bfTli.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance2());
    } else if ((Type.bdTxj.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance1());
    } else if ((Type.ftTxj.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance2());
    } else if ((Type.lcTxj.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance3());
    } else if ((Type.ftTbd.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance2());
    } else if ((Type.lcTbd.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance3());
    } else if ((Type.lcTft.name()).equals(type)) {
        // ?????????->?????????
        data.put("balance", member.getBalance3());
    }
    data.put("message", SUCCESS_MESSAGE);
    return data;
}


}