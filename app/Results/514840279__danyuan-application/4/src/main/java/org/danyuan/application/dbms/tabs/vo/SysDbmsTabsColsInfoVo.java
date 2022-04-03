package org.danyuan.application.dbms.tabs.vo;
 import java.util.ArrayList;
import java.util.List;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
public class SysDbmsTabsColsInfoVo extends Pagination<SysDbmsTabsColsInfo>{

 public  String userindex;

 public  String uservalue;

 public  String jdbcUuid;

 public  String tabsUuid;

 public  String tabsName;

 public  String dbType;

 public  String esName;

 public  String tabsDesc;

 public  Integer total;

 public  String type;

 public  String paramString;

 public  List<MulteityParam> paramList;

 public  String mapString;


public void setTotal(Integer total){
    this.total = total;
}


public String getMapString(){
    return mapString;
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


public void setTabsUuid(String tabsUuid){
    this.tabsUuid = tabsUuid;
}


public void setParamString(String paramString){
    this.paramString = paramString;
}


public String getTabsUuid(){
    return tabsUuid;
}


public void setTabsName(String tabsName){
    this.tabsName = tabsName;
}


public void setMapString(String mapString){
    this.mapString = mapString;
}


public String getEsName(){
    return esName;
}


public void setDbType(String dbType){
    this.dbType = dbType;
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


public Integer getTotal(){
    return total;
}


@Override
public String toString(){
    return "SysDbmsTabsColsInfoVo [userindex=" + userindex + ", uservalue=" + uservalue + ", tabsUuid=" + tabsUuid + ", tabsName=" + tabsName + ", dbType=" + dbType + ", esName=" + esName + ", tabsDesc=" + tabsDesc + ", total=" + total + ", type=" + type + ", paramString=" + paramString + ", paramList=" + paramList + ", mapString=" + mapString + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", uuid=" + uuid + ", searchText=" + searchText + ", username=" + username + ", sortName=" + sortName + ", filter=" + filter + ", sortOrder=" + sortOrder + ",  info=" + info + ", map=" + map + "]";
}


public void setUservalue(String uservalue){
    this.uservalue = uservalue;
}


public void setParamList(List<MulteityParam> paramList){
    this.paramList = paramList;
}


}