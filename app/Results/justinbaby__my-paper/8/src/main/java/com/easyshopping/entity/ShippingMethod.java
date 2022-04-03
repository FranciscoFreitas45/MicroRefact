package com.easyshopping.entity;
 import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.easyshopping.Setting;
import com.easyshopping.util.SettingUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_shipping_method")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_shipping_method_sequence")
public class ShippingMethod extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Integer firstWeight;

 private  Integer continueWeight;

 private  BigDecimal firstPrice;

 private  BigDecimal continuePrice;

 private  String icon;

 private  String description;

 private  DeliveryCorp defaultDeliveryCorp;

 private  Set<PaymentMethod> paymentMethods;

 private  Set<Order> orders;


public void setName(String name){
    this.name = name;
}


public void setFirstPrice(BigDecimal firstPrice){
    this.firstPrice = firstPrice;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@Transient
public BigDecimal calculateFreight(Integer weight){
    Setting setting = SettingUtils.get();
    BigDecimal freight = new BigDecimal(0);
    if (weight != null) {
        if (weight <= getFirstWeight() || getContinuePrice().compareTo(new BigDecimal(0)) == 0) {
            freight = getFirstPrice();
        } else {
            double contiuneWeightCount = Math.ceil((weight - getFirstWeight()) / (double) getContinueWeight());
            freight = getFirstPrice().add(getContinuePrice().multiply(new BigDecimal(contiuneWeightCount)));
        }
    }
    return setting.setScale(freight);
}


public void setFirstWeight(Integer firstWeight){
    this.firstWeight = firstWeight;
}


public void setPaymentMethods(Set<PaymentMethod> paymentMethods){
    this.paymentMethods = paymentMethods;
}


@ManyToOne(fetch = FetchType.LAZY)
public DeliveryCorp getDefaultDeliveryCorp(){
    return defaultDeliveryCorp;
}


@NotNull
@Min(1)
@Column(nullable = false)
public Integer getContinueWeight(){
    return continueWeight;
}


public void setDescription(String description){
    this.description = description;
}


@NotNull
@Min(0)
@Column(nullable = false)
public Integer getFirstWeight(){
    return firstWeight;
}


@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getContinuePrice(){
    return continuePrice;
}


public void setOrders(Set<Order> orders){
    this.orders = orders;
}


@Lob
public String getDescription(){
    return description;
}


@OneToMany(mappedBy = "shippingMethod", fetch = FetchType.LAZY)
public Set<Order> getOrders(){
    return orders;
}


@Length(max = 200)
public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


@ManyToMany(mappedBy = "shippingMethods", fetch = FetchType.LAZY)
public Set<PaymentMethod> getPaymentMethods(){
    return paymentMethods;
}


public void setContinueWeight(Integer continueWeight){
    this.continueWeight = continueWeight;
}


public void setContinuePrice(BigDecimal continuePrice){
    this.continuePrice = continuePrice;
}


public void setDefaultDeliveryCorp(DeliveryCorp defaultDeliveryCorp){
    this.defaultDeliveryCorp = defaultDeliveryCorp;
}


@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getFirstPrice(){
    return firstPrice;
}


@PreRemove
public void preRemove(){
    Set<PaymentMethod> paymentMethods = getPaymentMethods();
    if (paymentMethods != null) {
        for (PaymentMethod paymentMethod : paymentMethods) {
            paymentMethod.getShippingMethods().remove(this);
        }
    }
    Set<Order> orders = getOrders();
    if (orders != null) {
        for (Order order : orders) {
            order.setShippingMethod(null);
        }
    }
}


}