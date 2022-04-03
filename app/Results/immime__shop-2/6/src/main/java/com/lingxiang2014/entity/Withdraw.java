package com.lingxiang2014.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "lx_withdraw")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_withdraw_sequence")
public class Withdraw extends BaseEntity{

 private  String number;

 private  long serialVersionUID;

 private  BigDecimal balance;

 private  Admin operator;

 private  String memo;

 private  Member member;

 private  Bank bank;

 private  BigDecimal fee;

 private  Status status;

 private  BigDecimal realBalance;


public void setFee(BigDecimal fee){
    this.fee = fee;
}


public void setRealBalance(BigDecimal realBalance){
    this.realBalance = realBalance;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Member getMember(){
    return member;
}


public Status getStatus(){
    return status;
}


public void setNumber(String number){
    this.number = number;
}


public void setStatus(Status status){
    this.status = status;
}


@ManyToOne(fetch = FetchType.LAZY)
public Bank getBank(){
    return bank;
}


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


public void setBank(Bank bank){
    this.bank = bank;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getFee(){
    return fee;
}


public void setOperator(Admin operator){
    this.operator = operator;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


@Column(precision = 21, scale = 6)
public BigDecimal getRealBalance(){
    return realBalance;
}


@ManyToOne(fetch = FetchType.LAZY)
public Admin getOperator(){
    return operator;
}


}