package com.easyshopping.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
public class GiftItem extends BaseEntity{

 private  long serialVersionUID;

 private  Integer quantity;

 private  Product gift;

 private  Promotion promotion;


@JsonProperty
@NotNull
@Min(1)
@Column(nullable = false)
public Integer getQuantity(){
    return quantity;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Promotion getPromotion(){
    return promotion;
}


public void setGift(Product gift){
    this.gift = gift;
}


@JsonProperty
@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Product getGift(){
    return gift;
}


public void setPromotion(Promotion promotion){
    this.promotion = promotion;
}


}