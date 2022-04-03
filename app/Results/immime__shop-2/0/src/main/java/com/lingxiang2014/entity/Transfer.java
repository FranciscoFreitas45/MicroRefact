package com.lingxiang2014.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "lx_transfer")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_transfer_sequence")
public class Transfer extends BaseEntity{

 private  long serialVersionUID;

 private  Member fromMember;

 private  BigDecimal oldFromBalance;

 private  BigDecimal newFromBalance;

 private  Member toMember;

 private  BigDecimal oldToBalance;

 private  BigDecimal newToBalance;

 private  String memo;

 private  BigDecimal balance;

 private  BigDecimal fee;

 private  BigDecimal balance1;

 private  Type type;

 private  Method method;


public void setToMember(Member toMember){
    this.toMember = toMember;
}


public void setOldFromBalance(BigDecimal oldFromBalance){
    this.oldFromBalance = oldFromBalance;
}


public void setNewToBalance(BigDecimal newToBalance){
    this.newToBalance = newToBalance;
}


public void setFee(BigDecimal fee){
    this.fee = fee;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getNewToBalance(){
    return newToBalance;
}


public Method getMethod(){
    return method;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getOldToBalance(){
    return oldToBalance;
}


public void setType(Type type){
    this.type = type;
}


public void setBalance1(BigDecimal balance1){
    this.balance1 = balance1;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setMethod(Method method){
    this.method = method;
}


@Column(updatable = false, length = 8000)
public String getMemo(){
    return memo;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getBalance(){
    return balance;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getFromMember(){
    return fromMember;
}


public void setFromMember(Member fromMember){
    this.fromMember = fromMember;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getBalance1(){
    return balance1;
}


public Type getType(){
    return type;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getToMember(){
    return toMember;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getFee(){
    return fee;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getOldFromBalance(){
    return oldFromBalance;
}


public void setOldToBalance(BigDecimal oldToBalance){
    this.oldToBalance = oldToBalance;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getNewFromBalance(){
    return newFromBalance;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public void setNewFromBalance(BigDecimal newFromBalance){
    this.newFromBalance = newFromBalance;
}


}