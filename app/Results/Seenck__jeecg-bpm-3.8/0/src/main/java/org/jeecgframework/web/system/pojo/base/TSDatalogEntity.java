package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_data_log", schema = "")
@SuppressWarnings("serial")
public class TSDatalogEntity extends IdEntity{

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

@Excel(name = "表名")
 private  java.lang.String tableName;

@Excel(name = "数据ID")
 private  java.lang.String dataId;

@Excel(name = "数据内容")
 private  java.lang.String dataContent;

@Excel(name = "版本号")
 private  java.lang.Integer versionNumber;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setTableName(java.lang.String tableName){
    this.tableName = tableName;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setDataContent(java.lang.String dataContent){
    this.dataContent = dataContent;
}


@Column(name = "VERSION_NUMBER", nullable = true, length = 4)
public java.lang.Integer getVersionNumber(){
    return this.versionNumber;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "TABLE_NAME", nullable = true, length = 32)
public java.lang.String getTableName(){
    return this.tableName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "DATA_ID", nullable = true, length = 32)
public java.lang.String getDataId(){
    return this.dataId;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setDataId(java.lang.String dataId){
    this.dataId = dataId;
}


public void setVersionNumber(java.lang.Integer versionNumber){
    this.versionNumber = versionNumber;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


@Column(name = "DATA_CONTENT", nullable = true, length = 32)
public java.lang.String getDataContent(){
    return this.dataContent;
}


}