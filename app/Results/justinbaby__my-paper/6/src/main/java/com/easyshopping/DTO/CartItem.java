package com.easyshopping.DTO;
 import java.math.BigDecimal;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.easyshopping.Setting;
import com.easyshopping.util.SettingUtils;
public class CartItem extends BaseEntity{

 private  long serialVersionUID;

 public  Integer MAX_QUANTITY;

 private  Integer quantity;

 private  Product product;

 private  Cart cart;

 private  BigDecimal tempPrice;

 private  Long tempPoint;


@Transient
public void add(int quantity){
    if (quantity > 0) {
        if (getQuantity() != null) {
            setQuantity(getQuantity() + quantity);
        } else {
            setQuantity(quantity);
        }
    }
}


public void setProduct(Product product){
    this.product = product;
}


@Transient
public Long getTempPoint(){
    if (tempPoint == null) {
        return getPoint();
    }
    return tempPoint;
}


@Transient
public void setTempPoint(Long tempPoint){
    this.tempPoint = tempPoint;
}


@Transient
public long getPoint(){
    if (getProduct() != null && getProduct().getPoint() != null && getQuantity() != null) {
        return getProduct().getPoint() * getQuantity();
    } else {
        return 0L;
    }
}


@Transient
public boolean getIsLowStock(){
    if (getQuantity() != null && getProduct() != null && getProduct().getStock() != null && getQuantity() > getProduct().getAvailableStock()) {
        return true;
    } else {
        return false;
    }
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Product getProduct(){
    return product;
}


@Column(nullable = false)
public Integer getQuantity(){
    return quantity;
}


@Transient
public int getWeight(){
    if (getProduct() != null && getProduct().getWeight() != null && getQuantity() != null) {
        return getProduct().getWeight() * getQuantity();
    } else {
        return 0;
    }
}


public void setCart(Cart cart){
    this.cart = cart;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public Cart getCart(){
    return cart;
}


@Transient
public BigDecimal getPrice(){
    if (getProduct() != null && getProduct().getPrice() != null) {
        Setting setting = SettingUtils.get();
        if (getCart() != null && getCart().getMember() != null && getCart().getMember().getMemberRank() != null) {
            MemberRank memberRank = getCart().getMember().getMemberRank();
            Map<MemberRank, BigDecimal> memberPrice = getProduct().getMemberPrice();
            if (memberPrice != null && !memberPrice.isEmpty()) {
                if (memberPrice.containsKey(memberRank)) {
                    return setting.setScale(memberPrice.get(memberRank));
                }
            }
            if (memberRank.getScale() != null) {
                return setting.setScale(getProduct().getPrice().multiply(new BigDecimal(memberRank.getScale())));
            }
        }
        return setting.setScale(getProduct().getPrice());
    } else {
        return new BigDecimal(0);
    }
}


@Transient
public void setTempPrice(BigDecimal tempPrice){
    this.tempPrice = tempPrice;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


@Transient
public BigDecimal getTempPrice(){
    if (tempPrice == null) {
        return getSubtotal();
    }
    return tempPrice;
}


@Transient
public BigDecimal getSubtotal(){
    if (getQuantity() != null) {
        return getPrice().multiply(new BigDecimal(getQuantity()));
    } else {
        return new BigDecimal(0);
    }
}


}