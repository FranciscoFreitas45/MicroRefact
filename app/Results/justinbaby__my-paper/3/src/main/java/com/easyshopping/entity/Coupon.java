package com.easyshopping.entity;
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
@Entity
@Table(name = "xx_coupon")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_coupon_sequence")
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


public void setName(String name){
    this.name = name;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


public void setPoint(Long point){
    this.point = point;
}


@Min(0)
public Integer getMinimumQuantity(){
    return minimumQuantity;
}


public void setMaximumQuantity(Integer maximumQuantity){
    this.maximumQuantity = maximumQuantity;
}


public Date getEndDate(){
    return endDate;
}


public void setPriceExpression(String priceExpression){
    this.priceExpression = priceExpression;
}


public void setOrders(List<Order> orders){
    this.orders = orders;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getMaximumPrice(){
    return maximumPrice;
}


@Transient
public BigDecimal calculatePrice(Integer quantity,BigDecimal price){
    if (price == null || StringUtils.isEmpty(getPriceExpression())) {
        return price;
    }
    BigDecimal result = new BigDecimal(0);
    try {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("quantity", quantity);
        model.put("price", price);
        result = new BigDecimal(FreemarkerUtils.process("#{(" + getPriceExpression() + ");M50}", model));
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    }
    if (result.compareTo(price) > 0) {
        return price;
    }
    return result.compareTo(new BigDecimal(0)) > 0 ? result : new BigDecimal(0);
}


public void setPromotions(Set<Promotion> promotions){
    this.promotions = promotions;
}


public String getPriceExpression(){
    return priceExpression;
}


public void setIsExchange(Boolean isExchange){
    this.isExchange = isExchange;
}


public void setMinimumPrice(BigDecimal minimumPrice){
    this.minimumPrice = minimumPrice;
}


@Min(0)
public Long getPoint(){
    return point;
}


public void setCouponCodes(Set<CouponCode> couponCodes){
    this.couponCodes = couponCodes;
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


public void setIntroduction(String introduction){
    this.introduction = introduction;
}


@ManyToMany(mappedBy = "coupons", fetch = FetchType.LAZY)
public Set<Promotion> getPromotions(){
    return promotions;
}


@Lob
public String getIntroduction(){
    return introduction;
}


@Transient
public boolean hasBegun(){
    return getBeginDate() == null || new Date().after(getBeginDate());
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getMinimumPrice(){
    return minimumPrice;
}


public void setMinimumQuantity(Integer minimumQuantity){
    this.minimumQuantity = minimumQuantity;
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


public void setMaximumPrice(BigDecimal maximumPrice){
    this.maximumPrice = maximumPrice;
}


@Transient
public boolean hasExpired(){
    return getEndDate() != null && new Date().after(getEndDate());
}


public Date getBeginDate(){
    return beginDate;
}


public void setBeginDate(Date beginDate){
    this.beginDate = beginDate;
}


@NotNull
@Column(nullable = false)
public Boolean getIsExchange(){
    return isExchange;
}


public void setPrefix(String prefix){
    this.prefix = prefix;
}


public void setIsEnabled(Boolean isEnabled){
    this.isEnabled = isEnabled;
}


@OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<CouponCode> getCouponCodes(){
    return couponCodes;
}


@PreRemove
public void preRemove(){
    Set<Promotion> promotions = getPromotions();
    if (promotions != null) {
        for (Promotion promotion : promotions) {
            promotion.getCoupons().remove(this);
        }
    }
    List<Order> orders = getOrders();
    if (orders != null) {
        for (Order order : orders) {
            order.getCoupons().remove(this);
        }
    }
}


}