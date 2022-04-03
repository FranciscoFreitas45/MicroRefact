package org.sdrc.childinfo.model;
 public class IUSModel {

 private  Integer iusNId;

 private  String iusName;

 private  Integer indicatorId;

 private  String indicatorName;

 private  Integer unitId;

 private  String unitName;

 private  Integer subgroupId;

 private  String subgroupName;

 private  boolean isSelected;


public String getIusName(){
    return iusName;
}


public String getUnitName(){
    return unitName;
}


public Integer getSubgroupId(){
    return subgroupId;
}


public void setSelected(boolean isSelected){
    this.isSelected = isSelected;
}


public void setSubgroupId(Integer subgroupId){
    this.subgroupId = subgroupId;
}


public Integer getUnitId(){
    return unitId;
}


public void setSubgroupName(String subgroupName){
    this.subgroupName = subgroupName;
}


public void setUnitName(String unitName){
    this.unitName = unitName;
}


public Integer getIusNId(){
    return iusNId;
}


public void setUnitId(Integer unitId){
    this.unitId = unitId;
}


public void setIndicatorName(String indicatorName){
    this.indicatorName = indicatorName;
}


public boolean isSelected(){
    return isSelected;
}


public Integer getIndicatorId(){
    return indicatorId;
}


public void setIusNId(Integer iusNId){
    this.iusNId = iusNId;
}


public String getSubgroupName(){
    return subgroupName;
}


public void setIndicatorId(Integer indicatorId){
    this.indicatorId = indicatorId;
}


public String getIndicatorName(){
    return indicatorName;
}


public void setIusName(String iusName){
    this.iusName = iusName;
}


}