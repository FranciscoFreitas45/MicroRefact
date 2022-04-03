package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.constant.dictmap.DeptDict;
import cn.maxcj.core.common.constant.dictmap.FinanceDict;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.sms.SendSms;
import cn.maxcj.modular.system.model.Activity;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IActivityService;
import cn.maxcj.modular.system.service.IDeptService;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.warpper.FinanceWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.maxcj.modular.system.model.Finance;
import cn.maxcj.modular.system.service.IFinanceService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.Interface.IDeptService;
import cn.maxcj.Interface.IActivityService;
import cn.maxcj.DTO.Activity;
import cn.maxcj.DTO.User;
@Controller
@RequestMapping("/finance")
public class FinanceController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IFinanceService financeService;

@Autowired
 private  IUserService userService;

@Autowired
 private  IDeptService deptService;

@Autowired
 private  IActivityService activityService;


@BussinessLog(value = "新增财务申请", key = "finance")
@RequestMapping(value = "/add")
@ResponseBody
public Object add(Finance finance){
    finance.setDeptid(ShiroKit.getUser().getDeptId());
    finance.setCosttime(new Date());
    financeService.insert(finance);
    return SUCCESS_TIP;
}


@BussinessLog(value = "拒绝财务申请", key = "financeId")
@RequestMapping(value = "/apply_refuse")
@ResponseBody
public Object apply_refuse(Integer financeId){
    Finance finance = financeService.selectById(financeId);
    finance.setAgree(4);
    finance.setAgreetime(new Date());
    financeService.updateById(finance);
    SendSms sendSms = new SendSms();
    Activity activity = activityService.selectById(finance.getActivityid());
    User smsUser = userService.selectById(activity.getActivityPerson());
    boolean s = sendSms.sendSms(smsUser.getPhone(), smsUser.getName(), deptService.selectById(activity.getActivityClub()).getSimplename(), "财务", "审批未通过");
    return SUCCESS_TIP;
}


@BussinessLog(value = "撤销财务申请审批记录", key = "financeId")
@RequestMapping(value = "/apply_again")
@ResponseBody
public Object apply_again(Integer financeId){
    Finance finance = financeService.selectById(financeId);
    finance.setAgree(2);
    finance.setAgreetime(new Date());
    financeService.updateById(finance);
    return SUCCESS_TIP;
}


@RequestMapping("/finance_update/{financeId}")
public String financeUpdate(Integer financeId,Model model){
    Finance finance = financeService.selectById(financeId);
    model.addAttribute("item", finance);
    LogObjectHolder.me().set(finance);
    return PREFIX + "finance_edit.html";
}


@RequestMapping("/apply")
public String apply(){
    return PREFIX + "finance_apply.html";
}


@RequestMapping("")
public String index(){
    return PREFIX + "finance.html";
}


@BussinessLog(value = "更新财务申请", key = "finance")
@RequestMapping(value = "/update")
@ResponseBody
public Object update(Finance finance){
    financeService.updateById(finance);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/history")
@ResponseBody
public Object history(String condition,Integer category,Integer state){
    List<Map<String, Object>> map = financeService.getHistory(condition, category, state);
    return super.warpObject(new FinanceWarpper(map));
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition,Integer category){
    List<Map<String, Object>> map = financeService.getMyClubFinance(category, ShiroKit.getUser().getDeptId());
    return super.warpObject(new FinanceWarpper(map));
}


@BussinessLog(value = "删除财务申请", key = "financeId")
@RequestMapping(value = "/delete")
@ResponseBody
public Object delete(Integer financeId){
    financeService.deleteById(financeId);
    return SUCCESS_TIP;
}


@RequestMapping("/finance_add")
public String financeAdd(Model model,String con){
    /*Integer deptid = ShiroKit.getUser().getDeptId();
        List<Map<String, Object>> activity = activityService.activity_clublist(deptid, con);
        model.addAttribute("activity", activity);*/
    return PREFIX + "finance_add.html";
}


@RequestMapping(value = "/club_list")
@ResponseBody
public Object club_list(String condition,Integer category){
    List<Map<String, Object>> map = financeService.getClubFinance(condition, category);
    return super.warpObject(new FinanceWarpper(map));
}


@RequestMapping("/history_page")
public String hitory(){
    return PREFIX + "finance_history.html";
}


@BussinessLog(value = "通过财务申请", key = "financeId")
@RequestMapping(value = "/apply_agree")
@ResponseBody
public Object apply_agree(Integer financeId){
    Finance finance = financeService.selectById(financeId);
    finance.setAgree(3);
    finance.setAgreetime(new Date());
    financeService.updateById(finance);
    SendSms sendSms = new SendSms();
    Activity activity = activityService.selectById(finance.getActivityid());
    User smsUser = userService.selectById(activity.getActivityPerson());
    boolean s = sendSms.sendSms(smsUser.getPhone(), smsUser.getName(), deptService.selectById(activity.getActivityClub()).getSimplename(), "财务", "审批通过");
    return SUCCESS_TIP;
}


@RequestMapping(value = "/detail/{financeId}")
@ResponseBody
public Object detail(Integer financeId){
    return financeService.selectById(financeId);
}


}