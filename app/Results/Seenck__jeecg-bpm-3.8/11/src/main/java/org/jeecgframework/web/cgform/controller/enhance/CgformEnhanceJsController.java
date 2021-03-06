package org.jeecgframework.web.cgform.controller.enhance;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/cgformEnhanceJsController")
public class CgformEnhanceJsController extends BaseController{

@SuppressWarnings("unused")
 private  Logger logger;

@Autowired
 private  CgformEnhanceJsServiceI cgformenhanceJsService;

@Autowired
 private  SystemService systemService;


@RequestMapping(params = "addorupdate")
public ModelAndView addorupdate(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest req){
    // 根据cgJsType和formId初始化数据
    cgformenhanceJs.setCgJsType("form");
    if (StringUtil.isNotEmpty(cgformenhanceJs.getCgJsType()) && StringUtil.isNotEmpty(cgformenhanceJs.getFormId())) {
        CgformEnhanceJsEntity cgformenJs = cgformenhanceJsService.getCgformEnhanceJsByTypeFormId(cgformenhanceJs.getCgJsType(), cgformenhanceJs.getFormId());
        if (cgformenJs != null) {
            cgformenhanceJs = cgformenJs;
        }
    }
    req.setAttribute("cgformenhanceJsPage", cgformenhanceJs);
    return new ModelAndView("jeecg/cgform/enhance/cgformenhanceJs");
}


@RequestMapping(params = "cgformenhanceJs")
public ModelAndView cgformenhanceJs(HttpServletRequest request){
    return new ModelAndView("jeecg/cgform/enhance/cgformenhanceJsList");
}


@RequestMapping(params = "save")
@ResponseBody
public AjaxJson save(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    if (StringUtil.isNotEmpty(cgformenhanceJs.getId())) {
        message = "更新成功";
        CgformEnhanceJsEntity t = cgformenhanceJsService.get(CgformEnhanceJsEntity.class, cgformenhanceJs.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(cgformenhanceJs, t);
            cgformenhanceJsService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        message = "添加成功";
        cgformenhanceJsService.save(cgformenhanceJs);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "del")
@ResponseBody
public AjaxJson del(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    cgformenhanceJs = systemService.getEntity(CgformEnhanceJsEntity.class, cgformenhanceJs.getId());
    message = "删除成功";
    cgformenhanceJsService.delete(cgformenhanceJs);
    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    j.setMsg(message);
    return j;
}


@SuppressWarnings("unchecked")
@RequestMapping(params = "datagrid")
public void datagrid(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(CgformEnhanceJsEntity.class, dataGrid);
    // 查询条件组装器
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cgformenhanceJs, request.getParameterMap());
    this.cgformenhanceJsService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "doCgformEnhanceJs")
@ResponseBody
public AjaxJson doCgformEnhanceJs(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    CgformEnhanceJsEntity cgformenJs = cgformenhanceJsService.getCgformEnhanceJsByTypeFormId(cgformenhanceJs.getCgJsType(), cgformenhanceJs.getFormId());
    if (cgformenJs != null) {
        j.setObj(cgformenJs);
        j.setSuccess(true);
    } else {
        j.setSuccess(false);
    }
    return j;
}


}