package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_interrole", schema = "")
@SuppressWarnings("serial")
public class InterroleEntity {

 private  java.lang.String id;

@Excel(name = "接口角色编码", width = 15)
 private  java.lang.String roleCode;

@Excel(name = "接口角色名称", width = 15)
 private  java.lang.String roleName;

@Excel(name = "修改时间", width = 15)
 private  java.lang.String updateName;

@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date updateDate;

@Excel(name = "创建人id", width = 15)
 private  java.lang.String updateBy;

@Excel(name = "创建人", width = 15)
 private  java.lang.String createName;

@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date createDate;

@Excel(name = "创建人id", width = 15)
 private  java.lang.String createBy;


public void setRoleCode(java.lang.String roleCode){
    this.roleCode = roleCode;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "ROLE_NAME", nullable = true, length = 100)
public java.lang.String getRoleName(){
    return this.roleName;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "CREATE_NAME", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setRoleName(java.lang.String roleName){
    this.roleName = roleName;
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


@Column(name = "UPDATE_NAME", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "ROLE_CODE", nullable = true, length = 10)
public java.lang.String getRoleCode(){
    return this.roleCode;
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


@Column(name = "UPDATE_BY", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


}