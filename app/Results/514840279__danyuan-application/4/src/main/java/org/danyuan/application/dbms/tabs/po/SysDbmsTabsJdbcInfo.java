package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_tabs_jdbc_info")
@NamedQuery(name = "SysDbmsTabsJdbcInfo.findAll", query = "SELECT s FROM SysDbmsTabsJdbcInfo s")
public class SysDbmsTabsJdbcInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "driver")
 private  String driver;

@Column(name = "username")
 private  String username;

@Column(name = "db_type")
 private  String dbType;

@Column(name = "database_name")
 private  String databaseName;

@Column(name = "type")
 private  String type;

@Column(name = "port")
 private  String port;

@Column(name = "ip")
 private  String ip;

@Column(name = "password")
 private  String password;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsTabsJdbcInfo() {
    super();
}
public void setPassword(String password){
    this.password = password;
}


public void setDriver(String driver){
    this.driver = driver;
}


public void setUsername(String username){
    this.username = username;
}


public String getDbType(){
    return dbType;
}


public String getIp(){
    return ip;
}


public void setDbType(String dbType){
    this.dbType = dbType;
}


public String getDatabaseName(){
    return databaseName;
}


public void setType(String type){
    this.type = type;
}


public String getDriver(){
    return driver;
}


public void setPort(String port){
    this.port = port;
}


public void setIp(String ip){
    this.ip = ip;
}


public String getUsername(){
    return username;
}


public String getPassword(){
    return password;
}


public String getPort(){
    return port;
}


public String getType(){
    return type;
}


public void setDatabaseName(String databaseName){
    this.databaseName = databaseName;
}


}