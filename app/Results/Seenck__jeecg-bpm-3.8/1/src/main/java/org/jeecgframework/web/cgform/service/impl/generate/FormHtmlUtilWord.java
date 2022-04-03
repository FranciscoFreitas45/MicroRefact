package org.jeecgframework.web.cgform.service.impl.generate;
 import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import DTO.CgFormFieldEntity;
public class FormHtmlUtilWord {


public String getCheckboxFormHtml(CgFormFieldEntity cgFormFieldEntity){
    if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
        return getTextFormHtml(cgFormFieldEntity);
    } else {
        StringBuilder html = new StringBuilder("");
        html.append("<#assign checkboxstr>\\${data['").append(cgFormFieldEntity.getFieldName()).append("']?if_exists?html}</#assign>");
        html.append("<#assign checkboxlist=checkboxstr?split(\",\")> ");
        html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
        if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
            html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
        }
        html.append(" var=\"dictDataList\">");
        html.append("<#list dictDataList as dictdata>");
        html.append(" <input type=\"checkbox\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");
        html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
        html.append(" checked=\"true\" ");
        html.append("</c:if>");
        html.append("<#if dictdata.typecode=='\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}'>");
        html.append(" checked=\"true\" ");
        html.append("</#if> ");
        html.append(">");
        html.append("\\${dictdata.typename?if_exists?html}");
        html.append("</#list> ");
        html.append("</@DictData> ");
        return html.toString();
    }
}


public String getListFormHtml(CgFormFieldEntity cgFormFieldEntity){
    if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
        return getTextFormHtml(cgFormFieldEntity);
    } else {
        StringBuilder html = new StringBuilder("");
        html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
        if (!StringUtil.isEmpty(cgFormFieldEntity.getDictText())) {
            html.append(" text=\"" + cgFormFieldEntity.getDictText() + "\"");
        }
        if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
            html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
        }
        html.append(" var=\"dictDataList\">");
        html.append("<select name=\"" + cgFormFieldEntity.getFieldName() + "\" id=\"" + cgFormFieldEntity.getFieldName() + "\"> ");
        html.append("<#list dictDataList as dictdata>");
        html.append(" <option value=\"\\${dictdata.typecode?if_exists?html}\" ");
        html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
        html.append(" selected=\"selected\" ");
        html.append("</c:if>");
        html.append(">");
        html.append("\\${dictdata.typename?if_exists?html}");
        html.append("</option> ");
        html.append("</#list> ");
        html.append("</select>");
        html.append("</@DictData> ");
        return html.toString();
    }
}


public String getPwdFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"password\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    }
    if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
        html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
    } else {
        html.append("datatype=\"*\" ");
    }
    html.append("\\/>");
    return html.toString();
}


public String getFormHTML(CgFormFieldEntity cgFormFieldEntity_orig,String tableName){
    String html = "";
    CgFormFieldEntity cgFormFieldEntity = new CgFormFieldEntity();
    MyBeanUtils.copyBean2Bean(cgFormFieldEntity, cgFormFieldEntity_orig);
    String fieldName = oConvertUtils.camelName(cgFormFieldEntity.getFieldName());
    cgFormFieldEntity.setFieldName(fieldName);
    if (cgFormFieldEntity.getShowType().equals("text")) {
        if ("only".equalsIgnoreCase(cgFormFieldEntity.getFieldValidType())) {
            html = getTextOnlyFormHtml(cgFormFieldEntity_orig, tableName);
        } else {
            html = getTextFormHtml(cgFormFieldEntity);
        }
    } else if (cgFormFieldEntity.getShowType().equals("password")) {
        html = getPwdFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("radio")) {
        html = getRadioFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("checkbox")) {
        html = getCheckboxFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("list")) {
        html = getListFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("date")) {
        html = getDateFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("datetime")) {
        html = getDatetimeFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("file")) {
        html = getFileFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("textarea")) {
        html = getTextAreaFormHtml(cgFormFieldEntity);
    } else if (cgFormFieldEntity.getShowType().equals("popup")) {
        html = getPopupFormHtml(cgFormFieldEntity);
    } else {
        html = getTextFormHtml(cgFormFieldEntity);
    }
    return html;
}


public String getDateFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"text\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("class=\"Wdate\" ");
    html.append("onClick=\"WdatePicker()\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    }
    if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
        html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
    } else {
        html.append("datatype=\"*\" ");
    }
    html.append("\\/>");
    return html.toString();
}


public String getFileFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"text\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
    html.append("\\/>");
    return html.toString();
}


public String getDatetimeFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"text\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("class=\"Wdate\" ");
    html.append("onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    }
    if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
        html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
    } else {
        html.append("datatype=\"*\" ");
    }
    html.append("\\/>");
    return html.toString();
}


public String getPopupFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"text\" readonly=\"readonly\" class=\"searchbox-inputtext\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
    // -- update-begin--Author:zhoujf  Date:20180409 for：TASK #2627 【online表单】online表单 表单模板 popup控件回填问题--
    html.append("onclick=\"popupClick(this,'" + cgFormFieldEntity.getDictText() + "','" + cgFormFieldEntity.getDictField() + "','" + cgFormFieldEntity.getDictTable() + "');\" ");
    // -- update-end--Author:zhoujf  Date:20180409 for：TASK #2627 【online表单】online表单 表单模板 popup控件回填问题--
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    }
    if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
        html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
    } else {
        html.append("datatype=\"*\" ");
    }
    html.append("\\/>");
    return html.toString();
}


public String getTextAreaFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<textarea  style=\"width: 300px\" rows=\"6\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    }
    if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
        html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
    } else {
        html.append("datatype=\"*\" ");
    }
    html.append("\\>");
    html.append("\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}</textarea> ");
    return html.toString();
}


public String getTextOnlyFormHtml(CgFormFieldEntity cgFormFieldEntity,String tableName){
    String fieldName = oConvertUtils.camelName(cgFormFieldEntity.getFieldName());
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"text\" ");
    html.append("id=\"").append(fieldName).append("\" ");
    html.append("name=\"").append(fieldName).append("\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    // update--begin---author:scott------date:20151118---for:online代码生成自定义word模板----------------------
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(fieldName).append("}\" ");
    // update--begin---author:scott------date:20151118---for:online代码生成自定义word模板---------------------
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    } else {
        html.append("ignore=\"checked\" ");
    }
    html.append("validtype=\"").append(tableName).append(",").append(cgFormFieldEntity.getFieldName()).append(",id\" ");
    html.append("datatype=\"*\" ");
    html.append("\\/>");
    return html.toString();
}


public String getTextFormHtml(CgFormFieldEntity cgFormFieldEntity){
    StringBuilder html = new StringBuilder("");
    html.append("<input type=\"text\" ");
    html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
    if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
        html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
    }
    // update--begin---author:scott------date:20151118---for:online代码生成自定义word模板----------------------
    html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
    // update--begin---author:scott------date:20151118---for:online代码生成自定义word模板---------------------
    if ("Y".equals(cgFormFieldEntity.getIsNull())) {
        html.append("ignore=\"ignore\" ");
    }
    if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
        html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
    } else {
        if ("int".equals(cgFormFieldEntity.getType())) {
            html.append("datatype=\"n\" ");
        } else if ("double".equals(cgFormFieldEntity.getType())) {
            html.append("datatype=\"\\/^(-?\\\\d+)(\\\\.\\\\d+)?\\$\\/\" ");
        } else {
            html.append("datatype=\"*\" ");
        }
    }
    html.append("\\/>");
    return html.toString();
}


public String getRadioFormHtml(CgFormFieldEntity cgFormFieldEntity){
    if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
        return getTextFormHtml(cgFormFieldEntity);
    } else {
        StringBuilder html = new StringBuilder("");
        html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
        if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
            html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
        }
        html.append(" var=\"dictDataList\">");
        html.append("<#list dictDataList as dictdata>");
        html.append(" <input type=\"radio\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");
        html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
        html.append(" checked=\"true\" ");
        html.append("</c:if>");
        html.append(">");
        html.append("\\${dictdata.typename?if_exists?html}");
        html.append("</#list> ");
        html.append("</@DictData> ");
        return html.toString();
    }
}


}