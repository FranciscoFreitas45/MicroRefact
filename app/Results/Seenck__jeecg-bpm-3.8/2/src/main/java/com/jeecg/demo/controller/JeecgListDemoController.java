package com.jeecg.demo.controller;
 import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.fop.svg.PDFTranscoder;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.Highchart;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.JeecgDataAutorUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.controller.core.LoginController;
import org.jeecgframework.web.system.enums.InterfaceEnum;
import org.jeecgframework.web.system.pojo.base.InterfaceRuleDto;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSLog;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.util.InterfaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import com.alibaba.fastjson.JSONArray;
import com.jeecg.demo.dao.JeecgMinidaoDao;
import com.jeecg.demo.entity.JeecgDemoEntity;
import com.jeecg.demo.entity.JeecgDemoPage;
import com.jeecg.demo.entity.JeecgLogReport;
import com.jeecg.demo.service.JeecgDemoServiceI;
import Interface.SystemService;
import Interface.MutiLangServiceI;
import DTO.DataGrid;
import DTO.AjaxJson;
import DTO.CriteriaQuery;
@Controller
@RequestMapping("/jeecgListDemoController")
@Api(value = "JeecgDemo", description = "Angular JeecgDemo??????", tags = "AngularJeecgDemoAPI")
public class JeecgListDemoController extends BaseController{

 private  Logger logger;

 private  String BROSWER_COUNT_ANALYSIS;

@Autowired
 private  JeecgDemoServiceI jeecgDemoService;

@Autowired
 private  SystemService systemService;

@Autowired
 private  Validator validator;

@Autowired
 private  JeecgMinidaoDao jeecgMinidaoDao;

@Autowired
 private  MutiLangServiceI mutiLangService;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-update");
}


@RequestMapping(params = "querysBuilder")
public ModelAndView querysBuilder(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/querysBuilderDemo");
}


@RequestMapping(params = "addTab")
public ModelAndView addTab(HttpServletRequest request){
    String type = oConvertUtils.getString(request.getParameter("type"));
    return new ModelAndView("com/jeecg/demo/demoTab");
}


@RequestMapping(params = "minidaoDatagrid")
public void minidaoDatagrid(JeecgDemoEntity jeecgDemo,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    /**
     * ?????????minidao?????????springjdbc???????????????????????????????????????????????????????????????????????????
     * ???????????????????????????{user_name}
     * ???????????????????????????{userName}
     */
    // step.1 ??????????????????SQL??????
    String authSql = JeecgDataAutorUtils.loadDataSearchConditonSQLString();
    // update-begin--Author:liushaoqian Date:20180718 for???TASK #2975 ??????????????????minidao ???????????????????????????demo
    // ??????????????????
    // step.2 ?????????SQL?????????????????????SQL???
    MiniDaoPage<JeecgDemoEntity> list = jeecgMinidaoDao.getAllEntities(jeecgDemo, dataGrid.getPage(), dataGrid.getRows(), dataGrid.getSort(), dataGrid.getOrder(), authSql);
    // update-end--Author:liushaoqian Date:20180718 for???TASK #2975 ??????????????????minidao ???????????????????????????demo
    dataGrid.setTotal(list.getTotal());
    dataGrid.setResults(list.getResults());
    // step.3 ?????????????????? ?????????:???(????????????????????????????????????????????????) ???????????? ??? , ??????
    String total_salary = String.valueOf(jeecgMinidaoDao.getSumSalary());
    dataGrid.setFooter("salary:" + (total_salary.equalsIgnoreCase("null") ? "0.0" : total_salary) + ",age,email:??????");
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "goBootStrapTableAdd2")
public ModelAndView goBootStrapTableAdd2(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-add2");
}


@RequestMapping(params = "saveRows")
@ResponseBody
public AjaxJson saveRows(JeecgDemoPage page){
    String message = null;
    List<JeecgDemoEntity> demos = page.getDemos();
    AjaxJson j = new AjaxJson();
    if (CollectionUtils.isNotEmpty(demos)) {
        for (JeecgDemoEntity jeecgDemo : demos) {
            if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
                JeecgDemoEntity t = jeecgDemoService.get(JeecgDemoEntity.class, jeecgDemo.getId());
                try {
                    message = "JeecgDemo??????: " + jeecgDemo.getName() + "???????????????";
                    MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
                    jeecgDemoService.saveOrUpdate(t);
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                } catch (Exception e) {
                    message = "JeecgDemo??????: " + jeecgDemo.getName() + "????????????!!";
                    e.printStackTrace();
                }
            } else {
                try {
                    message = "JeecgDemo??????: " + jeecgDemo.getName() + "???????????????";
                    // jeecgDemo.setStatus("0");
                    jeecgDemoService.save(jeecgDemo);
                    systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
                } catch (Exception e) {
                    message = "JeecgDemo??????: " + jeecgDemo.getName() + "????????????!!";
                    e.printStackTrace();
                }
            }
        }
    }
    return j;
}


@RequestMapping(params = "upload")
public ModelAndView upload(HttpServletRequest req){
    req.setAttribute("controller_name", "jeecgListDemoController");
    return new ModelAndView("common/upload/pub_excel_upload");
}


@RequestMapping(params = "exportXls")
public String exportXls(JeecgDemoEntity jeecgDemo,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap){
    CriteriaQuery cq = new CriteriaQuery(JeecgDemoEntity.class, dataGrid);
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo, request.getParameterMap());
    List<JeecgDemoEntity> jeecgDemos = this.jeecgDemoService.getListByCriteriaQuery(cq, false);
    modelMap.put(NormalExcelConstants.FILE_NAME, "jeecg_demo");
    modelMap.put(NormalExcelConstants.CLASS, JeecgDemoEntity.class);
    modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("jeecg_demo??????", "?????????:" + ResourceUtil.getSessionUser().getRealName(), "????????????"));
    modelMap.put(NormalExcelConstants.DATA_LIST, jeecgDemos);
    return NormalExcelConstants.JEECG_EXCEL_VIEW;
}


@RequestMapping(params = "doCheck")
@ResponseBody
public AjaxJson doCheck(String content,String id,String status){
    // demo???????????????,???????????????????????????
    logger.info("-------????????????:" + content);
    String message = null;
    AjaxJson j = new AjaxJson();
    JeecgDemoEntity jeecgDemo = systemService.getEntity(JeecgDemoEntity.class, id);
    message = "????????????";
    try {
        jeecgDemo.setStatus(status);
        this.jeecgDemoService.updateEntitie(jeecgDemo);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goBootStrapTableUpdate")
public ModelAndView goBootStrapTableUpdate(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-update");
}


@RequestMapping(params = "vueBootstrapTableList")
public ModelAndView vueBootstrapTableList(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/vueBootstrapTableList");
}


@RequestMapping(params = "goBootStrapTableUpdate2")
public ModelAndView goBootStrapTableUpdate2(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-update2");
}


@RequestMapping(params = "listAllStatisticByJdbc")
public void listAllStatisticByJdbc(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    List<Map<String, Object>> maplist = systemService.findForJdbc("select l.broswer broswer ,count(broswer) broswercount from t_s_log l group by l.broswer", null);
    Long countSutent = systemService.getCountForJdbc("select count(*) from t_s_log where 1=1");
    for (Map map : maplist) {
        Long personcount = Long.parseLong(map.get("broswercount").toString());
        Double percentage = 0.0;
        if (personcount != null && personcount.intValue() != 0) {
            percentage = new Double(personcount) / countSutent;
        }
        map.put("rate", String.format("%.2f", percentage * 100) + "%");
    }
    Long count = 0L;
    if (JdbcDao.DATABSE_TYPE_SQLSERVER.equals(DBTypeUtil.getDBType())) {
        count = systemService.getCountForJdbcParam("select count(0) from (select l." + "  broswer ,count(broswer) broswercount from t_s_log  l group by l.broswer) as t( broswer, broswercount)", null);
    } else {
        count = systemService.getCountForJdbcParam("select count(0) from (select l.broswer broswer ,count(broswer) broswercount from t_s_log l group by l.broswer)t", null);
    }
    dataGrid.setTotal(count.intValue());
    dataGrid.setResults(maplist);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "PointChart1")
public ModelAndView PointChart1(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/PointChart1");
}


@RequestMapping(params = "mysearchListDemo")
public ModelAndView mysearchListDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/taglist_mysearch");
}


@RequestMapping(params = "goBuilderDemo")
public ModelAndView goBuilderDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/superQueryDemo");
}


@RequestMapping(params = "PointChart3")
public ModelAndView PointChart3(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/PointChart3");
}


@RequestMapping(params = "PointChart2")
public ModelAndView PointChart2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/PointChart2");
}


@RequestMapping(params = "getTotalReport")
@ResponseBody
public String getTotalReport(HttpServletRequest request){
    List<Map<String, Object>> maplist = systemService.findForJdbc("select l.broswer broswer ,count(broswer) broswercount from t_s_log l group by l.broswer", null);
    Long countSutent = systemService.getCountForJdbc("select count(*) from t_s_log where 1=1");
    for (Map map : maplist) {
        Long personcount = Long.parseLong(map.get("broswercount").toString());
        Double percentage = 0.0;
        if (personcount != null && personcount.intValue() != 0) {
            percentage = new Double(personcount) / countSutent;
        }
        map.put("rate", String.format("%.2f", percentage * 100) + "%");
    }
    Long count = 0L;
    if (JdbcDao.DATABSE_TYPE_SQLSERVER.equals(DBTypeUtil.getDBType())) {
        count = systemService.getCountForJdbcParam("select count(0) from (select l." + "  broswer ,count(broswer) broswercount from t_s_log  l group by l.broswer) as t( broswer, broswercount)", null);
    } else {
        count = systemService.getCountForJdbcParam("select count(0) from (select l.broswer broswer ,count(broswer) broswercount from t_s_log l group by l.broswer)t", null);
    }
    StringBuffer strb = new StringBuffer();
    strb.append("{\"title\": {\"text\": \"???????????????????????????\",\"subtext\": \"????????????\"},\"toolbox\": {\"show\": true,\"feature\": {\"restore\": {\"show\": true,\"title\": \"??????\"},\"saveAsImage\": {\"show\": true,\"title\": \"???????????????\",\"type\": \"png\",\"lang\": [\"????????????\"]},}},\"series\": [{\"data\": [");
    for (Map map : maplist) {
        strb.append("{\"value\": \" " + map.get("broswercount") + "\",\"name\": \"" + map.get("broswer") + "\"},");
    }
    strb.append("],\"type\": \"pie\"}]}");
    String option = strb.toString();
    return option;
}


@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
@ApiOperation(value = "??????jeecgDemo")
public ResponseMessage<?> create(JeecgDemoEntity jeecgDemo,UriComponentsBuilder uriBuilder,HttpServletRequest request){
    InterfaceRuleDto interfaceRuleDto = InterfaceUtil.getInterfaceRuleDto(request, InterfaceEnum.jeecgdemo_add);
    if (interfaceRuleDto == null) {
        return Result.error("??????????????????????????????");
    }
    logger.info("create[{}]", GsonUtil.toJson(jeecgDemo));
    // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
    Set<ConstraintViolation<JeecgDemoEntity>> failures = validator.validate(jeecgDemo);
    if (!failures.isEmpty()) {
        return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    // ??????
    try {
        jeecgDemo.setCreateDate(new Date());
        this.jeecgDemoService.save(jeecgDemo);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("jeecgDemo??????????????????");
    }
    return Result.success(jeecgDemo);
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(JeecgDemoEntity jeecgDemo,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "????????????";
    JeecgDemoEntity t = jeecgDemoService.get(JeecgDemoEntity.class, jeecgDemo.getId());
    try {
        MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
        jeecgDemoService.saveOrUpdate(t);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "rowListDemo")
public ModelAndView rowListDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/list_rowedtior");
}


@RequestMapping(params = "export")
public void export(HttpServletRequest request,HttpServletResponse response){
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String type = request.getParameter("type");
    String svg = request.getParameter("svg");
    String filename = request.getParameter("filename");
    filename = filename == null ? "chart" : filename;
    ServletOutputStream out = response.getOutputStream();
    try {
        if (null != type && null != svg) {
            svg = svg.replaceAll(":rect", "rect");
            String ext = "";
            Transcoder t = null;
            if (type.equals("image/png")) {
                ext = "png";
                t = new PNGTranscoder();
            } else if (type.equals("image/jpeg")) {
                ext = "jpg";
                t = new JPEGTranscoder();
            } else if (type.equals("application/pdf")) {
                ext = "pdf";
                t = (Transcoder) new PDFTranscoder();
            } else if (type.equals("image/svg+xml"))
                ext = "svg";
            response.addHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1") + "." + ext);
            response.addHeader("Content-Type", type);
            if (null != t) {
                TranscoderInput input = new TranscoderInput(new StringReader(svg));
                TranscoderOutput output = new TranscoderOutput(out);
                try {
                    t.transcode(input, output);
                } catch (TranscoderException e) {
                    out.print("Problem transcoding stream. See the web logs for more details.");
                    e.printStackTrace();
                }
            } else if (ext.equals("svg")) {
                // out.print(svg);
                OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
                writer.append(svg);
                writer.close();
            } else
                out.print("Invalid type: " + type);
        } else {
            response.addHeader("Content-Type", "text/html");
            out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted." + "\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
        }
    } finally {
        if (out != null) {
            out.flush();
            out.close();
        }
    }
}


@RequestMapping(params = "natureAceTableDemo")
public ModelAndView natureAceTableDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/jeecgDemo-nature-ace-list");
}


@RequestMapping(params = "collapseDemo")
public ModelAndView collapseDemo(){
    return new ModelAndView("com/jeecg/demo/jeecgDemoList-collapse");
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(JeecgDemoEntity jeecgDemo,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "????????????";
    try {
        jeecgDemoService.save(jeecgDemo);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "RectangularGraph")
public ModelAndView RectangularGraph(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/RectangularGraph");
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "????????????";
    try {
        for (String id : ids.split(",")) {
            JeecgDemoEntity jeecgDemo = systemService.getEntity(JeecgDemoEntity.class, id);
            jeecgDemoService.delete(jeecgDemo);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
@ResponseBody
@ApiOperation(value = "jeecgDemo????????????", produces = "application/json", httpMethod = "GET")
public ResponseMessage<Map<String,Object>> list(int pageNo,int pageSize,JeecgDemoEntity entity,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    InterfaceRuleDto interfaceRuleDto = InterfaceUtil.getInterfaceRuleDto(request, InterfaceEnum.jeecgdemo_list);
    if (interfaceRuleDto == null) {
        return Result.error("??????????????????????????????");
    }
    CriteriaQuery query = new CriteriaQuery(JeecgDemoEntity.class, dataGrid);
    InterfaceUtil.installCriteriaQuery(query, interfaceRuleDto, InterfaceEnum.jeecgdemo_list);
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(query, entity, request.getParameterMap());
    query.setCurPage(pageNo <= 0 ? 1 : pageNo);
    query.setPageSize(pageSize);
    query.addOrder("createDate", SortDirection.desc);
    query.add();
    this.jeecgDemoService.getDataGridReturn(query, true);
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("data", dataGrid.getResults());
    resultMap.put("total", dataGrid.getTotal());
    return Result.success(resultMap);
}


@RequestMapping(params = "jdbcBatchSave")
@ResponseBody
public AjaxJson jdbcBatchSave(HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    String message = "springjdbc ?????????????????????????????????";
    try {
        jeecgDemoService.jdbcBatchSave();
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "springjdbc ?????????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    logger.info(message);
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "echartDemo")
public ModelAndView echartDemo(HttpServletRequest req){
    return new ModelAndView("com/jeecg/demo/echartsDemo");
}


@RequestMapping(params = "exportXlsByT")
public String exportXlsByT(JeecgDemoEntity jeecgDemo,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap){
    modelMap.put(NormalExcelConstants.FILE_NAME, "jeecg_demo");
    modelMap.put(NormalExcelConstants.CLASS, JeecgDemoEntity.class);
    modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("jeecg_demo??????", "?????????:" + ResourceUtil.getSessionUser().getRealName(), "????????????"));
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
            List<JeecgDemoEntity> listJeecgDemoEntitys = ExcelImportUtil.importExcel(file.getInputStream(), JeecgDemoEntity.class, params);
            for (JeecgDemoEntity jeecgDemo : listJeecgDemoEntitys) {
                jeecgDemoService.save(jeecgDemo);
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


@RequestMapping(params = "goFormQuerysBuilder")
public ModelAndView goFormQuerysBuilder(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/form_querysBuilder");
}


@RequestMapping(params = "bootStrapEchartsDemo")
public ModelAndView bootStrapEchartsDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/bootstrap-echarts");
}


@RequestMapping(params = "bootstrapTableTagDemo")
public ModelAndView bootstrapTableTagDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-list-tag");
}


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(JeecgDemoEntity jeecgDemo,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    jeecgDemo = systemService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
    message = "????????????";
    try {
        jeecgDemoService.delete(jeecgDemo);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "logDatagrid")
public void logDatagrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSLog.class, dataGrid);
    // ????????????????????????
    String loglevel = request.getParameter("loglevel");
    if (loglevel != null && !"0".equals(loglevel)) {
        cq.eq("loglevel", org.jeecgframework.core.util.oConvertUtils.getShort(loglevel));
        cq.add();
    }
    // ????????????????????????
    String operatetime_begin = request.getParameter("operatetime_begin");
    String operatetime_end = request.getParameter("operatetime_end");
    if (oConvertUtils.isNotEmpty(operatetime_begin)) {
        try {
            cq.ge("operatetime", DateUtils.parseDate(operatetime_begin, "yyyy-MM-dd hh:mm:ss"));
        } catch (ParseException e) {
            logger.error(e.toString());
        }
        cq.add();
    }
    if (oConvertUtils.isNotEmpty(operatetime_end)) {
        try {
            cq.le("operatetime", DateUtils.parseDate(operatetime_end, "yyyy-MM-dd hh:mm:ss"));
        } catch (ParseException e) {
            logger.error(e.toString());
        }
        cq.add();
    }
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "vueBootstrapTableEdit")
public ModelAndView vueBootstrapTableEdit(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/vueBootstrapTableEdit");
}


@RequestMapping(params = "goChart")
public ModelAndView goChart(HttpServletRequest req,JeecgLogReport log){
    List<Map<String, Object>> list = this.jeecgMinidaoDao.getLogChartData(log);
    net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(list);
    req.setAttribute("logs", arr);
    return new ModelAndView("com/jeecg/demo/logrp-chart");
}


@RequestMapping(value = "loadSuggestData")
@ResponseBody
public Object loadSuggestData(String keyword,HttpServletRequest request){
    String sql = "select a.username,a.realname,IFNULL(c.departname,'?????????') as depart from t_s_base_user a left join t_s_user_org b on b.user_id  = a.ID left join t_s_depart c on c.id = b.org_id " + // TODO keyword ?????????
    "";
    JSONObject object = new JSONObject();
    object.put("message", "");
    try {
        List<Map<String, Object>> data = this.systemService.findForJdbc(sql);
        net.sf.json.JSONArray array = net.sf.json.JSONArray.fromObject(data);
        object.put("value", array);
        object.put("code", 200);
    } catch (Exception e) {
        e.printStackTrace();
    }
    object.put("redirect", "");
    return object;
}


@RequestMapping(params = "vueList")
public ModelAndView vueList(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/vueList");
}


@RequestMapping(params = "vueBootstrapTableAdd")
public ModelAndView vueBootstrapTableAdd(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/vueBootstrapTableAdd");
}


@RequestMapping(params = "broswerCount")
@ResponseBody
public List<Highchart> studentCount(HttpServletRequest request,String reportType,HttpServletResponse response){
    List<Highchart> list = new ArrayList<Highchart>();
    Highchart hc = new Highchart();
    StringBuffer sb = new StringBuffer();
    sb.append("SELECT broswer as className ,count(broswer)  FROM TSLog group by broswer");
    List userBroswerList = systemService.findByQueryString(sb.toString());
    Long count = systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_Log WHERE 1=1");
    List lt = new ArrayList();
    hc = new Highchart();
    hc.setName(mutiLangService.getLang(BROSWER_COUNT_ANALYSIS));
    hc.setType(reportType);
    Map<String, Object> map;
    if (userBroswerList.size() > 0) {
        for (Object object : userBroswerList) {
            map = new HashMap<String, Object>();
            Object[] obj = (Object[]) object;
            map.put("name", obj[0]);
            map.put("y", obj[1]);
            Long groupCount = (Long) obj[1];
            Double percentage = 0.0;
            if (count != null && count.intValue() != 0) {
                percentage = new Double(groupCount) / count;
            }
            map.put("percentage", percentage * 100);
            lt.add(map);
        }
    }
    hc.setData(lt);
    list.add(hc);
    return list;
}


@RequestMapping(params = "log")
public ModelAndView log(){
    return new ModelAndView("com/jeecg/demo/logList");
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-add");
}


@RequestMapping(params = "funnelPlot2")
public ModelAndView funnelPlot2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/funnelPlot2");
}


@RequestMapping(params = "funnelPlot1")
public ModelAndView funnelPlot1(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/funnelPlot1");
}


@RequestMapping(params = "goOnlyData")
public ModelAndView goOnlyData(HttpServletRequest req,JeecgLogReport log){
    return new ModelAndView("com/jeecg/demo/logrp-onlyData");
}


@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
@ApiOperation(value = "??????jeecgDemo", notes = "??????jeecgDemo")
public ResponseMessage<?> update(JeecgDemoEntity jeecgDemo,HttpServletRequest request){
    InterfaceRuleDto interfaceRuleDto = InterfaceUtil.getInterfaceRuleDto(request, InterfaceEnum.jeecgdemo_edit);
    if (interfaceRuleDto == null) {
        return Result.error("??????????????????????????????");
    }
    logger.info("update[{}]", GsonUtil.toJson(jeecgDemo));
    // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
    Set<ConstraintViolation<JeecgDemoEntity>> failures = validator.validate(jeecgDemo);
    if (!failures.isEmpty()) {
        return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    // ??????
    try {
        this.jeecgDemoService.saveOrUpdate(jeecgDemo);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("??????jeecgDemo????????????");
    }
    // ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
    return Result.success("??????jeecgDemo????????????");
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ApiOperation(value = "??????jeecgDemo")
@ResponseBody
public ResponseMessage<?> delete(String id,HttpServletRequest request){
    InterfaceRuleDto interfaceRuleDto = InterfaceUtil.getInterfaceRuleDto(request, InterfaceEnum.jeecgdemo_delete);
    if (interfaceRuleDto == null) {
        return Result.error("??????????????????????????????");
    }
    logger.info("delete[{}]", id);
    // ??????
    if (StringUtils.isEmpty(id)) {
        return Result.error("ID????????????");
    }
    try {
        this.jeecgDemoService.deleteEntityById(JeecgDemoEntity.class, id);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("jeecgDemo????????????");
    }
    return Result.success();
}


@RequestMapping(params = "vueNewList")
public ModelAndView vueNewList(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/vueNewList");
}


@RequestMapping(params = "PieChart1")
public ModelAndView PieChart1(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/PieChart1");
}


@RequestMapping(params = "goBootStrapTableAdd")
public ModelAndView goBootStrapTableAdd(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-add");
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
@ApiOperation(value = "??????ID??????jeecgDemo??????", notes = "??????ID??????jeecgDemo??????", httpMethod = "GET", produces = "application/json")
public ResponseMessage<?> get(String id,HttpServletRequest request){
    InterfaceRuleDto interfaceRuleDto = InterfaceUtil.getInterfaceRuleDto(request, InterfaceEnum.jeecgdemo_get);
    if (interfaceRuleDto == null) {
        return Result.error("??????????????????????????????");
    }
    JeecgDemoEntity task = this.jeecgDemoService.get(JeecgDemoEntity.class, id);
    if (task == null) {
        return Result.error("??????ID??????jeecgDemo????????????");
    }
    return Result.success(task);
}


@RequestMapping(params = "datagrid")
public void datagrid(JeecgDemoEntity jeecgDemo,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(JeecgDemoEntity.class, dataGrid);
    // ?????????????????????
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo, request.getParameterMap());
    try {
    // ???????????????????????????
    } catch (Exception e) {
        throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.jeecgDemoService.getDataGridReturn(cq, true);
    // String total_salary = String.valueOf(jeecgMinidaoDao.getSumSalary());
    /*
		 * ?????????????????? ?????????:???(????????????????????????????????????????????????) ???????????? ??? , ??????
		 */
    // dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:??????");
    List<JeecgDemoEntity> list = dataGrid.getResults();
    Map<String, Map<String, Object>> extMap = new HashMap<String, Map<String, Object>>();
    for (JeecgDemoEntity temp : list) {
        // ???????????????????????????????????????????????????
        Map m = new HashMap();
        m.put("extField", this.jeecgMinidaoDao.getOrgCode(temp.getDepId()));
        extMap.put(temp.getId(), m);
    }
    // dataGrid.setFooter("salary,age,name:??????");
    dataGrid.setFooter("[{'salary':'','age':'','name':'??????'}]");
    TagUtil.datagrid(response, dataGrid, extMap);
}


@RequestMapping(params = "jdbcProcedure")
@ResponseBody
public AjaxJson jdbcProcedure(HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    String message = "jdbc????????????????????????";
    try {
        jeecgDemoService.jdbcProcedure();
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        e.printStackTrace();
        message = "jdbc????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "broswerStatisticTabs")
public ModelAndView broswerStatisticTabs(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/reportDemo");
}


@RequestMapping(params = "other1")
public ModelAndView other1(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/other1");
}


@RequestMapping(params = "multiHeaList")
public ModelAndView multiHeaList(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/jeecgDemoList-multihead");
}


@RequestMapping(params = "goNatureAceTableUpdate")
public ModelAndView goNatureAceTableUpdate(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-nature-ace-update");
}


@RequestMapping(params = "BarGraph4")
public ModelAndView BarGraph4(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/BarGraph4");
}


@RequestMapping(params = "BarGraph3")
public ModelAndView BarGraph3(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/BarGraph3");
}


@RequestMapping(params = "InstrumentDiagram")
public ModelAndView InstrumenDtiagram(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/InstrumentDiagram");
}


@RequestMapping(params = "goNatureAceTableAdd")
public ModelAndView goNatureAceTableAdd(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-nature-ace-add");
}


@RequestMapping(params = "mysearchListDemo2")
public ModelAndView mysearchListDemo2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/taglist_mysearch2");
}


@RequestMapping(params = "suggest")
public ModelAndView suggest(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/suggest");
}


@RequestMapping(params = "goCheck")
public ModelAndView goCheck(HttpServletRequest request){
    logger.info("----??????-----");
    String id = request.getParameter("id");
    if (StringUtil.isNotEmpty(id)) {
        JeecgDemoEntity jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, id);
        request.setAttribute("jeecgDemoPage", jeecgDemo);
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-check");
}


@RequestMapping(params = "BarGraph2")
public ModelAndView BarGraph2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/BarGraph2");
}


@RequestMapping(params = "logrpDatagrid")
public void logrpDatagrid(HttpServletResponse response,JeecgLogReport log,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(JeecgLogReport.class, dataGrid);
    List<JeecgLogReport> list = this.jeecgMinidaoDao.getLogReportData(log);
    dataGrid.setResults(list);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "BarGraph1")
public ModelAndView BarGraph1(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/BarGraph1");
}


@RequestMapping(params = "PieChart2")
public ModelAndView PieChart2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/PieChart2");
}


@RequestMapping(params = "TotalReport")
public ModelAndView TotalReport(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/TotalReport");
}


@RequestMapping(params = "print")
public ModelAndView print(JeecgDemoEntity jeecgDemo,HttpServletRequest req){
    // ??????????????????
    List<TSDepart> departList = systemService.getList(TSDepart.class);
    req.setAttribute("departList", departList);
    if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
        jeecgDemo = jeecgDemoService.getEntity(JeecgDemoEntity.class, jeecgDemo.getId());
        req.setAttribute("jgDemo", jeecgDemo);
        if ("0".equals(jeecgDemo.getSex()))
            req.setAttribute("sex", "???");
        if ("1".equals(jeecgDemo.getSex()))
            req.setAttribute("sex", "???");
    }
    return new ModelAndView("com/jeecg/demo/jeecgDemo-print");
}


@RequestMapping(params = "vueBootstrapTableGet")
@ResponseBody
public AjaxJson vueBootstrapTableGet(String id,HttpServletRequest request){
    AjaxJson json = new AjaxJson();
    if (org.apache.commons.lang.StringUtils.isNotBlank(id)) {
        JeecgDemoEntity t = jeecgDemoService.get(JeecgDemoEntity.class, id);
        json.setObj(t);
    }
    json.setMsg("???????????????");
    return json;
}


@RequestMapping(params = "lineChart5")
public ModelAndView lineChart5(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/lineChart5");
}


@RequestMapping(params = "lineChart4")
public ModelAndView lineChart4(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/lineChart4");
}


@RequestMapping(params = "lineChart3")
public ModelAndView lineChart3(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/lineChart3");
}


@RequestMapping(params = "addWithbtn")
public ModelAndView addWithbtn(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/jeecgDemo-add-btn");
}


@RequestMapping(params = "minidaoListDemo")
public ModelAndView minidaoListDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/taglist_minidao");
}


@RequestMapping(params = "Schedule")
public ModelAndView Schedule(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/Schedule");
}


@RequestMapping(params = "lineChart2")
public ModelAndView lineChart2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/lineChart2");
}


@RequestMapping(params = "lineChart1")
public ModelAndView lineChart1(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/echartsDemo/lineChart1");
}


@RequestMapping(params = "bootTableDemo")
public ModelAndView bootTableDemo(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-list");
}


@RequestMapping(params = "bootstrapTableTagDemo2")
public ModelAndView bootstrapTableTagDemo2(HttpServletRequest request){
    return new ModelAndView("com/jeecg/demo/jeecgDemo-bootstrap-list-tag2");
}


}