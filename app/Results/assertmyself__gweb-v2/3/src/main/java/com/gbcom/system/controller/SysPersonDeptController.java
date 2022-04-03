package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysPersonDeptService;
import com.gbcom.system.domain.SysPersonDept;
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
import com.gbcom.DTO.QueryTranslateJq;
@Controller
public class SysPersonDeptController extends BaseCRUDActionController{

@Autowired
 private  SysPersonDeptService sysPersonDeptService;


@RequestMapping
@UserLog
public String add(Model model,Long parentId){
    SysPersonDept sysPersonDept = new SysPersonDept();
    // 如需增加其他默认值请在此添加
    // if (parentId != null) {
    // sysPersonDept.setParent(sysPersonDeptService.get(parentId));
    // }
    model.addAttribute("bean", sysPersonDept);
    return "view/system/sysPersonDept/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysPersonDept sysPersonDept = sysPersonDeptService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysPersonDept);
    return "view/system/sysPersonDept/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysPersonDept sysPersonDept = sysPersonDeptService.get(id);
    model.addAttribute("bean", sysPersonDept);
    return "view/system/sysPersonDept/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    return "view/system/sysPersonDept/grid";
}


@RequestMapping
public void save(HttpServletResponse response,SysPersonDept entity,HttpServletRequest request){
    try {
        SysPersonDept target;
        if (entity.getId() != null) {
            target = sysPersonDeptService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "position", "orderNo", "isValid", "isManager" });
        } else {
            target = entity;
        }
        sysPersonDeptService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows){
    try {
        Page<SysPersonDept> pageModel = new Page<SysPersonDept>(page, rows, true);
        String hql = "from SysPersonDept order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        pageModel = sysPersonDeptService.findByPage(pageModel, queryTranslate.toString());
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
    sysPersonDeptService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


}