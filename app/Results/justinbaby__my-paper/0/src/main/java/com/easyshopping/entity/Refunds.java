package com.easyshopping.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "xx_refunds")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_refunds_sequence")
public class Refunds extends BaseEntity{

 private  long serialVersionUID;

 private  String sn;

 private  Method method;

 private  String paymentMethod;

 private  String bank;

 private  String account;

 private  BigDecimal amount;

 private  String payee;

 private  String operator;

 private  String memo;

 private  Order order;


public void setPayee(String payee){
    this.payee = payee;
}


@Length(max = 200)
@Column(updatable = false)
public String getPayee(){
    return payee;
}


@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getSn(){
    return sn;
}


@NotNull
@Column(nullable = false, updatable = false)
public Method getMethod(){
    return method;
}


@Column(updatable = false)
public String getPaymentMethod(){
    return paymentMethod;
}


@Length(max = 200)
@Column(updatable = false)
public String getBank(){
    return bank;
}


public void setMethod(Method method){
    this.method = method;
}


public void setPaymentMethod(String paymentMethod){
    this.paymentMethod = paymentMethod;
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


public void setBank(String bank){
    this.bank = bank;
}


@Length(max = 200)
@Column(updatable = false)
public String getAccount(){
    return account;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orders", nullable = false, updatable = false)
public Order getOrder(){
    return order;
}


public void setSn(String sn){
    this.sn = sn;
}


public void setAccount(String account){
    this.account = account;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setAmount(BigDecimal amount){
    this.amount = amount;
}


@Column(nullable = false, updatable = false)
public String getOperator(){
    return operator;
}


@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getAmount(){
    return amount;
}


}