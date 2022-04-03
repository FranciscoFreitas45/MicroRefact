package com.easyshopping.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_shipping_item")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_shipping_item_sequence")
public class ShippingItem extends BaseEntity{

 private  long serialVersionUID;

 private  String sn;

 private  String name;

 private  Integer quantity;

 private  Shipping shipping;


public void setName(String name){
    this.name = name;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Shipping getShipping(){
    return shipping;
}


@NotEmpty
@Column(nullable = false, updatable = false)
public String getName(){
    return name;
}


@NotNull
@Min(1)
@Column(nullable = false, updatable = false)
public Integer getQuantity(){
    return quantity;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


public void setSn(String sn){
    this.sn = sn;
}


@NotEmpty
@Column(nullable = false, updatable = false)
public String getSn(){
    return sn;
}


public void setShipping(Shipping shipping){
    this.shipping = shipping;
}


}