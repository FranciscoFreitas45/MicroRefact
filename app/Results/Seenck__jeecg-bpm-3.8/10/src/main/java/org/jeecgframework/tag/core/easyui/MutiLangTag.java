package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.jeecgframework.core.util.StringUtil;
@SuppressWarnings({ "serial", "rawtypes", "unchecked", "static-access" })
public class MutiLangTag extends TagSupport{

 protected  String langKey;

 protected  String langArg;


public int doStartTag(){
    return EVAL_PAGE;
}


public void setLangArg(String langArg){
    this.langArg = langArg;
}


public String end(){
    // update-begin--author:scott Date:20170219 for:class声明类，减少内存占用写法修改------
    MutiLangServiceI mutiLangService = ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
    // update-end--author:scott Date:20170219 for:class声明类，减少内存占用写法修改------
    String lang_context = mutiLangService.getLang(langKey, langArg);
    return lang_context;
}


public void setLangKey(String langKey){
    this.langKey = langKey;
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


}