package com.lingxiang2014.DTO;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class Deposit extends BaseEntity{

 private  long serialVersionUID;

 private  Type type;

 private  BigDecimal credit;

 private  BigDecimal debit;

 private  BigDecimal balance;

 private  String operator;

 private  String memo;

 private  Member member;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Member getMember(){
    return member;
}


@Column(updatable = false, length = 8000)
public String getMemo(){
    return memo;
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


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getDebit(){
    return debit;
}


@Column(updatable = false)
public String getOperator(){
    return operator;
}


public void setType(Type type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCredit(BigDecimal credit){
    this.credit = credit;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCredit"))

.queryParam("credit",credit)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDebit(BigDecimal debit){
    this.debit = debit;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDebit"))

.queryParam("debit",debit)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBalance"))

.queryParam("balance",balance)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOperator(String operator){
    this.operator = operator;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOperator"))

.queryParam("operator",operator)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMember(Member member){
    this.member = member;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMember"))

.queryParam("member",member)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMemo(String memo){
    this.memo = memo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMemo"))

.queryParam("memo",memo)
;
restTemplate.put(builder.toUriString(),null);
}


}