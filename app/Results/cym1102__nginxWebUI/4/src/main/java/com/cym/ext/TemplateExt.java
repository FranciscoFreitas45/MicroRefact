package com.cym.ext;
 import java.util.List;
import com.cym.model.Param;
import com.cym.model.Template;
public class TemplateExt {

 private Template template;

 private Integer count;

 private List<Param> paramList;


public void setTemplate(Template template){
    this.template = template;
}


public List<Param> getParamList(){
    return paramList;
}


public Template getTemplate(){
    return template;
}


public void setParamList(List<Param> paramList){
    this.paramList = paramList;
}


public Integer getCount(){
    return count;
}


public void setCount(Integer count){
    this.count = count;
}


}