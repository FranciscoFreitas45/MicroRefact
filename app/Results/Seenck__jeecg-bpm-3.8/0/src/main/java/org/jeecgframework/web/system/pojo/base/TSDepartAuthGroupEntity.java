package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_depart_auth_group", schema = "")
@SuppressWarnings("serial")
public class TSDepartAuthGroupEntity {

 private  java.lang.String id;

@Excel(name = "权限组名称", width = 15)
 private  java.lang.String groupName;

@Excel(name = "部门ID", width = 15)
 private  java.lang.String deptId;

@Excel(name = "部门编码", width = 15)
 private  java.lang.String deptCode;

@Excel(name = "部门名称", width = 15)
 private  java.lang.String deptName;

@Excel(name = "类型", width = 15)
 private  java.lang.String departType;

@Excel(name = "级别", width = 15)
 private  java.lang.Integer level;

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


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "DEPART_TYPE", nullable = true, length = 2)
public java.lang.String getDepartType(){
    return this.departType;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "DEPT_ID", nullable = true, length = 36)
public java.lang.String getDeptId(){
    return this.deptId;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "DEPT_CODE", nullable = true, length = 50)
public java.lang.String getDeptCode(){
    return this.deptCode;
}


public void setLevel(java.lang.Integer level){
    this.level = level;
}


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
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


public void setDeptCode(java.lang.String deptCode){
    this.deptCode = deptCode;
}


@Column(name = "DEPT_NAME", nullable = true, length = 100)
public java.lang.String getDeptName(){
    return this.deptName;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "GROUP_NAME", nullable = true, length = 100)
public java.lang.String getGroupName(){
    return this.groupName;
}


public void setGroupName(java.lang.String groupName){
    this.groupName = groupName;
}


public void setDeptName(java.lang.String deptName){
    this.deptName = deptName;
}


@Column(name = "LEVEL", nullable = true, length = 10)
public java.lang.Integer getLevel(){
    return this.level;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setDeptId(java.lang.String deptId){
    this.deptId = deptId;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setDepartType(java.lang.String departType){
    this.departType = departType;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}