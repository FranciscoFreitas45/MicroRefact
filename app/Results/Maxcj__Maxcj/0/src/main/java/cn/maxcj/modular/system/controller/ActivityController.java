package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.sms.SendSms;
import cn.maxcj.modular.system.model.Join;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IDeptService;
import cn.maxcj.modular.system.service.IJoinService;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.warpper.ActivityWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.maxcj.modular.system.model.Activity;
import cn.maxcj.modular.system.service.IActivityService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.Interface.IDeptService;
import cn.maxcj.DTO.User;
import cn.maxcj.DTO.SendSms;
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IActivityService activityService;

@Autowired
 private  IUserService userService;

@Autowired
 private  IDeptService deptService;

@Autowired
 private  IJoinService joinService;


@RequestMapping("/activity_add")
public String activityAdd(){
    return PREFIX + "activity_add.html";
}


@BussinessLog(value = "添加社团活动", key = "activity")
@RequestMapping(value = "/add")
@ResponseBody
public Object add(Activity activity){
    activity.setActivityClub(ShiroKit.getUser().getDeptId().toString());
    activity.setActivityPerson(ShiroKit.getUser().getId());
    activity.setActivityCreatTime(new Date());
    activity.setActivityState(2);
    activityService.insert(activity);
    return SUCCESS_TIP;
}


@BussinessLog(value = "撤销社团活动审批", key = "activityId")
@RequestMapping(value = "/again")
@ResponseBody
public Object apply_again(Integer activityId){
    Activity activity = activityService.selectById(activityId);
    activity.setActivityState(2);
    activityService.updateById(activity);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/apply")
@ResponseBody
public Object apply(String condition,String activity_category,String beginTime){
    List<Map<String, Object>> activity_list = this.activityService.activity_apply(condition, activity_category, beginTime);
    return super.warpObject(new ActivityWarpper(activity_list));
}


@RequestMapping(value = "/tree")
@ResponseBody
public Object tree(){
    Integer deptid = ShiroKit.getUser().getDeptId();
    List<Map<String, Object>> tree = this.activityService.tree(deptid);
    // tree.add(ZTreeNode.createParent());
    return tree;
}


@RequestMapping("")
public String index(){
    return PREFIX + "activity.html";
}


@BussinessLog(value = "修改社团活动", key = "activity")
@RequestMapping(value = "/update")
@ResponseBody
public Object update(Activity activity){
    activityService.updateById(activity);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/history")
@ResponseBody
public Object history(String condition,String activity_category,String beginTime,Integer state){
    List<Map<String, Object>> activity_list = this.activityService.activity_history(condition, activity_category, beginTime, state);
    return super.warpObject(new ActivityWarpper(activity_list));
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition,String activity_category,String beginTime){
    List<Map<String, Object>> activity_list = this.activityService.activity_list(condition, activity_category, beginTime);
    return super.warpObject(new ActivityWarpper(activity_list));
}


@BussinessLog(value = "删除社团活动", key = "activityId")
@RequestMapping(value = "/delete")
@ResponseBody
public Object delete(Integer activityId){
    Activity activity = activityService.selectById(activityId);
    if (activity.getActivityState() > 2) {
        // 活动已经同意发起不允许撤销
        String s = "不允许撤销";
        return (Object) s;
    } else {
        activityService.deleteById(activityId);
    }
    return SUCCESS_TIP;
}


@RequestMapping("/apply_history")
public String history_page(){
    return PREFIX + "activity_history.html";
}


@RequestMapping(value = "/clublist")
@ResponseBody
public Object club_list(String condition){
    Integer deptid = ShiroKit.getUser().getDeptId();
    List<Map<String, Object>> activity_list = this.activityService.activity_clublist(deptid, condition);
    return super.warpObject(new ActivityWarpper(activity_list));
}


@RequestMapping("/apply_page")
public String apply_page(){
    return PREFIX + "activity_apply.html";
}


@RequestMapping("/activity_update/{activityId}")
public String activityUpdate(Integer activityId,Model model){
    Activity activity = activityService.selectById(activityId);
    model.addAttribute("item", activity);
    LogObjectHolder.me().set(activity);
    return PREFIX + "activity_edit.html";
}


@BussinessLog(value = "审批社团活动（通过）", key = "activityId")
@RequestMapping(value = "/apply_agree")
@ResponseBody
public Object apply_agree(Integer activityId){
    Activity activity = activityService.selectById(activityId);
    activity.setActivityState(3);
    activityService.updateById(activity);
    SendSms sendSms = new SendSms();
    User smsUser = userService.selectById(activity.getActivityPerson());
    boolean s = sendSms.sendSms(smsUser.getPhone(), smsUser.getName(), deptService.selectById(activity.getActivityClub()).getSimplename(), "活动", "审批通过");
    return SUCCESS_TIP;
}


@BussinessLog(value = "审批社团活动（拒绝）", key = "activityId")
@RequestMapping(value = "/apply_refuse")
@ResponseBody
public Object apply_activity(Integer activityId){
    Activity activity = activityService.selectById(activityId);
    activity.setActivityState(4);
    activityService.updateById(activity);
    SendSms sendSms = new SendSms();
    User smsUser = userService.selectById(activity.getActivityPerson());
    boolean s = sendSms.sendSms(smsUser.getPhone(), smsUser.getName(), deptService.selectById(activity.getActivityClub()).getSimplename(), "活动", "审批未通过");
    return SUCCESS_TIP;
}


@RequestMapping("/club")
public String club(){
    return PREFIX + "club_activity.html";
}


@RequestMapping(value = "/detail/{activityId}")
@ResponseBody
public Object detail(Integer activityId){
    return activityService.selectById(activityId);
}


@BussinessLog(value = "报名社团活动", key = "activity")
@RequestMapping(value = "/join")
@ResponseBody
public Object join(Integer activityId){
    Join join = new Join();
    join.setActivityId(activityId);
    join.setUserid(ShiroKit.getUser().getId());
    join.setJoinTime(new Date());
    join.setJoinState(1);
    joinService.insert(join);
    return SUCCESS_TIP;
}


}