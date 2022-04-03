package com.easyshopping.entity;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "xx_payment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_payment_sequence")
public class Payment extends BaseEntity{

 private  long serialVersionUID;

 public  String PAYMENT_METHOD_SEPARATOR;

 private  String sn;

 private  String tradeSn;

 private  Type type;

 private  Method method;

 private  Status status;

 private  String paymentMethod;

 private  String bank;

 private  String account;

 private  BigDecimal fee;

 private  BigDecimal amount;

 private  String payer;

 private  String operator;

 private  Date paymentDate;

 private  String memo;

 private  String paymentPluginId;

 private  Date expire;

 private  Deposit deposit;

 private  Member member;

 private  Order order;


public void setTradeSn(String tradeSn){
    this.tradeSn = tradeSn;
}


public void setFee(BigDecimal fee){
    this.fee = fee;
}


@Column(nullable = true, length = 100)
public String getTradeSn(){
    return tradeSn;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Member getMember(){
    return member;
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


@Column(nullable = false)
public Status getStatus(){
    return status;
}


@Transient
public BigDecimal getEffectiveAmount(){
    BigDecimal effectiveAmount = getAmount().subtract(getFee());
    return effectiveAmount.compareTo(new BigDecimal(0)) > 0 ? effectiveAmount : new BigDecimal(0);
}


@Column(updatable = false)
public String getPaymentMethod(){
    return paymentMethod;
}


@Length(max = 200)
public String getBank(){
    return bank;
}


public void setPayer(String payer){
    this.payer = payer;
}


public void setMember(Member member){
    this.member = member;
}


public void setExpire(Date expire){
    this.expire = expire;
}


@JoinColumn(updatable = false)
public Date getExpire(){
    return expire;
}


@Length(max = 200)
public String getAccount(){
    return account;
}


@JoinColumn(updatable = false)
public String getPaymentPluginId(){
    return paymentPluginId;
}


@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getFee(){
    return fee;
}


public void setSn(String sn){
    this.sn = sn;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setAmount(BigDecimal amount){
    this.amount = amount;
}


@OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
public Deposit getDeposit(){
    return deposit;
}


@Column(updatable = false)
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


public Date getPaymentDate(){
    return paymentDate;
}


@Length(max = 200)
public String getPayer(){
    return payer;
}


public void setType(Type type){
    this.type = type;
}


public void setStatus(Status status){
    this.status = status;
}


public void setMethod(Method method){
    this.method = method;
}


public void setPaymentMethod(String paymentMethod){
    this.paymentMethod = paymentMethod;
}


public void setPaymentDate(Date paymentDate){
    this.paymentDate = paymentDate;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setOrder(Order order){
    this.order = order;
}


@Transient
public boolean hasExpired(){
    return getExpire() != null && new Date().after(getExpire());
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


public void setPaymentPluginId(String paymentPluginId){
    this.paymentPluginId = paymentPluginId;
}


@Column(nullable = false, updatable = false)
public Type getType(){
    return type;
}


public void setBank(String bank){
    this.bank = bank;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orders", updatable = false)
public Order getOrder(){
    return order;
}


public void setAccount(String account){
    this.account = account;
}


public void setDeposit(Deposit deposit){
    this.deposit = deposit;
}


@PreRemove
public void preRemove(){
    if (getDeposit() != null) {
        getDeposit().setPayment(null);
    }
}


}