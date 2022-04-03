package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO.RuleAction;
import com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO.RuleCondition;
public class RuleUpdateNotification {

 private  long id;

@JsonProperty("river_id")
 private  long riverId;

 private  List<RuleCondition> conditions;

 private  List<RuleAction> actions;

@JsonProperty("all_conditions")
 private  boolean matchAllConditions;


public void setMatchAllConditions(boolean matchAllConditions){
    this.matchAllConditions = matchAllConditions;
}


public void setConditions(List<RuleCondition> conditions){
    this.conditions = conditions;
}


public boolean isMatchAllConditions(){
    return matchAllConditions;
}


public void setRiverId(long riverId){
    this.riverId = riverId;
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


public long getRiverId(){
    return riverId;
}


}