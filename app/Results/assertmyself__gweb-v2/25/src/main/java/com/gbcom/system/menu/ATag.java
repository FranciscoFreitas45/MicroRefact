package com.gbcom.system.menu;
 public class ATag {

 private  String href;

 private  String func;

 private  String text;

public ATag() {
}public ATag(String href, String func, String text) {
    this.href = href;
    this.func = func;
    this.text = text;
}
public String getHref(){
    return href;
}


public String getFunc(){
    return func;
}


public void setHref(String href){
    this.href = href;
}


public String getText(){
    return text;
}


public void setFunc(String func){
    this.func = func;
}


public void setText(String text){
    this.text = text;
}


}