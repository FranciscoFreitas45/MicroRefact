package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysDbmsAdviMessInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  String message;

 private  String tableDesc;

 private  String tableName;

 private  String type;

 private  String jdbcUuid;

 private  String executeSql;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

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
public String getTableName(){
    return tableName;
}


public String getMessage(){
    return message;
}


public String getExecuteSql(){
    return executeSql;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public String getType(){
    return type;
}


public String getTableDesc(){
    return tableDesc;
}


public void setMessage(String message){
    this.message = message;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setMessage"))

.queryParam("message",message)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExecuteSql(String executeSql){
    this.executeSql = executeSql;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setExecuteSql"))

.queryParam("executeSql",executeSql)
;
restTemplate.put(builder.toUriString(),null);
}


}