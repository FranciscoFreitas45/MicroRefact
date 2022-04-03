package org.jeecgframework.web.system.sms.controller;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.web.system.sms.entity.TSSmsSqlEntity;
import org.jeecgframework.web.system.sms.service.TSSmsSqlServiceI;
@Controller
@RequestMapping("/tSSmsSqlController")
public class TSSmsSqlController extends BaseController{

 private  Logger logger;

@Autowired
 private  TSSmsSqlServiceI tSSmsSqlService;

@Autowired
 private  SystemService systemService;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(TSSmsSqlEntity tSSmsSql,HttpServletRequest req){
    if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
        tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
        req.setAttribute("tSSmsSqlPage", tSSmsSql);
    }
    return new ModelAndView("system/sms/tSSmsSql-update");
}


@SuppressWarnings("unchecked")
@RequestMapping(params = "importExcel", method = RequestMethod.POST)
@ResponseBody
public AjaxJson importExcel(HttpServletRequest request,HttpServletResponse response){
    AjaxJson j = new AjaxJson();
    // MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    // Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
    // for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
    // MultipartFile file = entity.getValue();// 获取上传文件对象
    // ImportParams params = new ImportParams();
    // params.setTitleRows(2);
    // params.setSecondTitleRows(1);
    // params.setNeedSave(true);
    // try {
    // List<TSSmsSqlEntity> listTSSmsSqlEntitys =
    // (List<TSSmsSqlEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsSqlEntity.class,params);
    // for (TSSmsSqlEntity tSSmsSql : listTSSmsSqlEntitys) {
    // systemService.save(tSSmsSql);
    // }
    // j.setMsg("文件导入成功！");
    // } catch (Exception e) {
    // j.setMsg("文件导入失败！");
    // logger.error(ExceptionUtil.getExceptionMessage(e));
    // }finally{
    // try {
    // file.getInputStream().close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    return j;
}


@RequestMapping(params = "tSSmsSql")
public ModelAndView tSSmsSql(HttpServletRequest request){
    return new ModelAndView("system/sms/tSSmsSqlList");
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(TSSmsSqlEntity tSSmsSql,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "业务SQL表添加成功";
    try {
        tSSmsSqlService.save(tSSmsSql);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "业务SQL表添加失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(TSSmsSqlEntity tSSmsSql,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
    message = "业务SQL表删除成功";
    try {
        systemService.delete(tSSmsSql);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "业务SQL表删除失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "upload")
public ModelAndView upload(HttpServletRequest req){
    return new ModelAndView("system/sms/tSSmsSqlUpload");
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "业务SQL表删除成功";
    try {
        for (String id : ids.split(",")) {
            TSSmsSqlEntity tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, id);
            systemService.delete(tSSmsSql);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "业务SQL表删除失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(TSSmsSqlEntity tSSmsSql,HttpServletRequest req){
    if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
        tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
        req.setAttribute("tSSmsSqlPage", tSSmsSql);
    }
    return new ModelAndView("system/sms/tSSmsSql-add");
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(TSSmsSqlEntity tSSmsSql,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "业务SQL表更新成功";
    TSSmsSqlEntity t = systemService.get(TSSmsSqlEntity.class, tSSmsSql.getId());
    try {
        MyBeanUtils.copyBeanNotNull2Bean(tSSmsSql, t);
        systemService.saveOrUpdate(t);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "业务SQL表更新失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "datagrid")
public void datagrid(TSSmsSqlEntity tSSmsSql,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSSmsSqlEntity.class, dataGrid);
    // 查询条件组装器
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsSql, request.getParameterMap());
    try {
    // 自定义追加查询条件
    } catch (Exception e) {
        throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


}