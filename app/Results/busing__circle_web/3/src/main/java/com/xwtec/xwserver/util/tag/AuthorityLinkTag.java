package com.xwtec.xwserver.util.tag;
 import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.util.CommonUtil;
public class AuthorityLinkTag extends BodyTagSupport{

 private  long serialVersionUID;

 private  Logger log;

 private  String authority;

 private  String href;

 private  String onclick;

 private  String cssClass;

 private  boolean hasAuthority;


public String getCssClass(){
    return cssClass;
}


@SuppressWarnings("unchecked")
public int doStartTag(){
    try {
        this.hasAuthority = true;
        // 开始标签内容
        StringBuffer tagContent = new StringBuffer();
        tagContent.append("<a");
        if (!CommonUtil.isEmpty(this.href)) {
            tagContent.append(" href='" + this.href + "'");
        }
        if (!CommonUtil.isEmpty(this.onclick)) {
            // 判断onclick事件中方法的参数用是的单引号还是双引号
            // 来决定onclick事件的最外层是用双引号还是单引号
            String quotes = "\"";
            if (this.onclick.indexOf("\"") > 0) {
                quotes = "'";
            }
            tagContent.append(" onclick=" + quotes + this.onclick + quotes);
        }
        if (!CommonUtil.isEmpty(this.cssClass)) {
            tagContent.append(" class='" + this.cssClass + "'");
        }
        tagContent.append(">");
        super.pageContext.getOut().write(tagContent.toString());
        return EVAL_BODY_INCLUDE;
    } catch (Exception e) {
        this.hasAuthority = false;
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return SKIP_BODY;
    }
}


public String getHref(){
    return href;
}


public void setAuthority(String authority){
    this.authority = authority;
}


public void setCssClass(String cssClass){
    this.cssClass = cssClass;
}


public void setHref(String href){
    this.href = href;
}


public boolean isHasAuthority(){
    return hasAuthority;
}


public String getOnclick(){
    return onclick;
}


public void setHasAuthority(boolean hasAuthority){
    this.hasAuthority = hasAuthority;
}


public void setOnclick(String onclick){
    this.onclick = onclick;
}


public String getAuthority(){
    return authority;
}


public int doEndTag(){
    if (this.hasAuthority) {
        // 结束标签内容
        StringBuffer tagContent = new StringBuffer();
        tagContent.append("</a>");
        try {
            super.pageContext.getOut().write(tagContent.toString());
        } catch (IOException e) {
            log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        }
    }
    return EVAL_PAGE;
}


}