package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.core.shiro.ShiroUser;
import cn.maxcj.modular.system.model.Activity;
import cn.maxcj.modular.system.model.Clubinfo;
import cn.maxcj.modular.system.model.Message;
import cn.maxcj.modular.system.service;
import cn.maxcj.modular.system.warpper.ActivityWarpper;
import cn.maxcj.modular.system.warpper.ClubInfoWarpper;
import cn.maxcj.modular.system.warpper.DeptWarpper;
import cn.maxcj.modular.system.warpper.NoticeWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IDeptService;
import cn.maxcj.Interface.IClubinfoService;
import cn.maxcj.Interface.IActivityService;
import cn.maxcj.DTO.IActivityService;
import cn.maxcj.DTO.IDeptService;
@Controller
@RequestMapping("/welcome")
public class WelcomeController extends BaseController{

@Autowired
 private  IDeptService deptService;

@Autowired
 private  IMessageService messageService;

@Autowired
 private  INoticeService noticeService;

@Autowired
 private  IClubinfoService clubinfoService;

@Autowired
 private  IActivityService activityService;


@RequestMapping(value = "/clubactivity/{deptid}")
@ResponseBody
public Object clublist(Integer deptid,String condition){
    List<Map<String, Object>> activity_list = this.activityService.activity_club(deptid, condition);
    return super.warpObject(new ActivityWarpper(activity_list));
}


@RequestMapping(value = "/message/add")
@ResponseBody
public Object add(Message message){
    String username = ShiroKit.getUser().getName();
    if (username != null) {
        message.setUsername(username);
    }
    message.setCreatetime(new Date());
    messageService.insert(message);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/clubdetil/{deptId}")
public Object clubDetil(Integer deptId,Model model){
    if (deptId == null || "".equals(deptId)) {
        return "/404.html";
    }
    model.addAttribute("item", deptId);
    return "/clubdetail.html";
}


@RequestMapping(value = "/club/{deptId}")
@ResponseBody
public Object club(Integer deptId){
    return super.warpObject(new ClubInfoWarpper(clubinfoService.queryClubInfo(deptId)));
}


@RequestMapping(value = "/clublist/category/{categoryId}")
@ResponseBody
public Object clublistWithClassfiy(Integer categoryId,String condition){
    List<Map<String, Object>> list = this.deptService.clublist(categoryId, condition);
    return super.warpObject(new DeptWarpper(list));
}


@RequestMapping(value = "/addmessage")
@ResponseBody
public Object AddMessage(Message message,Model model){
    if (ShiroKit.getUser() == null) {
        ShiroKit.getSubject().logout();
        model.addAttribute("tips", "登录之后才可留言");
        return "/login.html";
    }
    message.setCreatetime(new Date());
    message.setUsername(ShiroKit.getUser().getName());
    messageService.insert(message);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition){
    List<Map<String, Object>> list = this.noticeService.list(condition);
    return super.warpObject(new NoticeWrapper(list));
}


@RequestMapping(value = "", method = RequestMethod.GET)
public String welcome(){
    return "/welcome.html";
}


}