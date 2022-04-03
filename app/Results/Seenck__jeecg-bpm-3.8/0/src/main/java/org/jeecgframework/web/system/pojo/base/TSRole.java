package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_role")
public class TSRole extends IdEntity{

@Excel(name = "角色名称", width = 20)
 private  String roleName;

@Excel(name = "角色编码", width = 20)
 private  String roleCode;

@Excel(name = "部门权限组ID")
 private  String departAgId;

 private  String roleType;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;


public void setRoleCode(String roleCode){
    this.roleCode = roleCode;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "rolename", nullable = false, length = 100)
public String getRoleName(){
    return this.roleName;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public void setDepartAgId(String departAgId){
    this.departAgId = departAgId;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "role_type", nullable = true, length = 32)
public String getRoleType(){
    return roleType;
}


@Column(name = "depart_ag_id", nullable = true, length = 32)
public String getDepartAgId(){
    return departAgId;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "rolecode", length = 10)
public String getRoleCode(){
    return this.roleCode;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setRoleType(String roleType){
    this.roleType = roleType;
}


}