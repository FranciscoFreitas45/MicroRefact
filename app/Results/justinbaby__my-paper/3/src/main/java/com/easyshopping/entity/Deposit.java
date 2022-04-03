package com.easyshopping.entity;
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
@Table(name = "xx_deposit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_deposit_sequence")
public class Deposit extends BaseEntity{

 private  long serialVersionUID;

 private  Type type;

 private  BigDecimal credit;

 private  BigDecimal debit;

 private  BigDecimal balance;

 private  String operator;

 private  String memo;

 private  Member member;

 private  Order order;

 private  Payment payment;


public void setCredit(BigDecimal credit){
    this.credit = credit;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Member getMember(){
    return member;
}


public void setType(Type type){
    this.type = type;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setOrder(Order order){
    this.order = order;
}


@Length(max = 200)
@Column(updatable = false)
public String getMemo(){
    return memo;
}


public void setMember(Member member){
    this.member = member;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getCredit(){
    return credit;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getBalance(){
    return balance;
}


@Column(nullable = false, updatable = false)
public Type getType(){
    return type;
}


public void setDebit(BigDecimal debit){
    this.debit = debit;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orders")
public Order getOrder(){
    return order;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getDebit(){
    return debit;
}


@ManyToOne(fetch = FetchType.LAZY)
public Payment getPayment(){
    return payment;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


@Column(updatable = false)
public String getOperator(){
    return operator;
}


public void setPayment(Payment payment){
    this.payment = payment;
}


}