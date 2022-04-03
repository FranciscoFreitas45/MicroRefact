package com.gbcom.system.domain.base;
 import java.io.Serializable;
import com.gbcom.Interface.SysCodeDetail;
public class BaseSysPerson implements Serializable{

 public  String REF;

 public  String PROP_BORN_DATE;

 public  String PROP_BORN_PLACE;

 public  String PROP_OFFICE_TEL;

 public  String PROP_AGE;

 public  String PROP_WORK_YEAR;

 public  String PROP_UPDATE_TIME;

 public  String PROP_QQ_CODE;

 public  String PROP_MSN_CODE;

 public  String PROP_CODE;

 public  String PROP_ZIPCODE;

 public  String PROP_NAME;

 public  String PROP_FAX_TEL;

 public  String PROP_EDUCATION;

 public  String PROP_CREATE_USER;

 public  String PROP_EMAIL;

 public  String PROP_CREATE_TIME;

 public  String PROP_MEMO;

 public  String PROP_MOBILE;

 public  String PROP_ID;

 public  String PROP_CARD;

 public  String PROP_UPDATE_USER;

 public  String PROP_SEX;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.String card;

 private  java.lang.Integer age;

 private  java.lang.Boolean sex;

 private  java.util.Date bornDate;

 private  java.lang.String bornPlace;

 private  java.lang.String mobile;

 private  java.lang.String officeTel;

 private  java.lang.String faxTel;

 private  java.lang.String email;

 private  java.lang.String zipcode;

 private  java.lang.Integer workYear;

 private  java.lang.String msnCode;

 private  java.lang.String qqCode;

 private  java.lang.String memo;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String updateUser;

 private  java.lang.String createUser;

 private  com.gbcom.system.domain.SysCodeDetail education;

 private  java.util.Set<com.gbcom.system.domain.SysUser> sysUsers;

 private  java.util.Set<com.gbcom.system.domain.SysPersonDept> sysPersonDepts;

 private  java.lang.String profilePhotoUrl;

 private  java.lang.String avatarUrl;

// constructors
public BaseSysPerson() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysPerson(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public java.lang.String getFaxTel(){
    return faxTel;
}


public java.lang.String getCreateUser(){
    return createUser;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public java.lang.String getName(){
    return name;
}


public void addTosysUsers(com.gbcom.system.domain.SysUser sysUser){
    if (null == getSysUsers())
        setSysUsers(new java.util.LinkedHashSet<com.gbcom.system.domain.SysUser>());
    getSysUsers().add(sysUser);
}


public void setEducation(com.gbcom.system.domain.SysCodeDetail education){
    this.education = education;
}


public void setSysUsers(java.util.Set<com.gbcom.system.domain.SysUser> sysUsers){
    this.sysUsers = sysUsers;
}


public java.lang.Integer getWorkYear(){
    return workYear;
}


public java.util.Date getBornDate(){
    return bornDate;
}


public void setBornPlace(java.lang.String bornPlace){
    this.bornPlace = bornPlace;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public java.lang.String getProfilePhotoUrl(){
    return profilePhotoUrl;
}


public java.lang.String getCode(){
    return code;
}


public void setSysPersonDepts(java.util.Set<com.gbcom.system.domain.SysPersonDept> sysPersonDepts){
    this.sysPersonDepts = sysPersonDepts;
}


public java.lang.String getBornPlace(){
    return bornPlace;
}


public java.lang.String getMsnCode(){
    return msnCode;
}


public java.util.Set<com.gbcom.system.domain.SysPersonDept> getSysPersonDepts(){
    if (sysPersonDepts == null) {
        sysPersonDepts = new java.util.LinkedHashSet<com.gbcom.system.domain.SysPersonDept>();
    }
    return sysPersonDepts;
}


public void setAvatarUrl(java.lang.String avatarUrl){
    this.avatarUrl = avatarUrl;
}


public void addTosysPersonDepts(com.gbcom.system.domain.SysPersonDept sysPersonDept){
    if (null == getSysPersonDepts())
        setSysPersonDepts(new java.util.LinkedHashSet<com.gbcom.system.domain.SysPersonDept>());
    getSysPersonDepts().add(sysPersonDept);
}


public void setProfilePhotoUrl(java.lang.String profilePhotoUrl){
    this.profilePhotoUrl = profilePhotoUrl;
}


public void setSex(java.lang.Boolean sex){
    this.sex = sex;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public void setFaxTel(java.lang.String faxTel){
    this.faxTel = faxTel;
}


public void setZipcode(java.lang.String zipcode){
    this.zipcode = zipcode;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public void setMobile(java.lang.String mobile){
    this.mobile = mobile;
}


public java.lang.String getMemo(){
    return memo;
}


public void setEmail(java.lang.String email){
    this.email = email;
}


public java.lang.String getUpdateUser(){
    return updateUser;
}


public java.lang.Boolean getSex(){
    return sex;
}


public java.lang.String getEmail(){
    return email;
}


public void setAge(java.lang.Integer age){
    this.age = age;
}


public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.String getAvatarUrl(){
    return avatarUrl;
}


public java.lang.Integer getAge(){
    return age;
}


public java.util.Set<com.gbcom.system.domain.SysUser> getSysUsers(){
    if (sysUsers == null) {
        sysUsers = new java.util.LinkedHashSet<com.gbcom.system.domain.SysUser>();
    }
    return sysUsers;
}


public void setBornDate(java.util.Date bornDate){
    this.bornDate = bornDate;
}


public java.lang.Long getId(){
    return id;
}


public void setOfficeTel(java.lang.String officeTel){
    this.officeTel = officeTel;
}


public java.lang.String getZipcode(){
    return zipcode;
}


public java.lang.String getOfficeTel(){
    return officeTel;
}


public int hashCode(){
    if (Integer.MIN_VALUE == this.hashCode) {
        if (null == this.getId())
            return super.hashCode();
        else {
            String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
            this.hashCode = hashStr.hashCode();
        }
    }
    return this.hashCode;
}


public void setQqCode(java.lang.String qqCode){
    this.qqCode = qqCode;
}


public void setWorkYear(java.lang.Integer workYear){
    this.workYear = workYear;
}


public void setCard(java.lang.String card){
    this.card = card;
}


public java.lang.String getQqCode(){
    return qqCode;
}


public void setMsnCode(java.lang.String msnCode){
    this.msnCode = msnCode;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public com.gbcom.system.domain.SysCodeDetail getEducation(){
    return education;
}


public void setCreateUser(java.lang.String createUser){
    this.createUser = createUser;
}


public void setMemo(java.lang.String memo){
    this.memo = memo;
}


public void setUpdateUser(java.lang.String updateUser){
    this.updateUser = updateUser;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysPerson))
        return false;
    else {
        com.gbcom.system.domain.SysPerson sysPerson = (com.gbcom.system.domain.SysPerson) obj;
        if (null == this.getId() || null == sysPerson.getId())
            return false;
        else
            return (this.getId().equals(sysPerson.getId()));
    }
}


public java.lang.String getMobile(){
    return mobile;
}


public void setUpdateTime(java.sql.Timestamp updateTime){
    this.updateTime = updateTime;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(name);
    builder.append(card);
    builder.append(age);
    builder.append(sex);
    builder.append(bornDate);
    builder.append(bornPlace);
    builder.append(mobile);
    builder.append(officeTel);
    builder.append(faxTel);
    builder.append(email);
    builder.append(zipcode);
    builder.append(workYear);
    builder.append(msnCode);
    builder.append(qqCode);
    builder.append(memo);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(updateUser);
    builder.append(createUser);
    return builder.toString();
}


public void initialize(){
}


public java.lang.String getCard(){
    return card;
}


}