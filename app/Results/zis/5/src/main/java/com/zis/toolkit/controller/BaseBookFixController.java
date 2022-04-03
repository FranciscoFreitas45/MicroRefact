package com.zis.toolkit.controller;
 import java.util.List;
import org.springframework.ui.ModelMap;
public class BaseBookFixController {

 protected  long serialVersionUID;

 private  Integer maxShowCount;


public void setMaxShowCount(Integer maxShowCount){
    this.maxShowCount = maxShowCount;
}


public Integer getMaxShowCount(){
    return maxShowCount;
}


public void showResult(List<String> list,ModelMap context){
    context.put("actionMessage", "成功处理记录条数：" + list.size());
    Integer max = list.size() > maxShowCount ? maxShowCount : list.size();
    context.put("showResult", list.subList(0, max));
}


}