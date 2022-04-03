package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_company_position", schema = "")
@SuppressWarnings("serial")
public class TSCompanyPositionEntity {

 private  java.lang.String id;

@Excel(name = "公司ID", width = 15)
 private  java.lang.String companyId;

@Excel(name = "职务编码", width = 15)
 private  java.lang.String positionCode;

@Excel(name = "职务名称", width = 15)
 private  java.lang.String positionName;

@Excel(name = "职务英文名", width = 15)
 private  java.lang.String positionNameEn;

@Excel(name = "职务缩写", width = 15)
 private  java.lang.String positionNameAbbr;

@Excel(name = "职务级别", width = 15)
 private  java.lang.String positionLevel;

@Excel(name = "备注", width = 15)
 private  java.lang.String memo;

@Excel(name = "逻辑删除状态", width = 15)
 private  java.lang.Integer delFlag;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysCompanyCode;

 private  java.lang.String sysOrgCode;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setPositionCode(java.lang.String positionCode){
    this.positionCode = positionCode;
}


@Column(name = "POSITION_NAME_EN", nullable = true, length = 255)
public java.lang.String getPositionNameEn(){
    return this.positionNameEn;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "DEL_FLAG", nullable = true, length = 10)
public java.lang.Integer getDelFlag(){
    return this.delFlag;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


public void setDelFlag(java.lang.Integer delFlag){
    this.delFlag = delFlag;
}


public void setPositionNameEn(java.lang.String positionNameEn){
    this.positionNameEn = positionNameEn;
}


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setPositionName(java.lang.String positionName){
    this.positionName = positionName;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true)
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


public void setPositionNameAbbr(java.lang.String positionNameAbbr){
    this.positionNameAbbr = positionNameAbbr;
}


@Column(name = "POSITION_CODE", nullable = true, length = 64)
public java.lang.String getPositionCode(){
    return this.positionCode;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


@Column(name = "POSITION_NAME_ABBR", nullable = true, length = 255)
public java.lang.String getPositionNameAbbr(){
    return this.positionNameAbbr;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setPositionLevel(java.lang.String positionLevel){
    this.positionLevel = positionLevel;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setMemo(java.lang.String memo){
    this.memo = memo;
}


@Column(name = "MEMO", nullable = true, length = 500)
public java.lang.String getMemo(){
    return this.memo;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "COMPANY_ID", nullable = true, length = 36)
public java.lang.String getCompanyId(){
    return this.companyId;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


public void setCompanyId(java.lang.String companyId){
    this.companyId = companyId;
}


@Column(name = "POSITION_NAME", nullable = true, length = 100)
public java.lang.String getPositionName(){
    return this.positionName;
}


@Column(name = "POSITION_LEVEL", nullable = true, length = 50)
public java.lang.String getPositionLevel(){
    return this.positionLevel;
}


}