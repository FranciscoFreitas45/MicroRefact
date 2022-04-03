package org.live.sys.vo;
 import java.util.Map;
public class TreeViewNode {

 private  String text;

 private  String type;

 public  Map<String,Object> additionalParameters;


public Map<String,Object> getAdditionalParameters(){
    return additionalParameters;
}


public String getType(){
    return type;
}


public String getText(){
    return text;
}


public void setAdditionalParameters(Map<String,Object> additionalParameters){
    this.additionalParameters = additionalParameters;
}


public void setType(String type){
    this.type = type;
}


public void setText(String text){
    this.text = text;
}


}