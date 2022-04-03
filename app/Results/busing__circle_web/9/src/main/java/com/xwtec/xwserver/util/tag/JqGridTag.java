package com.xwtec.xwserver.util.tag;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.constant.ConstantKeys;
import com.xwtec.xwserver.util.CommonUtil;
public class JqGridTag extends BodyTagSupport{

 private  long serialVersionUID;

 private  Logger log;

 private  List<ColumnTag> list;

 private  String name;

 private  String id;

 private  String caption;

 private  String queryUrl;

 private  String deleteUrl;

 private  String postData;

 private  Boolean page;

 private  Boolean enter;


public void setName(String name){
    this.name = name;
}


public void setQueryUrl(String queryUrl){
    this.queryUrl = queryUrl;
}


public Boolean getEnter(){
    return enter;
}


public String getName(){
    return name;
}


public String getDeleteUrl(){
    return deleteUrl;
}


public void setPostData(String postData){
    this.postData = postData;
}


public Boolean getPage(){
    return page;
}


public String getId(){
    return id;
}


public void setCaption(String caption){
    this.caption = caption;
}


public String getCaption(){
    return caption;
}


public String getQueryUrl(){
    return queryUrl;
}


public int doEndTag(){
    try {
        // 标签体内容
        StringBuffer tagContent = new StringBuffer();
        // 引用js和css文件--start
        tagContent.append("<link type='text/css' href='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/component/jqGrid/themes/ui.jqgrid.css' rel='stylesheet' />" + "<link type='text/css' href='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/component/jqGrid/themes/redmond/jquery-ui-1.8.2.custom.css' rel='stylesheet' />" + "<script type='text/javascript' src='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/json.js'></script>" + "<script type='text/javascript' src='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/common.js'></script>" + "<script type='text/javascript' src='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/component/jqGrid/js/myjqgrid.js'></script>" + "<script type='text/javascript' src='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/component/jqGrid/js/jquery-ui-1.8.2.custom.min.js'></script>" + "<script type='text/javascript' src='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/component/jqGrid/js/i18n/grid.locale-cn.js'></script>" + "<script type='text/javascript' src='" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "/js/common/component/jqGrid/js/jquery.jqGrid.min.js'></script>");
        // 引用js和css文件--end
        // table内容--start
        if (!CommonUtil.isEmpty(this.getName())) {
            tagContent.append("<table id='" + this.id + "' style='border-collapse: separate;font-size:12px;'></table><input type='hidden' name='" + this.name + "' id='" + this.name + "'/>");
        } else {
            if (this.getPage() != null && this.getPage())
                tagContent.append("<table id='" + this.id + "' style='border-collapse: separate;'></table><div id='" + this.getId() + "Pager'></div>");
            else
                tagContent.append("<table id='" + this.id + "' style='border-collapse: separate;'></table>");
        }
        // table内容--end
        // 脚本--start
        tagContent.append("<script>");
        tagContent.append("var colModel_" + this.id + "=[];");
        for (int i = 0; i < list.size(); i++) {
            ColumnTag column = list.get(i);
            tagContent.append("var column={};");
            tagContent.append("column.name='" + column.getName() + "';");
            if (!(column.getLabel() == null)) {
                tagContent.append("column.label='" + column.getLabel() + "';");
            }
            if (!(column.getIndex() == null)) {
                tagContent.append("column.index='" + column.getIndex() + "';");
            }
            if (!(column.getWidth() == null)) {
                tagContent.append("column.width=" + column.getWidth() + ";");
            }
            if (!(column.getEditable() == null)) {
                tagContent.append("column.editable=" + column.getEditable() + ";");
            }
            if (!(column.getHidden() == null)) {
                tagContent.append("column.hidden=" + column.getHidden() + ";");
            }
            if (!(column.getKey() == null)) {
                tagContent.append("column.key=" + column.getKey() + ";");
            }
            if (!(column.getTitle() == null)) {
                tagContent.append("column.title=" + column.getTitle() + ";");
            }
            if (!(column.getSortable() == null)) {
                tagContent.append("column.sortable=" + column.getSortable() + ";");
            }
            if (!CommonUtil.isEmpty(column.getAlign())) {
                tagContent.append("column.align='" + column.getAlign() + "';");
            }
            if (!CommonUtil.isEmpty(column.getEdittype())) {
                tagContent.append("column.edittype='" + column.getEdittype() + "';");
            }
            if (!CommonUtil.isEmpty(column.getEditoptions())) {
                tagContent.append("column.editoptions=" + column.getEditoptions() + ";");
            }
            if (!(column.getEditrules() == null)) {
                tagContent.append("column.editrules=" + column.getEditrules() + ";");
            }
            if (!(column.getFormatter() == null)) {
                tagContent.append("column.formatter=" + column.getFormatter() + ";");
            }
            if (!(column.getUnformat() == null)) {
                tagContent.append("column.unformat=" + column.getUnformat() + ";");
            }
            if (!(column.getFormatoptions() == null)) {
                tagContent.append("column.formatoptions=" + column.getFormatoptions() + ";");
            }
            tagContent.append("colModel_" + this.id + ".push(column);");
        }
        tagContent.append("$(function(){");
        if (this.getPage() != null && this.getPage()) {
            tagContent.append("initPageJqGrid('" + this.getId() + "','" + this.getId() + "Pager','" + this.getQueryUrl() + "',colModel_" + this.id + "," + this.getPostData() + ");");
        } else {
            tagContent.append("initEditJqGrid('" + this.getId() + "','" + this.getQueryUrl() + "','" + this.getDeleteUrl() + "',colModel_" + this.id + "," + this.getPostData() + ",'" + this.getCaption() + "','" + this.getName() + "','" + this.getEnter() + "');");
        }
        tagContent.append("});");
        tagContent.append("</script>");
        // 脚本--start
        list.clear();
        super.pageContext.getOut().write(tagContent.toString());
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return EVAL_PAGE;
}


public int doStartTag(){
    return EVAL_BODY_INCLUDE;
}


public String getPostData(){
    return postData;
}


public void setEnter(Boolean enter){
    this.enter = enter;
}


public void setId(String id){
    this.id = id;
}


public void setDeleteUrl(String deleteUrl){
    this.deleteUrl = deleteUrl;
}


public void addValue(ColumnTag column){
    list.add(column);
}


public void setPage(Boolean page){
    this.page = page;
}


}