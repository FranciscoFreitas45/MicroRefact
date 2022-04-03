package com.gbcom.common.template.xml.prefilter;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("conditionContext")
public class PreFilterConditionContext {

@XStreamAlias("filterSwitch")
 private  boolean filterSwitch;

@XStreamAlias("list")
 private  List<PreFilterCondition> list;


public void setFilterSwitch(boolean filterSwitch){
    this.filterSwitch = filterSwitch;
}


public List<PreFilterCondition> getList(){
    return list;
}


public boolean isFilterSwitch(){
    return filterSwitch;
}


public void setList(List<PreFilterCondition> list){
    this.list = list;
}


}