package org.jeecgframework.tag.vo.easyui;
 public class Tab {

 private  String href;

 private  String iframe;

 private  String id;

 private  String title;

 private  String icon;

 private  String width;

 private  String heigth;

 private  boolean cache;

 private  String content;

 private  boolean closable;


public String getHref(){
    return href;
}


public void setContent(String content){
    this.content = content;
}


public void setHref(String href){
    this.href = href;
}


public String getContent(){
    return content;
}


public boolean isClosable(){
    return closable;
}


public void setTitle(String title){
    this.title = title;
}


public String getHeigth(){
    return heigth;
}


public String getId(){
    return id;
}


public String getWidth(){
    return width;
}


public void setCache(boolean cache){
    this.cache = cache;
}


public String getIframe(){
    return iframe;
}


public void setWidth(String width){
    this.width = width;
}


public void setIframe(String iframe){
    this.iframe = iframe;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public boolean isCache(){
    return cache;
}


public String getTitle(){
    return title;
}


public void setHeigth(String heigth){
    this.heigth = heigth;
}


public void setId(String id){
    this.id = id;
}


public void setClosable(boolean closable){
    this.closable = closable;
}


}