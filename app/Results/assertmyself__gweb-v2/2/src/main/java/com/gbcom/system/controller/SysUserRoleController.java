package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysUserRoleService;
import com.gbcom.system.domain.SysUserRole;
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
public class SysUserRoleController extends BaseCRUDActionController{

@Autowired
 private  SysUserRoleService sysUserRoleService;


@RequestMapping
@UserLog
public String add(Model model,Long parentId){
    SysUserRole sysUserRole = new SysUserRole();
    // 如需增加其他默认值请在此添加
    // if (parentId != null) {
    // sysUserRole.setParent(sysUserRoleService.get(parentId));
    // }
    model.addAttribute("bean", sysUserRole);
    return "view/system/sysUserRole/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysUserRole sysUserRole = sysUserRoleService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysUserRole);
    return "view/system/sysUserRole/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysUserRole sysUserRole = sysUserRoleService.get(id);
    model.addAttribute("bean", sysUserRole);
    return "view/system/sysUserRole/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    return "view/system/sysUserRole/grid";
}


@RequestMapping
public void save(HttpServletResponse response,SysUserRole entity,HttpServletRequest request){
    try {
        SysUserRole target;
        if (entity.getId() != null) {
            target = sysUserRoleService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] {});
        } else {
            target = entity;
        }
        sysUserRoleService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows){
    try {
        Page<SysUserRole> pageModel = new Page<SysUserRole>(page, rows, true);
        String hql = "from SysUserRole order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        pageModel = sysUserRoleService.findByPage(pageModel, queryTranslate.toString());
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
    sysUserRoleService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


}