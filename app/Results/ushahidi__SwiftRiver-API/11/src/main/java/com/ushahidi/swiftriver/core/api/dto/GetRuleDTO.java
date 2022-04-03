package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO.RuleAction;
import com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO.RuleCondition;
public class GetRuleDTO {

 private  long id;

 private  String name;

 private  int type;

 private  List<RuleCondition> conditions;

 private  List<RuleAction> actions;

@JsonProperty("all_conditions")
 private  boolean matchAllConditions;


public void setName(String name){
    this.name = name;
}


public void setMatchAllConditions(boolean matchAllConditions){
    this.matchAllConditions = matchAllConditions;
}


public String getName(){
    return name;
}


public void setConditions(List<RuleCondition> conditions){
    this.conditions = conditions;
}


public boolean isMatchAllConditions(){
    return matchAllConditions;
}


public int getType(){
    return type;
}


public void setActions(List<RuleAction> actions){
    this.actions = actions;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public List<RuleCondition> getConditions(){
    return conditions;
}


public List<RuleAction> getActions(){
    return actions;
}


public void setType(int type){
    this.type = type;
}


}