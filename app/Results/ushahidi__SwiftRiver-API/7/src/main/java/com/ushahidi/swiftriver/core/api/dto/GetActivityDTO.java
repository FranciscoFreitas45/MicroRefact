package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
import com.ushahidi.swiftriver.core.Interface.AccountDTO;
import com.ushahidi.swiftriver.core.Interface.AccountDTO;
public class GetActivityDTO {

 private  String id;

@JsonProperty("date_added")
 private  String actionDateAdd;

 private  AccountDTO account;

 private  String action;

@JsonProperty("action_on")
 private  String actionOn;

@JsonProperty("action_on_obj")
 private  Object actionOnObj;

 private  AccountDTO actionTo;


public void setActionDateAdd(String actionDateAdd){
    this.actionDateAdd = actionDateAdd;
}


public String getActionDateAdd(){
    return actionDateAdd;
}


public void setAction(String action){
    this.action = action;
}


public AccountDTO getActionTo(){
    return actionTo;
}


public String getActionOn(){
    return actionOn;
}


public void setActionTo(AccountDTO actionTo){
    this.actionTo = actionTo;
}


public String getId(){
    return id;
}


public String getAction(){
    return action;
}


public void setActionOn(String actionOn){
    this.actionOn = actionOn;
}


public AccountDTO getAccount(){
    return account;
}


public void setId(String id){
    this.id = id;
}


public void setAccount(AccountDTO account){
    this.account = account;
}


public Object getActionOnObj(){
    return actionOnObj;
}


public void setActionOnObj(Object actionOnObj){
    this.actionOnObj = actionOnObj;
}


}