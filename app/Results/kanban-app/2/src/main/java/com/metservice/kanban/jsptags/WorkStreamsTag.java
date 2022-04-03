package com.metservice.kanban.jsptags;
 import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.Interface.WorkItem;
public class WorkStreamsTag extends TagSupport{

 private  long serialVersionUID;

 private  Logger LOGGER;

 private  String name;

 private  KanbanProject project;

 private  WorkItem workItem;

 private  String initialWorkStream;


public int doStartTag(){
    try {
        if (project != null) {
            pageContext.getOut().write("var " + name + " = [ ");
            for (String ws : project.getWorkStreams()) {
                ws = ws.trim();
                pageContext.getOut().write("'" + ws + "',");
            }
            StringBuilder initialTags = new StringBuilder();
            if (!StringUtils.isEmpty(initialWorkStream)) {
                initialTags.append('\'').append(initialWorkStream).append('\'');
            }
            if (workItem != null) {
                for (String s : workItem.getWorkStreams()) {
                    if (initialTags.length() > 0) {
                        initialTags.append(',');
                    }
                    initialTags.append('\'').append(s).append('\'');
                }
            }
            pageContext.getOut().write("'' ];\n");
            pageContext.getOut().write("       $(function() {\n" + "               $('#workStreams').tagit({\n" + "                       tagSource   : workStreams,\n" + "                       triggerKeys : [ 'enter', 'comma' ],\n" + "                       initialTags : [ " + initialTags + " ],\n" + "                       select: true" + "               });\n" + "        });\n");
        } else {
            pageContext.getOut().write("ERROR: Project is not set.");
        }
    } catch (IOException e) {
        LOGGER.error("Got exception handling work streams tag", e);
    }
    return EVAL_PAGE;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public WorkItem getWorkItem(){
    return workItem;
}


public void setInitialWorkStream(String initialWorkStream){
    this.initialWorkStream = initialWorkStream;
}


public void setWorkItem(WorkItem workItem){
    this.workItem = workItem;
}


public void setProject(KanbanProject project){
    this.project = project;
}


public KanbanProject getProject(){
    return project;
}


public String getInitialWorkStream(){
    return initialWorkStream;
}


}