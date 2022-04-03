package org.danyuan.application.crawler.config.po;
 import java.util.ArrayList;
import java.util.List;
public class GroupList {

 private  String groupRule;

 private  List<RuleColumn> columns;

 private  String nextPageRule;


public void setColumns(List<RuleColumn> columns){
    this.columns = columns;
}


public String getNextPageRule(){
    return nextPageRule;
}


public void setGroupRule(String groupRule){
    this.groupRule = groupRule;
}


public String getGroupRule(){
    return groupRule;
}


public void setNextPageRule(String nextPageRule){
    this.nextPageRule = nextPageRule;
}


public List<RuleColumn> getColumns(){
    return columns;
}


}