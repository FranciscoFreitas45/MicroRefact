package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysPrivilegeService;
import com.gbcom.system.daoservice.SysRolePrivilegeService;
import com.gbcom.system.daoservice.SysRoleService;
import com.gbcom.system.daoservice.SysUserRoleService;
import com.gbcom.system.domain;
import com.gbcom.system.manager.SysCodeManager;
import com.gbcom.system.manager.SysPrivilegeManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util;
import com.gbcom.Interface.SysCodeManager;
@Controller
public class SysRoleController extends BaseCRUDActionController{

@Autowired
 private  SysRoleService sysRoleService;

@Autowired
 private  SysPrivilegeManager sysPrivilegeManager;

@Autowired
 private  SysRolePrivilegeService sysRolePrivilegeService;

@Autowired
 private  SysPrivilegeService sysPrivilegeService;

@Autowired
 private  SysUserRoleService sysUserRoleService;

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysCodeManager sysCodeManager;


@RequestMapping
@UserLog
public String add(Model model){
    SysRole sysRole = new SysRole();
    model.addAttribute("bean", sysRole);
    return "view/system/sysRole/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysRole sysRole = sysRoleService.get(id);
    // ????????????????????????
    model.addAttribute("bean", sysRole);
    return "view/system/sysRole/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysRole sysRole = sysRoleService.get(id);
    model.addAttribute("bean", sysRole);
    return "view/system/sysRole/view";
}


@RequestMapping
public String setPrivilege(Model model,Long id){
    SysRole sysRole = sysRoleService.get(id);
    model.addAttribute("bean", sysRole);
    Set<Long> rolePrivilegeIdSet = new HashSet<Long>();
    // ????????????????????????????????????
    Set<SysRolePrivilege> sysRolePrivileges = sysRole.getSysRolePrivileges();
    for (SysRolePrivilege sysRolePrivilege : sysRolePrivileges) {
        rolePrivilegeIdSet.add(sysRolePrivilege.getPrivilege().getId());
    }
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    // ???????????????????????????
    List<SysPrivilege> sysPrivileges = sysPrivilegeManager.getAllPrivileges();
    for (SysPrivilege sysPrivilege : sysPrivileges) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sysPrivilege", sysPrivilege);
        // ?????????????????????????????????????????????????????????????????????????????????
        map.put("isChecked", rolePrivilegeIdSet.contains(sysPrivilege.getId()));
        String levelStr = JspHelper.getLevelStr(sysPrivilege.getTreeId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        System.out.println(sysPrivilege.getName() + "|" + sysPrivilege.getTreeId() + " = " + levelStr);
        map.put("space", levelStr);
        list.add(map);
    }
    // ????????????
    SysCodeDetail privilegeType = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);
    model.addAttribute("buttonType", privilegeType.getId());
    model.addAttribute("list", list);
    return "view/system/sysRole/setPrivilege";
}


@RequestMapping
@UserLog
public String grid(Model model){
    // ???????????????????????????
    model.addAttribute("canDesignate", sysUserManager.hasPrivilege(PrivilegeCode.SYS_ROLE_DESIGNATE));
    // ???????????????????????????
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_ROLE_EDIT));
    return "view/system/sysRole/grid";
}


@RequestMapping
@UserLog
public void savePrivilege(HttpServletResponse response,Long id,HttpServletRequest request){
    String[] privilegeIds = request.getParameterValues("sysPrivilegeId");
    SysRole sysRole = sysRoleService.get(id);
    Set<SysRolePrivilege> sysRolePrivileges = sysRole.getSysRolePrivileges();
    // ?????????????????????????????????
    for (SysRolePrivilege sysRolePrivilege : sysRolePrivileges) {
        sysRolePrivilegeService.delete(sysRolePrivilege);
    }
    if (privilegeIds != null) {
        SysPrivilege sysPrivilege;
        Long privilegeIdLong;
        for (String privilegeId : privilegeIds) {
            SysRolePrivilege sysRolePrivilege = new SysRolePrivilege();
            sysRolePrivilege.setRole(sysRole);
            privilegeIdLong = Long.valueOf(privilegeId);
            sysPrivilege = sysPrivilegeService.get(privilegeIdLong);
            sysRolePrivilege.setPrivilege(sysPrivilege);
            sysRolePrivilegeService.save(sysRolePrivilege);
        }
    }
    sendSuccessJSON(response, "????????????");
}


@RequestMapping
public void save(HttpServletResponse response,SysRole entity){
    try {
        SysRole target;
        if (entity.getId() != null) {
            target = sysRoleService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "code", "roleName", "description" });
        } else {
            target = entity;
            // ???????????????????????????
            target.setRoleType(4);
            target.setDomainType(0);
            target.setViewType(0);
        }
        sysRoleService.save(target);
        sendSuccessJSON(response, "????????????");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,HttpSession session){
    try {
        Page<SysRole> pageModel = new Page<SysRole>(page, rows, true);
        String hql = "from SysRole order by id";
        // ???????????????????????????
        // ????????????
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysRoleService.findByPage(pageModel, query);
        session.setAttribute(Constants.GRID_SQL_KEY, query);
        // ????????????
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    SysRole sysRole = sysRoleService.get(id);
    // ???????????????????????????
    Set<SysRolePrivilege> rolePrivileges = sysRole.getSysRolePrivileges();
    for (SysRolePrivilege sysRolePrivilege : rolePrivileges) {
        sysRolePrivilegeService.delete(sysRolePrivilege);
    }
    // ???????????????????????????
    Set<SysUserRole> sysUserRoles = sysRole.getSysUserRoles();
    for (SysUserRole sysUserRole : sysUserRoles) {
        sysUserRoleService.delete(sysUserRole);
    }
    // ????????????
    sysRoleService.delete(sysRole);
    sendSuccessJSON(response, "????????????");
}


}