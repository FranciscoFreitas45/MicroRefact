package com.poseidon.invoice.domain;
 import java.time.OffsetDateTime;
import java.util.StringJoiner;
public class InvoiceVO {

 private  Long id;

 private  Long transactionId;

 private  String tagNo;

 private  Long customerId;

 private  String customerName;

 private  String description;

 private  String serialNo;

 private  int quantity;

 private  Double rate;

 private  Double amount;

 private  Boolean startsWith;

 private  Boolean includes;

 private  Boolean greater;

 private  Boolean lesser;

 private  OffsetDateTime createdDate;

 private  OffsetDateTime modifiedDate;

 private  String createdBy;

 private  String modifiedBy;


public int getQuantity(){
    return quantity;
}


public void setTagNo(String tagNo){
    this.tagNo = tagNo;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public Long getCustomerId(){
    return customerId;
}


public Double getRate(){
    return rate;
}


public void setStartsWith(Boolean startsWith){
    this.startsWith = startsWith;
}


public void setLesser(Boolean lesser){
    this.lesser = lesser;
}


public void setId(Long id){
    this.id = id;
}


public void setSerialNo(String serialNo){
    this.serialNo = serialNo;
}


public Boolean getLesser(){
    return lesser;
}


public OffsetDateTime getCreatedDate(){
    return createdDate;
}


public void setAmount(Double amount){
    this.amount = amount;
}


public void setGreater(Boolean greater){
    this.greater = greater;
}


public String getSerialNo(){
    return serialNo;
}


public Double getAmount(){
    return amount;
}


public Boolean getStartsWith(){
    return startsWith;
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


public void setRate(Double rate){
    this.rate = rate;
}


public String getModifiedBy(){
    return modifiedBy;
}


public OffsetDateTime getModifiedDate(){
    return modifiedDate;
}


public Boolean getGreater(){
    return greater;
}


public void setModifiedDate(OffsetDateTime modifiedDate){
    this.modifiedDate = modifiedDate;
}


public Long getTransactionId(){
    return transactionId;
}


public void setCreatedDate(OffsetDateTime createdDate){
    this.createdDate = createdDate;
}


public void setQuantity(int quantity){
    this.quantity = quantity;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setTransactionId(Long transactionId){
    this.transactionId = transactionId;
}


public Boolean getIncludes(){
    return includes;
}


@Override
public String toString(){
    return new StringJoiner(", ", InvoiceVO.class.getSimpleName() + "[", "]").add("id=" + id).add("tagNo='" + tagNo + "'").add("customerId=" + customerId).add("customerName='" + customerName + "'").add("description='" + description + "'").add("serialNo='" + serialNo + "'").add("quantity=" + quantity).add("rate=" + rate).add("amount=" + amount).add("startsWith=" + startsWith).add("includes=" + includes).add("greater=" + greater).add("lesser=" + lesser).add("createdDate=" + createdDate).add("modifiedDate=" + modifiedDate).add("createdBy='" + createdBy + "'").add("modifiedBy='" + modifiedBy + "'").toString();
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