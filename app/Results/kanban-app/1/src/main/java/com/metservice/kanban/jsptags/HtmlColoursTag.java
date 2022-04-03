package com.metservice.kanban.jsptags;
 import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import com.metservice.kanban.charts.KanbanDrawingSupplier;
import com.metservice.kanban.model.HtmlColour;
public class HtmlColoursTag extends TagSupport{

 private  long serialVersionUID;

 private  String name;

 private  int series;


public int doStartTag(){
    HtmlColour[] htmlColours = KanbanDrawingSupplier.getHtmlColours(series);
    pageContext.getRequest().setAttribute(name, htmlColours);
    return EVAL_PAGE;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public int getSeries(){
    return series;
}


public void setSeries(int series){
    this.series = series;
}


}