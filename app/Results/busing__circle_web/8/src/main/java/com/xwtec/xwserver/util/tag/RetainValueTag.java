package com.xwtec.xwserver.util.tag;
 import java.util.Map.Entry;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.CommonUtil;
public class RetainValueTag extends TagSupport{

 private  long serialVersionUID;

 private  Logger log;

 private  Page page;

 private  String prefix;


public int doStartTag(){
    try {
        // 标签体内容
        StringBuffer tagContent = new StringBuffer();
        String currentPage;
        String searchParam;
        if (CommonUtil.isEmpty(this.prefix)) {
            currentPage = "currentPage";
            searchParam = "searchParam";
        } else {
            currentPage = this.prefix + ".currentPage";
            searchParam = this.prefix + ".searchParam";
        }
        if (this.page != null) {
            tagContent.append("<input type='hidden' name='" + currentPage + "' value='" + this.page.getCurrentPage() + "'/>");
            if (this.page.getSearchParam() != null) {
                for (Entry<String, String> entry : this.page.getSearchParam().entrySet()) {
                    tagContent.append("<input type='hidden' name='" + searchParam + "[" + entry.getKey() + "]' value='" + entry.getValue() + "'/>");
                }
            }
        }
        super.pageContext.getOut().write(tagContent.toString());
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return SKIP_BODY;
}


public String getPrefix(){
    return prefix;
}


public void setPrefix(String prefix){
    this.prefix = prefix;
}


public Page getPage(){
    return page;
}


public int doEndTag(){
    return EVAL_PAGE;
}


public void setPage(Page page){
    this.page = page;
}


}