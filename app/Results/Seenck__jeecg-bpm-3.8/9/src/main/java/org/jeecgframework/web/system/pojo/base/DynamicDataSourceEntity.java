package org.jeecgframework.web.system.pojo.base;
 import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_s_data_source", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DynamicDataSourceEntity {

 private  java.lang.String id;

 private  java.lang.String dbKey;

 private  java.lang.String description;

 private  java.lang.String driverClass;

 private  java.lang.String url;

 private  java.lang.String dbUser;

 private  java.lang.String dbPassword;

 private  java.lang.String dbType;

 private  java.lang.String dbName;


public void setDbKey(java.lang.String dbKey){
    this.dbKey = dbKey;
}


@Column(name = "DB_TYPE", nullable = true, precision = 50, length = 50)
public java.lang.String getDbType(){
    return this.dbType;
}


@Column(name = "DB_KEY", nullable = false, precision = 50, length = 50)
public java.lang.String getDbKey(){
    return this.dbKey;
}


@Column(name = "DRIVER_CLASS", nullable = false, precision = 50, length = 50)
public java.lang.String getDriverClass(){
    return this.driverClass;
}


public void setDbType(java.lang.String dbType){
    this.dbType = dbType;
}


@Column(name = "DB_PASSWORD", nullable = true, precision = 50, length = 50)
public java.lang.String getDbPassword(){
    return this.dbPassword;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, precision = 36, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setDescription(java.lang.String description){
    this.description = description;
}


public void setDriverClass(java.lang.String driverClass){
    this.driverClass = driverClass;
}


@Column(name = "DESCRIPTION", nullable = false, precision = 50, length = 50)
public java.lang.String getDescription(){
    return this.description;
}


public void setUrl(java.lang.String url){
    this.url = url;
}


public void setDbPassword(java.lang.String dbPassword){
    this.dbPassword = dbPassword;
}


@Column(name = "URL", nullable = false, precision = 100, length = 100)
public java.lang.String getUrl(){
    return this.url;
}


public void setDbName(java.lang.String dbName){
    this.dbName = dbName;
}


public void setDbUser(java.lang.String dbUser){
    this.dbUser = dbUser;
}


@Column(name = "DB_USER", nullable = false, precision = 50, length = 50)
public java.lang.String getDbUser(){
    return this.dbUser;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "DB_NAME", nullable = true, precision = 50, length = 50)
public java.lang.String getDbName(){
    return dbName;
}


}