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
@Table(name = "xx_returns_item")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_returns_item_sequence")
public class ReturnsItem extends BaseEntity{

 private  long serialVersionUID;

 private  String sn;

 private  String name;

 private  Integer quantity;

 private  Returns returns;


public void setName(String name){
    this.name = name;
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


public void setReturns(Returns returns){
    this.returns = returns;
}


public void setSn(String sn){
    this.sn = sn;
}


@NotEmpty
@Column(nullable = false, updatable = false)
public String getSn(){
    return sn;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Returns getReturns(){
    return returns;
}


}