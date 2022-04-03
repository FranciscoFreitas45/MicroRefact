package org.danyuan.application.DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysDbmsTabsColsInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  String tabsUuid;

 private  String colsDesc;

 private  String colsName;

 private  Integer colsLength;

 private  Integer dataPrecision;

 private  Integer useful;

 private  String userIndex;

 private  Integer colsOrder;

 private  String colsType;

 private  String nullable;

 private  String colsSort;

 private  Integer dataScale;

 private  Boolean colsSwitchable;

 private  String colsValign;

 private  Integer pageList;

 private  String userIcon;

 private  Integer colsWidth;

 private  Boolean dimeFlag;

 private  Boolean colsVisible;

 private  String colsDefault;

 private  String colsAlign;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysDbmsTabsColsInfo() {
    super();
}public SysDbmsTabsColsInfo(String tabsUuid) {
    this.tabsUuid = tabsUuid;
}/**
 * 构造方法：
 * 描 述： TODO(这里用一句话描述这个方法的作用)
 * 参 数： @param uuid
 * 参 数： @param tabsUuid
 * 参 数： @param colsName
 * 参 数： @param colsDesc
 * 参 数： @param colsType
 * 参 数： @param colsLength
 * 参 数： @param colsOrder
 * 参 数： @param colsAlign
 * 参 数： @param colsValign
 * 参 数： @param colsWidth
 * 参 数： @param colsVisible
 * 参 数： @param colsSwitchable
 * 参 数： @param userIndex
 * 参 数： @param userIcon
 * 参 数： @param discription
 * 参 数： @param createTime
 * 参 数： @param createUser
 * 参 数： @param updateTime
 * 参 数： @param updateUser
 * 参 数： @param deleteFlag
 * 作 者 ： Administrator
 * @throws
 */
public SysDbmsTabsColsInfo(String uuid, String tabsUuid, String colsName, String colsDesc, String colsType, Integer colsLength, Integer pageList, Integer colsOrder, String colsAlign, String colsValign, Integer colsWidth, Boolean colsVisible, Boolean colsSwitchable, String userIndex, String userIcon, String discription, Date createTime, String createUser, Date updateTime, String updateUser, Integer deleteFlag) {
    super();
    this.uuid = uuid;
    this.tabsUuid = tabsUuid;
    this.colsName = colsName;
    this.colsDesc = colsDesc;
    this.colsType = colsType;
    this.colsLength = colsLength;
    this.colsOrder = colsOrder;
    this.colsAlign = colsAlign;
    this.colsValign = colsValign;
    this.colsWidth = colsWidth;
    this.colsVisible = colsVisible;
    this.colsSwitchable = colsSwitchable;
    this.userIndex = userIndex;
    this.userIcon = userIcon;
    this.discription = discription;
    this.createTime = createTime;
    this.createUser = createUser;
    this.updateTime = updateTime;
    this.updateUser = updateUser;
    this.deleteFlag = deleteFlag;
}/**
 * 构造方法：
 * 描 述： TODO(这里用一句话描述这个方法的作用)
 * 参 数： @param uuid
 * 参 数： @param tabsUuid
 * 参 数： @param colsName
 * 参 数： @param colsDesc
 * 参 数： @param colsType
 * 参 数： @param colsLength
 * 参 数： @param colsOrder
 * 参 数： @param colsAlign
 * 参 数： @param colsValign
 * 参 数： @param colsWidth
 * 参 数： @param colsVisible
 * 参 数： @param colsSwitchable
 * 参 数： @param deleteFlag
 * 作 者 ： Administrator
 * @throws
 */
public SysDbmsTabsColsInfo(String uuid, String tabsUuid, String colsName, String colsDesc, String colsType, Integer colsLength, Integer pageList, Integer colsOrder, String colsAlign, String colsValign, Integer colsWidth, Boolean colsVisible, Boolean colsSwitchable, Integer deleteFlag) {
    super();
    this.uuid = uuid;
    this.tabsUuid = tabsUuid;
    this.colsName = colsName;
    this.colsDesc = colsDesc;
    this.colsType = colsType;
    this.colsLength = colsLength;
    this.colsOrder = colsOrder;
    this.colsAlign = colsAlign;
    this.colsValign = colsValign;
    this.colsWidth = colsWidth;
    this.colsVisible = colsVisible;
    this.colsSwitchable = colsSwitchable;
    this.deleteFlag = deleteFlag;
}
public Integer getDataPrecision(){
    return dataPrecision;
}


public void setPageList(Integer pageList){
    this.pageList = pageList;
}


public String getColsType(){
    return colsType;
}


public void setDimeFlag(Boolean dimeFlag){
    this.dimeFlag = dimeFlag;
}


public void setUserIcon(String userIcon){
    this.userIcon = userIcon;
}


public Integer getUseful(){
    return useful;
}


public String getColsValign(){
    return colsValign;
}


public String getColsName(){
    return colsName;
}


public Integer getPageList(){
    return pageList;
}


public void setNullable(String nullable){
    if ("YES".equals(nullable)) {
        this.nullable = "Y";
    } else if ("NO".equals(nullable)) {
        this.nullable = "N";
    } else {
        this.nullable = nullable;
    }
}


public Integer getColsOrder(){
    return colsOrder;
}


public String getColsDesc(){
    return colsDesc;
}


public String getTabsUuid(){
    return tabsUuid;
}


public Integer getDataScale(){
    return dataScale;
}


public void setColsDefault(String colsDefault){
    this.colsDefault = colsDefault;
}


public Integer getColsLength(){
    return colsLength;
}


public void setColsName(String colsName){
    this.colsName = colsName;
}


public String getUserIndex(){
    return userIndex;
}


public void setDataPrecision(Integer dataPrecision){
    this.dataPrecision = dataPrecision;
}


public Boolean getColsVisible(){
    return colsVisible;
}


public String getNullable(){
    return nullable;
}


public Boolean getColsSwitchable(){
    return colsSwitchable;
}


public String getUserIcon(){
    return userIcon;
}


public String getColsDefault(){
    return colsDefault;
}


public String getColsSort(){
    return colsSort;
}


public void setColsAlign(String colsAlign){
    this.colsAlign = colsAlign;
}


public Boolean getDimeFlag(){
    return dimeFlag;
}


public String getColsAlign(){
    return colsAlign;
}


public Integer getColsWidth(){
    return colsWidth;
}


}