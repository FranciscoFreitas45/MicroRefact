package org.jeecgframework.tag.core.easyui;
 import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.oConvertUtils;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
public class UserSelectTag extends TagSupport{

 private  long serialVersionUID;

 private  String title;

 private  String selectedNamesInputId;

 private  String selectedIdsInputId;

 private  boolean hasLabel;

 private  String userNamesDefalutVal;

 private  String userIdsDefalutVal;

 private  String readonly;

 private  String inputWidth;

 private  String windowWidth;

 private  String windowHeight;

 private  String callback;


public String getWindowWidth(){
    return windowWidth;
}


public String getUserNamesDefalutVal(){
    return userNamesDefalutVal;
}


public String getReadonly(){
    return readonly;
}


public void setInputWidth(String inputWidth){
    this.inputWidth = inputWidth;
}


public void setCallback(String callback){
    this.callback = callback;
}


public void setSelectedNamesInputId(String _selectedNamesInputId){
    this.selectedNamesInputId = _selectedNamesInputId;
}


public boolean isHasLabel(){
    return hasLabel;
}


public String getWindowHeight(){
    return windowHeight;
}


public void setUserNamesDefalutVal(String userNamesDefalutVal){
    this.userNamesDefalutVal = userNamesDefalutVal;
}


public String getTitle(){
    return title;
}


public void setWindowWidth(String windowWidth){
    this.windowWidth = windowWidth;
}


public void setWindowHeight(String windowHeight){
    this.windowHeight = windowHeight;
}


public StringBuffer end(){
    StringBuffer sb = new StringBuffer();
    if (StringUtils.isBlank(selectedNamesInputId)) {
        // 默认id
        selectedNamesInputId = "userNames";
    }
    if (StringUtils.isBlank(title)) {
        title = "用户名称";
    }
    if (StringUtils.isBlank(inputWidth)) {
        inputWidth = "150px";
    }
    if (StringUtils.isBlank(windowWidth)) {
        windowWidth = "400px";
    }
    if (StringUtils.isBlank(windowHeight)) {
        windowHeight = "350px";
    }
    if (hasLabel && oConvertUtils.isNotEmpty(title)) {
        sb.append(title + "：");
    }
    sb.append("<input class=\"inuptxt\" readonly=\"" + readonly + "\" type=\"text\" id=\"" + selectedNamesInputId + "\" name=\"" + selectedNamesInputId + "\" style=\"width: " + inputWidth + "\" onclick=\"openUserSelect()\" ");
    if (StringUtils.isNotBlank(userNamesDefalutVal)) {
        sb.append(" value=\"" + userNamesDefalutVal + "\"");
    }
    sb.append(" />");
    if (oConvertUtils.isNotEmpty(selectedIdsInputId)) {
        sb.append("<input class=\"inuptxt\" id=\"" + selectedIdsInputId + "\" name=\"" + selectedIdsInputId + "\" type=\"hidden\" ");
        if (StringUtils.isNotBlank(userIdsDefalutVal)) {
            sb.append(" value=\"" + userIdsDefalutVal + "\"");
        }
        sb.append("/>");
    }
    String commonConfirm = MutiLangUtil.getLang("common.confirm");
    String commonCancel = MutiLangUtil.getLang("common.cancel");
    sb.append("<script type=\"text/javascript\">");
    sb.append("function openUserSelect() {");
    // update--begin--author:zhangjiaqiang date:20170527 for:TASK #1866 【bug】Online 弹出popup获取遮挡，原因获取getCookie不是一个源头
    sb.append("    $.dialog({content: 'url:userController.do?userSelect', zIndex: getzIndex(), title: '" + title + "', lock: true, width: '" + windowWidth + "', height: '" + windowHeight + "', opacity: 0.4, button: [");
    // update-begin-author:taoyan date:20170814 for:支持自定义回调函数--
    sb.append("       {name: '" + commonConfirm + "', callback: " + getCallback() + ", focus: true},");
    // update-end-author:taoyan date:20170814 for:支持自定义回调函数--
    sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
    sb.append("   ]});");
    // update--begin--author:zhangjiaqiang date:20170527 for:TASK #1866 【bug】Online 弹出popup获取遮挡，原因获取getCookie不是一个源头
    sb.append("}");
    sb.append("function callbackUserSelect() {");
    sb.append("var iframe = this.iframe.contentWindow;");
    sb.append("var rowsData = iframe.$('#userList1').datagrid('getSelections');");
    sb.append("if (!rowsData || rowsData.length==0) {");
    sb.append("tip('<t:mutiLang langKey=\"common.please.select.edit.item\"/>');");
    sb.append("return;");
    sb.append("}");
    sb.append(" var ids='',names='';");
    sb.append("for(i=0;i<rowsData.length;i++){");
    sb.append(" var node = rowsData[i];");
    sb.append(" ids += node.id+',';");
    sb.append(" names += node.realName+',';");
    sb.append("}");
    sb.append("$('#" + selectedNamesInputId + "').val(names);");
    sb.append("$('#" + selectedNamesInputId + "').blur();");
    if (oConvertUtils.isNotEmpty(selectedIdsInputId)) {
        sb.append("$('#" + selectedIdsInputId + "').val(ids);");
    }
    sb.append("}");
    sb.append("</script>");
    return sb;
}


public void setHasLabel(boolean hasLabel){
    this.hasLabel = hasLabel;
}


public String getInputWidth(){
    return inputWidth;
}


public void setTitle(String title){
    this.title = title;
}


public void setReadonly(String readonly){
    this.readonly = readonly;
}


public String getCallback(){
    if (oConvertUtils.isEmpty(callback)) {
        callback = "callbackUserSelect";
    }
    return callback;
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
        } catch (Exception e2) {
        }
    }
    return EVAL_PAGE;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public String getUserIdsDefalutVal(){
    return userIdsDefalutVal;
}


public void setUserIdsDefalutVal(String userIdsDefalutVal){
    this.userIdsDefalutVal = userIdsDefalutVal;
}


public String getSelectedNamesInputId(){
    return selectedNamesInputId;
}


public void setSelectedIdsInputId(String selectedIdsInputId){
    this.selectedIdsInputId = selectedIdsInputId;
}


public String getSelectedIdsInputId(){
    return selectedIdsInputId;
}


}