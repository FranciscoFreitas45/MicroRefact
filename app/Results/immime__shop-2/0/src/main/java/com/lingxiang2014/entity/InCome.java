package com.lingxiang2014.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "lx_inCome")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_inCome_sequence")
public class InCome extends BaseEntity{

 private  long serialVersionUID;

 private  String number;

 private  BigDecimal balance;

 private  String memo;

 private  Member member;


public String getNumber(){
    return number;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Column(updatable = false, length = 8000)
public String getMemo(){
    return memo;
}


public void setMember(Member member){
    this.member = member;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getBalance(){
    return balance;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Member getMember(){
    return member;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public void setNumber(String number){
    this.number = number;
}


}