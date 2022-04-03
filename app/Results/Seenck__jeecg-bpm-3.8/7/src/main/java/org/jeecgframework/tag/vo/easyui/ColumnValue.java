package org.jeecgframework.tag.vo.easyui;
 public class ColumnValue {

 private  String name;

 private  String text;

 private  String value;


public void setName(String name){
    this.name = name;
}


public String getValue(){
    return value;
}


public String getName(){
    return name;
}


public String getText(){
    return text;
}


public void setValue(String value){
    this.value = value;
}


public String toString(){
    return new StringBuffer().append("ColumnValue [name=").append(name).append(",text=").append(text).append(",value=").append(value).append("]").toString();
}


public void setText(String text){
    this.text = text;
}


}