package org.jeecgframework.web.system.pojo.base;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_depart")
public class TSDepart extends IdEntity{

 private  TSDepart TSPDepart;

@Excel(name = "部门名称", width = 20)
 private  String departname;

@Excel(name = "部门描述", width = 20)
 private  String description;

@Excel(name = "机构编码", width = 20)
 private  String orgCode;

@Excel(name = "机构类型编码", width = 25)
 private  String orgType;

@Excel(name = "电话", width = 20)
 private  String mobile;

@Excel(name = "传真", width = 20)
 private  String fax;

@Excel(name = "地址", width = 20)
 private  String address;

 private  String departOrder;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

 private  List<TSDepart> TSDeparts;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setTSDeparts(List<TSDepart> tSDeparts){
    TSDeparts = tSDeparts;
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


public void setOrgCode(String orgCode){
    this.orgCode = orgCode;
}


public void setDescription(String description){
    this.description = description;
}


@Column(name = "description", length = 500)
public String getDescription(){
    return this.description;
}


@Column(name = "org_code", length = 64)
public String getOrgCode(){
    return orgCode;
}


public void setOrgType(String orgType){
    this.orgType = orgType;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
@JsonDeserialize(using = CustomJsonDateDeserializer.class)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setFax(String fax){
    this.fax = fax;
}


@Column(name = "address", length = 100)
public String getAddress(){
    return address;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "parentdepartid")
public TSDepart getTSPDepart(){
    return this.TSPDepart;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSPDepart")
public List<TSDepart> getTSDeparts(){
    return TSDeparts;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setAddress(String address){
    this.address = address;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setTSPDepart(TSDepart TSPDepart){
    this.TSPDepart = TSPDepart;
}


@Column(name = "depart_order")
public String getDepartOrder(){
    return departOrder;
}


public void setDepartOrder(String departOrder){
    this.departOrder = departOrder;
}


@Column(name = "fax", length = 32)
public String getFax(){
    return fax;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setDepartname(String departname){
    this.departname = departname;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


@Column(name = "org_type", length = 1)
public String getOrgType(){
    return orgType;
}


@JsonDeserialize(using = CustomJsonDateDeserializer.class)
public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "mobile", length = 32)
public String getMobile(){
    return mobile;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


@Column(name = "departname", nullable = false, length = 100)
public String getDepartname(){
    return this.departname;
}


}