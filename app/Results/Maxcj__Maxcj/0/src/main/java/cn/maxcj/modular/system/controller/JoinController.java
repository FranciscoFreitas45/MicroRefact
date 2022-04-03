package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.system.warpper.JoinWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.maxcj.modular.system.model.Join;
import cn.maxcj.modular.system.service.IJoinService;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/join")
public class JoinController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IJoinService joinService;


@RequestMapping(value = "/add")
@ResponseBody
public Object add(Join join){
    joinService.insert(join);
    return SUCCESS_TIP;
}


@RequestMapping("/join_update/{joinId}")
public String joinUpdate(Integer joinId,Model model){
    Join join = joinService.selectById(joinId);
    model.addAttribute("item", join);
    LogObjectHolder.me().set(join);
    return PREFIX + "join_edit.html";
}


@RequestMapping("")
public String index(){
    return PREFIX + "join.html";
}


@RequestMapping(value = "/update")
@ResponseBody
public Object update(Join join){
    joinService.updateById(join);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/detail/{joinId}")
@ResponseBody
public Object detail(Integer joinId){
    return joinService.selectById(joinId);
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition){
    Integer userid = ShiroKit.getUser().getId();
    List<Map<String, Object>> list = joinService.myJoin(userid, condition);
    return super.warpObject(new JoinWarpper(list));
}


@RequestMapping(value = "/delete")
@ResponseBody
public Object delete(Integer joinId){
    joinService.deleteById(joinId);
    return SUCCESS_TIP;
}


@RequestMapping("/join_add")
public String joinAdd(){
    return PREFIX + "join_add.html";
}


}