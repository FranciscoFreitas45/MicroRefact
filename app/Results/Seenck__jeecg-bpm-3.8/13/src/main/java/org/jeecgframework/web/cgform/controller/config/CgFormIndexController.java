package org.jeecgframework.web.cgform.controller.config;
 import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.log4j.Logger;
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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Controller
@RequestMapping("/cgFormIndexController")
public class CgFormIndexController extends BaseController{

 private  Logger logger;

@Autowired
 private  CgFormIndexServiceI cgFormIndexService;

@Autowired
 private  SystemService systemService;

@Autowired
 private  Validator validator;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(CgFormIndexEntity cgFormIndex,HttpServletRequest req){
    if (StringUtil.isNotEmpty(cgFormIndex.getId())) {
        cgFormIndex = cgFormIndexService.getEntity(CgFormIndexEntity.class, cgFormIndex.getId());
        req.setAttribute("cgFormIndexPage", cgFormIndex);
    }
    return new ModelAndView("com/jeecg/index/cgFormIndex-update");
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(CgFormIndexEntity cgFormIndex,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "?????????????????????";
    try {
        cgFormIndexService.save(cgFormIndex);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "?????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "upload")
public ModelAndView upload(HttpServletRequest req){
    req.setAttribute("controller_name", "cgFormIndexController");
    return new ModelAndView("common/upload/pub_excel_upload");
}


@RequestMapping(params = "exportXls")
public String exportXls(CgFormIndexEntity cgFormIndex,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap){
    CriteriaQuery cq = new CriteriaQuery(CgFormIndexEntity.class, dataGrid);
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cgFormIndex, request.getParameterMap());
    List<CgFormIndexEntity> cgFormIndexs = this.cgFormIndexService.getListByCriteriaQuery(cq, false);
    modelMap.put(NormalExcelConstants.FILE_NAME, "?????????");
    modelMap.put(NormalExcelConstants.CLASS, CgFormIndexEntity.class);
    modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("???????????????", "?????????:" + ResourceUtil.getSessionUser().getRealName(), "????????????"));
    modelMap.put(NormalExcelConstants.DATA_LIST, cgFormIndexs);
    return NormalExcelConstants.JEECG_EXCEL_VIEW;
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "?????????????????????";
    try {
        for (String id : ids.split(",")) {
            CgFormIndexEntity cgFormIndex = systemService.getEntity(CgFormIndexEntity.class, id);
            cgFormIndexService.delete(cgFormIndex);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "?????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(CgFormIndexEntity cgFormIndex,HttpServletRequest req){
    if (StringUtil.isNotEmpty(cgFormIndex.getId())) {
        cgFormIndex = cgFormIndexService.getEntity(CgFormIndexEntity.class, cgFormIndex.getId());
        req.setAttribute("cgFormIndexPage", cgFormIndex);
    }
    return new ModelAndView("com/jeecg/index/cgFormIndex-add");
}


@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> update(CgFormIndexEntity cgFormIndex){
    // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
    Set<ConstraintViolation<CgFormIndexEntity>> failures = validator.validate(cgFormIndex);
    if (!failures.isEmpty()) {
        return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
    }
    // ??????
    cgFormIndexService.saveOrUpdate(cgFormIndex);
    // ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
    return new ResponseEntity(HttpStatus.NO_CONTENT);
}


@RequestMapping(method = RequestMethod.GET)
@ResponseBody
public List<CgFormIndexEntity> list(){
    List<CgFormIndexEntity> listCgFormIndexs = cgFormIndexService.getList(CgFormIndexEntity.class);
    return listCgFormIndexs;
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(String id){
    cgFormIndexService.deleteEntityById(CgFormIndexEntity.class, id);
}


@RequestMapping(params = "exportXlsByT")
public String exportXlsByT(CgFormIndexEntity cgFormIndex,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap){
    modelMap.put(NormalExcelConstants.FILE_NAME, "?????????");
    modelMap.put(NormalExcelConstants.CLASS, CgFormIndexEntity.class);
    modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("???????????????", "?????????:" + ResourceUtil.getSessionUser().getRealName(), "????????????"));
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
            List<CgFormIndexEntity> listCgFormIndexEntitys = ExcelImportUtil.importExcel(file.getInputStream(), CgFormIndexEntity.class, params);
            for (CgFormIndexEntity cgFormIndex : listCgFormIndexEntitys) {
                cgFormIndexService.save(cgFormIndex);
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


@RequestMapping(params = "getIndexList")
@ResponseBody
public List<CgFormIndexEntity> getIndexList(CgFormIndexEntity cgFormHead,String type,HttpServletRequest req){
    List<CgFormIndexEntity> columnList = new ArrayList<CgFormIndexEntity>();
    if (StringUtil.isNotEmpty(cgFormHead.getId())) {
        CriteriaQuery cq = new CriteriaQuery(CgFormIndexEntity.class);
        cq.eq("table.id", cgFormHead.getId());
        cq.add();
        columnList = cgFormIndexService.getListByCriteriaQuery(cq, false);
    // ???????????????????????????
    // Collections.sort(columnList,new FieldNumComparator());
    } else {
    // columnList=getInitDataList();
    }
    return columnList;
}


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(CgFormIndexEntity cgFormIndex,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    cgFormIndex = systemService.getEntity(CgFormIndexEntity.class, cgFormIndex.getId());
    message = "?????????????????????";
    try {
        cgFormIndexService.delete(cgFormIndex);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "?????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
public ResponseEntity<?> get(String id){
    CgFormIndexEntity task = cgFormIndexService.get(CgFormIndexEntity.class, id);
    if (task == null) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(task, HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<?> create(CgFormIndexEntity cgFormIndex,UriComponentsBuilder uriBuilder){
    // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
    Set<ConstraintViolation<CgFormIndexEntity>> failures = validator.validate(cgFormIndex);
    if (!failures.isEmpty()) {
        return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
    }
    // ??????
    cgFormIndexService.save(cgFormIndex);
    // ??????Restful???????????????????????????????????????url, ?????????????????????id?????????.
    String id = cgFormIndex.getId();
    URI uri = uriBuilder.path("/rest/cgFormIndexController/" + id).build().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uri);
    return new ResponseEntity(headers, HttpStatus.CREATED);
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(CgFormIndexEntity cgFormIndex,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "?????????????????????";
    CgFormIndexEntity t = cgFormIndexService.get(CgFormIndexEntity.class, cgFormIndex.getId());
    try {
        MyBeanUtils.copyBeanNotNull2Bean(cgFormIndex, t);
        cgFormIndexService.saveOrUpdate(t);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "?????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "datagrid")
public void datagrid(CgFormIndexEntity cgFormIndex,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(CgFormIndexEntity.class, dataGrid);
    // ?????????????????????
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cgFormIndex, request.getParameterMap());
    try {
    // ???????????????????????????
    } catch (Exception e) {
        throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.cgFormIndexService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


}