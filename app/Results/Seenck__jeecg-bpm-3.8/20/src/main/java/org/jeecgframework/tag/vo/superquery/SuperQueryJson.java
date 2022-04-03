package org.jeecgframework.tag.vo.superquery;
 import java.util.ArrayList;
import java.util.List;
public class SuperQueryJson {

 private  String type;

 private  List<Table> tabs;

 private  List<TConfig> configs;


public void setTabs(List<Table> tabs){
    this.tabs = tabs;
}


public void setConfigs(List<TConfig> configs){
    this.configs = configs;
}


public List<Table> getTabs(){
    return tabs;
}


public List<TConfig> getConfigs(){
    return configs;
}


public String getType(){
    return type;
}


public void addTconfig(TConfig t){
    configs.add(t);
}


public void setType(String type){
    this.type = type;
}


public void addTable(Table e){
    tabs.add(e);
}


}