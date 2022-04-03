package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_depart_authg_function_rel", schema = "")
@SuppressWarnings("serial")
public class TSDepartAuthgFunctionRelEntity {

 private  java.lang.String id;

@Excel(name = "权限组ID", width = 15)
 private  TSDepartAuthGroupEntity tsDepartAuthGroup;

@Excel(name = "权限ID", width = 15)
 private  TSFunction tsFunction;

@Excel(name = "页面操作权限", width = 15)
 private  java.lang.String operation;

@Excel(name = "数据权限", width = 15)
 private  java.lang.String datarule;

@Excel(name = "创建人", width = 15)
 private  java.lang.String createName;

@Excel(name = "创建人id", width = 15)
 private  java.lang.String createBy;

@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date createDate;

@Excel(name = "修改人", width = 15)
 private  java.lang.String updateName;

@Excel(name = "修改人id", width = 15)
 private  java.lang.String updateBy;

@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date updateDate;

@Excel(name = "所属部门", width = 15)
 private  java.lang.String sysOrgCode;

@Excel(name = "所属公司", width = 15)
 private  java.lang.String sysCompanyCode;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "auth_id")
// update-begin-author:taoYan date:20180802 for:TASK #3043 【垃圾数据BUG处理】
@NotFound(action = NotFoundAction.IGNORE)
public TSFunction getTsFunction(){
    return tsFunction;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
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


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "DATARULE", nullable = true, length = 1000)
public java.lang.String getDatarule(){
    return this.datarule;
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


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "group_id")
public TSDepartAuthGroupEntity getTsDepartAuthGroup(){
    return tsDepartAuthGroup;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setTsDepartAuthGroup(TSDepartAuthGroupEntity tsDepartAuthGroup){
    this.tsDepartAuthGroup = tsDepartAuthGroup;
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


public void setTsFunction(TSFunction tsFunction){
    this.tsFunction = tsFunction;
}


public void setDatarule(java.lang.String datarule){
    this.datarule = datarule;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "OPERATION", nullable = true, length = 2000)
public java.lang.String getOperation(){
    return this.operation;
}


public void setOperation(java.lang.String operation){
    this.operation = operation;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}