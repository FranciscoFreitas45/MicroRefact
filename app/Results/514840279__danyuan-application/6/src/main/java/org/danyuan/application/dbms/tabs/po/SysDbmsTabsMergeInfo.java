package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_tabs_merge_info")
@NamedQuery(name = "SysDbmsTabsMergeInfo.findAll", query = "SELECT s FROM SysDbmsTabsMergeInfo s")
public class SysDbmsTabsMergeInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "cols_name_2", columnDefinition = " varchar(30) COMMENT '表2列名'")
 private  String colsName2;

@Column(name = "cols_uuid_2", columnDefinition = " varchar(36) COMMENT '表2列id'")
 private  String colsUuid2;

@Column(name = "table_uuid_2", columnDefinition = " varchar(36) COMMENT '表二id'")
 private  String tableUuid2;

@Column(name = "cols_desc_2", columnDefinition = " varchar(50) COMMENT '表2列含义'")
 private  String colsDesc2;

@Column(name = "cols_name_1", columnDefinition = " varchar(30) COMMENT '表一列名'")
 private  String colsName1;

@Column(name = "cols_desc_1", columnDefinition = " varchar(50) COMMENT '表1列含义'")
 private  String colsDesc1;

@Column(name = "cols_uuid_1", columnDefinition = " varchar(36) COMMENT '表1列id'")
 private  String colsUuid1;

@Column(name = "table_uuid_1", columnDefinition = " varchar(36) COMMENT '表1id'")
 private  String tableUuid1;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysDbmsTabsMergeInfo() {
    super();
}
public String getColsName2(){
    return colsName2;
}


public void setColsName1(String colsName1){
    this.colsName1 = colsName1;
}


public void setColsName2(String colsName2){
    this.colsName2 = colsName2;
}


public String getColsName1(){
    return colsName1;
}


public String getColsDesc1(){
    return colsDesc1;
}


public String getColsUuid1(){
    return colsUuid1;
}


public void setTableUuid1(String tableUuid1){
    this.tableUuid1 = tableUuid1;
}


public void setTableUuid2(String tableUuid2){
    this.tableUuid2 = tableUuid2;
}


public String getColsUuid2(){
    return colsUuid2;
}


public void setColsDesc2(String colsDesc2){
    this.colsDesc2 = colsDesc2;
}


public String getColsDesc2(){
    return colsDesc2;
}


public void setColsDesc1(String colsDesc1){
    this.colsDesc1 = colsDesc1;
}


public String getTableUuid1(){
    return tableUuid1;
}


public String getTableUuid2(){
    return tableUuid2;
}


public void setColsUuid1(String colsUuid1){
    this.colsUuid1 = colsUuid1;
}


public void setColsUuid2(String colsUuid2){
    this.colsUuid2 = colsUuid2;
}


}