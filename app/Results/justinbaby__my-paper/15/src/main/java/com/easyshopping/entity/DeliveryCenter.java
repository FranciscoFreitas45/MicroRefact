package com.easyshopping.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_delivery_center")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_delivery_center_sequence")
public class DeliveryCenter extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  String contact;

 private  String areaName;

 private  String address;

 private  String zipCode;

 private  String phone;

 private  String mobile;

 private  String memo;

 private  Boolean isDefault;

 private  Area area;


public void setName(String name){
    this.name = name;
}


@Length(max = 200)
public String getPhone(){
    return phone;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


public void setAddress(String address){
    this.address = address;
}


@Length(max = 200)
public String getZipCode(){
    return zipCode;
}


public void setArea(Area area){
    this.area = area;
}


@PrePersist
public void prePersist(){
    if (getArea() != null) {
        setAreaName(getArea().getFullName());
    }
}


@Column(nullable = false)
public String getAreaName(){
    return areaName;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setContact(String contact){
    this.contact = contact;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getContact(){
    return contact;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@NotNull
@Column(nullable = false)
public Boolean getIsDefault(){
    return isDefault;
}


public void setIsDefault(Boolean isDefault){
    this.isDefault = isDefault;
}


@Length(max = 200)
public String getMobile(){
    return mobile;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getAddress(){
    return address;
}


@PreUpdate
public void preUpdate(){
    if (getArea() != null) {
        setAreaName(getArea().getFullName());
    }
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
public Area getArea(){
    return area;
}


}