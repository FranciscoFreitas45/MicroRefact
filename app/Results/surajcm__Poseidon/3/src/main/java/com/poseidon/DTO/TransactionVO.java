package com.poseidon.DTO;
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


public String getComplaintDiagnosed(){
    return complaintDiagnosed;
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


public Long getCustomerId(){
    return customerId;
}


public String getEnggRemark(){
    return enggRemark;
}


public Boolean getStartswith(){
    return startswith;
}


public String getComplaintReported(){
    return complaintReported;
}


public String getSerialNo(){
    return serialNo;
}


public void setStartswith(Boolean startswith){
    this.startswith = startswith;
}


public OffsetDateTime getCreatedOn(){
    return createdOn;
}


public void setRepairAction(String repairAction){
    this.repairAction = repairAction;
}


public String getDateReported(){
    return dateReported;
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public String getCreatedBy(){
    return createdBy;
}


public Long getMakeId(){
    return makeId;
}


public Long getId(){
    return id;
}


public String getMakeName(){
    return makeName;
}


public String getNotes(){
    return notes;
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


public void setComplaintDiagnosed(String complaintDiagnosed){
    this.complaintDiagnosed = complaintDiagnosed;
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


public String getModifiedBy(){
    return modifiedBy;
}


public String getProductCategory(){
    return productCategory;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public Boolean getIncludes(){
    return includes;
}


public void setAccessories(String accessories){
    this.accessories = accessories;
}


}