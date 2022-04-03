package com.poseidon.customer.domain;
 import java.time.OffsetDateTime;
public class CustomerAdditionalDetailsVO {

 private  Long customerId;

 private  String contactPerson;

 private  String contactMobile;

 private  String notes;

 private  OffsetDateTime createdOn;

 private  OffsetDateTime modifiedOn;

 private  String createdBy;

 private  String modifiedBy;


public void setContactPerson(String contactPerson){
    this.contactPerson = contactPerson;
}


public String getContactPerson(){
    return contactPerson;
}


public OffsetDateTime getModifiedOn(){
    return modifiedOn;
}


public OffsetDateTime getCreatedOn(){
    return createdOn;
}


public String getModifiedBy(){
    return modifiedBy;
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


public void setModifiedOn(OffsetDateTime modifiedOn){
    this.modifiedOn = modifiedOn;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setContactMobile(String contactMobile){
    this.contactMobile = contactMobile;
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public String getCreatedBy(){
    return createdBy;
}


}