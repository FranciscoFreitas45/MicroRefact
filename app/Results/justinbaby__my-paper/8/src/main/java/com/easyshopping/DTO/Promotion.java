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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.easyshopping.util.FreemarkerUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import freemarker.template.TemplateException;
public class Promotion extends OrderEntity{

 private  long serialVersionUID;

 private  String PATH_PREFIX;

 private  String PATH_SUFFIX;

 private  String name;

 private  String title;

 private  Date beginDate;

 private  Date endDate;

 private  Integer minimumQuantity;

 private  Integer maximumQuantity;

 private  BigDecimal minimumPrice;

 private  BigDecimal maximumPrice;

 private  String priceExpression;

 private  String pointExpression;

 private  Boolean isFreeShipping;

 private  Boolean isCouponAllowed;

 private  String introduction;

 private  Set<MemberRank> memberRanks;

 private  Set<ProductCategory> productCategories;

 private  Set<Product> products;

 private  Set<Brand> brands;

 private  Set<Coupon> coupons;

 private  List<GiftItem> giftItems;


public void setName(String name){
    this.name = name;
}


@Valid
@OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
public List<GiftItem> getGiftItems(){
    return giftItems;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_promotion_member_rank")
public Set<MemberRank> getMemberRanks(){
    return memberRanks;
}


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_promotion_brand")
public Set<Brand> getBrands(){
    return brands;
}


@JsonProperty
@Min(0)
public Integer getMinimumQuantity(){
    return minimumQuantity;
}


public void setMaximumQuantity(Integer maximumQuantity){
    this.maximumQuantity = maximumQuantity;
}


@JsonProperty
public Date getEndDate(){
    return endDate;
}


public void setPriceExpression(String priceExpression){
    this.priceExpression = priceExpression;
}


public void setProductCategories(Set<ProductCategory> productCategories){
    this.productCategories = productCategories;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


@JsonProperty
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getMaximumPrice(){
    return maximumPrice;
}


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getTitle(){
    return title;
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


public String getPriceExpression(){
    return priceExpression;
}


public void setProducts(Set<Product> products){
    this.products = products;
}


@Transient
public String getPath(){
    if (getId() != null) {
        return PATH_PREFIX + "/" + getId() + PATH_SUFFIX;
    }
    return null;
}


public String getPointExpression(){
    return pointExpression;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_promotion_product")
public Set<Product> getProducts(){
    return products;
}


@Transient
public Long calculatePoint(Integer quantity,Long point){
    if (point == null || StringUtils.isEmpty(getPointExpression())) {
        return point;
    }
    Long result = 0L;
    try {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("quantity", quantity);
        model.put("point", point);
        result = Double.valueOf(FreemarkerUtils.process("#{(" + getPointExpression() + ");M50}", model)).longValue();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
    if (result < point) {
        return point;
    }
    return result > 0L ? result : 0L;
}


public void setIsFreeShipping(Boolean isFreeShipping){
    this.isFreeShipping = isFreeShipping;
}


public void setCoupons(Set<Coupon> coupons){
    this.coupons = coupons;
}


@NotNull
@Column(nullable = false)
public Boolean getIsFreeShipping(){
    return isFreeShipping;
}


public void setMinimumPrice(BigDecimal minimumPrice){
    this.minimumPrice = minimumPrice;
}


@JsonProperty
@Min(0)
public Integer getMaximumQuantity(){
    return maximumQuantity;
}


public void setIntroduction(String introduction){
    this.introduction = introduction;
}


public void setGiftItems(List<GiftItem> giftItems){
    this.giftItems = giftItems;
}


public void setPointExpression(String pointExpression){
    this.pointExpression = pointExpression;
}


@Lob
public String getIntroduction(){
    return introduction;
}


@Transient
public boolean hasBegun(){
    return getBeginDate() == null || new Date().after(getBeginDate());
}


@JsonProperty
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getMinimumPrice(){
    return minimumPrice;
}


public void setTitle(String title){
    this.title = title;
}


public void setMinimumQuantity(Integer minimumQuantity){
    this.minimumQuantity = minimumQuantity;
}


@JsonProperty
@NotNull
@Column(nullable = false)
public Boolean getIsCouponAllowed(){
    return isCouponAllowed;
}


public void setMaximumPrice(BigDecimal maximumPrice){
    this.maximumPrice = maximumPrice;
}


public void setMemberRanks(Set<MemberRank> memberRanks){
    this.memberRanks = memberRanks;
}


@JsonProperty
public Date getBeginDate(){
    return beginDate;
}


public void setBeginDate(Date beginDate){
    this.beginDate = beginDate;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_promotion_coupon")
public Set<Coupon> getCoupons(){
    return coupons;
}


public void setIsCouponAllowed(Boolean isCouponAllowed){
    this.isCouponAllowed = isCouponAllowed;
}


@Transient
public boolean hasEnded(){
    return getEndDate() != null && new Date().after(getEndDate());
}


public void setBrands(Set<Brand> brands){
    this.brands = brands;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_promotion_product_category")
public Set<ProductCategory> getProductCategories(){
    return productCategories;
}


}