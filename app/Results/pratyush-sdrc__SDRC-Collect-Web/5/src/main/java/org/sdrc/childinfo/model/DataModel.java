package org.sdrc.childinfo.model;
 public class DataModel {

 private  String areaCode;

 private  String level;

 private  int arealevel;

 private  int areaNId;

 private  String areaName;

 private  int areaParentNId;

 private  Boolean isVisible;

 private  Boolean isSelected;


public Boolean getIsVisible(){
    return isVisible;
}


public void setIsVisible(Boolean isVisible){
    this.isVisible = isVisible;
}


public Boolean getIsSelected(){
    return isSelected;
}


public void setIsSelected(Boolean isSelected){
    this.isSelected = isSelected;
}


public void setAreaNId(int areaNId){
    this.areaNId = areaNId;
}


public void setArealevel(int arealevel){
    this.arealevel = arealevel;
}


public void setAreaCode(String areaCode){
    this.areaCode = areaCode;
}


public int getAreaNId(){
    return areaNId;
}


public String getAreaName(){
    return areaName;
}


public void setAreaParentNId(int areaParentNId){
    this.areaParentNId = areaParentNId;
}


public void setLevel(String level){
    this.level = level;
}


public String getLevel(){
    return level;
}


public String getAreaCode(){
    return areaCode;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


public int getAreaParentNId(){
    return areaParentNId;
}


public int getArealevel(){
    return arealevel;
}


}