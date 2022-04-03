package com.lingxiang2014.controller.admin;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Withdraw;
import com.lingxiang2014.entity.Withdraw.Status;
import com.lingxiang2014.service.AdminService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.AdminService;
@Controller("adminWithdrawController")
@RequestMapping("/admin/withdraw")
public class WithdrawController extends BaseController{

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "adminServiceImpl")
 private  AdminService adminService;


@RequestMapping(value = "/reject", method = RequestMethod.POST)
@ResponseBody
public Message reject(Long id){
    Message message = new Message();
    Withdraw withdraw = withdrawService.find(id);
    if (withdraw == null) {
        message = ERROR_MESSAGE;
    } else if (withdraw.getStatus() == Status.wait) {
        withdraw.setStatus(Status.failure);
        withdraw.setOperator(adminService.getCurrent());
        withdrawService.update(withdraw);
        Member member = withdraw.getMember();
        member.setBalance(member.getBalance().add(withdraw.getBalance()));
        memberService.update(member);
        message = SUCCESS_MESSAGE;
    } else {
        message = ERROR_MESSAGE;
    }
    return message;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Long memberId,Pageable pageable,ModelMap model,String status){
    Member member = memberService.find(memberId);
    Status status1 = null;
    if (status != null) {
        status1 = Status.valueOf(status);
    }
    if (member != null) {
        model.addAttribute("member", member);
        model.addAttribute("page", withdrawService.findPage(member, status1, pageable));
    } else {
        model.addAttribute("page", withdrawService.findPage(null, status1, pageable));
    }
    return "/admin/withdraw/list";
}


@RequestMapping(value = "/ok", method = RequestMethod.POST)
@ResponseBody
public Message ok(Long id){
    Message message = new Message();
    Withdraw withdraw = withdrawService.find(id);
    if (withdraw == null) {
        message = ERROR_MESSAGE;
    } else if (withdraw.getStatus() == Status.wait) {
        withdraw.setStatus(Status.success);
        withdraw.setOperator(adminService.getCurrent());
        withdrawService.update(withdraw);
        message = SUCCESS_MESSAGE;
    } else {
        message = ERROR_MESSAGE;
    }
    return message;
}


}