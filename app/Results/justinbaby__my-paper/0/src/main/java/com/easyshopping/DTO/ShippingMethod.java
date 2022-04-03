package com.easyshopping.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
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


@ManyToMany(mappedBy = "shippingMethods", fetch = FetchType.LAZY)
public Set<PaymentMethod> getPaymentMethods(){
    return paymentMethods;
}


@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getFirstPrice(){
    return firstPrice;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/calculateFreight"))

.queryParam("weight",weight)
;
BigDecimal aux = restTemplate.getForObject(builder.toUriString(),BigDecimal.class);
return aux;
}


}