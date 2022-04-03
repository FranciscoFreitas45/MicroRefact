package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_advi_mess_info")
@NamedQuery(name = "SysDbmsAdviMessInfo.findAll", query = "SELECT s FROM SysDbmsAdviMessInfo s")
public class SysDbmsAdviMessInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "message")
 private  String message;

@Column(name = "table_desc")
 private  String tableDesc;

@Column(name = "table_name")
 private  String tableName;

@Column(name = "type")
 private  String type;

@Column(name = "jdbc_uuid")
 private  String jdbcUuid;

@Column(name = "execute_sql")
 private  String executeSql;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysDbmsAdviMessInfo() {
    super();
}/**
 * 构造方法：
 * 描 述： TODO(这里用一句话描述这个方法的作用)
 * 参 数： @param uuid
 * 参 数： @param type
 * 参 数： @param tableDesc
 * 参 数： @param tableName
 * 参 数： @param jdbcUuid
 * 作 者 ： Administrator
 * @throws
 */
public SysDbmsAdviMessInfo(String uuid, String type, String tableDesc, String tableName, String jdbcUuid) {
    super();
    this.uuid = uuid;
    this.type = type;
    this.tableDesc = tableDesc;
    this.tableName = tableName;
    this.jdbcUuid = jdbcUuid;
}
public void setTableName(String tableName){
    this.tableName = tableName;
}


public String getTableName(){
    return tableName;
}


public String getMessage(){
    return message;
}


public String getExecuteSql(){
    return executeSql;
}


public void setMessage(String message){
    this.message = message;
}


public void setType(String type){
    this.type = type;
}


public void setJdbcUuid(String jdbcUuid){
    this.jdbcUuid = jdbcUuid;
}


public void setTableDesc(String tableDesc){
    this.tableDesc = tableDesc;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public void setExecuteSql(String executeSql){
    this.executeSql = executeSql;
}


public String getType(){
    return type;
}


public String getTableDesc(){
    return tableDesc;
}


@Override
public String toString(){
    return "SysAdviceMess [uuid=" + uuid + ", createTime=" + createTime + ", deleteFlag=" + deleteFlag + ", type=" + type + ", message=" + message + ", executeSql=" + executeSql + "]";
}


}