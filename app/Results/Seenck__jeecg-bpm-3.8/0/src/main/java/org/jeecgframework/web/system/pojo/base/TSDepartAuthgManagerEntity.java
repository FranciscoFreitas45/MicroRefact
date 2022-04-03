package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_depart_authg_manager", schema = "")
@SuppressWarnings("serial")
public class TSDepartAuthgManagerEntity {

 private  java.lang.String id;

@Excel(name = "权限组ID", width = 15)
 private  java.lang.String groupId;

@Excel(name = "用户ID", width = 15)
 private  java.lang.String userId;

@Excel(name = "权限组类型", width = 15)
 private  java.lang.Integer type;

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


public void setGroupId(java.lang.String groupId){
    this.groupId = groupId;
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


public void setType(java.lang.Integer type){
    this.type = type;
}


@Column(name = "GROUP_ID", nullable = true, length = 36)
public java.lang.String getGroupId(){
    return this.groupId;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "TYPE", nullable = true, length = 3)
public java.lang.Integer getType(){
    return this.type;
}


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
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


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


@Column(name = "USER_ID", nullable = true, length = 36)
public java.lang.String getUserId(){
    return this.userId;
}


public void setUserId(java.lang.String userId){
    this.userId = userId;
}


}