package com.xwtec.xwserver.util.tag;
 import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.util.CommonUtil;
public class SearchTag extends TagSupport{

 private  String onclick;

 private  long serialVersionUID;

 private  Logger log;


public int doStartTag(){
    try {
        // 标签体内容
        StringBuffer tagContent = new StringBuffer();
        tagContent.append("<input type='button' onclick=");
        if (!CommonUtil.isEmpty(this.onclick)) {
            tagContent.append(this.onclick);
            tagContent.append(";");
        }
        tagContent.append("changePage(1); class='tabSub' value='查询'/>");
        super.pageContext.getOut().write(tagContent.toString());
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return SKIP_BODY;
}


public String getOnclick(){
    return onclick;
}


public void setOnclick(String onclick){
    this.onclick = onclick;
}


public int doEndTag(){
    return EVAL_PAGE;
}


}