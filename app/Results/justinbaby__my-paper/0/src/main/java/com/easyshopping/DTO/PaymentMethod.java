package com.easyshopping.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class PaymentMethod extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Method method;

 private  Integer timeout;

 private  String icon;

 private  String description;

 private  String content;

 private  Set<ShippingMethod> shippingMethods;

 private  Set<Order> orders;


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@Lob
public String getContent(){
    return content;
}


@NotNull
@Column(nullable = false)
public Method getMethod(){
    return method;
}


@Length(max = 200)
public String getDescription(){
    return description;
}


@OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY)
public Set<Order> getOrders(){
    return orders;
}


@Length(max = 200)
public String getIcon(){
    return icon;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_payment_shipping_method")
@OrderBy("order asc")
public Set<ShippingMethod> getShippingMethods(){
    return shippingMethods;
}


@Min(1)
public Integer getTimeout(){
    return timeout;
}


}