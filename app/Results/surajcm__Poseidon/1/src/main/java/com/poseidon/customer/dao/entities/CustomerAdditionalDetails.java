package com.poseidon.customer.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "customer_additional_details")
public class CustomerAdditionalDetails extends CommonEntity{

@Column(name = "customerId")
 private  Long customerId;

@Column(name = "contactPerson")
 private  String contactPerson;

@Column(name = "contactPhone")
 private  String contactPhone;

@Column(name = "note")
 private  String note;


public void setContactPerson(String contactPerson){
    this.contactPerson = contactPerson;
}


public String getContactPhone(){
    return contactPhone;
}


public String getContactPerson(){
    return contactPerson;
}


public void setNote(String note){
    this.note = note;
}


public String getNote(){
    return note;
}


public void setCustomerId(Long customerId){
    this.customerId = customerId;
}


public Long getCustomerId(){
    return customerId;
}


public void setContactPhone(String contactPhone){
    this.contactPhone = contactPhone;
}


}