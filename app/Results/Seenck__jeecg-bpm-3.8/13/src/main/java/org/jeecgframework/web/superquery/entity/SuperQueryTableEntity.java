package org.jeecgframework.web.superquery.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "super_query_table", schema = "")
@SuppressWarnings("serial")
public class SuperQueryTableEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

@Excel(name = "序号", width = 15)
 private  java.lang.String seq;

@Excel(name = "表名", width = 15)
 private  java.lang.String tableName;

@Excel(name = "说明", width = 15)
 private  java.lang.String instruction;

@Excel(name = "是否是主表", width = 15, dicCode = "is_main")
 private  java.lang.String isMain;

@Excel(name = "外键字段", width = 15)
 private  java.lang.String fkField;

 private  java.lang.String mainId;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setTableName(java.lang.String tableName){
    this.tableName = tableName;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "IS_MAIN", nullable = true, length = 32)
public java.lang.String getIsMain(){
    return this.isMain;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "FK_FIELD", nullable = true, length = 32)
public java.lang.String getFkField(){
    return this.fkField;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "instruction", nullable = true, length = 32)
public java.lang.String getInstruction(){
    return instruction;
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


public void setSeq(java.lang.String seq){
    this.seq = seq;
}


@Column(name = "SEQ", nullable = true, length = 32)
public java.lang.String getSeq(){
    return this.seq;
}


public void setMainId(java.lang.String mainId){
    this.mainId = mainId;
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


@Column(name = "TABLE_NAME", nullable = true, length = 32)
public java.lang.String getTableName(){
    return this.tableName;
}


public void setFkField(java.lang.String fkField){
    this.fkField = fkField;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "MAIN_ID", nullable = true, length = 32)
public java.lang.String getMainId(){
    return this.mainId;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setIsMain(java.lang.String isMain){
    this.isMain = isMain;
}


public void setInstruction(java.lang.String instruction){
    this.instruction = instruction;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}