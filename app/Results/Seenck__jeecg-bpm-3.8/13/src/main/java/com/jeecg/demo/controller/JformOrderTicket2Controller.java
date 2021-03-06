package com.jeecg.demo.controller;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import com.alibaba.fastjson.JSONArray;
import com.jeecg.demo.entity.JformOrderTicket2Entity;
import com.jeecg.demo.page.JformOrderMain2Page;
import com.jeecg.demo.service.JformOrderMain2ServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Controller
@RequestMapping("/jformOrderTicket2Controller")
@Api(value = "JformOrderTicket2", description = "??????????????????", tags = "jformOrderTicket2Controller")
public class JformOrderTicket2Controller extends BaseController{

 private  Logger logger;

@Autowired
 private  JformOrderMain2ServiceI jformOrderMain2Service;

@Autowired
 private  SystemService systemService;

@Autowired
 private  Validator validator;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jformOrderTicket2.getId())) {
        jformOrderTicket2 = jformOrderMain2Service.getEntity(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
        req.setAttribute("jformOrderTicket2Page", jformOrderTicket2);
    }
    return new ModelAndView("com/jeecg/demo/jformOrderMain2/jformOrderTicket2/update");
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "??????????????????????????????";
    try {
        jformOrderMain2Service.save(jformOrderTicket2);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "??????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "saveRows")
@ResponseBody
public AjaxJson saveRows(JformOrderMain2Page page,HttpServletRequest req){
    String message = "???????????????";
    List<JformOrderTicket2Entity> lists = page.getJformOrderTicket2List();
    AjaxJson j = new AjaxJson();
    String mainId = req.getParameter("mainId");
    if (CollectionUtils.isNotEmpty(lists)) {
        for (JformOrderTicket2Entity temp : lists) {
            if (StringUtil.isNotEmpty(temp.getId())) {
                JformOrderTicket2Entity t = this.systemService.get(JformOrderTicket2Entity.class, temp.getId());
                try {
                    MyBeanUtils.copyBeanNotNull2Bean(temp, t);
                    systemService.saveOrUpdate(t);
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    // temp.setDelFlag(0);??????????????????
                    temp.setFckId(mainId);
                    systemService.save(temp);
                    systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    return j;
}


@RequestMapping(params = "upload")
public ModelAndView upload(HttpServletRequest req){
    req.setAttribute("controller_name", "jformOrderTicket2Controller");
    return new ModelAndView("common/upload/pub_excel_upload");
}


@RequestMapping(params = "exportXls")
public String exportXls(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap){
    CriteriaQuery cq = new CriteriaQuery(JformOrderTicket2Entity.class, dataGrid);
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformOrderTicket2, request.getParameterMap());
    List<JformOrderTicket2Entity> jformOrderTicket2s = this.jformOrderMain2Service.getListByCriteriaQuery(cq, false);
    modelMap.put(NormalExcelConstants.FILE_NAME, "??????????????????");
    modelMap.put(NormalExcelConstants.CLASS, JformOrderTicket2Entity.class);
    modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("????????????????????????", "?????????:" + ResourceUtil.getSessionUser().getRealName(), "????????????"));
    modelMap.put(NormalExcelConstants.DATA_LIST, jformOrderTicket2s);
    return NormalExcelConstants.JEECG_EXCEL_VIEW;
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "??????????????????????????????";
    try {
        for (String id : ids.split(",")) {
            JformOrderTicket2Entity jformOrderTicket2 = systemService.getEntity(JformOrderTicket2Entity.class, id);
            if (jformOrderTicket2 != null) {
                jformOrderMain2Service.delete(jformOrderTicket2);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "??????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jformOrderTicket2.getId())) {
        jformOrderTicket2 = jformOrderMain2Service.getEntity(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
        req.setAttribute("jformOrderTicket2Page", jformOrderTicket2);
    }
    req.setAttribute("mainId", req.getParameter("mainId"));
    return new ModelAndView("com/jeecg/demo/jformOrderMain2/jformOrderTicket2/add");
}


@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
@ApiOperation(value = "????????????????????????", notes = "????????????????????????")
public ResponseMessage<?> update(JformOrderTicket2Entity jformOrderTicket2){
    // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
    Set<ConstraintViolation<JformOrderTicket2Entity>> failures = validator.validate(jformOrderTicket2);
    if (!failures.isEmpty()) {
        return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    // ??????
    try {
        jformOrderMain2Service.saveOrUpdate(jformOrderTicket2);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("????????????????????????????????????");
    }
    // ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
    return Result.success("????????????????????????????????????");
}


@RequestMapping(method = RequestMethod.GET)
@ResponseBody
@ApiOperation(value = "??????????????????????????????", produces = "application/json", httpMethod = "GET")
public ResponseMessage<List<JformOrderTicket2Entity>> list(){
    List<JformOrderTicket2Entity> listJformOrderTicket2s = jformOrderMain2Service.getList(JformOrderTicket2Entity.class);
    return Result.success(listJformOrderTicket2s);
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
@ApiOperation(value = "????????????????????????")
public ResponseMessage<?> delete(String id){
    logger.info("delete[{}]", id);
    // ??????
    if (StringUtils.isEmpty(id)) {
        return Result.error("ID????????????");
    }
    try {
        jformOrderMain2Service.deleteEntityById(JformOrderTicket2Entity.class, id);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("??????????????????????????????");
    }
    return Result.success();
}


@RequestMapping(params = "exportXlsByT")
public String exportXlsByT(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap){
    modelMap.put(NormalExcelConstants.FILE_NAME, "??????????????????");
    modelMap.put(NormalExcelConstants.CLASS, JformOrderTicket2Entity.class);
    modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("????????????????????????", "?????????:" + ResourceUtil.getSessionUser().getRealName(), "????????????"));
    modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
    return NormalExcelConstants.JEECG_EXCEL_VIEW;
}


@SuppressWarnings("unchecked")
@RequestMapping(params = "importExcel", method = RequestMethod.POST)
@ResponseBody
public AjaxJson importExcel(HttpServletRequest request,HttpServletResponse response){
    AjaxJson j = new AjaxJson();
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
    for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        // ????????????????????????
        MultipartFile file = entity.getValue();
        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        params.setHeadRows(1);
        params.setNeedSave(true);
        try {
            List<JformOrderTicket2Entity> listJformOrderTicket2Entitys = ExcelImportUtil.importExcel(file.getInputStream(), JformOrderTicket2Entity.class, params);
            for (JformOrderTicket2Entity jformOrderTicket2 : listJformOrderTicket2Entitys) {
                jformOrderMain2Service.save(jformOrderTicket2);
            }
            j.setMsg("?????????????????????");
        } catch (Exception e) {
            j.setMsg("?????????????????????");
            logger.error(ExceptionUtil.getExceptionMessage(e));
        } finally {
            try {
                file.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return j;
}


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    jformOrderTicket2 = systemService.getEntity(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
    message = "??????????????????????????????";
    try {
        if (jformOrderTicket2 != null) {
            jformOrderMain2Service.delete(jformOrderTicket2);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "??????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
@ApiOperation(value = "??????ID??????????????????????????????", notes = "??????ID??????????????????????????????", httpMethod = "GET", produces = "application/json")
public ResponseMessage<?> get(String id){
    JformOrderTicket2Entity task = jformOrderMain2Service.get(JformOrderTicket2Entity.class, id);
    if (task == null) {
        return Result.error("??????ID????????????????????????????????????");
    }
    return Result.success(task);
}


@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
@ApiOperation(value = "????????????????????????")
public ResponseMessage<?> create(JformOrderTicket2Entity jformOrderTicket2,UriComponentsBuilder uriBuilder){
    // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
    Set<ConstraintViolation<JformOrderTicket2Entity>> failures = validator.validate(jformOrderTicket2);
    if (!failures.isEmpty()) {
        return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    // ??????
    try {
        jformOrderMain2Service.save(jformOrderTicket2);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("????????????????????????????????????");
    }
    return Result.success(jformOrderTicket2);
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "??????????????????????????????";
    JformOrderTicket2Entity t = jformOrderMain2Service.get(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
    try {
        MyBeanUtils.copyBeanNotNull2Bean(jformOrderTicket2, t);
        jformOrderMain2Service.saveOrUpdate(t);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "??????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "datagrid")
public void datagrid(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(JformOrderTicket2Entity.class, dataGrid);
    String mainId = request.getParameter("mainId");
    if (oConvertUtils.isNotEmpty(mainId)) {
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformOrderTicket2, request.getParameterMap());
        try {
            // ???????????????????????????
            cq.eq("fckId", mainId);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.jformOrderMain2Service.getDataGridReturn(cq, true);
    }
    TagUtil.datagrid(response, dataGrid);
}


}