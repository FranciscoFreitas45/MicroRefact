package com.gs.common.bean;
 public class ComboBox4EasyUI {

 private  String id;

 private  String text;

 private  boolean selected;


public void setSelected(boolean selected){
    this.selected = selected;
}


public String getText(){
    return text;
}


public boolean isSelected(){
    return selected;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setText(String text){
    this.text = text;
}


}