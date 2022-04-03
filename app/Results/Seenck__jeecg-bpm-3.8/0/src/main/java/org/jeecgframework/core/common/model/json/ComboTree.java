package org.jeecgframework.core.common.model.json;
 import java.util.List;
import java.util.Map;
public class ComboTree {

 private  String id;

 private  String text;

 private  String iconCls;

 private  Boolean checked;

 private  Map<String,Object> attributes;

 private  List<ComboTree> children;

 private  String state;


public String getIconCls(){
    return iconCls;
}


public Map<String,Object> getAttributes(){
    return attributes;
}


public void setIconCls(String iconCls){
    this.iconCls = iconCls;
}


public String getText(){
    return text;
}


public String getId(){
    return id;
}


public void setAttributes(Map<String,Object> attributes){
    this.attributes = attributes;
}


public List<ComboTree> getChildren(){
    return children;
}


public String getState(){
    return state;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


public void setId(String id){
    this.id = id;
}


public void setState(String state){
    this.state = state;
}


public Boolean getChecked(){
    return checked;
}


public void setChildren(List<ComboTree> children){
    this.children = children;
}


public void setText(String text){
    this.text = text;
}


}