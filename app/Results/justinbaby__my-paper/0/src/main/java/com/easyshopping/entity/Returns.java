package com.easyshopping.entity;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_returns")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_returns_sequence")
public class Returns extends BaseEntity{

 private  long serialVersionUID;

 private  String sn;

 private  String shippingMethod;

 private  String deliveryCorp;

 private  String trackingNo;

 private  BigDecimal freight;

 private  String shipper;

 private  String area;

 private  String address;

 private  String zipCode;

 private  String phone;

 private  String operator;

 private  String memo;

 private  Order order;

 private  List<ReturnsItem> returnsItems;


public void setShipper(String shipper){
    this.shipper = shipper;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false, updatable = false)
public String getPhone(){
    return phone;
}


public void setFreight(BigDecimal freight){
    this.freight = freight;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


@Transient
public int getQuantity(){
    int quantity = 0;
    if (getReturnsItems() != null) {
        for (ReturnsItem returnsItem : getReturnsItems()) {
            if (returnsItem != null && returnsItem.getQuantity() != null) {
                quantity += returnsItem.getQuantity();
            }
        }
    }
    return quantity;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false, updatable = false)
public String getZipCode(){
    return zipCode;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false, updatable = false)
public String getShipper(){
    return shipper;
}


public void setArea(String area){
    this.area = area;
}


public void setTrackingNo(String trackingNo){
    this.trackingNo = trackingNo;
}


@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getSn(){
    return sn;
}


public void setSn(String sn){
    this.sn = sn;
}


public void setDeliveryCorp(String deliveryCorp){
    this.deliveryCorp = deliveryCorp;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false, updatable = false)
public String getAddress(){
    return address;
}


public void setOperator(String operator){
    this.operator = operator;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(updatable = false, precision = 21, scale = 6)
public BigDecimal getFreight(){
    return freight;
}


@Column(nullable = false, updatable = false)
public String getOperator(){
    return operator;
}


@Length(max = 200)
@Column(updatable = false)
public String getTrackingNo(){
    return trackingNo;
}


@Column(updatable = false)
public String getDeliveryCorp(){
    return deliveryCorp;
}


public void setAddress(String address){
    this.address = address;
}


public void setShippingMethod(String shippingMethod){
    this.shippingMethod = shippingMethod;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setOrder(Order order){
    this.order = order;
}


@Valid
@NotEmpty
@OneToMany(mappedBy = "returns", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
public List<ReturnsItem> getReturnsItems(){
    return returnsItems;
}


@Length(max = 200)
@Column(updatable = false)
public String getMemo(){
    return memo;
}


public void setReturnsItems(List<ReturnsItem> returnsItems){
    this.returnsItems = returnsItems;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "orders", nullable = false, updatable = false)
public Order getOrder(){
    return order;
}


@Column(updatable = false)
public String getShippingMethod(){
    return shippingMethod;
}


@NotEmpty
@Column(nullable = false, updatable = false)
public String getArea(){
    return area;
}


}