package com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class RuleCondition {

 private  String field;

 private  String operator;

 private  String value;


public String getValue(){
    return value;
}


public void setField(String field){
    this.field = field;
}


public void setValue(String value){
    this.value = value;
}


public String getField(){
    return field;
}


public void setOperator(String operator){
    this.operator = operator;
}


public String getOperator(){
    return operator;
}


}