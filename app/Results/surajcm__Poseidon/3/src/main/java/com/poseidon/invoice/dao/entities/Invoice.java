package com.poseidon.invoice.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "invoice")
public class Invoice extends CommonEntity{

@Column(name = "transactionId")
 private  Long transactionId;

@Column(name = "description")
 private  String description;

@Column(name = "serialNumber")
 private  String serialNumber;

@Column(name = "amount")
 private  Long amount;

@Column(name = "quantity")
 private  Integer quantity;

@Column(name = "rate")
 private  Long rate;

@Column(name = "customerId")
 private  Long customerId;

@Column(name = "customername")
 private  String customerName;

@Column(name = "tagNumber")
 private  String tagNumber;


public String getSerialNumber(){
    return serialNumber;
}


public Integer getQuantity(){
    return quantity;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
}


public String getCustomerName(){
    return customerName;
}


public void setCustomerName(String customerName){
    this.customerName = customerName;
}


public void setDescription(String description){
    this.description = description;
}


public String getTagNumber(){
    return tagNumber;
}


public String getDescription(){
    return description;
}


public void setRate(Long rate){
    this.rate = rate;
}


public Long getCustomerId(){
    return customerId;
}


public Long getRate(){
    return rate;
}


public Long getTransactionId(){
    return transactionId;
}


public void setTagNumber(String tagNumber){
    this.tagNumber = tagNumber;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


public void setTransactionId(Long transactionId){
    this.transactionId = transactionId;
}


public void setAmount(Long amount){
    this.amount = amount;
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


public Long getAmount(){
    return amount;
}


}