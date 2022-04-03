package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.warpper.ApplyWarpper;
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
import cn.maxcj.modular.system.model.Apply;
import cn.maxcj.modular.system.service.IApplyService;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.DTO.User;
@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IApplyService applyService;

@Autowired
 private  IUserService userService;


@BussinessLog(value = "拒绝加入社团申请", key = "applyId")
@RequestMapping(value = "/disagree")
@ResponseBody
public Object disagree(Integer applyId){
    Apply apply = applyService.selectById(applyId);
    apply.setAgree(-1);
    applyService.updateById(apply);
    return SUCCESS_TIP;
}


@RequestMapping("/apply_add")
public String applyAdd(){
    return PREFIX + "apply_add.html";
}


@RequestMapping("/apply_update/{applyId}")
public String applyUpdate(Integer applyId,Model model){
    Apply apply = applyService.selectById(applyId);
    model.addAttribute("item", apply);
    LogObjectHolder.me().set(apply);
    return PREFIX + "apply_edit.html";
}


@RequestMapping("")
public String index(){
    return PREFIX + "apply.html";
}


@RequestMapping(value = "/update")
@ResponseBody
public Object update(Apply apply){
    applyService.updateById(apply);
    return SUCCESS_TIP;
}


@BussinessLog(value = "通过加入社团申请", key = "applyId")
@RequestMapping(value = "/agree")
@ResponseBody
public Object agree(Integer applyId){
    Apply apply = applyService.selectById(applyId);
    User user = userService.selectById(apply.getUserid());
    Integer deptid = ShiroKit.getUser().getDeptId();
    user.setDeptid(deptid);
    userService.updateById(user);
    apply.setAgree(1);
    applyService.updateById(apply);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/detail/{applyId}")
@ResponseBody
public Object detail(Integer applyId){
    return applyService.selectById(applyId);
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition){
    List<Map<String, Object>> list = applyService.list(condition);
    return super.warpObject(new ApplyWarpper(list));
}


@RequestMapping(value = "/club_apply")
@ResponseBody
public Object club_apply(String condition){
    Integer deptid = ShiroKit.getUser().getDeptId();
    List<Map<String, Object>> list = applyService.list(deptid, condition);
    return super.warpObject(new ApplyWarpper(list));
}


@BussinessLog(value = "一键通过加入社团申请")
@RequestMapping(value = "/agree_all")
@ResponseBody
public Object agree_all(){
    Integer deptid = ShiroKit.getUser().getDeptId();
    // 将所有申请此社团的用户通过
    // 1 审批记录置1
    // 2 对应用户设置其deptid
    List<Apply> applies = this.applyService.applys(deptid);
    try {
        for (int i = 0; i < applies.size(); i++) {
            Apply apply = applies.get(i);
            apply.setAgree(1);
            this.applyService.updateById(apply);
            User u = this.userService.selectById(apply.getUserid());
            u.setDeptid(deptid);
            this.userService.updateById(u);
        }
    } catch (Exception e) {
        return false;
    }
    return SUCCESS_TIP;
}


@RequestMapping(value = "/delete")
@ResponseBody
public Object delete(Integer applyId){
    applyService.deleteById(applyId);
    return SUCCESS_TIP;
}


}