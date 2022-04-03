package com.easyshopping.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Receiver extends BaseEntity{

 private  long serialVersionUID;

 public  Integer MAX_RECEIVER_COUNT;

 private  String consignee;

 private  String areaName;

 private  String address;

 private  String zipCode;

 private  String phone;

 private  Boolean isDefault;

 private  Area area;

 private  Member member;


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getConsignee(){
    return consignee;
}


public void setConsignee(String consignee){
    this.consignee = consignee;
}


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getPhone(){
    return phone;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


public void setAddress(String address){
    this.address = address;
}


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
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


@JsonProperty
@Column(nullable = false)
public String getAreaName(){
    return areaName;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Member getMember(){
    return member;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setMember(Member member){
    this.member = member;
}


@JsonProperty
@NotNull
@Column(nullable = false)
public Boolean getIsDefault(){
    return isDefault;
}


public void setIsDefault(Boolean isDefault){
    this.isDefault = isDefault;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


@JsonProperty
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