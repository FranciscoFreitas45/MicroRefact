package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_user")
@PrimaryKeyJoinColumn(name = "id")
public class TSUser extends TSBaseUser{

 private  long serialVersionUID;

 private  String signatureFile;

@Excel(name = "手机", width = 20)
 private  String mobilePhone;

@Excel(name = "办公电话", width = 20)
 private  String officePhone;

@Excel(name = "邮箱", width = 25)
 private  String email;

 private  java.lang.String portrait;

 private  java.lang.String devFlag;

 private  String userType;

 private  String personType;

 private  String sex;

 private  String empNo;

 private  String citizenNo;

 private  String fax;

 private  String address;

 private  String post;

 private  String memo;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;


@Column(name = "portrait", length = 100)
public String getPortrait(){
    return portrait;
}


@Column(name = "officePhone", length = 20)
public String getOfficePhone(){
    return this.officePhone;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


@Column(name = "signatureFile", length = 100)
public String getSignatureFile(){
    return this.signatureFile;
}


@Column(name = "person_type")
public String getPersonType(){
    return personType;
}


public void setOfficePhone(String officePhone){
    this.officePhone = officePhone;
}


@Column(name = "post")
public String getPost(){
    return post;
}


@Column(name = "emp_no")
public String getEmpNo(){
    return empNo;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public void setPortrait(String portrait){
    this.portrait = portrait;
}


public void setUserType(String userType){
    this.userType = userType;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "mobilePhone", length = 30)
public String getMobilePhone(){
    return this.mobilePhone;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setFax(String fax){
    this.fax = fax;
}


@Column(name = "address")
public String getAddress(){
    return address;
}


@Column(name = "dev_flag", length = 2)
public String getDevFlag(){
    return devFlag;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "user_type")
public String getUserType(){
    return userType;
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


public void setSex(String sex){
    this.sex = sex;
}


public void setPersonType(String personType){
    this.personType = personType;
}


public void setPost(String post){
    this.post = post;
}


@Column(name = "fax")
public String getFax(){
    return fax;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "memo")
public String getMemo(){
    return memo;
}


public void setEmail(String email){
    this.email = email;
}


@Column(name = "citizen_no")
public String getCitizenNo(){
    return citizenNo;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setDevFlag(String devFlag){
    this.devFlag = devFlag;
}


public void setSignatureFile(String signatureFile){
    this.signatureFile = signatureFile;
}


@Column(name = "sex")
public String getSex(){
    return sex;
}


@Column(name = "email", length = 50)
public String getEmail(){
    return this.email;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("TSUser [signatureFile=");
    builder.append(signatureFile);
    builder.append(", mobilePhone=");
    builder.append(mobilePhone);
    builder.append(", officePhone=");
    builder.append(officePhone);
    builder.append(", email=");
    builder.append(email);
    builder.append(", createDate=");
    builder.append(createDate);
    builder.append(", createBy=");
    builder.append(createBy);
    builder.append(", createName=");
    builder.append(createName);
    builder.append(", updateDate=");
    builder.append(updateDate);
    builder.append(", updateBy=");
    builder.append(updateBy);
    builder.append(", updateName=");
    builder.append(updateName);
    builder.append(", portrait=");
    builder.append(portrait);
    builder.append(", devFlag=");
    builder.append(devFlag);
    builder.append(", userType=");
    builder.append(userType);
    builder.append(", personType=");
    builder.append(personType);
    builder.append(", sex=");
    builder.append(sex);
    builder.append(", empNo=");
    builder.append(empNo);
    builder.append(", citizenNo=");
    builder.append(citizenNo);
    builder.append(", fax=");
    builder.append(fax);
    builder.append(", address=");
    builder.append(address);
    builder.append(", post=");
    builder.append(post);
    builder.append(", memo=");
    builder.append(memo);
    builder.append("]");
    return builder.toString();
}


public void setMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
}


public void setCitizenNo(String citizenNo){
    this.citizenNo = citizenNo;
}


}