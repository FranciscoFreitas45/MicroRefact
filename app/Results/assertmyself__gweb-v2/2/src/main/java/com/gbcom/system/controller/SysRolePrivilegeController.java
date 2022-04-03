package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysRolePrivilegeService;
import com.gbcom.system.domain.SysRolePrivilege;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class SysRolePrivilegeController extends BaseCRUDActionController{

@Autowired
 private  SysRolePrivilegeService sysRolePrivilegeService;


@RequestMapping
@UserLog
public String add(Model model,Long parentId){
    SysRolePrivilege sysRolePrivilege = new SysRolePrivilege();
    // 如需增加其他默认值请在此添加
    // if (parentId != null) {
    // sysRolePrivilege.setParent(sysRolePrivilegeService.get(parentId));
    // }
    model.addAttribute("bean", sysRolePrivilege);
    return "view/system/sysRolePrivilege/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysRolePrivilege sysRolePrivilege = sysRolePrivilegeService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysRolePrivilege);
    return "view/system/sysRolePrivilege/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysRolePrivilege sysRolePrivilege = sysRolePrivilegeService.get(id);
    model.addAttribute("bean", sysRolePrivilege);
    return "view/system/sysRolePrivilege/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    return "view/system/sysRolePrivilege/grid";
}


@RequestMapping
public void save(HttpServletResponse response,SysRolePrivilege entity,HttpServletRequest request){
    try {
        SysRolePrivilege target;
        if (entity.getId() != null) {
            target = sysRolePrivilegeService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] {});
        } else {
            target = entity;
        }
        sysRolePrivilegeService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows){
    try {
        Page<SysRolePrivilege> pageModel = new Page<SysRolePrivilege>(page, rows, true);
        String hql = "from SysRolePrivilege order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        pageModel = sysRolePrivilegeService.findByPage(pageModel, queryTranslate.toString());
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    sysRolePrivilegeService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


}