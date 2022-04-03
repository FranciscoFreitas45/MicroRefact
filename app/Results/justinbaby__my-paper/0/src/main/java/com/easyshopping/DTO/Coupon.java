package com.easyshopping.DTO;
 import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.easyshopping.util.FreemarkerUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import freemarker.template.TemplateException;
public class Coupon extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  String prefix;

 private  Date beginDate;

 private  Date endDate;

 private  Integer minimumQuantity;

 private  Integer maximumQuantity;

 private  BigDecimal minimumPrice;

 private  BigDecimal maximumPrice;

 private  String priceExpression;

 private  Boolean isEnabled;

 private  Boolean isExchange;

 private  Long point;

 private  String introduction;

 private  Set<CouponCode> couponCodes;

 private  Set<Promotion> promotions;

 private  List<Order> orders;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@Min(0)
public Integer getMinimumQuantity(){
    return minimumQuantity;
}


public Date getEndDate(){
    return endDate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getMaximumPrice(){
    return maximumPrice;
}


public String getPriceExpression(){
    return priceExpression;
}


@Min(0)
public Long getPoint(){
    return point;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getPrefix(){
    return prefix;
}


@Min(0)
public Integer getMaximumQuantity(){
    return maximumQuantity;
}


@ManyToMany(mappedBy = "coupons", fetch = FetchType.LAZY)
public Set<Promotion> getPromotions(){
    return promotions;
}


@Lob
public String getIntroduction(){
    return introduction;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getMinimumPrice(){
    return minimumPrice;
}


@ManyToMany(mappedBy = "coupons", fetch = FetchType.LAZY)
public List<Order> getOrders(){
    return orders;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


public Date getBeginDate(){
    return beginDate;
}


@NotNull
@Column(nullable = false)
public Boolean getIsExchange(){
    return isExchange;
}


@OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<CouponCode> getCouponCodes(){
    return couponCodes;
}


@Transient
public boolean hasBegun(){
    return getBeginDate() == null || new Date().after(getBeginDate());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasBegun"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


@Transient
public boolean hasExpired(){
    return getEndDate() != null && new Date().after(getEndDate());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasExpired"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}