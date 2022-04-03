package com.easyshopping.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class ProductNotify extends BaseEntity{

 private  long serialVersionUID;

 private  String email;

 private  Boolean hasSent;

 private  Member member;

 private  Product product;


public void setProduct(Product product){
    this.product = product;
}


public void setMember(Member member){
    this.member = member;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Product getProduct(){
    return product;
}


public void setEmail(String email){
    this.email = email;
}


public void setHasSent(Boolean hasSent){
    this.hasSent = hasSent;
}


@NotEmpty
@Email
@Length(max = 200)
@Column(nullable = false, updatable = false)
public String getEmail(){
    return email;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Member getMember(){
    return member;
}


@Column(nullable = false)
public Boolean getHasSent(){
    return hasSent;
}


}