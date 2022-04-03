package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.constant.cache.Cache;
import cn.maxcj.core.common.constant.dictmap.RoleDict;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.core.util.CacheUtil;
import cn.maxcj.modular.system.model.Role;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IRoleService;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.warpper.RoleWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IUserService;
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IUserService userService;

@Autowired
 private  IRoleService roleService;


@RequestMapping(value = "/add")
@BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
@Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData add(Role role,BindingResult result){
    if (result.hasErrors()) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    role.setId(null);
    this.roleService.insert(role);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/view/{roleId}")
@ResponseBody
public ResponseData view(Integer roleId){
    if (ToolUtil.isEmpty(roleId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    this.roleService.selectById(roleId);
    return SUCCESS_TIP;
}


@RequestMapping("/setAuthority")
@BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
@Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData setAuthority(Integer roleId,String ids){
    if (ToolUtil.isOneEmpty(roleId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    this.roleService.setAuthority(roleId, ids);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/roleTreeList")
@ResponseBody
public List<ZTreeNode> roleTreeList(){
    List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
    roleTreeList.add(ZTreeNode.createParent());
    return roleTreeList;
}


@Permission
@RequestMapping(value = "/role_edit/{roleId}")
public String roleEdit(Integer roleId,Model model){
    if (ToolUtil.isEmpty(roleId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    Role role = this.roleService.selectById(roleId);
    model.addAttribute(role);
    model.addAttribute("pName", ConstantFactory.me().getSingleRoleName(role.getPid()));
    model.addAttribute("deptName", ConstantFactory.me().getDeptName(role.getDeptid()));
    LogObjectHolder.me().set(role);
    return PREFIX + "/role_edit.html";
}


@RequestMapping(value = "/edit")
@BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
@Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData edit(Role role,BindingResult result){
    if (result.hasErrors()) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    this.roleService.updateById(role);
    // 删除缓存
    CacheUtil.removeAll(Cache.CONSTANT);
    return SUCCESS_TIP;
}


@RequestMapping("")
public String index(){
    return PREFIX + "/role.html";
}


@RequestMapping(value = "/role_add")
public String roleAdd(){
    return PREFIX + "/role_add.html";
}


@Permission
@RequestMapping(value = "/list")
@ResponseBody
public Object list(String roleName){
    List<Map<String, Object>> roles = this.roleService.selectRoles(super.getPara("roleName"));
    return super.warpObject(new RoleWarpper(roles));
}


@RequestMapping(value = "/remove")
@BussinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
@Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData remove(Integer roleId){
    if (ToolUtil.isEmpty(roleId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 不能删除超级管理员角色
    if (roleId.equals(Const.ADMIN_ROLE_ID)) {
        throw new ServiceException(BizExceptionEnum.CANT_DELETE_ADMIN);
    }
    // 缓存被删除的角色名称
    LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));
    this.roleService.delRoleById(roleId);
    // 删除缓存
    CacheUtil.removeAll(Cache.CONSTANT);
    return SUCCESS_TIP;
}


@Permission
@RequestMapping(value = "/role_assign/{roleId}")
public String roleAssign(Integer roleId,Model model){
    if (ToolUtil.isEmpty(roleId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    model.addAttribute("roleId", roleId);
    model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
    return PREFIX + "/role_assign.html";
}


@RequestMapping(value = "/roleTreeListByUserId/{userId}")
@ResponseBody
public List<ZTreeNode> roleTreeListByUserId(Integer userId){
    User theUser = this.userService.selectById(userId);
    String roleid = theUser.getRoleid();
    if (ToolUtil.isEmpty(roleid)) {
        return this.roleService.roleTreeList();
    } else {
        String[] strArray = roleid.split(",");
        return this.roleService.roleTreeListByRoleId(strArray);
    }
}


}