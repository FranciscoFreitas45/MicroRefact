package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysParameterService;
import com.gbcom.system.domain.SysParameter;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import com.hc.core.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import com.gbcom.Interface.SysUserManager;
@Controller
public class SysParameterController extends BaseCRUDActionController{

@Autowired
 private  SysParameterService sysParameterService;

@Autowired
 private  SysUserManager sysUserManager;


@RequestMapping
@UserLog
public String add(Model model,Long parentId){
    SysParameter sysParameter = new SysParameter();
    // 如需增加其他默认值请在此添加
    // if (parentId != null) {
    // sysParameter.setParent(sysParameterService.get(parentId));
    // }
    model.addAttribute("bean", sysParameter);
    return "view/system/sysParameter/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysParameter sysParameter = sysParameterService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysParameter);
    return "view/system/sysParameter/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysParameter sysParameter = sysParameterService.get(id);
    String updateUser = sysParameter.getUpdateUser();
    if (StringHelper.isNotEmpty(updateUser)) {
        // 将登陆名替换成显示名
        model.addAttribute("updateUser", sysUserManager.getDisplayName(updateUser));
    }
    model.addAttribute("bean", sysParameter);
    return "view/system/sysParameter/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_PARAMETER));
    return "view/system/sysParameter/grid";
}


@RequestMapping
public void save(HttpServletResponse response,SysParameter entity,HttpServletRequest request){
    try {
        SysParameter target;
        if (entity.getId() != null) {
            target = sysParameterService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "code", "name", // ,
            "value" // "constraint",
            // "clobvalue",
            // "createTime",
            // "updateTime",
            // "createUser",
            // "updateUser"
            });
        } else {
            target = entity;
        }
        sysParameterService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows){
    try {
        Page<SysParameter> pageModel = new Page<SysParameter>(page, rows, true);
        String hql = "from SysParameter order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysParameterService.findByPage(pageModel, query);
        List<Map> list = GridJq.getGridValue(pageModel.getRows(), columns);
        for (Map bean : list) {
            // 将登陆名替换成显示名
            String updateUser = (String) bean.get("updateUser");
            if (StringHelper.isNotEmpty(updateUser)) {
                bean.put("updateUser", sysUserManager.getDisplayName(updateUser));
            }
        }
        // 输出显示
        String json = GridJq.toJSON(list, pageModel);
        // //输出显示
        // String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    sysParameterService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


}