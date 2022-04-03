package org.live.live.vo;
 public class LiveCategoryVo {

 private  String id;

 private  String categoryName;

 private  String description;

 private  boolean enabled;

 private  int serialNo;


public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public boolean isEnabled(){
    return enabled;
}


public String getCategoryName(){
    return categoryName;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
}


public void setDescription(String description){
    this.description = description;
}


public void setSerialNo(int serialNo){
    this.serialNo = serialNo;
}


public String getDescription(){
    return description;
}


public int getSerialNo(){
    return serialNo;
}


}