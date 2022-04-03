package com.poseidon.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public void setPhoneNo(String phoneNo){
    this.phoneNo = phoneNo;
}


public Long getCustomerId(){
    return customerId;
}


public String getContactMobile(){
    return contactMobile;
}


public void setNotes(String notes){
    this.notes = notes;
}


public String getNotes(){
    return notes;
}


public CustomerAdditionalDetailsVO getCustomerAdditionalDetailsVO(){
    return customerAdditionalDetailsVO;
}


public String getPhoneNo(){
    return phoneNo;
}


public void setContactMobile(String contactMobile){
    this.contactMobile = contactMobile;
}


public String getAddress(){
    return address;
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


public String getModifiedBy(){
    return modifiedBy;
}


public void setEmail(String email){
    this.email = email;
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


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


public void setIncludes(Boolean includes){
    this.includes = includes;
}


public String getCreatedBy(){
    return createdBy;
}


public void setCreatedOn(OffsetDateTime createdOn){
    this.createdOn = createdOn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatedOn"))

.queryParam("createdOn",createdOn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setModifiedOn(OffsetDateTime modifiedOn){
    this.modifiedOn = modifiedOn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setModifiedOn"))

.queryParam("modifiedOn",modifiedOn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setModifiedBy"))

.queryParam("modifiedBy",modifiedBy)
;
restTemplate.put(builder.toUriString(),null);
}


}