package com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class RuleAction {

 private  boolean markAsRead;

 private  boolean removeFromRiver;

 private  long addToBucket;


public void setAddToBucket(long addToBucket){
    this.addToBucket = addToBucket;
}


public boolean isRemoveFromRiver(){
    return removeFromRiver;
}


public void setRemoveFromRiver(boolean removeFromRiver){
    this.removeFromRiver = removeFromRiver;
}


public void setMarkAsRead(boolean markAsRead){
    this.markAsRead = markAsRead;
}


public boolean isMarkAsRead(){
    return markAsRead;
}


public long getAddToBucket(){
    return addToBucket;
}


}