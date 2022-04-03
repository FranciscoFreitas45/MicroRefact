package com.xwtec.xwserver.util.tag;
 import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.CommonUtil;
public class TurnPageTag extends TagSupport{

 private  long serialVersionUID;

 private  Logger log;

 private  Page page;

 private  String requestPath;

 private  String prefix;


public int doStartTag(){
    try {
        // 标签体内容
        StringBuffer tagContent = new StringBuffer();
        // 脚本--start
        tagContent.append("<script>");
        tagContent.append("function changePage(pageNo) {");
        tagContent.append("if(!pageNo) {");
        tagContent.append("pageNo = $('#currentPageText').val();");
        tagContent.append("}");
        tagContent.append("var rule = /^[0-9]*[1-9][0-9]*$/;");
        tagContent.append("if(!rule.test(pageNo) || pageNo < 1) {");
        tagContent.append("pageNo = 1;");
        tagContent.append("}");
        tagContent.append("if(pageNo > " + this.page.getTotalPage() + ") {");
        tagContent.append("pageNo = " + this.page.getTotalPage() + ";");
        tagContent.append("}");
        tagContent.append("$('#currentPage').val(pageNo);");
        if (!CommonUtil.isEmpty(this.requestPath)) {
            tagContent.append("$('form').eq(0).attr('action','" + this.requestPath + "');");
        }
        tagContent.append("$('form').eq(0).submit();");
        tagContent.append("}");
        tagContent.append("</script>");
        // 脚本--end
        // 分页内容--start
        tagContent.append("<div class='page fix'>");
        String currentPage;
        if (CommonUtil.isEmpty(this.prefix)) {
            currentPage = "currentPage";
        } else {
            currentPage = this.prefix + ".currentPage";
        }
        tagContent.append("<input type='hidden' name='" + currentPage + "' id='currentPage' value='" + this.page.getCurrentPage() + "'/>");
        tagContent.append("共 <b>" + this.page.getTotalRow() + "</b> 条");
        if (this.page.getCurrentPage() != 1) {
            tagContent.append("<a href='###' onclick=\"changePage('1');\" class='first'>首页</a>");
            tagContent.append("<a href='###' onclick=\"changePage('" + (this.page.getCurrentPage() - 1) + "');\" class='pre'>上一页</a>");
        }
        tagContent.append("当前第<span>" + this.page.getCurrentPage() + "/" + this.page.getTotalPage() + "</span>页");
        if (this.page.getCurrentPage() != this.page.getTotalPage()) {
            tagContent.append("<a href='###' onclick=\"changePage('" + (this.page.getCurrentPage() + 1) + "')\" class='next'>下一页</a>");
            tagContent.append("<a href='###' onclick=\"changePage('" + this.page.getTotalPage() + "');\" class='last'>末页</a>");
        }
        tagContent.append("跳至 &nbsp;  <input type='text' id='currentPageText' value='" + this.page.getCurrentPage() + "' class='allInput w28' /> &nbsp; 页 &nbsp;");
        tagContent.append("<a href='###' onclick='changePage();' class='go'>GO</a>");
        tagContent.append("</div>");
        // 分页内容--end
        super.pageContext.getOut().write(tagContent.toString());
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return SKIP_BODY;
}


public String getPrefix(){
    return prefix;
}


public void setRequestPath(String requestPath){
    this.requestPath = requestPath;
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


public String getRequestPath(){
    return requestPath;
}


public void setPage(Page page){
    // 获取分页对象的同时对当前页数与总页数进行异常处理：
    // 当前页数小于1置为1，总页数小于置为1
    if (page.getCurrentPage() < 1) {
        page.setCurrentPage(1);
    }
    if (page.getTotalPage() < 1) {
        page.setTotalPage(1);
    }
    this.page = page;
}


}