package com.poseidon.transaction.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "transaction")
public class Transaction extends CommonEntity{

@Column(name = "tagno")
 private  String tagno;

@Column(name = "datereported")
 private  LocalDateTime dateReported;

@Column(name = "customerId")
 private  Long customerId;

@Column(name = "productcategory")
 private  String productCategory;

@Column(name = "makeId")
 private  Long makeId;

@Column(name = "modelId")
 private  Long modelId;

@Column(name = "serialNumber")
 private  String serialNumber;

@Column(name = "accessories")
 private  String accessories;

@Column(name = "complaintReported")
 private  String complaintReported;

@Column(name = "complaintDiagnosed")
 private  String complaintDiagnosed;

@Column(name = "engineerRemarks")
 private  String engineerRemarks;

@Column(name = "repairAction")
 private  String repairAction;

@Column(name = "note")
 private  String note;

@Column(name = "status")
 private  String status;


public Long getModelId(){
    return modelId;
}


public void setModelId(Long modelId){
    this.modelId = modelId;
}


public String getComplaintDiagnosed(){
    return complaintDiagnosed;
}


public String getTagno(){
    return tagno;
}


public String getStatus(){
    return status;
}


public Long getCustomerId(){
    return customerId;
}


public String getEngineerRemarks(){
    return engineerRemarks;
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


public String getComplaintReported(){
    return complaintReported;
}


public void setComplaintDiagnosed(String complaintDiagnosed){
    this.complaintDiagnosed = complaintDiagnosed;
}


public String getSerialNumber(){
    return serialNumber;
}


public void setTagno(String tagno){
    this.tagno = tagno;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
}


public String getNote(){
    return note;
}


public void setStatus(String status){
    this.status = status;
}


public void setProductCategory(String productCategory){
    this.productCategory = productCategory;
}


public void setRepairAction(String repairAction){
    this.repairAction = repairAction;
}


public String getProductCategory(){
    return productCategory;
}


public void setEngineerRemarks(String engineerRemarks){
    this.engineerRemarks = engineerRemarks;
}


public void setNote(String note){
    this.note = note;
}


public LocalDateTime getDateReported(){
    return dateReported;
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


public void setAccessories(String accessories){
    this.accessories = accessories;
}


public void setMakeId(Long makeId){
    this.makeId = makeId;
}


public Long getMakeId(){
    return makeId;
}


}