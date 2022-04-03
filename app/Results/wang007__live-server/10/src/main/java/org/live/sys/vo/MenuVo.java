package org.live.sys.vo;
 public class MenuVo {

 private  String id;

 private  Integer serialNo;

 private  String menuName;

 private  String menuUrl;

 private  String menuIcon;

 private  String permissionValue;

 private  int isEnable;

 private  int saveType;

 private  String referenceId;


public void setReferenceId(String referenceId){
    this.referenceId = referenceId;
}


public String getMenuUrl(){
    return menuUrl;
}


public String getId(){
    return id;
}


public void setMenuIcon(String menuIcon){
    this.menuIcon = menuIcon;
}


public void setPermissionValue(String permissionValue){
    this.permissionValue = permissionValue;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public String getReferenceId(){
    return referenceId;
}


public void setSaveType(int saveType){
    this.saveType = saveType;
}


public String getMenuIcon(){
    return menuIcon;
}


public void setId(String id){
    this.id = id;
}


public String getMenuName(){
    return menuName;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


public void setIsEnable(int isEnable){
    this.isEnable = isEnable;
}


public int getSaveType(){
    return saveType;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


public Integer getSerialNo(){
    return serialNo;
}


public String getPermissionValue(){
    return permissionValue;
}


public int getIsEnable(){
    return isEnable;
}


}