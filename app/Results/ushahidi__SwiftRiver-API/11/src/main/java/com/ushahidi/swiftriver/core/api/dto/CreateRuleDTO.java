package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class CreateRuleDTO {

 private  String name;

 private  Integer type;

 private  List<RuleCondition> conditions;

 private  List<RuleAction> actions;

@JsonProperty("all_conditions")
 private  boolean matchAllConditions;

 private  String field;

 private  String operator;

 private  String value;

 private  boolean markAsRead;

 private  boolean removeFromRiver;

 private  long addToBucket;


public void setName(String name){
    this.name = name;
}


public void setMatchAllConditions(boolean matchAllConditions){
    this.matchAllConditions = matchAllConditions;
}


public void setAddToBucket(long addToBucket){
    this.addToBucket = addToBucket;
}


public boolean isRemoveFromRiver(){
    return removeFromRiver;
}


public void setField(String field){
    this.field = field;
}


public String getName(){
    return name;
}


public boolean isMatchAllConditions(){
    return matchAllConditions;
}


public void setActions(List<RuleAction> actions){
    this.actions = actions;
}


public String getField(){
    return field;
}


public void setMarkAsRead(boolean markAsRead){
    this.markAsRead = markAsRead;
}


public List<RuleCondition> getConditions(){
    return conditions;
}


public List<RuleAction> getActions(){
    return actions;
}


public void setType(Integer type){
    this.type = type;
}


public long getAddToBucket(){
    return addToBucket;
}


public String getValue(){
    return value;
}


public void setConditions(List<RuleCondition> conditions){
    this.conditions = conditions;
}


public void setRemoveFromRiver(boolean removeFromRiver){
    this.removeFromRiver = removeFromRiver;
}


public Integer getType(){
    return type;
}


public void setValue(String value){
    this.value = value;
}


public void setOperator(String operator){
    this.operator = operator;
}


public boolean isMarkAsRead(){
    return markAsRead;
}


public String getOperator(){
    return operator;
}


}