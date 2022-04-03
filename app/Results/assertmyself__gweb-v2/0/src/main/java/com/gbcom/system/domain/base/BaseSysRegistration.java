package com.gbcom.system.domain.base;
 import java.io.Serializable;
import com.gbcom.Interface.SysUser;
public class BaseSysRegistration implements Serializable{

 public  String REF;

 public  String PROP_TELEPHONE;

 public  String PROP_REGISTER_DATE;

 public  String PROP_CHECK_USER;

 public  String PROP_PROVINCE;

 public  String PROP_ORGANIZATION_CODE;

 public  String PROP_ORGANIZATION_CERTIFICATE;

 public  String PROP_CONTACTER;

 public  String PROP_CITY;

 public  String PROP_NAME;

 public  String PROP_LICENSE;

 public  String PROP_CHECK_RESULT;

 public  String PROP_ADDRESS;

 public  String PROP_NOTIFY_PHONE;

 public  String PROP_ID;

 public  String PROP_CHECK_DATE;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String name;

 private  java.lang.String organizationCode;

 private  java.lang.String address;

 private  java.lang.String contacter;

 private  java.lang.String telephone;

 private  java.lang.String notifyPhone;

 private  java.lang.String license;

 private  Byte[] organizationCertificate;

 private  java.sql.Date registerDate;

 private  java.lang.String checkResult;

 private  java.sql.Date checkDate;

 private  com.gbcom.system.domain.SysUser checkUser;

 private  com.gbcom.system.domain.SysCodeDetail city;

 private  com.gbcom.system.domain.SysCodeDetail province;

// constructors
public BaseSysRegistration() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysRegistration(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysRegistration(java.lang.Long id, java.lang.String name, java.lang.String organizationCode, java.lang.String address, java.lang.String contacter, java.lang.String telephone, java.lang.String notifyPhone) {
    this.setId(id);
    this.setName(name);
    this.setOrganizationCode(organizationCode);
    this.setAddress(address);
    this.setContacter(contacter);
    this.setTelephone(telephone);
    this.setNotifyPhone(notifyPhone);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.String getName(){
    return name;
}


public void setProvince(com.gbcom.system.domain.SysCodeDetail province){
    this.province = province;
}


public java.sql.Date getRegisterDate(){
    return registerDate;
}


public void setCheckResult(java.lang.String checkResult){
    this.checkResult = checkResult;
}


public java.sql.Date getCheckDate(){
    return checkDate;
}


public void setOrganizationCode(java.lang.String organizationCode){
    this.organizationCode = organizationCode;
}


public java.lang.Long getId(){
    return id;
}


public void setLicense(java.lang.String license){
    this.license = license;
}


public void setOrganizationCertificate(Byte[] organizationCertificate){
    this.organizationCertificate = organizationCertificate;
}


public java.lang.String getCheckResult(){
    return checkResult;
}


public java.lang.String getTelephone(){
    return telephone;
}


public com.gbcom.system.domain.SysUser getCheckUser(){
    return checkUser;
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


public com.gbcom.system.domain.SysCodeDetail getProvince(){
    return province;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setRegisterDate(java.sql.Date registerDate){
    this.registerDate = registerDate;
}


public java.lang.String getAddress(){
    return address;
}


public com.gbcom.system.domain.SysCodeDetail getCity(){
    return city;
}


public void setAddress(java.lang.String address){
    this.address = address;
}


public void setCity(com.gbcom.system.domain.SysCodeDetail city){
    this.city = city;
}


public void setNotifyPhone(java.lang.String notifyPhone){
    this.notifyPhone = notifyPhone;
}


public Byte[] getOrganizationCertificate(){
    return organizationCertificate;
}


public void setTelephone(java.lang.String telephone){
    this.telephone = telephone;
}


public void setCheckDate(java.sql.Date checkDate){
    this.checkDate = checkDate;
}


public java.lang.String getNotifyPhone(){
    return notifyPhone;
}


public void setCheckUser(com.gbcom.system.domain.SysUser checkUser){
    this.checkUser = checkUser;
}


public void setContacter(java.lang.String contacter){
    this.contacter = contacter;
}


public java.lang.String getLicense(){
    return license;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysRegistration))
        return false;
    else {
        com.gbcom.system.domain.SysRegistration sysRegistration = (com.gbcom.system.domain.SysRegistration) obj;
        if (null == this.getId() || null == sysRegistration.getId())
            return false;
        else
            return (this.getId().equals(sysRegistration.getId()));
    }
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(name);
    builder.append(organizationCode);
    builder.append(address);
    builder.append(contacter);
    builder.append(telephone);
    builder.append(notifyPhone);
    builder.append(license);
    builder.append(organizationCertificate);
    builder.append(registerDate);
    builder.append(checkResult);
    builder.append(checkDate);
    return builder.toString();
}


public void initialize(){
}


public java.lang.String getOrganizationCode(){
    return organizationCode;
}


public java.lang.String getContacter(){
    return contacter;
}


}