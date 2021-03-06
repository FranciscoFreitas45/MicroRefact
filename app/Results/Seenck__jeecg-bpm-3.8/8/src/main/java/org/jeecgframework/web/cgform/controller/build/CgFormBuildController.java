package org.jeecgframework.web.cgform.controller.build;
 import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.common.CommUtils;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgSubTableVO;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.cgform.util.FillRuleUtil;
import org.jeecgframework.web.cgform.util.PublicUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import DTO.CgUploadEntity;
@Controller
@RequestMapping("/cgFormBuildController")
public class CgFormBuildController extends BaseController{

 private  Logger logger;

@Autowired
 private  TempletContext templetContext;

@Autowired
 private  DataBaseService dataBaseService;

@Autowired
 private  CgformTemplateServiceI cgformTemplateService;

@Autowired
 private  CgFormFieldServiceI cgFormFieldService;


@RequestMapping(value = "ftlForm/{tableName}/goUpdate")
public void goUpdate(String tableName,HttpServletRequest request,HttpServletResponse response){
    ftlForm(tableName, "", request, response);
}


public void ftlForm(String tableName,String mode,HttpServletRequest request,HttpServletResponse response){
    try {
        long start = System.currentTimeMillis();
        // String tableName =request.getParameter("tableName");
        // update-beign-author:taoYan date:20180705 for:TASK #2894 ???bug???online?????????js??????????????????????????????
        String lang = (String) request.getSession().getAttribute("lang");
        if (oConvertUtils.isEmpty(lang)) {
            lang = "zh-cn";
        }
        // update-end-author:taoYan date:20180705 for:TASK #2894 ???bug???online?????????js??????????????????????????????
        Map<String, Object> data = new HashMap<String, Object>();
        String id = request.getParameter("id");
        // update-begin--Author:?????????  Date:20150707 for???online???????????????????????????????????????????????????????????????
        // String mode=request.getParameter("mode");
        // update-begin--Author:gengjiajia  Date:20160809 for???TASK #1214 online????????????????????????????????????,??????????????????
        String tablename = PublicUtil.replaceTableName(tableName);
        String templateName = tablename + "_";
        // String templateName=tableName+"_";
        // update-end--Author:gengjiajia  Date:20160809 for???TASK #1214 online????????????????????????????????????,??????????????????
        // update-begin--Author:scott  Date:20170804 for???online????????????????????????????????????id??????????????????--------------------
        // update-begin--Author:gj_shaojc  Date:20180404 for???TASK #2586 ??????????????????online ?????????word??????---------
        // Map<String, Object> dataForm = new HashMap<String, Object>();
        // if(StringUtils.isNotEmpty(id)){
        // //update-begin--Author:gengjiajia  Date:20160809 for???TASK #1214 online????????????????????????????????????,??????????????????
        // dataForm = dataBaseService.findOneForJdbc(tablename, id);
        // //dataForm = dataBaseService.findOneForJdbc(tableName, id);
        // //update-end--Author:gengjiajia  Date:20160809 for???TASK #1214 online????????????????????????????????????,??????????????????
        // //update-begin--Author:zhoujf  Date:20151223 for?????????--------------------
        // if(dataForm!=null){
        // Iterator it=dataForm.entrySet().iterator();
        // while(it.hasNext()){
        // Map.Entry entry=(Map.Entry)it.next();
        // String ok=(String)entry.getKey();
        // Object ov=entry.getValue();
        // data.put(ok, ov);
        // }
        // }else{
        // logger.info("online?????????"+tablename+"??????"+id+"????????????");
        // id = null;
        // dataForm = new HashMap<String, Object>();
        // }
        // //update-begin--Author:zhoujf  Date:20151223 for?????????--------------------
        // }
        // update-end--Author:gj_shaojc  Date:20180404 for???TASK #2586 ??????????????????online ?????????word??????---------
        // update-end--Author:scott  Date:20170804 for???online????????????????????????????????????id??????????????????-------------------
        // update-begin--Author:?????????  Date:20151019 for???url?????????olstylecode ???????????????
        TemplateUtil.TemplateType templateType = TemplateUtil.TemplateType.LIST;
        if (StringUtils.isBlank(id)) {
            templateName += TemplateUtil.TemplateType.ADD.getName();
            templateType = TemplateUtil.TemplateType.ADD;
        } else if ("read".equals(mode)) {
            templateName += TemplateUtil.TemplateType.DETAIL.getName();
            templateType = TemplateUtil.TemplateType.DETAIL;
        } else {
            templateName += TemplateUtil.TemplateType.UPDATE.getName();
            templateType = TemplateUtil.TemplateType.UPDATE;
        }
        // ???????????????
        String version = cgFormFieldService.getCgFormVersionByTableName(tableName);
        // ??????????????????
        Map configData = cgFormFieldService.getFtlFormConfig(tableName, version);
        data = new HashMap(configData);
        // update-begin--Author:gj_shaojc  Date:20180404 for???TASK #2586 ??????????????????online ?????????word??????---------
        Map<String, Object> dataForm = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(id)) {
            dataForm = dataBaseService.findOneForJdbc(tablename, id);
            if (dataForm != null) {
                Iterator it = dataForm.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String ok = (String) entry.getKey();
                    Object ov = entry.getValue();
                    // update-begin--Author:Yandong----  Date:20180521 ----for???TASK #2727 ???online?????????UE??????????????? ????????????----blob???????????? ??????????????????-----
                    if (ov instanceof byte[]) {
                        ov = new String((byte[]) ov, "utf-8");
                        entry.setValue(ov);
                    }
                    // update-end--Author:Yandong----  Date:20180521 ----for???TASK #2727 ???online?????????UE??????????????? ????????????----blob???????????? ??????????????????-----
                    data.put(ok, ov);
                }
            } else {
                logger.info("online?????????" + tablename + "??????" + id + "????????????");
                id = null;
                dataForm = new HashMap<String, Object>();
            }
        }
        // update-end--Author:gj_shaojc  Date:20180404 for???TASK #2586 ??????????????????online ?????????word??????---------
        // ??????????????????????????????????????????
        CgFormHeadEntity head = (CgFormHeadEntity) data.get("head");
        Map<String, Object> tableData = new HashMap<String, Object>();
        // ?????????????????????????????????
        // update-begin--Author:gengjiajia  Date:20160809 for???TASK #1214 online????????????????????????????????????,??????????????????
        // update-begin--Author:Yandong  Date:20180105 for???TASK #2469 ???online?????????????????????????????????
        if (StringUtils.isBlank(id)) {
            // update-begin--Author:scott----------Date:20180604----------for???Online???????????????select\radio\checkbox ?????????????????????--------------------
            // Online???????????????select\radio\checkbox ?????????????????????
            initAddDictTagDefaultVal((List<Map<String, Object>>) data.get("columns"), dataForm);
            // update-end--Author:scott------------Date:20180604----------for???Online???????????????select\radio\checkbox ?????????????????????--------------------
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.info("============================????????????????????????:" + sdf.format(new Date()) + "==============================");
            long startTime = System.currentTimeMillis();
            // ???????????????????????????
            putFormData((List<Map<String, Object>>) data.get("columns"), dataForm);
            putFormData((List<Map<String, Object>>) data.get("columnhidden"), dataForm);
            // ??????????????????
            String subTableStr = head.getSubTableStr();
            if (StringUtils.isNotEmpty(subTableStr)) {
                String[] subTables = subTableStr.split(",");
                Map<String, Object> subDataForm = null;
                List<Map<String, Object>> subTableData = null;
                Map<String, Object> field = (Map<String, Object>) data.get("field");
                for (String subTable : subTables) {
                    CgSubTableVO subTableVO = (CgSubTableVO) field.get(subTable);
                    subTableData = new ArrayList<Map<String, Object>>();
                    subDataForm = new HashMap<String, Object>();
                    // update-begin--Author:scott----------Date:20180604----------for???Online???????????????select\radio\checkbox ?????????????????????--------------------
                    // Online???????????????select\radio\checkbox????????? ?????????????????????
                    initAddDictTagDefaultVal((List<Map<String, Object>>) subTableVO.getFieldList(), subDataForm);
                    // update-end--Author:scott----------Date:20180604----------for???Online???????????????select\radio\checkbox ?????????????????????--------------------
                    putFormData(subTableVO.getFieldList(), subDataForm);
                    putFormData(subTableVO.getHiddenFieldList(), subDataForm);
                    subTableData.add(subDataForm);
                    tableData.put(subTable, subTableData);
                }
            }
            long endTime = System.currentTimeMillis();
            logger.info("================================????????????????????????:" + sdf.format(new Date()) + "==============================");
            logger.info("================================??????????????????:" + (endTime - startTime) + "ms==============================");
        }
        // update-end--Author:Yandong  Date:20180105 for???TASK #2469 ???online?????????????????????????????????
        tableData.put(tablename, dataForm);
        // tableData.put(tableName, dataForm);
        // update-end--Author:gengjiajia  Date:20160809 for???TASK #1214 online????????????????????????????????????,??????????????????
        // ???????????????????????????
        if (StringUtils.isNotEmpty(id)) {
            // add-begin--Author:Yandong  Date:20180521 for???TASK #2723 ???bug???online????????????????????????
            // ??????????????????value??????
            replaceExtendJson((List<Map<String, Object>>) data.get("columns"));
            replaceExtendJson((List<Map<String, Object>>) data.get("columnhidden"));
            // add-end--Author:Yandong  Date:20180521 for???TASK #2723 ???bug???online????????????????????????
            if (head.getJformType() == CgAutoListConstant.JFORM_TYPE_MAIN_TALBE) {
                String subTableStr = head.getSubTableStr();
                if (StringUtils.isNotEmpty(subTableStr)) {
                    String[] subTables = subTableStr.split(",");
                    List<Map<String, Object>> subTableData = new ArrayList<Map<String, Object>>();
                    Map<String, Object> field = (Map<String, Object>) data.get("field");
                    for (String subTable : subTables) {
                        subTableData = cgFormFieldService.getSubTableData(tableName, subTable, id);
                        tableData.put(subTable, subTableData);
                        CgSubTableVO subTableVO = (CgSubTableVO) field.get(subTable);
                        // add-begin--Author:Yandong  Date:20180521 for???TASK #2723 ???bug???online????????????????????????
                        replaceExtendJson(subTableVO.getFieldList());
                        replaceExtendJson(subTableVO.getHiddenFieldList());
                    // add-end--Author:Yandong  Date:20180521 for???TASK #2723 ???bug???online????????????????????????
                    }
                }
            }
        }
        // ?????????
        data.put("lang", lang);
        // ????????????/(???????????????)????????????
        data.put("data", tableData);
        data.put("id", id);
        // update-begin--Author:?????????  Date:20150610 for???online???????????????--------------------
        data.put("head", head);
        // update-end--Author:?????????  Date:20150610 for???online???????????????----------------------
        // ????????????js??????
        data.put(CgAutoListConstant.CONFIG_IFRAME, getHtmlHead(request));
        // ????????????????????????
        pushFiles(data, id);
        pushImages(data, id);
        // ??????basePath
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        data.put(CgAutoListConstant.BASEPATH, basePath);
        // update--begin--author:scott date:20170628 for: ?????????????????????IE???????????? ?????????????????????layer/easyui
        data.put("brower_type", ContextHolderUtils.getSession().getAttribute("brower_type"));
        // update--end--author:scott date:20170628 for: T ?????????????????????IE???????????? ?????????????????????layer/easyui
        String content = null;
        response.setContentType("text/html;charset=utf-8");
        // update-begin--Author:?????????  Date:20151019 for???url?????????olstylecode ???????????????
        String urlTemplateName = request.getParameter("olstylecode");
        // update-begin---author:scott---date:20160301-----for:online??????????????????????????????-----
        if (oConvertUtils.isEmpty(urlTemplateName)) {
            urlTemplateName = (String) request.getAttribute("olstylecode");
        }
        // update-end---author:scott---date:20160301-----for:online??????????????????????????????-----
        if (StringUtils.isNotBlank(urlTemplateName)) {
            data.put("this_olstylecode", urlTemplateName);
            LogUtil.debug("-------------urlTemplateName-----------" + urlTemplateName);
            content = getUrlTemplate(urlTemplateName, templateType, data);
        } else {
            data.put("this_olstylecode", head.getFormTemplate());
            LogUtil.debug("-------------formTemplate-----------" + head.getFormTemplate());
            content = getTableTemplate(templateName, request, data);
        }
        // update-end--Author:?????????  Date:20151019 for???url?????????olstylecode ???????????????
        response.getWriter().print(content);
        response.getWriter().flush();
        long end = System.currentTimeMillis();
        logger.debug("??????????????????????????????" + (end - start) + " ms");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public void pushImages(Map<String,Object> data,String id){
    List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
    List<Map<String, Object>> images = new ArrayList<Map<String, Object>>(0);
    for (CgUploadEntity b : uploadBeans) {
        // ?????????
        String title = b.getAttachmenttitle();
        // ????????????
        String fileKey = b.getId();
        // ????????????
        String path = b.getRealpath();
        // ????????????????????????????????????
        String field = b.getCgformField();
        Map<String, Object> image = new HashMap<String, Object>();
        image.put("title", title);
        image.put("fileKey", fileKey);
        image.put("path", path);
        image.put("field", field == null ? "" : field);
        images.add(image);
    }
    data.put("imageList", images);
}


@RequestMapping(value = "ftlForm/{tableName}/goDetail")
public void goDatilFtlForm(String tableName,HttpServletRequest request,HttpServletResponse response){
    ftlForm(tableName, "read", request, response);
}


@RequestMapping(value = "ftlForm/{tableName}/goAdd")
public void goAdd(String tableName,HttpServletRequest request,HttpServletResponse response){
    ftlForm(tableName, "", request, response);
}


@RequestMapping(params = "mobileForm")
public void mobileForm(HttpServletRequest request,HttpServletResponse response){
    String tableName = request.getParameter("tableName");
    String sql = "select form_template_mobile from cgform_head where table_name = ?";
    Map<String, Object> mp = cgFormFieldService.findOneForJdbc(sql, tableName);
    if (mp.containsKey("form_template_mobile") && oConvertUtils.isNotEmpty(mp.get("form_template_mobile"))) {
        String urlTemplateName = request.getParameter("olstylecode");
        if (oConvertUtils.isEmpty(urlTemplateName)) {
            request.setAttribute("olstylecode", mp.get("form_template_mobile").toString().trim());
        }
    }
    // update-begin--Author:zhoujf  Date:20170310 for???TASK #1736 ????????????online????????????????????????????????????????????????restful?????????????????????.do???--------------------
    ftlForm(tableName, "", request, response);
// update-end--Author:zhoujf  Date:20170310 for???TASK #1736 ????????????online????????????????????????????????????????????????restful?????????????????????.do???--------------------
}


@RequestMapping(value = "ftlForm/{tableName}/goAddButton")
public void goAddButton(String tableName,HttpServletRequest request,HttpServletResponse response){
    ftlForm(tableName, "onbutton", request, response);
}


public void putFormData(List<Map<String,Object>> list,Map<String,Object> dataForm){
    if (list != null && !list.isEmpty()) {
        for (Map<String, Object> column : list) {
            Object value = column.get("fill_rule_code");
            if (value != null && !value.equals("")) {
                dataForm.put(column.get("field_name").toString(), FillRuleUtil.executeRule(value.toString()));
            }
        }
    }
}


@SuppressWarnings("unchecked")
@RequestMapping(params = "doButton")
@ResponseBody
public AjaxJson doButton(HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    try {
        String formId = request.getParameter("formId");
        String buttonCode = request.getParameter("buttonCode");
        String tableName = request.getParameter("tableName");
        String id = request.getParameter("id");
        Map<String, Object> data = dataBaseService.findOneForJdbc(tableName, id);
        if (data != null) {
            // ????????????
            Iterator it = data.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object ok = entry.getKey();
                Object ov = entry.getValue() == null ? "" : entry.getValue();
                logger.debug("name:" + ok.toString() + ";value:" + ov.toString());
            }
            data = CommUtils.mapConvert(data);
            dataBaseService.executeSqlExtend(formId, buttonCode, data);
            // update-start--Author:luobaoli  Date:20150630 for???  ??????java??????????????????
            dataBaseService.executeJavaExtend(formId, buttonCode, data);
        // update-end--Author:luobaoli  Date:20150630 for???  ??????java??????????????????
        }
        j.setSuccess(true);
        message = "????????????";
        logger.info("[" + IpUtil.getIpAddr(request) + "][online?????????????????????action??????]" + message + "?????????" + tableName);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????";
    }
    j.setMsg(message);
    return j;
}


public void initAddDictTagDefaultVal(List<Map<String,Object>> list,Map<String,Object> dataForm){
    if (list != null && !list.isEmpty()) {
        for (Map<String, Object> column : list) {
            Object extendJson = column.get("extend_json");
            Object show_type = column.get("show_type");
            if (oConvertUtils.isNotEmpty(extendJson) && oConvertUtils.isNotEmpty(show_type) && "radio|checkbox|list".contains(show_type.toString())) {
                Pattern p = Pattern.compile("value=\"[\\S]*\" ");
                Matcher m = p.matcher(extendJson.toString());
                String dfVal = "";
                while (m.find()) {
                    dfVal = m.group();
                }
                dfVal = dfVal.replace("value=", "").replace("\"", "").trim();
                dataForm.put(column.get("field_name").toString(), dfVal);
                logger.debug("--------------online??????????????????????????????????????????----------field_name:{} ,dfVal:{} ,show_type :{}", new Object[] { column.get("field_name").toString(), dfVal, show_type.toString() });
            }
        }
    }
}


@SuppressWarnings("unchecked")
@RequestMapping(params = "saveOrUpdateMore")
@ResponseBody
public AjaxJson saveOrUpdateMore(HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    Map data = request.getParameterMap();
    if (data != null) {
        data = CommUtils.mapConvert(data);
        String tableName = (String) data.get("tableName");
        String id = (String) data.get("id");
        // ????????????
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object ok = entry.getKey();
            Object ov = entry.getValue() == null ? "" : entry.getValue();
            logger.debug("name:" + ok.toString() + ";value:" + ov.toString());
        }
        Map<String, List<Map<String, Object>>> mapMore = CommUtils.mapConvertMore(data, tableName);
        if (StringUtils.isEmpty(id)) {
            logger.info("???????????????!!!!!");
            try {
                Map<String, Object> result = dataBaseService.insertTableMore(mapMore, tableName);
                data.put("id", result.get("id"));
                j.setSuccess(true);
                message = "????????????";
            } catch (BusinessException e) {
                e.printStackTrace();
                j.setSuccess(false);
                message = e.getMessage();
            }
        } else {
            logger.info("???????????????!!!!!");
            try {
                dataBaseService.updateTableMore(mapMore, tableName);
                j.setSuccess(true);
                message = "????????????";
            } catch (BusinessException e) {
                e.printStackTrace();
                j.setSuccess(false);
                message = e.getMessage();
            }
        }
        logger.info("[" + IpUtil.getIpAddr(request) + "][online????????????????????????????????????]" + message + "?????????" + tableName);
    }
    j.setMsg(message);
    j.setObj(data);
    return j;
}


public String getUrlTemplate(String templateName,TemplateUtil.TemplateType templateType,Map dataMap){
    String content = null;
    CgformTemplateEntity entity = cgformTemplateService.findByCode(templateName);
    if (entity != null) {
        FreemarkerHelper viewEngine = new FreemarkerHelper();
        dataMap.put("DictData", ApplicationContextUtil.getContext().getBean("dictDataTag"));
        content = viewEngine.parseTemplate(TemplateUtil.getTempletPath(entity, 0, templateType), dataMap);
    }
    return content;
}


public void pushFiles(Map<String,Object> data,String id){
    List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
    List<Map<String, Object>> files = new ArrayList<Map<String, Object>>(0);
    for (CgUploadEntity b : uploadBeans) {
        // ?????????
        String title = b.getAttachmenttitle();
        // ????????????
        String fileKey = b.getId();
        // ????????????
        String path = b.getRealpath();
        // ????????????????????????????????????
        String field = b.getCgformField();
        Map<String, Object> file = new HashMap<String, Object>();
        file.put("title", title);
        file.put("fileKey", fileKey);
        file.put("path", path);
        file.put("field", field == null ? "" : field);
        files.add(file);
    }
    data.put("filesList", files);
}


public String getTableTemplate(String templateName,HttpServletRequest request,Map data){
    StringWriter stringWriter = new StringWriter();
    BufferedWriter writer = new BufferedWriter(stringWriter);
    // ??????URL?????????ftlVersion???????????????????????????word??????????????????
    String wordFtlVersion = request.getParameter("ftlVersion");
    Template template = templetContext.getTemplate(templateName, wordFtlVersion);
    try {
        // update-begin---author:scott---date:20150118-----for:??????linux????????????????????????-------------
        template.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        template.setDateFormat("yyyy-MM-dd");
        template.setTimeFormat("HH:mm:ss");
        // update-end---author:scott---date:20150118-----for:??????linux????????????????????????---------------
        template.process(data, writer);
    } catch (TemplateException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return stringWriter.toString();
}


@RequestMapping(value = "ftlForm/{tableName}/goUpdateButton")
public void goUpdateButton(String tableName,HttpServletRequest request,HttpServletResponse response){
    ftlForm(tableName, "onbutton", request, response);
}


public String getHtmlHead(HttpServletRequest request){
    HttpSession session = ContextHolderUtils.getSession();
    String lang = (String) session.getAttribute("lang");
    if (lang == null || lang.length() <= 0) {
        lang = "zh-cn";
    }
    StringBuilder sb = new StringBuilder("");
    SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery/jquery-1.8.3.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/dataformat.js\"></script>");
    sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + basePath + "/plug-in/accordion/css/accordion.css\">");
    sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum, basePath));
    // update-begin--Author:scott ---  Date:20170406 ------ for???online??????????????????????????????????????????--------
    sb.append(SysThemesUtil.getEasyUiIconTheme(sysThemesEnum));
    // sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+basePath+"/plug-in/accordion/css/icons.css\">");
    // update-end--Author:scott --- Date:20170406 ------ for???online??????????????????????????????????????????--------
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/locale/zh-cn.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/syUtil.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/My97DatePicker/WdatePicker.js\"></script>");
    // sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/common.css\" type=\"text/css\"></link>");
    // common.css
    sb.append(SysThemesUtil.getCommonTheme(sysThemesEnum, basePath));
    // sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
    sb.append(SysThemesUtil.getLhgdialogTheme(sysThemesEnum, basePath));
    // update--begin--author:scott Date:20170304 for:??????layer???????????????
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/layer/layer.js\"></script>");
    // update--end--author:scott Date:20170304 for:??????layer???????????????
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/curdtools.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/easyuiextend.js\"></script>");
    sb.append(SysThemesUtil.getEasyUiMainTheme(sysThemesEnum, basePath));
    sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/Map.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/js/datatype_zh-cn.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
    // style.css
    sb.append(SysThemesUtil.getValidformStyleTheme(sysThemesEnum, basePath));
    // tablefrom.css
    sb.append(SysThemesUtil.getValidformTablefrom(sysThemesEnum, basePath));
    // update--begin-------author:zhoujf------date:20170316----for:TASK #1770 ????????????????????????umeditor
    // uedit
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ueditor/ueditor.config.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ueditor/ueditor.all.js\"></script>");
    // update--end-------author:zhoujf------date:20170316----for:TASK #1770 ????????????????????????umeditor
    return sb.toString();
}


public void replaceExtendJson(List<Map<String,Object>> list){
    if (list != null && !list.isEmpty()) {
        for (Map<String, Object> column : list) {
            Object extendJson = column.get("extend_json");
            if (extendJson != null && !extendJson.equals("")) {
                String reg = "value=\"[\\S]*\" ";
                column.put("extend_json", extendJson.toString().replaceAll(reg, ""));
            }
        }
    }
}


@SuppressWarnings("unchecked")
@RequestMapping(params = "saveOrUpdate")
@ResponseBody
public AjaxJson saveOrUpdate(HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    Map data = request.getParameterMap();
    if (data != null) {
        data = CommUtils.mapConvert(data);
        String tableName = (String) data.get("tableName");
        String id = (String) data.get("id");
        // ????????????
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object ok = entry.getKey();
            Object ov = entry.getValue() == null ? "" : entry.getValue();
            logger.debug("name:" + ok.toString() + ";value:" + ov.toString());
        }
        if (StringUtils.isEmpty(id)) {
            // ????????????????????????
            String[] filterName = { "tableName", "saveOrUpdate" };
            data = CommUtils.attributeMapFilter(data, filterName);
            // ???????????????
            try {
                Object pkValue = null;
                pkValue = dataBaseService.getPkValue(tableName);
                data.put("id", pkValue);
                // --author???luobaoli---------date:20150615--------for: ??????service??????????????????
                try {
                    dataBaseService.insertTable(tableName, data);
                    j.setSuccess(true);
                    message = "????????????";
                } catch (Exception e) {
                    j.setSuccess(false);
                    message = "????????????";
                    e.printStackTrace();
                }
            // --author???luobaoli---------date:20150615--------for: ??????service??????????????????
            } catch (Exception e) {
                e.printStackTrace();
                j.setSuccess(false);
                message = e.getMessage();
            }
        } else {
            // ????????????????????????
            String[] filterName = { "tableName", "saveOrUpdate", "id" };
            data = CommUtils.attributeMapFilter(data, filterName);
            // ???????????????
            try {
                int num = dataBaseService.updateTable(tableName, id, data);
                if (num > 0) {
                    j.setSuccess(true);
                    message = "????????????";
                } else {
                    j.setSuccess(false);
                    message = "????????????";
                }
            } catch (Exception e) {
                e.printStackTrace();
                j.setSuccess(false);
                message = e.getMessage();
            }
            logger.info("[" + IpUtil.getIpAddr(request) + "][online?????????????????????????????????]" + message + "?????????" + tableName);
        }
    }
    j.setMsg(message);
    j.setObj(data);
    return j;
}


}