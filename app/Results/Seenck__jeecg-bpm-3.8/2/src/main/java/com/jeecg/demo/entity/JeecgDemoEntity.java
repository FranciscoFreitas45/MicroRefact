package com.jeecg.demo.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jeecg_demo", schema = "")
@SuppressWarnings("serial")
public class JeecgDemoEntity {

 private  java.lang.String id;

@Excel(name = "名称", width = 32)
 private  java.lang.String name;

@Excel(name = "年龄")
 private  java.lang.Integer age;

@Excel(name = "生日", format = "yyyy-MM-dd")
 private  java.util.Date birthday;

@Excel(name = "个人介绍")
 private  java.lang.String content;

@Excel(name = "部门", dictTable = "t_s_depart", dicCode = "id", dicText = "departname")
 private  java.lang.String depId;

@Excel(name = "邮箱", width = 32)
 private  java.lang.String email;

@Excel(name = "电话", width = 12)
 private  java.lang.String phone;

@Excel(name = "工资", type = 4)
 private  Double salary;

@Excel(name = "性别", dicCode = "sex")
 private  java.lang.String sex;

@Excel(name = "入职状态", dicCode = "sf_yn")
 private  java.lang.String status;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String updateName;

@Excel(name = "部门编码", width = 200)
 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

 private  java.lang.String touxiang;

 private  java.lang.String fujian;


public void setName(java.lang.String name){
    this.name = name;
}


@Column(name = "PHONE", nullable = true, length = 255)
public java.lang.String getPhone(){
    return this.phone;
}


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setFujian(java.lang.String fujian){
    this.fujian = fujian;
}


@Column(name = "AGE", nullable = true, length = 10)
public java.lang.Integer getAge(){
    return this.age;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "NAME", nullable = false, length = 255)
public java.lang.String getName(){
    return this.name;
}


@Column(name = "sys_org_code", nullable = true, length = 15)
public java.lang.String getSysOrgCode(){
    return sysOrgCode;
}


@Column(name = "CONTENT", nullable = true, length = 255)
public java.lang.String getContent(){
    return this.content;
}


@Column(name = "CREATE_NAME", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
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


@Column(name = "STATUS", nullable = true, length = 255)
public java.lang.String getStatus(){
    return this.status;
}


@Column(name = "touxiang", nullable = true, length = 255)
public java.lang.String getTouxiang(){
    return touxiang;
}


public void setTouxiang(java.lang.String touxiang){
    this.touxiang = touxiang;
}


public void setDepId(java.lang.String depId){
    this.depId = depId;
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


@Column(name = "UPDATE_BY", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "DEP_ID", nullable = true, length = 255)
public java.lang.String getDepId(){
    return this.depId;
}


@Column(name = "SALARY", nullable = true, scale = 2, length = 19)
public Double getSalary(){
    return this.salary;
}


public void setSalary(Double salary){
    this.salary = salary;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "BIRTHDAY", nullable = true)
public java.util.Date getBirthday(){
    return this.birthday;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setSex(java.lang.String sex){
    this.sex = sex;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setPhone(java.lang.String phone){
    this.phone = phone;
}


public void setStatus(java.lang.String status){
    this.status = status;
}


@JsonDeserialize(using = CustomJsonDateDeserializer.class)
public void setBirthday(java.util.Date birthday){
    this.birthday = birthday;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setEmail(java.lang.String email){
    this.email = email;
}


@JsonDeserialize(using = CustomJsonDateDeserializer.class)
public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "fujian", nullable = true, length = 255)
public java.lang.String getFujian(){
    return fujian;
}


@Column(name = "SEX", nullable = true, length = 255)
public java.lang.String getSex(){
    return this.sex;
}


@Column(name = "EMAIL", nullable = true, length = 255)
public java.lang.String getEmail(){
    return this.email;
}


@Column(name = "sys_company_code", nullable = true, length = 15)
public java.lang.String getSysCompanyCode(){
    return sysCompanyCode;
}


public void setAge(java.lang.Integer age){
    this.age = age;
}


}