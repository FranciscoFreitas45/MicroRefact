package com.poseidon.customer.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "customer")
public class Customer extends CommonEntity{

@Column(name = "name")
 private  String name;

@Column(name = "address")
 private  String address;

@Column(name = "phone")
 private  String phone;

@Column(name = "mobile")
 private  String mobile;

@Column(name = "email")
 private  String email;


public void setName(String name){
    this.name = name;
}


public String getPhone(){
    return phone;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getName(){
    return name;
}


public void setAddress(String address){
    this.address = address;
}


public void setEmail(String email){
    this.email = email;
}


public String getMobile(){
    return mobile;
}


public String getEmail(){
    return email;
}


public String getAddress(){
    return address;
}


public void setPhone(String phone){
    this.phone = phone;
}


}