package com.easyshopping.entity;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_delivery_corp")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_delivery_corp_sequence")
public class DeliveryCorp extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  String url;

 private  String code;

 private  Set<ShippingMethod> shippingMethods;


public void setName(String name){
    this.name = name;
}


@Length(max = 200)
public String getUrl(){
    return url;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@OneToMany(mappedBy = "defaultDeliveryCorp", fetch = FetchType.LAZY)
public Set<ShippingMethod> getShippingMethods(){
    return shippingMethods;
}


public void setShippingMethods(Set<ShippingMethod> shippingMethods){
    this.shippingMethods = shippingMethods;
}


public void setCode(String code){
    this.code = code;
}


@PreRemove
public void preRemove(){
    Set<ShippingMethod> shippingMethods = getShippingMethods();
    if (shippingMethods != null) {
        for (ShippingMethod shippingMethod : shippingMethods) {
            shippingMethod.setDefaultDeliveryCorp(null);
        }
    }
}


@Length(max = 200)
public String getCode(){
    return code;
}


public void setUrl(String url){
    this.url = url;
}


}