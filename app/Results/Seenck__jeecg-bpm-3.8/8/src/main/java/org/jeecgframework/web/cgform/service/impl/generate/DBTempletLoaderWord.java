package org.jeecgframework.web.cgform.service.impl.generate;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import freemarker.cache.TemplateLoader;
@Component("templetLoaderWord")
public class DBTempletLoaderWord implements TemplateLoader{

 public  String TEMPLET;

 public  String TEMPLET_ONE_MANY;

 private  String regEx_attr;

@Autowired
 private  CgformFtlServiceI cgformFtlService;

@Autowired
 private  CgFormFieldServiceI cgFormFieldService;

@Autowired
 private  CgformTemplateServiceI cgformTemplateService;


public String getHiddenForm(List<CgFormFieldEntity> hiddenFielList){
    StringBuffer html = new StringBuffer("");
    if (hiddenFielList != null && hiddenFielList.size() > 0) {
        for (CgFormFieldEntity cgFormFieldEntity : hiddenFielList) {
            // update--begin-------author:zhoujf------date:20180614----for:TASK #2787 ???online ??????????????????????????????word ????????????---------------
            String fieldName = oConvertUtils.camelName(cgFormFieldEntity.getFieldName());
            html.append("<input type=\"hidden\" ");
            html.append("id=\"").append(fieldName).append("\" ");
            html.append("name=\"").append(fieldName).append("\" ");
            html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(fieldName).append("}\" ");
            html.append("\\/>\r\n");
        // update--end-------author:zhoujf------date:20180614----for:TASK #2787 ???online ??????????????????????????????word ????????????---------------
        }
    }
    return html.toString();
}


public String replaceAddJSP(String cgformJspHtml){
    String key, realKey;
    while (cgformJspHtml.indexOf("#{") > 0) {
        key = cgformJspHtml.substring(cgformJspHtml.indexOf("#{"), cgformJspHtml.indexOf("}", cgformJspHtml.indexOf("#{")) + 1);
        realKey = key.substring(2, key.length() - 1);
        // if(!"jform_hidden_field".equals(realKey)){
        cgformJspHtml = cgformJspHtml.replace(key, "<input id=\"" + JeecgReadTable.formatField(realKey) + "\" name=\"" + JeecgReadTable.formatField(realKey) + "\" type=\"text\" value=\"@@@{@@{entityName?uncap_first}." + JeecgReadTable.formatField(realKey) + "}\" style=\"width: 150px\" class=\"inputxt\" >");
    // }
    }
    return cgformJspHtml;
}


public Object getObject(String name){
    // update-start--Author:zhangguoming  Date:20140922 for?????????ftlVersion??????????????????
    String ftlVersion = "";
    String ftlVersionParam = "&ftlVersion=";
    if (name.contains(ftlVersionParam)) {
        ftlVersion = name.substring(name.indexOf(ftlVersionParam) + ftlVersionParam.length());
        name = name.substring(0, name.indexOf(ftlVersionParam));
    }
    // update-end--Author:zhangguoming  Date:20140922 for?????????ftlVersion??????????????????
    // update-begin--Author:?????????  Date:20150707 for???online???????????????????????????????????????????????????????????????
    TemplateUtil.TemplateType templateType = null;
    if (name.lastIndexOf(".ftl") == -1 && name.lastIndexOf("_") != -1) {
        templateType = TemplateUtil.TemplateType.getVal(name.substring(name.lastIndexOf("_") + 1));
        name = name.substring(0, name.lastIndexOf("_"));
    }
    if (templateType == null) {
        templateType = TemplateUtil.TemplateType.UPDATE;
    }
    // update-end--Author:?????????  Date:20150707 for???online???????????????????????????????????????????????????????????????
    PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
    if (name.lastIndexOf(".ftl") == -1) {
        // ???????????????include?????????
        // ???????????????????????????????????????
        CgFormHeadEntity head = cgFormFieldService.getCgFormHeadByTableName(name);
        if (head == null)
            return null;
        // update-begin--Author:?????????  Date:20150707 for???online???????????????????????????????????????????????????????????????
        CgformTemplateEntity entity = cgformTemplateService.findByCode(head.getFormTemplate());
        // update-end--Author:?????????  Date:20150707 for???online???????????????????????????????????????????????????????????????
        if (head.getJformType() == CgAutoListConstant.JFORM_TYPE_MAIN_TALBE) {
            // update-begin--Author:?????????  Date:20150623 for??????????????????
            Resource[] resources = patternResolver.getResources(TemplateUtil.getTempletPath(entity, head.getJformType(), templateType));
            // update-end--Author:?????????  Date:20150623 for??????????????????
            InputStreamReader inputStreamReader = null;
            if (resources != null && resources.length > 0) {
                inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
            }
            return inputStreamReader;
        }
        // 1?????????table name ??????cgformftl ????????????????????????
        // 2?????????cgformftl ?????????cgformfield ??????cgformfield????????????
        // update-start--Author:zhangguoming  Date:20140922 for?????????ftlVersion??????????????????
        Map<String, Object> cgformFtlEntity = new HashMap<String, Object>();
        if (ftlVersion != null && ftlVersion.length() > 0) {
            cgformFtlEntity = cgformFtlService.getCgformFtlByTableName(name, ftlVersion);
        } else {
            cgformFtlEntity = cgformFtlService.getCgformFtlByTableName(name);
        }
        // update-end--Author:zhangguoming  Date:20140922 for?????????ftlVersion??????????????????
        if (cgformFtlEntity != null) {
            String content = (String) (cgformFtlEntity.get("ftl_content") == null ? "" : cgformFtlEntity.get("ftl_content"));
            content = initFormHtml(content, name);
            content = content.replace("${id?if_exists?html}", "@@@{onlineCodeGenereateEntityKey.id}");
            // org.jeecgframework.core.util.LogUtil.info(content);
            return new StringBuilder(content);
        } else {
            // update-begin--Author:?????????  Date:20150623 for??????????????????
            Resource[] resources = patternResolver.getResources(TemplateUtil.getTempletPath(entity, head.getJformType(), templateType));
            // update-end--Author:?????????  Date:20150623 for??????????????????
            InputStreamReader inputStreamReader = null;
            if (resources != null && resources.length > 0) {
                inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
            }
            return inputStreamReader;
        }
    } else {
        Resource[] resources = patternResolver.getResources(name);
        InputStreamReader inputStreamReader = null;
        if (resources != null && resources.length > 0) {
            inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
        }
        return inputStreamReader;
    }
}


public long getLastModified(Object templateSource){
    return 0;
}


public Reader getReader(Object templateSource,String encoding){
    Reader br = new StringReader("");
    if (templateSource instanceof InputStreamReader) {
        br = new BufferedReader((InputStreamReader) templateSource);
    } else {
        StringBuilder str = (StringBuilder) templateSource;
        br = new StringReader(str.toString());
    }
    return br;
}


public void closeTemplateSource(Object templateSource){
}


public Object findTemplateSource(String name){
    // update by Robin  postgreSQL ???????????????????????? 2013-03-13
    name = name.replace("_zh_cn", "").replace("_ZH_CN", "").replace("_zh_CN", "");
    name = name.replace("_en_us", "").replace("_EN_US", "").replace("_en_US", "");
    // update by Robin postgreSQL ???????????????????????? 2013-03-13
    org.jeecgframework.core.util.LogUtil.info("table name----------->" + name);
    Object obj = getObject(name);
    return obj;
}


public String initFormHtml(String htmlStr,String tableName){
    Pattern pattern;
    Matcher matcher;
    try {
        // ??????formid???????????????CgFormFieldEntity
        Map<String, CgFormFieldEntity> fieldMap = cgFormFieldService.getAllCgFormFieldByTableName(tableName);
        // ??????formid???????????????CgFormFieldEntity
        List<CgFormFieldEntity> hiddenFielList = cgFormFieldService.getHiddenCgFormFieldByTableName(tableName);
        // ??????input??????
        pattern = Pattern.compile(regEx_attr, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        StringBuffer sb = new StringBuffer();
        String thStr = "";
        String inputStr = "";
        boolean result = matcher.find();
        while (result) {
            thStr = matcher.group(1);
            inputStr = "";
            // update--begin-------author:zhoujf------date:20180614----for:TASK #2787 ???online ??????????????????????????????word ????????????---------------
            if ("jform_hidden_field".equals(thStr)) {
                // ?????????????????????
                // inputStr = getHiddenForm(hiddenFielList);
                inputStr = "";
            } else {
                if (fieldMap.get(thStr) != null) {
                    CgFormFieldEntity cgFormFieldEntity = fieldMap.get(thStr);
                    if ("Y".equals(cgFormFieldEntity.getIsShow())) {
                        inputStr = FormHtmlUtilWord.getFormHTML(cgFormFieldEntity, tableName);
                        inputStr += "<span class=\"Validform_checktip\">&nbsp;</span>";
                    }
                }
            }
            // update--end-------author:zhoujf------date:20180614----for:TASK #2787 ???online ??????????????????????????????word ????????????---------------
            matcher.appendReplacement(sb, inputStr);
            result = matcher.find();
        }
        matcher.appendTail(sb);
        htmlStr = sb.toString();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return htmlStr;
}


}