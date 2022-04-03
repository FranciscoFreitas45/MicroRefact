package com.lingxiang2014.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Bank extends BaseEntity{

 private  long serialVersionUID;

 private  Member member;

 private  String bankAccount;

 private  BankType bankType;

 private  String bankTrueName;

 private  Boolean isDefault;

 private  Area area;

 private  String address;


public void setBankAccount(String bankAccount){
    this.bankAccount = bankAccount;
}


@NotNull
@Column(nullable = false)
public String getBankTrueName(){
    return bankTrueName;
}


public void setAddress(String address){
    this.address = address;
}


@ManyToOne(fetch = FetchType.LAZY)
public BankType getBankType(){
    return bankType;
}


public void setBankTrueName(String bankTrueName){
    this.bankTrueName = bankTrueName;
}


public void setArea(Area area){
    this.area = area;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getMember(){
    return member;
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


@NotNull
@Column(nullable = false)
public String getBankAccount(){
    return bankAccount;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


public void setBankType(BankType bankType){
    this.bankType = bankType;
}


@ManyToOne(fetch = FetchType.LAZY)
public Area getArea(){
    return area;
}


}