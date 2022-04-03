package com.lingxiang2014.entity;
 import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "lx_bankType")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_bankType_sequence")
public class BankType extends BaseEntity{

 private  long serialVersionUID;

 private  String fullName;

 private  String shortName;

 private  Boolean isEnabled;

 private  BigDecimal feeRate;

 private  Set<Bank> banks;


@OneToMany(mappedBy = "bankType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate desc")
public Set<Bank> getBanks(){
    return banks;
}


public void setIsEnabled(Boolean isEnabled){
    this.isEnabled = isEnabled;
}


@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getFeeRate(){
    return feeRate;
}


@NotNull
@Column(nullable = false)
public String getShortName(){
    return shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public void setBanks(Set<Bank> banks){
    this.banks = banks;
}


public void setFeeRate(BigDecimal feeRate){
    this.feeRate = feeRate;
}


@NotNull
@Column(nullable = false)
public String getFullName(){
    return fullName;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


}