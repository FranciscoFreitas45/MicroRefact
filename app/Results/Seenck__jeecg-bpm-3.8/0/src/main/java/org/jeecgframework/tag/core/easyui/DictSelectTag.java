package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
public class DictSelectTag extends TagSupport{

 private  long serialVersionUID;

 private  String typeGroupCode;

 private  String field;

 private  String id;

 private  String defaultVal;

 private  String divClass;

 private  String labelClass;

 private  String title;

 private  boolean hasLabel;

 private  String type;

 private  String dictTable;

 private  String dictField;

 private  String dictText;

 private  String extendJson;

 private  String readonly;

 private  String dictCondition;

 private  String datatype;

@Autowired
 private  SystemService systemService;


public String getDictText(){
    return dictText;
}


public void setField(String field){
    this.field = field;
}


public void select(String name,String code,StringBuffer sb){
    // update-begin--Author:gj_shaojc  Date:20180326 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题
    if (code.equals(this.defaultVal)) {
        if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
            sb.append(" <option style=\"display: none;\"  value=\"" + code + "\" selected=\"selected\">");
        } else {
            sb.append(" <option  value=\"" + code + "\" selected=\"selected\">");
        }
    } else {
        if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
            sb.append(" <option style=\"display: none;\" value=\"" + code + "\">");
        } else {
            sb.append(" <option  value=\"" + code + "\">");
        }
    }
    // update-end--Author:gj_shaojc  Date:20180326 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题
    sb.append(MutiLangUtil.getLang(name));
    sb.append(" </option>");
}


public String getReadonly(){
    return readonly;
}


public String getId(){
    return id;
}


public boolean isHasLabel(){
    return hasLabel;
}


public String getDictTable(){
    return dictTable;
}


public void setExtendJson(String extendJson){
    this.extendJson = extendJson;
}


public void radio(String name,String code,StringBuffer sb){
    // update-begin-author:taoyan date:20180511 for:dictselet radio 参考select增加extendJson---
    if (code.equals(this.defaultVal)) {
        sb.append("<input type=\"radio\" name=\"" + field + "\" checked=\"checked\" value=\"" + code + "\"");
    } else {
        sb.append("<input type=\"radio\" name=\"" + field + "\" value=\"" + code + "\"");
    }
    if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + id + "\"");
    }
    // update-begin--Author:jg_xugj  许国杰  Date:20151209 for：#775 增加只读属性
    this.readonly(sb);
    // update-end--Author:jg_xugj 许国杰  Date:20151209 for：#775 增加只读属性
    this.datatype(sb);
    if (!StringUtils.isBlank(this.extendJson)) {
        sb.append(this.getExtendJsonCommon(extendJson));
    }
    sb.append(" />");
    sb.append(MutiLangUtil.getLang(name) + "&nbsp;&nbsp;");
// update-end-author:taoyan date:20180511 for:dictselet radio 参考select增加extendJson---
}


public String getDatatype(){
    return datatype;
}


public String getTitle(){
    return title;
}


public StringBuffer readonly(StringBuffer sb){
    // update-begin--Author:gj_shaojc  Date:20180326 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题
    if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
        if ("radio".equals(type)) {
            sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" disabled=\"true\" ");
        } else if ("checkbox".equals(type)) {
            sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" disabled=\"true\" ");
        } else if ("text".equals(type)) {
        } else if ("list".equals(type)) {
            sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" ");
        } else {
            sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" ");
        }
    }
    // update-end--Author:gj_shaojc  Date:20180326 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题
    return sb;
}


public StringBuffer datatype(StringBuffer sb){
    if (!StringUtils.isBlank(this.datatype)) {
        sb.append(" datatype=\"" + datatype + "\"");
    }
    return sb;
}


public void setDictCondition(String dicCondition){
    this.dictCondition = dicCondition;
}


public void setDictTable(String dictTable){
    this.dictTable = dictTable;
}


public void checkbox(String name,String code,StringBuffer sb){
    // update-begin--Author:scott  --- date:20160819 --- for：论坛问题，多选框出错
    if (this.defaultVal == null) {
        this.defaultVal = "";
    }
    // update-end--Author:scott --- date:20160819 --- for：论坛问题，多选框出错
    String[] values = this.defaultVal.split(",");
    Boolean checked = false;
    for (int i = 0; i < values.length; i++) {
        String value = values[i];
        if (code.equals(value)) {
            checked = true;
            break;
        }
        checked = false;
    }
    // update-end-author:taoyan date:20180511 for:dictselet checkbox 参考select增加extendJson---
    if (checked) {
        sb.append("<input type=\"checkbox\" name=\"" + field + "\" checked=\"checked\" value=\"" + code + "\"");
    } else {
        sb.append("<input type=\"checkbox\" name=\"" + field + "\" value=\"" + code + "\"");
    }
    if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + id + "\"");
    }
    // update-begin--Author:jg_xugj  许国杰  Date:20151209 for：#775 增加只读属性
    this.readonly(sb);
    // update-end--Author:jg_xugj 许国杰  Date:20151209 for：#775 增加只读属性
    this.datatype(sb);
    if (!StringUtils.isBlank(this.extendJson)) {
        sb.append(" " + this.getExtendJsonCommon(extendJson));
    }
    sb.append(" />");
    sb.append(MutiLangUtil.getLang(name) + "&nbsp;&nbsp;");
// update-end-author:taoyan date:20180511 for:dictselet checkbox 参考select增加extendJson---
}


public void setId(String id){
    this.id = id;
}


public StringBuffer end(){
    StringBuffer sb = new StringBuffer();
    if (StringUtils.isBlank(divClass)) {
        // 默认form样式
        divClass = "form";
    }
    if (StringUtils.isBlank(labelClass)) {
        // 默认label样式
        labelClass = "Validform_label";
    }
    if (dictTable != null) {
        List<Map<String, Object>> list = queryDic();
        if ("radio".equals(type)) {
            for (Map<String, Object> map : list) {
                radio(map.get("text").toString(), map.get("field").toString(), sb);
            }
        } else if ("checkbox".equals(type)) {
            for (Map<String, Object> map : list) {
                checkbox(map.get("text").toString(), map.get("field").toString(), sb);
            }
        } else if ("text".equals(type)) {
            for (Map<String, Object> map : list) {
                text(map.get("text").toString(), map.get("field").toString(), sb);
            }
        } else {
            sb.append("<select name=\"" + field + "\"");
            // update-begin--Author:gj_shaojc  Date:20180326 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题
            // 增加扩展属性
            if (!StringUtils.isBlank(this.extendJson)) {
                // update-begin--Author:gj_shaojc  Date:20180327 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题(修改)
                sb.append(this.getExtendJsonCommon(extendJson));
            // update-end--Author:gj_shaojc  Date:20180327 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题(修改)
            }
            // update-begin--Author:jg_xugj  许国杰  Date:20151209 for：#775 增加只读属性
            this.readonly(sb);
            // update-end--Author:jg_xugj 许国杰  Date:20151209 for：#775 增加只读属性
            // update-end--Author:gj_shaojc  Date:20180326 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题
            if (!StringUtils.isBlank(this.id)) {
                sb.append(" id=\"" + id + "\"");
            }
            // update-begin--Author:xuelin  Date:20170512 for：TASK #1896 [Online开发] 关于标签dictSelect的dataType属性的bug--------------------
            this.datatype(sb);
            // update-end--Author:xuelin  Date:20170512 for：TASK #1896 [Online开发] 关于标签dictSelect的dataType属性的bug--------------------
            sb.append(">");
            // update-begin--Author:zhangdaihao  Date:20140724 for：[bugfree号]默认选择项目--------------------
            select("common.please.select", "", sb);
            // update-end--Author:zhangdaihao  Date:20140724 for：[bugfree号]默认选择项目----------------------
            for (Map<String, Object> map : list) {
                select(map.get("text").toString(), map.get("field").toString(), sb);
            }
            sb.append("</select>");
        }
    } else {
        TSTypegroup typeGroup = ResourceUtil.getCacheTypeGroup(this.typeGroupCode.toLowerCase());
        List<TSType> types = ResourceUtil.getCacheTypes(this.typeGroupCode.toLowerCase());
        if (hasLabel) {
            sb.append("<div class=\"" + divClass + "\">");
            sb.append("<label class=\"" + labelClass + "\" >");
        }
        if (typeGroup != null) {
            if (hasLabel) {
                if (StringUtils.isBlank(this.title)) {
                    this.title = MutiLangUtil.getLang(typeGroup.getTypegroupname());
                }
                sb.append(this.title + ":");
                sb.append("</label>");
            }
            if ("radio".equals(type)) {
                for (TSType type : types) {
                    radio(type.getTypename(), type.getTypecode(), sb);
                }
            } else if ("checkbox".equals(type)) {
                for (TSType type : types) {
                    checkbox(type.getTypename(), type.getTypecode(), sb);
                }
            } else if ("text".equals(type)) {
                for (TSType type : types) {
                    text(type.getTypename(), type.getTypecode(), sb);
                }
            } else {
                sb.append("<select name=\"" + field + "\"");
                // update-begin--Author:gj_shaojc  Date:20180327 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题（修改）
                // 增加扩展属性
                if (!StringUtils.isBlank(this.extendJson)) {
                    sb.append(this.getExtendJsonCommon(extendJson));
                }
                // update-begin--Author:jg_xugj  许国杰  Date:20151209 for：#775 增加只读属性
                this.readonly(sb);
                // update-end--Author:jg_xugj 许国杰  Date:20151209 for：#775 增加只读属性
                // update-end--Author:gj_shaojc  Date:20180327 for：TASK #2582 【bug修改】t:dictSelect标签 readonly 的问题（修改）
                if (!StringUtils.isBlank(this.id)) {
                    sb.append(" id=\"" + id + "\"");
                }
                this.datatype(sb);
                sb.append(">");
                // update-begin--Author:zhangdaihao  Date:20140724 for：[bugfree号]默认选择项目--------------------
                select("common.please.select", "", sb);
                // update-end--Author:zhangdaihao  Date:20140724 for：[bugfree号]默认选择项目----------------------
                for (TSType type : types) {
                    select(type.getTypename(), type.getTypecode(), sb);
                }
                sb.append("</select>");
            }
            if (hasLabel) {
                sb.append("</div>");
            }
        }
    }
    return sb;
}


public void text(String name,String code,StringBuffer sb){
    if (code.equals(this.defaultVal)) {
        sb.append("<input name='" + field + "'" + " id='" + id + "' value='" + MutiLangUtil.getLang(name) + "' readOnly = 'readOnly' />");
    } else {
    }
}


public void setDivClass(String divClass){
    this.divClass = divClass;
}


public String getExtendJson(){
    return extendJson;
}


public void setHasLabel(boolean hasLabel){
    this.hasLabel = hasLabel;
}


public String getDivClass(){
    return divClass;
}


public void setDatatype(String datatype){
    this.datatype = datatype;
}


public List<Map<String,Object>> queryDic(){
    String sql = "select " + dictField + " as field," + dictText + " as text from " + dictTable;
    // update-begin--Author:jg_longjb龙金波  Date:20150313 for：增加查询条件属性 例如：where type='xxx'
    if (dictCondition != null) {
        sql += " " + dictCondition + " ";
    }
    // update--end--Author:jg_longjb龙金波  Date:20150313 for：增加查询条件属性
    systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
    List<Map<String, Object>> list = systemService.findForJdbc(sql);
    return list;
}


public void setLabelClass(String labelClass){
    this.labelClass = labelClass;
}


public StringBuffer getExtendJsonCommon(String extendJson){
    Gson gson = new Gson();
    Map<String, String> mp = gson.fromJson(extendJson, Map.class);
    StringBuffer sb = new StringBuffer();
    sb.append(" ");
    for (Map.Entry<String, String> entry : mp.entrySet()) {
        // 判断select标签中是否含有style属性
        if (entry.getKey().equals("style")) {
            // 并且readonly属性不为空
            if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
                // 拼接Style属性
                String entryValue = entry.getValue() + ";background-color:#eee;cursor:no-drop;";
                // 把拼接好的属性加入到sb字符串中
                sb.append(entry.getKey() + "=\"" + entryValue + "\"");
            } else {
                // 如果readonly属性为空，走原来的样式
                sb.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
            }
        } else {
            // 如果没有Style属性的话走原来的方法，readonly属性在下边readonly方法中已经默认添加了样式
            sb.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
        }
    }
    return sb;
}


public String getDictField(){
    return dictField;
}


public String getDefaultVal(){
    return defaultVal;
}


public String getField(){
    return field;
}


public void setTypeGroupCode(String typeGroupCode){
    this.typeGroupCode = typeGroupCode;
}


public void setTitle(String title){
    this.title = title;
}


public void setType(String type){
    this.type = type;
}


public void setReadonly(String readonly){
    this.readonly = readonly;
}


public int doEndTag(){
    JspWriter out = null;
    try {
        out = this.pageContext.getOut();
        out.print(end().toString());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            out.clear();
            out.close();
            end().setLength(0);
        } catch (Exception e2) {
        }
    }
    return EVAL_PAGE;
}


public String getDictCondition(){
    return dictCondition;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public String getType(){
    return type;
}


public void setDictText(String dictText){
    this.dictText = dictText;
}


public void setDefaultVal(String defaultVal){
    this.defaultVal = defaultVal;
}


public String getLabelClass(){
    return labelClass;
}


public void setDictField(String dictField){
    this.dictField = dictField;
}


public String getTypeGroupCode(){
    return typeGroupCode;
}


}