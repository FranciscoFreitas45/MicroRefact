package org.jeecgframework.web.system.pojo.base;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_s_category", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class TSCategoryEntity {

 private  long serialVersionUID;

 private  java.lang.String id;

 private  java.lang.String name;

 private  java.lang.String code;

 private  TSIcon icon;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

 private  TSCategoryEntity parent;

 private  List<TSCategoryEntity> list;


public void setName(java.lang.String name){
    this.name = name;
}


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


@Column(name = "NAME", nullable = true, length = 32)
public java.lang.String getName(){
    return this.name;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 15)
public java.lang.String getSysOrgCode(){
    return sysOrgCode;
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


@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent")
public List<TSCategoryEntity> getList(){
    return list;
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


public void setParent(TSCategoryEntity parent){
    this.parent = parent;
}


@Column(name = "CODE", nullable = true, length = 32)
public java.lang.String getCode(){
    return this.code;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "PARENT_CODE", referencedColumnName = "code")
public TSCategoryEntity getParent(){
    return this.parent;
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


public void setCode(java.lang.String code){
    this.code = code;
}


public void setList(List<TSCategoryEntity> list){
    this.list = list;
}


@ManyToOne()
@JoinColumn(name = "ICON_ID")
public TSIcon getIcon(){
    return icon;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setIcon(TSIcon icon){
    this.icon = icon;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 15)
public java.lang.String getSysCompanyCode(){
    return sysCompanyCode;
}


}