package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_tabs_info")
@NamedQuery(name = "SysDbmsTabsInfo.findAll", query = "SELECT s FROM SysDbmsTabsInfo s")
public class SysDbmsTabsInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "tabs_space", precision = 10)
 private  Integer tabsSpace;

@Column(name = "tabs_name")
 private  String tabsName;

@Column(name = "tabs_desc")
 private  String tabsDesc;

@Column(name = "dissql", precision = 10)
 private  Integer dissql;

@Column(name = "tabs_rows", precision = 10)
 private  Integer tabsRows;

@Column(name = "tabs_order", precision = 10)
 private  Integer tabsOrder;

@Column(name = "type_uuid")
 private  String typeUuid;

@Column(name = "db_type")
 private  String dbType;

@Column(name = "jdbc_uuid")
 private  String jdbcUuid;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsTabsInfo() {
    super();
}
public void setTabsName(String tabsName){
    this.tabsName = tabsName;
}


public String getTypeUuid(){
    return typeUuid;
}


public String getDbType(){
    return dbType;
}


public void setDbType(String dbType){
    this.dbType = dbType;
}


public Integer getTabsOrder(){
    return tabsOrder;
}


public void setTabsOrder(Integer tabsOrder){
    this.tabsOrder = tabsOrder;
}


public String getTabsDesc(){
    return tabsDesc;
}


public String getTabsName(){
    return tabsName;
}


public void setDissql(Integer dissql){
    this.dissql = dissql;
}


public void setJdbcUuid(String jdbcUuid){
    this.jdbcUuid = jdbcUuid;
}


public Integer getDissql(){
    return dissql;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public void setTabsRows(Integer tabsRows){
    this.tabsRows = tabsRows;
}


public void setTypeUuid(String typeUuid){
    this.typeUuid = typeUuid;
}


public Integer getTabsSpace(){
    return tabsSpace;
}


public void setTabsDesc(String tabsDesc){
    this.tabsDesc = tabsDesc;
}


public Integer getTabsRows(){
    return tabsRows;
}


public void setTabsSpace(Integer tabsSpace){
    this.tabsSpace = tabsSpace;
}


}