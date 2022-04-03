package com.xwtec.xwserver.util.tag;
 import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.constant.ConstantKeys;
import com.xwtec.xwserver.util.CommonUtil;
public class ChartTag extends TagSupport{

 private  long serialVersionUID;

 private  Logger log;

 private  String id;

 private  String data;

 private  String type;

 private  String chartId;

 private  String width;

 private  String height;


public void setData(String data){
    this.data = data;
}


public String getId(){
    return id;
}


public String getWidth(){
    return width;
}


public void setType(String type){
    this.type = type;
}


public void setWidth(String width){
    this.width = width;
}


public void setHeight(String height){
    this.height = height;
}


public int doEndTag(){
    return EVAL_PAGE;
}


public int doStartTag(){
    try {
        // 标签体内容
        StringBuilder tagContent = new StringBuilder();
        tagContent.append("<div id='" + this.id + "'></div>");
        tagContent.append("<script>");
        tagContent.append("load" + this.type + "({");
        tagContent.append("basePath : '" + super.pageContext.getRequest().getAttribute(ConstantKeys.BASE_PATH_KEY) + "'");
        tagContent.append(",id : '" + this.id + "'");
        tagContent.append(",data : '" + this.data + "'");
        if (!CommonUtil.isEmpty(this.chartId)) {
            tagContent.append(",chartId : '" + this.chartId + "'");
        }
        if (!CommonUtil.isEmpty(this.width)) {
            tagContent.append(",width : " + this.width);
        }
        if (!CommonUtil.isEmpty(this.height)) {
            tagContent.append(",height : '" + this.height + "'");
        }
        tagContent.append("});");
        tagContent.append("</script>");
        super.pageContext.getOut().write(tagContent.toString());
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return SKIP_BODY;
}


public String getHeight(){
    return height;
}


public String getType(){
    return type;
}


public void setId(String id){
    this.id = id;
}


public String getChartId(){
    return chartId;
}


public void setChartId(String chartId){
    this.chartId = chartId;
}


public String getData(){
    return data;
}


}