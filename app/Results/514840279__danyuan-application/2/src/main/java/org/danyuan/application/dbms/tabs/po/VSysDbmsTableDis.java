package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "v_sys_dbms_table_dis")
@NamedQuery(name = "VSysDbmsTableDis.findAll", query = "SELECT s FROM VSysDbmsTableDis s")
public class VSysDbmsTableDis implements Serializable{

 private  long serialVersionUID;

@Id
@Column(unique = true, nullable = false, columnDefinition = " varchar(36) COMMENT '主键'")
 protected  String uuid;

@Column(name = "jdbc_uuid", columnDefinition = " varchar(36) COMMENT '数据库表id'")
 private  String jdbcUuid;

@Column(name = "drop_sql", columnDefinition = " varchar(2000) COMMENT 'drop_sql'")
 private  String dropSql;

@Column(name = "rename_sql", columnDefinition = "varchar(2000) COMMENT 'rename_sql'")
 private  String renameSql;

@Column(name = "tabs_desc", columnDefinition = "varchar(200) COMMENT '表的含义'")
 private  String tabsDesc;

@Column(name = "type", columnDefinition = "varchar(200) COMMENT '数据库类型'")
 private  String type;

@Column(name = "type_name", columnDefinition = "varchar(200) COMMENT '类型名'")
 private  String typeName;

@Column(name = "dis_sql", columnDefinition = "varchar(2000) COMMENT 'dis_sql'")
 private  String disSql;

@Column(name = "tabs_rows", columnDefinition = "varchar(200) COMMENT '数据库表数据量'")
 private  Integer tabsRows;

@Column(name = "tabs_name", columnDefinition = "varchar(200) COMMENT '数据库表名'")
 private  String tabsName;

@Column(name = "reset_sql", columnDefinition = "varchar(200) COMMENT 'reset_sql'")
 private  String resetSql;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public VSysDbmsTableDis() {
    super();
}
public void setTabsName(String tabsName){
    this.tabsName = tabsName;
}


public String getTabsDesc(){
    return tabsDesc;
}


public void setType(String type){
    this.type = type;
}


public void setDisSql(String disSql){
    this.disSql = disSql;
}


public String getTabsName(){
    return tabsName;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public void setJdbcUuid(String jdbcUuid){
    this.jdbcUuid = jdbcUuid;
}


public String getResetSql(){
    return resetSql;
}


public String getDisSql(){
    return disSql;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public void setResetSql(String resetSql){
    this.resetSql = resetSql;
}


public void setTabsRows(Integer tabsRows){
    this.tabsRows = tabsRows;
}


public String getType(){
    return type;
}


public void setTabsDesc(String tabsDesc){
    this.tabsDesc = tabsDesc;
}


public String getUuid(){
    return uuid;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public Integer getTabsRows(){
    return tabsRows;
}


public String getRenameSql(){
    return renameSql;
}


public void setRenameSql(String renameSql){
    this.renameSql = renameSql;
}


public void setDropSql(String dropSql){
    this.dropSql = dropSql;
}


public String getDropSql(){
    return dropSql;
}


public String getTypeName(){
    return typeName;
}


}