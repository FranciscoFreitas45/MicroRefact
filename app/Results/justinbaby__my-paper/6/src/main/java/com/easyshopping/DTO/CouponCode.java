package com.easyshopping.DTO;
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
public class CouponCode extends BaseEntity{

 private  long serialVersionUID;

 private  String code;

 private  Boolean isUsed;

 private  Date usedDate;

 private  Coupon coupon;

 private  Member member;

 private  Order order;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Coupon getCoupon(){
    return coupon;
}


public void setCode(String code){
    this.code = code;
}


public void setCoupon(Coupon coupon){
    this.coupon = coupon;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getMember(){
    return member;
}


public Date getUsedDate(){
    return usedDate;
}


public void setUsedDate(Date usedDate){
    this.usedDate = usedDate;
}


public void setOrder(Order order){
    this.order = order;
}


public void setMember(Member member){
    this.member = member;
}


@OneToOne(mappedBy = "couponCode", fetch = FetchType.LAZY)
@JoinColumn(name = "orders")
public Order getOrder(){
    return order;
}


@PreRemove
public void preRemove(){
    if (getOrder() != null) {
        getOrder().setCouponCode(null);
    }
}


@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getCode(){
    return code;
}


@Column(nullable = false)
public Boolean getIsUsed(){
    return isUsed;
}


public void setIsUsed(Boolean isUsed){
    this.isUsed = isUsed;
}


}