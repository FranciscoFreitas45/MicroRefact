package com.poseidon.DTO;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
public class Customer extends CommonEntity{

 private  String name;

 private  String address;

 private  String phone;

 private  String mobile;

 private  String email;


public String getPhone(){
    return phone;
}


public String getName(){
    return name;
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


}