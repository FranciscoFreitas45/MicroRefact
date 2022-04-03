package DTO;
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


public String getText(){
    return text;
}


public String getId(){
    return id;
}


public List<ComboTree> getChildren(){
    return children;
}


public String getState(){
    return state;
}


public void setId(String id){
    this.id = id;
}


public Boolean getChecked(){
    return checked;
}


public void setText(String text){
    this.text = text;
}


}