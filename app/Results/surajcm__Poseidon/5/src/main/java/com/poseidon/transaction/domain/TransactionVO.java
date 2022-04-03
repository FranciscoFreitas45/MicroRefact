package com.poseidon.transaction.domain;
 import java.time.OffsetDateTime;
import java.util.StringJoiner;
public class TransactionVO {

 private  Long id;

 private  String tagNo;

 private  String dateReported;

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

 private  OffsetDateTime createdOn;

 private  OffsetDateTime modifiedOn;

 private  String createdBy;

 private  String modifiedBy;

 private  Boolean startswith;

 private  Boolean includes;

 private  String notes;

 private  String startDate;

 private  String endDate;


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


public OffsetDateTime getCreatedOn(){
    return createdOn;
}


public void setProductCategory(String productCategory){
    this.productCategory = productCategory;
}


public void setRepairAction(String repairAction){
    this.repairAction = repairAction;
}


public void setModifiedOn(OffsetDateTime modifiedOn){
    this.modifiedOn = modifiedOn;
}


public String getDateReported(){
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


public Long getId(){
    return id;
}


public void setCreatedOn(OffsetDateTime createdOn){
    this.createdOn = createdOn;
}


public String getMakeName(){
    return makeName;
}


public String getNotes(){
    return notes;
}


public void setComplaintReported(String complaintReported){
    this.complaintReported = complaintReported;
}


public void setDateReported(String dateReported){
    this.dateReported = dateReported;
}


public String getAccessories(){
    return accessories;
}


public String getRepairAction(){
    return repairAction;
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


public OffsetDateTime getModifiedOn(){
    return modifiedOn;
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


public String getProductCategory(){
    return productCategory;
}


public void setModelName(String modelName){
    this.modelName = modelName;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public Boolean getIncludes(){
    return includes;
}


@Override
public String toString(){
    return new StringJoiner(", ", TransactionVO.class.getSimpleName() + "[", "]").add("id=" + id).add("tagNo='" + tagNo + "'").add("dateReported='" + dateReported + "'").add("customerId=" + customerId).add("customerName='" + customerName + "'").add("productCategory='" + productCategory + "'").add("makeId=" + makeId).add("makeName='" + makeName + "'").add("modelId=" + modelId).add("modelName='" + modelName + "'").add("serialNo='" + serialNo + "'").add("accessories='" + accessories + "'").add("complaintReported='" + complaintReported + "'").add("complaintDiagonsed='" + complaintDiagnosed + "'").add("enggRemark='" + enggRemark + "'").add("repairAction='" + repairAction + "'").add("status='" + status + "'").add("createdOn=" + createdOn).add("modifiedOn=" + modifiedOn).add("createdBy='" + createdBy + "'").add("modifiedBy='" + modifiedBy + "'").add("startswith=" + startswith).add("includes=" + includes).add("notes='" + notes + "'").add("startDate='" + startDate + "'").add("endDate='" + endDate + "'").toString();
}


public void setAccessories(String accessories){
    this.accessories = accessories;
}


public void setMakeId(Long makeId){
    this.makeId = makeId;
}


}