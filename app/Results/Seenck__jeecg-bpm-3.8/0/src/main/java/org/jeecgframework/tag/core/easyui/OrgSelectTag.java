package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.oConvertUtils;
public class OrgSelectTag extends TagSupport{

 private  long serialVersionUID;

 private  String selectedIdsInputId;

 private  String selectedNamesInputId;

 private  String inputWidth;

 private  String windowWidth;

 private  String windowHeight;

 private  String departIdsDefalutVal;

 private  String departNamesDefalutVal;

 private  String readonly;

 private  boolean hasLabel;

 private  String title;


public String getWindowWidth(){
    return windowWidth;
}


public String getInputWidth(){
    return inputWidth;
}


public String getReadonly(){
    return readonly;
}


public void setInputWidth(String inputWidth){
    this.inputWidth = inputWidth;
}


public void setTitle(String title){
    this.title = title;
}


public void setSelectedNamesInputId(String _selectedNamesInputId){
    this.selectedNamesInputId = _selectedNamesInputId;
}


public void setDepartNamesDefalutVal(String departNamesDefalutVal){
    this.departNamesDefalutVal = departNamesDefalutVal;
}


public boolean isHasLabel(){
    return hasLabel;
}


public String getDepartNamesDefalutVal(){
    return departNamesDefalutVal;
}


public void setReadonly(String readonly){
    this.readonly = readonly;
}


public String getWindowHeight(){
    return windowHeight;
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


public String getTitle(){
    return title;
}


public String getSelectedNamesInputId(){
    return selectedNamesInputId;
}


public void setWindowWidth(String windowWidth){
    this.windowWidth = windowWidth;
}


public void setWindowHeight(String windowHeight){
    this.windowHeight = windowHeight;
}


public void setDepartIdsDefalutVal(String departIdsDefalutVal){
    this.departIdsDefalutVal = departIdsDefalutVal;
}


public void setSelectedIdsInputId(String _selectedIdsInputId){
    this.selectedIdsInputId = _selectedIdsInputId;
}


public StringBuffer end(){
    StringBuffer sb = new StringBuffer();
    if (StringUtils.isBlank(selectedNamesInputId)) {
        // 默认id
        selectedNamesInputId = "orgNames";
    }
    if (StringUtils.isBlank(selectedIdsInputId)) {
        // 默认id
        selectedIdsInputId = "orgIds";
    }
    if (StringUtils.isBlank(title)) {
        title = "选择部门";
    }
    if (StringUtils.isBlank(inputWidth)) {
        inputWidth = "150px";
    }
    // add-begin--Author:xuelin  Date:20170408 for：[#1814]【标签】弹出组织机构选择，树列表方式，修改默认值显示样式--------------------
    if (StringUtils.isBlank(windowWidth)) {
        windowWidth = "660px";
    }
    // add-end--Author:xuelin  Date:20170408 for：[#1814]【标签】弹出组织机构选择，树列表方式，修改默认值显示样式----------------------
    if (StringUtils.isBlank(windowHeight)) {
        windowHeight = "350px";
    }
    if (hasLabel && oConvertUtils.isNotEmpty(title)) {
        sb.append(title + "：");
    }
    sb.append("<input readonly=\"true\" type=\"text\" id=\"" + selectedNamesInputId + "\" name=\"" + selectedNamesInputId + "\" style=\"width: " + inputWidth + "\" onclick=\"openOrgSelect()\" ");
    if (StringUtils.isNotBlank(departNamesDefalutVal)) {
        sb.append(" value=\"" + departNamesDefalutVal + "\"");
    }
    sb.append(" />");
    String orgIds = "";
    sb.append("<input id=\"" + selectedIdsInputId + "\" name=\"" + selectedIdsInputId + "\" type=\"hidden\" ");
    if (StringUtils.isNotBlank(departIdsDefalutVal)) {
        sb.append(" value=\"" + departIdsDefalutVal + "\"");
        orgIds = "&orgIds=" + departIdsDefalutVal;
    }
    sb.append("/>");
    String commonDepartmentList = MutiLangUtil.getLang("common.department.list");
    String commonConfirm = MutiLangUtil.getLang("common.confirm");
    String commonCancel = MutiLangUtil.getLang("common.cancel");
    sb.append("<script type=\"text/javascript\">");
    sb.append("function openOrgSelect() {");
    // TODO
    sb.append("    $.dialog.setting.zIndex = 9999; ");
    sb.append("    $.dialog({content: 'url:departController.do?orgSelect" + orgIds + "', zIndex: 2100, title: '" + commonDepartmentList + "', lock: true, width: '" + windowWidth + "', height: '" + windowHeight + "', opacity: 0.4, button: [");
    sb.append("       {name: '" + commonConfirm + "', callback: callbackOrgSelect, focus: true},");
    sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
    sb.append("   ]}).zindex();");
    sb.append("}");
    sb.append("function callbackOrgSelect() {");
    sb.append("    var iframe = this.iframe.contentWindow;");
    sb.append("var nodes = iframe.document.getElementsByClassName(\"departId\");");
    sb.append(" if(nodes.length>0){");
    sb.append(" var ids='',names='';");
    sb.append("for(i=0;i<nodes.length;i++){");
    sb.append(" var node = nodes[i];");
    sb.append(" if(node.checked){");
    sb.append("   ids += node.value+',';");
    sb.append("   names += node.name+',';");
    sb.append(" }");
    sb.append("}");
    sb.append(" $('#" + selectedNamesInputId + "').val(names);");
    sb.append(" $('#" + selectedNamesInputId + "').blur();");
    sb.append(" $('#" + selectedIdsInputId + "').val(ids);");
    sb.append("}");
    sb.append("}");
    sb.append("</script>");
    return sb;
}


public String getSelectedIdsInputId(){
    return selectedIdsInputId;
}


public String getDepartIdsDefalutVal(){
    return departIdsDefalutVal;
}


public void setHasLabel(boolean hasLabel){
    this.hasLabel = hasLabel;
}


}