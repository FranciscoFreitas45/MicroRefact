package com.easyshopping.DTO;
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
public class DeliveryCorp extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  String url;

 private  String code;

 private  Set<ShippingMethod> shippingMethods;


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


@Length(max = 200)
public String getCode(){
    return code;
}


}