package com.poseidon.transaction.domain;
 import java.time.LocalDateTime;
import java.util.StringJoiner;
public class TransactionReportVO {

 private  Long id;

 private  String tagNo;

 private  LocalDateTime dateReported;

 private  Long customerId;

 private  String customerName;

 private  String productCategory;

 private  Long makeId;

 private  String makeName;

 private  Long modelId;

 private  String modelName;

 private  String serialNo;

 private  String accessories;

 private  String complaintReported;

 private  String complaintDiagnosed;

 private  String enggRemark;

 private  String repairAction;

 private  String status;

 private  String createdBy;

 private  String modifiedBy;

 private  Boolean startswith;

 private  Boolean includes;

 private  String notes;

 private  String startDate;

 private  String endDate;

 private  String address;

 private  String phone;

 private  String mobile;

 private  String email;

 private  String companyName;

 private  String companyAddress;

 private  String companyPhoneNumber;

 private  String companyWebsite;

 private  String companyEmail;

 private  String companyTerms;


public Long getModelId(){
    return modelId;
}


public String getStartDate(){
    return startDate;
}


public void setModelId(Long modelId){
    this.modelId = modelId;
}


public String getComplaintDiagnosed(){
    return complaintDiagnosed;
}


public void setTagNo(String tagNo){
    this.tagNo = tagNo;
}


public String getEndDate(){
    return endDate;
}


public String getStatus(){
    return status;
}


public String getModelName(){
    return modelName;
}


public void setEndDate(String endDate){
    this.endDate = endDate;
}


public String getCompanyTerms(){
    return companyTerms;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public Long getCustomerId(){
    return customerId;
}


public void setNotes(String notes){
    this.notes = notes;
}


public String getEnggRemark(){
    return enggRemark;
}


public void setId(Long id){
    this.id = id;
}


public Boolean getStartswith(){
    return startswith;
}


public String getComplaintReported(){
    return complaintReported;
}


public void setEnggRemark(String enggRemark){
    this.enggRemark = enggRemark;
}


public String getSerialNo(){
    return serialNo;
}


public void setStartDate(String startDate){
    this.startDate = startDate;
}


public void setStartswith(Boolean startswith){
    this.startswith = startswith;
}


public void setCompanyEmail(String companyEmail){
    this.companyEmail = companyEmail;
}


public String getCompanyEmail(){
    return companyEmail;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setProductCategory(String productCategory){
    this.productCategory = productCategory;
}


public void setRepairAction(String repairAction){
    this.repairAction = repairAction;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getCompanyPhoneNumber(){
    return companyPhoneNumber;
}


public void setEmail(String email){
    this.email = email;
}


public String getEmail(){
    return email;
}


public LocalDateTime getDateReported(){
    return dateReported;
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


public void setIncludes(Boolean includes){
    this.includes = includes;
}


public Long getMakeId(){
    return makeId;
}


public String getPhone(){
    return phone;
}


public Long getId(){
    return id;
}


public String getMakeName(){
    return makeName;
}


public void setCompanyWebsite(String companyWebsite){
    this.companyWebsite = companyWebsite;
}


public void setCompanyPhoneNumber(String companyPhoneNumber){
    this.companyPhoneNumber = companyPhoneNumber;
}


public String getNotes(){
    return notes;
}


public void setComplaintReported(String complaintReported){
    this.complaintReported = complaintReported;
}


public void setDateReported(LocalDateTime dateReported){
    this.dateReported = dateReported;
}


public String getAccessories(){
    return accessories;
}


public String getRepairAction(){
    return repairAction;
}


public String getAddress(){
    return address;
}


public void setCompanyTerms(String companyTerms){
    this.companyTerms = companyTerms;
}


public void setSerialNo(String serialNo){
    this.serialNo = serialNo;
}


public void setComplaintDiagnosed(String complaintDiagnosed){
    this.complaintDiagnosed = complaintDiagnosed;
}


public void setMakeName(String makeName){
    this.makeName = makeName;
}


public void setAddress(String address){
    this.address = address;
}


public String getCustomerName(){
    return customerName;
}


public String getTagNo(){
    return tagNo;
}


public void setCustomerName(String customerName){
    this.customerName = customerName;
}


public String getModifiedBy(){
    return modifiedBy;
}


public void setStatus(String status){
    this.status = status;
}


public String getCompanyWebsite(){
    return companyWebsite;
}


public String getProductCategory(){
    return productCategory;
}


public void setModelName(String modelName){
    this.modelName = modelName;
}


public String getCompanyAddress(){
    return companyAddress;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public String getCompanyName(){
    return companyName;
}


public void setCompanyAddress(String companyAddress){
    this.companyAddress = companyAddress;
}


public String getMobile(){
    return mobile;
}


public Boolean getIncludes(){
    return includes;
}


@Override
public String toString(){
    return new StringJoiner(", ", TransactionReportVO.class.getSimpleName() + "[", "]").add("id=" + id).add("tagNo='" + tagNo + "'").add("dateReported=" + dateReported).add("customerId=" + customerId).add("customerName='" + customerName + "'").add("productCategory='" + productCategory + "'").add("makeId=" + makeId).add("makeName='" + makeName + "'").add("modelId=" + modelId).add("modelName='" + modelName + "'").add("serialNo='" + serialNo + "'").add("accessories='" + accessories + "'").add("complaintReported='" + complaintReported + "'").add("complaintDiagnosed='" + complaintDiagnosed + "'").add("enggRemark='" + enggRemark + "'").add("repairAction='" + repairAction + "'").add("status='" + status + "'").add("createdBy='" + createdBy + "'").add("modifiedBy='" + modifiedBy + "'").add("startswith=" + startswith).add("includes=" + includes).add("notes='" + notes + "'").add("startDate='" + startDate + "'").add("endDate='" + endDate + "'").add("address='" + address + "'").add("phone='" + phone + "'").add("mobile='" + mobile + "'").add("email='" + email + "'").add("companyName='" + companyName + "'").add("companyAddress='" + companyAddress + "'").add("companyPhoneNumber='" + companyPhoneNumber + "'").add("companyWebsite='" + companyWebsite + "'").add("companyEmail='" + companyEmail + "'").add("companyTerms='" + companyTerms + "'").toString();
}


public void setAccessories(String accessories){
    this.accessories = accessories;
}


public void setMakeId(Long makeId){
    this.makeId = makeId;
}


}