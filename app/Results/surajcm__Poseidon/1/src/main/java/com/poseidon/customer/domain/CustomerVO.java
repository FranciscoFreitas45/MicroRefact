package com.poseidon.customer.domain;
 import java.time.OffsetDateTime;
import java.util.StringJoiner;
public class CustomerVO {

 private  Long customerId;

 private  String customerName;

 private  String address;

 private  String phoneNo;

 private  String mobile;

 private  String email;

 private  String contactPerson;

 private  String contactMobile;

 private  String notes;

 private  CustomerAdditionalDetailsVO customerAdditionalDetailsVO;

 private  Boolean startsWith;

 private  Boolean includes;

 private  OffsetDateTime createdOn;

 private  OffsetDateTime modifiedOn;

 private  String createdBy;

 private  String modifiedBy;


public void setContactPerson(String contactPerson){
    this.contactPerson = contactPerson;
}


public void setPhoneNo(String phoneNo){
    this.phoneNo = phoneNo;
}


public Long getCustomerId(){
    return customerId;
}


public String getContactMobile(){
    return contactMobile;
}


public void setCreatedOn(OffsetDateTime createdOn){
    this.createdOn = createdOn;
}


public void setNotes(String notes){
    this.notes = notes;
}


public String getNotes(){
    return notes;
}


public void setStartsWith(Boolean startsWith){
    this.startsWith = startsWith;
}


public CustomerAdditionalDetailsVO getCustomerAdditionalDetailsVO(){
    return customerAdditionalDetailsVO;
}


public String getPhoneNo(){
    return phoneNo;
}


public void setCustomerAdditionalDetailsVO(CustomerAdditionalDetailsVO customerAdditionalDetailsVO){
    this.customerAdditionalDetailsVO = customerAdditionalDetailsVO;
}


public void setContactMobile(String contactMobile){
    this.contactMobile = contactMobile;
}


public String getAddress(){
    return address;
}


public void setAddress(String address){
    this.address = address;
}


public String getContactPerson(){
    return contactPerson;
}


public Boolean getStartsWith(){
    return startsWith;
}


public OffsetDateTime getModifiedOn(){
    return modifiedOn;
}


public String getCustomerName(){
    return customerName;
}


public OffsetDateTime getCreatedOn(){
    return createdOn;
}


public void setCustomerName(String customerName){
    this.customerName = customerName;
}


public String getModifiedBy(){
    return modifiedBy;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public void setEmail(String email){
    this.email = email;
}


public void setModifiedOn(OffsetDateTime modifiedOn){
    this.modifiedOn = modifiedOn;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public String getMobile(){
    return mobile;
}


public String getEmail(){
    return email;
}


public Boolean getIncludes(){
    return includes;
}


@Override
public String toString(){
    return new StringJoiner(", ", CustomerVO.class.getSimpleName() + "[", "]").add("customerId=" + customerId).add("customerName='" + customerName + "'").add("address='" + address + "'").add("phoneNo='" + phoneNo + "'").add("mobile='" + mobile + "'").add("email='" + email + "'").add("contactPerson='" + contactPerson + "'").add("contactMobile='" + contactMobile + "'").add("notes='" + notes + "'").add("startsWith=" + startsWith).add("includes=" + includes).add("createdOn=" + createdOn).add("modifiedOn=" + modifiedOn).add("createdBy='" + createdBy + "'").add("modifiedBy='" + modifiedBy + "'").toString();
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public void setIncludes(Boolean includes){
    this.includes = includes;
}


public String getCreatedBy(){
    return createdBy;
}


}