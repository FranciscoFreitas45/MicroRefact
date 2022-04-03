package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_dict_table_config", schema = "")
@SuppressWarnings("serial")
public class TSDictTableConfigEntity {

 private  java.lang.String id;

@Excel(name = "表名", width = 15)
 private  java.lang.String tableName;

@Excel(name = "值字段名", width = 15)
 private  java.lang.String valueCol;

@Excel(name = "文本字段名", width = 15)
 private  java.lang.String textCol;

@Excel(name = "字典表查询条件", width = 15)
 private  java.lang.String dictCondition;

@Excel(name = "是否启用", width = 15, dicCode = "sf_yn")
 private  java.lang.String isvalid;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;


public void setTableName(java.lang.String tableName){
    this.tableName = tableName;
}


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


public void setIsvalid(java.lang.String isvalid){
    this.isvalid = isvalid;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "VALUE_COL", nullable = true, length = 50)
public java.lang.String getValueCol(){
    return this.valueCol;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setValueCol(java.lang.String valueCol){
    this.valueCol = valueCol;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setDictCondition(java.lang.String dictCondition){
    this.dictCondition = dictCondition;
}


@Column(name = "TEXT_COL", nullable = true, length = 50)
public java.lang.String getTextCol(){
    return this.textCol;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "TABLE_NAME", nullable = true, length = 100)
public java.lang.String getTableName(){
    return this.tableName;
}


public void setTextCol(java.lang.String textCol){
    this.textCol = textCol;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "DICT_CONDITION")
public java.lang.String getDictCondition(){
    return dictCondition;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "ISVALID", nullable = true, length = 32)
public java.lang.String getIsvalid(){
    return this.isvalid;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}