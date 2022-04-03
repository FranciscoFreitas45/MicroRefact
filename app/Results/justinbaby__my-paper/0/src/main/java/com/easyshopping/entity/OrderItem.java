package com.easyshopping.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "xx_order_item")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_order_item_sequence")
public class OrderItem extends BaseEntity{

 private  long serialVersionUID;

 private  String sn;

 private  String name;

 private  String fullName;

 private  BigDecimal price;

 private  Integer weight;

 private  String thumbnail;

 private  Boolean isGift;

 private  Integer quantity;

 private  Integer shippedQuantity;

 private  Integer returnQuantity;

 private  Product product;

 private  Order order;


public void setName(String name){
    this.name = name;
}


@Column(nullable = false)
public Integer getShippedQuantity(){
    return shippedQuantity;
}


@JsonProperty
@Column(nullable = false, updatable = false)
public String getName(){
    return name;
}


@JsonProperty
@NotNull
@Min(1)
@Max(10000)
@Column(nullable = false)
public Integer getQuantity(){
    return quantity;
}


@JsonProperty
@Column(updatable = false)
public Integer getWeight(){
    return weight;
}


@JsonProperty
@NotEmpty
@Column(nullable = false, updatable = false)
public String getSn(){
    return sn;
}


@JsonProperty
@Column(nullable = false, updatable = false)
public Boolean getIsGift(){
    return isGift;
}


public void setPrice(BigDecimal price){
    this.price = price;
}


@JsonProperty
@Column(updatable = false)
public String getThumbnail(){
    return thumbnail;
}


public void setIsGift(Boolean isGift){
    this.isGift = isGift;
}


public void setSn(String sn){
    this.sn = sn;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


@JsonProperty
@Transient
public int getTotalWeight(){
    if (getWeight() != null && getQuantity() != null) {
        return getWeight() * getQuantity();
    } else {
        return 0;
    }
}


@JsonProperty
@Column(nullable = false, updatable = false)
public String getFullName(){
    return fullName;
}


@Column(nullable = false)
public Integer getReturnQuantity(){
    return returnQuantity;
}


public void setProduct(Product product){
    this.product = product;
}


@ManyToOne(fetch = FetchType.LAZY)
public Product getProduct(){
    return product;
}


public void setShippedQuantity(Integer shippedQuantity){
    this.shippedQuantity = shippedQuantity;
}


public void setThumbnail(String thumbnail){
    this.thumbnail = thumbnail;
}


public void setWeight(Integer weight){
    this.weight = weight;
}


@JsonProperty
@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getPrice(){
    return price;
}


public void setOrder(Order order){
    this.order = order;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


@JsonProperty
@Transient
public BigDecimal getSubtotal(){
    if (getPrice() != null && getQuantity() != null) {
        return getPrice().multiply(new BigDecimal(getQuantity()));
    } else {
        return new BigDecimal(0);
    }
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orders", nullable = false, updatable = false)
public Order getOrder(){
    return order;
}


public void setReturnQuantity(Integer returnQuantity){
    this.returnQuantity = returnQuantity;
}


}