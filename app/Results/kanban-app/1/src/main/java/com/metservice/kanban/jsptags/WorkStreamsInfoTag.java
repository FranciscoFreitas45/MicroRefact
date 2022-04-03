package com.metservice.kanban.jsptags;
 import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import com.metservice.kanban.model.WorkItem;
public class WorkStreamsInfoTag extends TagSupport{

 private  long serialVersionUID;

 private  WorkItem workItem;

 private  String cssClass;


public int doStartTag(){
    try {
        for (String s : workItem.getWorkStreams()) {
            pageContext.getOut().write(String.format("<span class='%s'>[%s]</span> ", cssClass, s));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return EVAL_PAGE;
}


public WorkItem getWorkItem(){
    return workItem;
}


public void setCssClass(String cssClass){
    this.cssClass = cssClass;
}


public void setWorkItem(WorkItem workItem){
    this.workItem = workItem;
}


}