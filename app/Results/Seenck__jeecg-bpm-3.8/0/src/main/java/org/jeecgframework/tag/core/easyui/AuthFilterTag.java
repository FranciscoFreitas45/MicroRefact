package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
public class AuthFilterTag extends TagSupport{

 private  long serialVersionUID;

 protected  String name;

@Autowired
 private  SystemService systemService;


public int doStartTag(){
    return super.doStartTag();
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public int doEndTag(){
    JspWriter out = null;
    // update-begin--Author:scott  Date:20170330 for：重构页面智能权限控制JS生成方法--------------------
    try {
        out = this.pageContext.getOut();
        systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
        out.print(systemService.getAuthFilterJS());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            try {
                out.clearBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    return EVAL_PAGE;
// update-end--Author:scott  Date:20170330 for：重构页面智能权限控制JS生成方法--------------------
}


}