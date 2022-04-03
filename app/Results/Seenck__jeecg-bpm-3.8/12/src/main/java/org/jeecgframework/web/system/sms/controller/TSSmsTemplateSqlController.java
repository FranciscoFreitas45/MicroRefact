package org.jeecgframework.web.system.sms.controller;
 import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
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
import org.jeecgframework.web.system.sms.entity.TSSmsTemplateSqlEntity;
import org.jeecgframework.web.system.sms.service.TSSmsTemplateSqlServiceI;
import org.jeecgframework.web.system.sms.util.TuiSongMsgUtil;
@Controller
@RequestMapping("/tSSmsTemplateSqlController")
public class TSSmsTemplateSqlController extends BaseController{

 private  Logger logger;

@Autowired
 private  TSSmsTemplateSqlServiceI tSSmsTemplateSqlService;

@Autowired
 private  SystemService systemService;

 private  String message;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest req){
    if (StringUtil.isNotEmpty(tSSmsTemplateSql.getId())) {
        tSSmsTemplateSql = tSSmsTemplateSqlService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
        req.setAttribute("tSSmsTemplateSqlPage", tSSmsTemplateSql);
    }
    return new ModelAndView("system/sms/tSSmsTemplateSql-update");
}


@RequestMapping(params = "pushMsg")
@ResponseBody
public AjaxJson pushMsg(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    if (StringUtils.isBlank(tSSmsTemplateSql.getCode())) {
        j.setSuccess(false);
        j.setMsg("配置CODE不能为空");
    } else {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "4028d881436d514601436d521ae80165");
        String r = TuiSongMsgUtil.sendMessage("消息推送测试333", "2", tSSmsTemplateSql.getCode(), map, "411944058@qq.com");
        if (!"success".equals(r)) {
            j.setSuccess(false);
            j.setMsg(r);
        }
    }
    return j;
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    message = "消息模板_业务SQL配置表添加成功";
    try {
        tSSmsTemplateSqlService.save(tSSmsTemplateSql);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "消息模板_业务SQL配置表添加失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "tSSmsTemplateSql")
public ModelAndView tSSmsTemplateSql(HttpServletRequest request){
    return new ModelAndView("system/sms/tSSmsTemplateSqlList");
}


@RequestMapping(params = "upload")
public ModelAndView upload(HttpServletRequest req){
    return new ModelAndView("system/sms/tSSmsTemplateSqlUpload");
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    message = "消息模板_业务SQL配置表删除成功";
    try {
        for (String id : ids.split(",")) {
            TSSmsTemplateSqlEntity tSSmsTemplateSql = systemService.getEntity(TSSmsTemplateSqlEntity.class, id);
            tSSmsTemplateSqlService.delete(tSSmsTemplateSql);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "消息模板_业务SQL配置表删除失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest req){
    if (StringUtil.isNotEmpty(tSSmsTemplateSql.getId())) {
        tSSmsTemplateSql = tSSmsTemplateSqlService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
        req.setAttribute("tSSmsTemplateSqlPage", tSSmsTemplateSql);
    }
    return new ModelAndView("system/sms/tSSmsTemplateSql-add");
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
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
    // //params.setSecondTitleRows(1);
    // params.setNeedSave(true);
    // try {
    // List<TSSmsTemplateSqlEntity> listTSSmsTemplateSqlEntitys =
    // (List<TSSmsTemplateSqlEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsTemplateSqlEntity.class,params);
    // for (TSSmsTemplateSqlEntity tSSmsTemplateSql : listTSSmsTemplateSqlEntitys) {
    // tSSmsTemplateSqlService.save(tSSmsTemplateSql);
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


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    tSSmsTemplateSql = systemService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
    message = "消息模板_业务SQL配置表删除成功";
    try {
        tSSmsTemplateSqlService.delete(tSSmsTemplateSql);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "消息模板_业务SQL配置表删除失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    message = "消息模板_业务SQL配置表更新成功";
    TSSmsTemplateSqlEntity t = tSSmsTemplateSqlService.get(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
    try {
        MyBeanUtils.copyBeanNotNull2Bean(tSSmsTemplateSql, t);
        tSSmsTemplateSqlService.saveOrUpdate(t);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "消息模板_业务SQL配置表更新失败";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "datagrid")
public void datagrid(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSSmsTemplateSqlEntity.class, dataGrid);
    // 查询条件组装器
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsTemplateSql, request.getParameterMap());
    try {
    // 自定义追加查询条件
    } catch (Exception e) {
        throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.tSSmsTemplateSqlService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


}