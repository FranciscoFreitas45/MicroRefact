package org.jeecgframework.tag.core.easyui;
 import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;
public class TabTag extends TagSupport{

 private  String href;

 private  String iframe;

 private  String id;

 private  String title;

 private  String icon;

 private  String width;

 private  String heigth;

 private  boolean cache;

 private  String content;

 private  boolean closable;

 private  String langArg;


public int doStartTag(){
    return EVAL_PAGE;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setContent(String content){
    this.content = content;
}


public void setHeigth(String heigth){
    this.heigth = heigth;
}


public void setHref(String href){
    this.href = href;
}


public void setId(String id){
    this.id = id;
}


public void setTitle(String title){
    // update-begin--author:scott Date:20170219 for:class声明类，减少内存占用写法修改------
    MutiLangServiceI mutiLangService = ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
    // update-end--author:scott Date:20170219 for:class声明类，减少内存占用写法修改------
    String lang_context = mutiLangService.getLang(title, langArg);
    this.title = lang_context;
}


public void setCache(boolean cache){
    this.cache = cache;
}


public void setWidth(String width){
    this.width = width;
}


public void setClosable(boolean closable){
    this.closable = closable;
}


public void setIframe(String iframe){
    this.iframe = iframe;
}


public int doEndTag(){
    Tag t = findAncestorWithClass(this, TabsTag.class);
    TabsTag parent = (TabsTag) t;
    parent.setTab(id, title, iframe, href, icon, cache, content, width, heigth, closable);
    return EVAL_PAGE;
}


}