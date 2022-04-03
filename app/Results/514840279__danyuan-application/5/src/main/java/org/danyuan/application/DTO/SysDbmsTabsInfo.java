package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysDbmsTabsInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  Integer tabsSpace;

 private  String tabsName;

 private  String tabsDesc;

 private  Integer dissql;

 private  Integer tabsRows;

 private  Integer tabsOrder;

 private  String typeUuid;

 private  String dbType;

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
public String getTypeUuid(){
    return typeUuid;
}


public String getDbType(){
    return dbType;
}


public Integer getTabsOrder(){
    return tabsOrder;
}


public String getTabsDesc(){
    return tabsDesc;
}


public String getTabsName(){
    return tabsName;
}


public Integer getDissql(){
    return dissql;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public Integer getTabsSpace(){
    return tabsSpace;
}


public Integer getTabsRows(){
    return tabsRows;
}


}