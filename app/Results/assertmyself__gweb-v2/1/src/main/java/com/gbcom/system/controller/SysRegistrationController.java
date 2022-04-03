package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysRegistrationService;
import com.gbcom.system.domain.SysRegistration;
import com.gbcom.system.manager.SysCodeManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.PrivilegeCode;
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
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysCodeManager;
@Controller
public class SysRegistrationController extends BaseCRUDActionController<SysRegistration>{

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysCodeManager sysCodeManager;

@Autowired
 private  SysRegistrationService sysRegistrationService;


@RequestMapping
@UserLog
public String add(Model model){
    SysRegistration sysRegistration = new SysRegistration();
    // 如需增加其他默认值请在此添加
    model.addAttribute("bean", sysRegistration);
    return "view/system/sysRegistration/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysRegistration sysRegistration = sysRegistrationService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysRegistration);
    return "view/system/sysRegistration/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysRegistration sysRegistration = sysRegistrationService.get(id);
    model.addAttribute("bean", sysRegistration);
    return "view/system/sysRegistration/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_SAMPLE_EDIT));
    return "view/system/sysRegistration/grid";
}


@RequestMapping
public void save(HttpServletResponse response,SysRegistration entity,HttpServletRequest request){
    try {
        SysRegistration target;
        if (entity.getId() != null) {
            target = sysRegistrationService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "name", "organizationCode", "address", "contacter", "telephone", "notifyPhone", "license", "organizationCertificate", "registerDate", "checkResult", "checkDate" });
        } else {
            target = entity;
        }
        sysRegistrationService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows){
    try {
        Page<SysRegistration> pageModel = new Page<SysRegistration>(page, rows, true);
        String hql = "from SysRegistration order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        pageModel = sysRegistrationService.findByPage(pageModel, queryTranslate.toString());
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
    sysRegistrationService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


}