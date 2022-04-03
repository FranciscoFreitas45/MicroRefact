package com.zis.DTO;
 import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
public class SaveOrUpdateCompanyDto {

 private  Integer companyId;

 private  String companyName;

 private  String contacts;

 private  String mobile;

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
public String getContacts(){
    return contacts;
}


public String getTypeStatus(){
    return typeStatus;
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


}