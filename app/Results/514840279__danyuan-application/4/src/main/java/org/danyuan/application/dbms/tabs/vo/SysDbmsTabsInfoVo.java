package org.danyuan.application.dbms.tabs.vo;
 import java.util.ArrayList;
import java.util.List;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
public class SysDbmsTabsInfoVo extends Pagination<SysDbmsTabsInfo>{

 private  String databaseUuid;

 private  String typeUuid;

 private  String jdbcUuid;

 private SysDbmsTabsInfo old;

 private SysDbmsTabsInfo now;

 public  String userindex;

 public  String uservalue;

 public  String type;

 public  String tabsuuid;

 public  String tabsName;

 public  String dbType;

 public  String esName;

 public  String tabsDesc;

 public  String tabsRows;

 public  String paramString;

 public  List<MulteityParam> paramList;


public String getTypeUuid(){
    return typeUuid;
}


public void setEsName(String esName){
    this.esName = esName;
}


public String getUserindex(){
    return userindex;
}


public String getDbType(){
    return dbType;
}


public String getTabsuuid(){
    return tabsuuid;
}


public String getTabsName(){
    return tabsName;
}


public void setJdbcUuid(String jdbcUuid){
    this.jdbcUuid = jdbcUuid;
}


public String getParamString(){
    return paramString;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public String getDatabaseUuid(){
    return databaseUuid;
}


public void setTypeUuid(String typeUuid){
    this.typeUuid = typeUuid;
}


public void setParamString(String paramString){
    this.paramString = paramString;
}


public void setNow(SysDbmsTabsInfo now){
    this.now = now;
}


public void setTabsName(String tabsName){
    this.tabsName = tabsName;
}


public String getEsName(){
    return esName;
}


public void setTabsuuid(String tabsuuid){
    this.tabsuuid = tabsuuid;
}


public void setDbType(String dbType){
    this.dbType = dbType;
}


public void setOld(SysDbmsTabsInfo old){
    this.old = old;
}


public SysDbmsTabsInfo getNow(){
    return now;
}


public String getTabsDesc(){
    return tabsDesc;
}


public void setType(String type){
    this.type = type;
}


public void setUserindex(String userindex){
    this.userindex = userindex;
}


public void setTabsRows(String tabsRows){
    this.tabsRows = tabsRows;
}


public String getUservalue(){
    return uservalue;
}


public String getType(){
    return type;
}


public List<MulteityParam> getParamList(){
    return paramList;
}


public void setTabsDesc(String tabsDesc){
    this.tabsDesc = tabsDesc;
}


public String getTabsRows(){
    return tabsRows;
}


public void setDatabaseUuid(String databaseUuid){
    this.databaseUuid = databaseUuid;
}


public SysDbmsTabsInfo getOld(){
    return old;
}


@Override
public String toString(){
    return "SysDbmsTabsInfoVo [databaseUuid=" + databaseUuid + ", typeUuid=" + typeUuid + ", old=" + old + ", now=" + now + ", userindex=" + userindex + ", uservalue=" + uservalue + ", type=" + type + ", tabsuuid=" + tabsuuid + ", tabsName=" + tabsName + ", dbType=" + dbType + ", esName=" + esName + ", tabsDesc=" + tabsDesc + ", tabsRows=" + tabsRows + ", paramString=" + paramString + ", paramList=" + paramList + "]";
}


public void setUservalue(String uservalue){
    this.uservalue = uservalue;
}


public void setParamList(List<MulteityParam> paramList){
    this.paramList = paramList;
}


}