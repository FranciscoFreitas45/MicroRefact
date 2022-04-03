package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.dictmap.DeptDict;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.system.model;
import cn.maxcj.modular.system.service;
import cn.maxcj.modular.system.warpper.ClubWarpper;
import cn.maxcj.modular.system.warpper.DeptWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.Interface.IApplyService;
import cn.maxcj.Interface.IClubinfoService;
import cn.maxcj.Interface.IActivityService;
import cn.maxcj.Interface.IActivityStatisticsService;
import cn.maxcj.DTO.User;
import cn.maxcj.DTO.Apply;
import cn.maxcj.DTO.IUserService;
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IDeptService deptService;

@Autowired
 private  IUserService userService;

@Autowired
 private  IApplyService applyService;

@Autowired
 private  IClubinfoService clubinfoService;

@Autowired
 private  IActivityService activityService;

@Autowired
 private  IActivityStatisticsService activityStatisticsService;


@BussinessLog(value = "添加部门", key = "simplename", dict = DeptDict.class)
@RequestMapping(value = "/add")
@Permission
@ResponseBody
public Object add(Dept dept){
    if (ToolUtil.isOneEmpty(dept, dept.getSimplename())) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 完善pids,根据pid拿到pid的pids
    deptSetPids(dept);
    this.deptService.insert(dept);
    // 初始化社团对应的社团信息
    Integer id = this.deptService.selectById(dept).getId();
    clubinfoService.init(id);
    return true;
}


@RequestMapping(value = "/clubtree")
@ResponseBody
public List<ZTreeNode> clubtree(){
    List<ZTreeNode> tree = this.deptService.clubtree(ShiroKit.getUser().deptId);
    // tree.add(ZTreeNode.createParent());
    return tree;
}


@Permission
@RequestMapping("/dept_update/{deptId}")
public String deptUpdate(Integer deptId,Model model){
    Dept dept = deptService.selectById(deptId);
    model.addAttribute(dept);
    model.addAttribute("pName", ConstantFactory.me().getDeptName(dept.getPid()));
    LogObjectHolder.me().set(dept);
    return PREFIX + "dept_edit.html";
}


@BussinessLog(value = "加入社团", key = "deptId", dict = DeptDict.class)
@RequestMapping(value = "/apply")
@ResponseBody
public Object apply(Integer deptId){
    User user = userService.getByAccount(ShiroKit.getUser().getAccount());
    // 判断是否加入过社团
    /*if (user.getDeptid() != null){
            return null;
        }*/
    if (applyService.apply_exist(user.getId())) {
        return SUCCESS_TIP;
    }
    Apply apply = new Apply();
    apply.setUserid(user.getId());
    apply.setDeptid(deptId);
    if (applyService.selectById(apply) != null) {
        return null;
    }
    apply.setApplytime(new Date());
    applyService.insert(apply);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/tree")
@ResponseBody
public List<ZTreeNode> tree(){
    List<ZTreeNode> tree = this.deptService.tree();
    return tree;
}


@RequestMapping("")
public String index(){
    return PREFIX + "dept.html";
}


@BussinessLog(value = "修改部门", key = "simplename", dict = DeptDict.class)
@RequestMapping(value = "/update")
@Permission
@ResponseBody
public Object update(Dept dept){
    if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    deptSetPids(dept);
    deptService.updateById(dept);
    return SUCCESS_TIP;
}


public void deptSetPids(Dept dept){
    if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0)) {
        dept.setPid(0);
        dept.setPids("[0],");
    } else {
        int pid = dept.getPid();
        Dept temp = deptService.selectById(pid);
        String pids = temp.getPids();
        dept.setPid(pid);
        dept.setPids(pids + "[" + pid + "],");
    }
}


@RequestMapping(value = "/list")
@Permission
@ResponseBody
public Object list(String condition){
    List<Map<String, Object>> list = this.deptService.list(condition);
    return super.warpObject(new DeptWarpper(list));
}


@BussinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
@RequestMapping(value = "/delete")
@Permission
@ResponseBody
public Object delete(Integer deptId){
    // 缓存被删除的部门名称
    LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));
    // 查询社团下的所有成员，如果有成员不允许删除
    List<Map<String, Object>> list = this.userService.selectUsers(null, null, null, null, deptId);
    if (list.size() > 0) {
        return null;
    }
    try {
        // 删除社团/部门前删除关于社团/部门的信息、活动
        clubinfoService.deleteById(clubinfoService.getClubInfoByDeptid(deptId).getId());
        List<Activity> list1 = activityService.clublist(deptId, null);
        for (int i = 0; i < list1.size(); i++) {
            activityService.deleteById(list1.get(i));
        }
        List<ActivityStatistics> list2 = activityStatisticsService.list(deptId);
        for (int i = 0; i < list2.size(); i++) {
            activityStatisticsService.deleteById(list2.get(i));
        }
        deptService.deleteDept(deptId);
    } catch (Exception e) {
        return null;
    }
    return SUCCESS_TIP;
}


@RequestMapping("/dept_add")
public String deptAdd(){
    return PREFIX + "dept_add.html";
}


@RequestMapping(value = "/detail/{deptId}")
@Permission
@ResponseBody
public Object detail(Integer deptId){
    return deptService.selectById(deptId);
}


@RequestMapping(value = "/allclub")
@ResponseBody
public Object allclub(String condition,String clubCategory){
    List<Map<String, Object>> list = this.deptService.allclub(condition, clubCategory);
    return super.warpObject(new ClubWarpper(list));
}


}