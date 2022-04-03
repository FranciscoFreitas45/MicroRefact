package org.jeecgframework.web.system.controller.core;
 import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;
import org.jeecgframework.web.system.service.NoticeAuthorityRoleServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/noticeAuthorityRoleController")
public class NoticeAuthorityRoleController extends BaseController{

 private  Logger logger;

@Autowired
 private  NoticeAuthorityRoleServiceI noticeAuthorityRoleService;

@Autowired
 private  SystemService systemService;

 private  ExecutorService executor;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest req){
    if (StringUtil.isNotEmpty(noticeAuthorityRole.getId())) {
        noticeAuthorityRole = noticeAuthorityRoleService.getEntity(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
        req.setAttribute("noticeAuthorityRolePage", noticeAuthorityRole);
    }
    return new ModelAndView("system/user/noticeAuthorityRole-update");
}


@RequestMapping(params = "doSave")
@ResponseBody
public AjaxJson doSave(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "通知公告角色授权保存成功";
    try {
        this.noticeAuthorityRoleService.saveTSNoticeAuthorityRole(noticeAuthorityRole);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (BusinessException e) {
        e.printStackTrace();
        message = e.getMessage();
    } catch (Exception e) {
        e.printStackTrace();
        message = "通知公告角色授权保存失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "通知公告角色授权添加成功";
    try {
        noticeAuthorityRoleService.save(noticeAuthorityRole);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "通知公告角色授权添加失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "通知公告角色授权删除成功";
    try {
        noticeAuthorityRoleService.doDelTSNoticeAuthorityRole(noticeAuthorityRole);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "通知公告角色授权删除失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "通知公告角色授权删除成功";
    try {
        for (String id : ids.split(",")) {
            TSNoticeAuthorityRole noticeAuthorityRole = systemService.getEntity(TSNoticeAuthorityRole.class, id);
            noticeAuthorityRoleService.delete(noticeAuthorityRole);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "通知公告角色授权删除失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest req){
    if (StringUtil.isNotEmpty(noticeAuthorityRole.getId())) {
        noticeAuthorityRole = noticeAuthorityRoleService.getEntity(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
        req.setAttribute("noticeAuthorityRolePage", noticeAuthorityRole);
    }
    return new ModelAndView("system/user/noticeAuthorityRole-add");
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "通知公告角色授权更新成功";
    TSNoticeAuthorityRole t = noticeAuthorityRoleService.get(TSNoticeAuthorityRole.class, noticeAuthorityRole.getId());
    try {
        MyBeanUtils.copyBeanNotNull2Bean(noticeAuthorityRole, t);
        noticeAuthorityRoleService.saveOrUpdate(t);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "通知公告角色授权更新失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "datagrid")
public void datagrid(TSNoticeAuthorityRole noticeAuthorityRole,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSNoticeAuthorityRole.class, dataGrid);
    // 查询条件组装器
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, noticeAuthorityRole, request.getParameterMap());
    try {
    // 自定义追加查询条件
    } catch (Exception e) {
        throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.noticeAuthorityRoleService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "selectRole")
public ModelAndView selectUser(HttpServletRequest request){
    return new ModelAndView("system/role/roleList-select");
}


@RequestMapping(params = "noticeAuthorityRole")
public ModelAndView noticeAuthorityRole(String noticeId,HttpServletRequest request){
    request.setAttribute("noticeId", noticeId);
    return new ModelAndView("system/notice/noticeAuthorityRoleList");
}


}