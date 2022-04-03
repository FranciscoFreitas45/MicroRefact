package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_tabs_cols_info")
@NamedQuery(name = "SysDbmsTabsColsInfo.findAll", query = "SELECT s FROM SysDbmsTabsColsInfo s")
public class SysDbmsTabsColsInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "tabs_uuid")
 private  String tabsUuid;

@Column(name = "cols_desc")
 private  String colsDesc;

@Column(name = "cols_name")
 private  String colsName;

@Column(name = "cols_length", precision = 10)
 private  Integer colsLength;

@Column(name = "data_precision", precision = 10)
 private  Integer dataPrecision;

@Column(name = "useful", precision = 10)
 private  Integer useful;

@Column(name = "user_index")
 private  String userIndex;

@Column(name = "cols_order", precision = 10)
 private  Integer colsOrder;

@Column(name = "cols_type")
 private  String colsType;

@Column(name = "nullable")
 private  String nullable;

@Column(name = "cols_sort")
 private  String colsSort;

@Column(name = "data_scale", precision = 10)
 private  Integer dataScale;

@Column(name = "cols_switchable")
 private  Boolean colsSwitchable;

@Column(name = "cols_valign")
 private  String colsValign;

@Column(name = "page_list", precision = 10)
 private  Integer pageList;

@Column(name = "user_icon")
 private  String userIcon;

@Column(name = "cols_width")
 private  Integer colsWidth;

@Column(name = "dime_flag", precision = 3)
 private  Boolean dimeFlag;

@Column(name = "cols_visible")
 private  Boolean colsVisible;

@Column(name = "cols_default")
 private  String colsDefault;

@Column(name = "cols_align")
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


public void setUserIndex(String userIndex){
    this.userIndex = userIndex;
}


public void setPageList(Integer pageList){
    this.pageList = pageList;
}


public String getColsType(){
    return colsType;
}


public void setColsValign(String colsValign){
    this.colsValign = colsValign;
}


public void setDimeFlag(Boolean dimeFlag){
    this.dimeFlag = dimeFlag;
}


public void setColsType(String colsType){
    this.colsType = colsType;
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


public void setTabsUuid(String tabsUuid){
    this.tabsUuid = tabsUuid;
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


public void setColsSwitchable(Boolean colsSwitchable){
    this.colsSwitchable = colsSwitchable;
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


public void setDataScale(Integer dataScale){
    this.dataScale = dataScale;
}


public void setColsDefault(String colsDefault){
    this.colsDefault = colsDefault;
}


public void setColsDesc(String colsDesc){
    this.colsDesc = colsDesc;
}


public Integer getColsLength(){
    return colsLength;
}


public void setColsOrder(Integer colsOrder){
    this.colsOrder = colsOrder;
}


public void setColsName(String colsName){
    this.colsName = colsName;
}


public String getUserIndex(){
    return userIndex;
}


public void setColsWidth(Integer colsWidth){
    this.colsWidth = colsWidth;
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


public void setColsVisible(Boolean colsVisible){
    this.colsVisible = colsVisible;
}


public String getColsDefault(){
    return colsDefault;
}


public void setColsLength(Integer colsLength){
    this.colsLength = colsLength;
}


public String getColsSort(){
    return colsSort;
}


public void setUseful(Integer useful){
    this.useful = useful;
}


public void setColsAlign(String colsAlign){
    this.colsAlign = colsAlign;
}


public Boolean getDimeFlag(){
    return dimeFlag;
}


public void setColsSort(String colsSort){
    this.colsSort = colsSort;
}


public String getColsAlign(){
    return colsAlign;
}


public Integer getColsWidth(){
    return colsWidth;
}


}