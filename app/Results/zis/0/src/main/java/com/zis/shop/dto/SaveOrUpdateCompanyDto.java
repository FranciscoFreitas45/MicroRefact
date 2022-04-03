package com.zis.shop.dto;
 import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
public class SaveOrUpdateCompanyDto {

 private  Integer companyId;

@NotBlank(message = "公司姓名不能为空")
 private  String companyName;

@NotBlank(message = "联系人不能为空")
 private  String contacts;

@NotBlank(message = "手机号不能为空")
@Pattern(regexp = "^((13[0-9])|(17[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$", message = "手机号码格式不符")
 private  String mobile;

@NotBlank(message = "公司地址不能为空")
 private  String address;

 private  String typeStatus;

public SaveOrUpdateCompanyDto() {
}public SaveOrUpdateCompanyDto(Integer companyId, String companyName, String contacts, String mobile, String address, String typeStatus) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.contacts = contacts;
    this.mobile = mobile;
    this.address = address;
    this.typeStatus = typeStatus;
}
public void setAddress(String address){
    this.address = address;
}


public void setContacts(String contacts){
    this.contacts = contacts;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getContacts(){
    return contacts;
}


public String getTypeStatus(){
    return typeStatus;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public Integer getCompanyId(){
    return companyId;
}


public String getCompanyName(){
    return companyName;
}


public String getMobile(){
    return mobile;
}


public String getAddress(){
    return address;
}


@Override
public String toString(){
    return "SaveOrUpdateCompanyDto [companyId=" + companyId + ", companyName=" + companyName + ", contacts=" + contacts + ", mobile=" + mobile + ", address=" + address + ", typeStatus=" + typeStatus + "]";
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


public void setTypeStatus(String typeStatus){
    this.typeStatus = typeStatus;
}


}