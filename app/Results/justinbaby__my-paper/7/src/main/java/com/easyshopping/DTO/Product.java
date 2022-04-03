package com.easyshopping.DTO;
 import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.io.SAXReader;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Similarity;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.io.ClassPathResource;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKSimilarity;
import com.easyshopping.BigDecimalNumericFieldBridge;
import com.easyshopping.CommonAttributes;
import com.easyshopping.util.FreemarkerUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import freemarker.template.TemplateException;
public class Product extends BaseEntity{

 private  long serialVersionUID;

 public  String HITS_CACHE_NAME;

 public  int HITS_CACHE_INTERVAL;

 public  int ATTRIBUTE_VALUE_PROPERTY_COUNT;

 public  String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX;

 public  String FULL_NAME_SPECIFICATION_PREFIX;

 public  String FULL_NAME_SPECIFICATION_SUFFIX;

 public  String FULL_NAME_SPECIFICATION_SEPARATOR;

 private  String staticPath;

 private  String sn;

 private  String name;

 private  String fullName;

 private  BigDecimal price;

 private  BigDecimal cost;

 private  BigDecimal marketPrice;

 private  String image;

 private  String unit;

 private  Integer weight;

 private  Integer stock;

 private  Integer allocatedStock;

 private  String stockMemo;

 private  Long point;

 private  Boolean isMarketable;

 private  Boolean isList;

 private  Boolean isTop;

 private  Boolean isGift;

 private  String introduction;

 private  String memo;

 private  String keyword;

 private  String seoTitle;

 private  String seoKeywords;

 private  String seoDescription;

 private  Float score;

 private  Long totalScore;

 private  Long scoreCount;

 private  Long hits;

 private  Long weekHits;

 private  Long monthHits;

 private  Long sales;

 private  Long weekSales;

 private  Long monthSales;

 private  Date weekHitsDate;

 private  Date monthHitsDate;

 private  Date weekSalesDate;

 private  Date monthSalesDate;

 private  String attributeValue0;

 private  String attributeValue1;

 private  String attributeValue2;

 private  String attributeValue3;

 private  String attributeValue4;

 private  String attributeValue5;

 private  String attributeValue6;

 private  String attributeValue7;

 private  String attributeValue8;

 private  String attributeValue9;

 private  String attributeValue10;

 private  String attributeValue11;

 private  String attributeValue12;

 private  String attributeValue13;

 private  String attributeValue14;

 private  String attributeValue15;

 private  String attributeValue16;

 private  String attributeValue17;

 private  String attributeValue18;

 private  String attributeValue19;

 private  ProductCategory productCategory;

 private  Goods goods;

 private  Brand brand;

 private  List<ProductImage> productImages;

 private  Set<Review> reviews;

 private  Set<Consultation> consultations;

 private  Set<Tag> tags;

 private  Set<Member> favoriteMembers;

 private  Set<Specification> specifications;

 private  Set<SpecificationValue> specificationValues;

 private  Set<Promotion> promotions;

 private  Set<CartItem> cartItems;

 private  Set<OrderItem> orderItems;

 private  Set<GiftItem> giftItems;

 private  Set<ProductNotify> productNotifies;

 private  Map<MemberRank,BigDecimal> memberPrice;

 private  Map<Parameter,String> parameterValue;


public void setCartItems(Set<CartItem> cartItems){
    this.cartItems = cartItems;
}


@Transient
public String getAttributeValue(Attribute attribute){
    if (attribute != null && attribute.getPropertyIndex() != null) {
        try {
            String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + attribute.getPropertyIndex();
            return (String) PropertyUtils.getProperty(this, propertyName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    return null;
}


public void setCost(BigDecimal cost){
    this.cost = cost;
}


public void setAllocatedStock(Integer allocatedStock){
    this.allocatedStock = allocatedStock;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_product_tag")
@OrderBy("order asc")
public Set<Tag> getTags(){
    return tags;
}


@Transient
public Set<Promotion> getValidPromotions(){
    Set<Promotion> allPromotions = new HashSet<Promotion>();
    if (getPromotions() != null) {
        allPromotions.addAll(getPromotions());
    }
    if (getProductCategory() != null && getProductCategory().getPromotions() != null) {
        allPromotions.addAll(getProductCategory().getPromotions());
    }
    if (getBrand() != null && getBrand().getPromotions() != null) {
        allPromotions.addAll(getBrand().getPromotions());
    }
    Set<Promotion> validPromotions = new TreeSet<Promotion>();
    for (Promotion promotion : allPromotions) {
        if (promotion != null && promotion.hasBegun() && !promotion.hasEnded() && promotion.getMemberRanks() != null && !promotion.getMemberRanks().isEmpty()) {
            validPromotions.add(promotion);
        }
    }
    return validPromotions;
}


public void setConsultations(Set<Consultation> consultations){
    this.consultations = consultations;
}


public void setTotalScore(Long totalScore){
    this.totalScore = totalScore;
}


@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "xx_product_member_price")
public Map<MemberRank,BigDecimal> getMemberPrice(){
    return memberPrice;
}


@JsonProperty
@Transient
public String getPath(){
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", getId());
    model.put("createDate", getCreateDate());
    model.put("modifyDate", getModifyDate());
    model.put("sn", getSn());
    model.put("name", getName());
    model.put("fullName", getFullName());
    model.put("seoTitle", getSeoTitle());
    model.put("seoKeywords", getSeoKeywords());
    model.put("seoDescription", getSeoDescription());
    model.put("productCategory", getProductCategory());
    try {
        return FreemarkerUtils.process(staticPath, model);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    }
    return null;
}


public void setIsList(Boolean isList){
    this.isList = isList;
}


@JsonProperty
@Field(store = Store.YES, index = Index.NO)
@Column(nullable = false)
public String getFullName(){
    return fullName;
}


@Field(store = Store.YES, index = Index.NO)
@Column(nullable = false)
public Long getMonthSales(){
    return monthSales;
}


public void setGoods(Goods goods){
    this.goods = goods;
}


@Column(nullable = false)
public Long getTotalScore(){
    return totalScore;
}


@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<ProductNotify> getProductNotifies(){
    return productNotifies;
}


@Field(store = Store.YES, index = Index.NO)
@Min(0)
@Column(nullable = false)
public Long getPoint(){
    return point;
}


@Column(nullable = false)
public Date getMonthSalesDate(){
    return monthSalesDate;
}


@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<CartItem> getCartItems(){
    return cartItems;
}


@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
@Lob
public String getIntroduction(){
    return introduction;
}


@Field(store = Store.YES, index = Index.NO)
@Column(nullable = false)
public Integer getAllocatedStock(){
    return allocatedStock;
}


@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
public Set<OrderItem> getOrderItems(){
    return orderItems;
}


@Transient
public void setAttributeValue(Attribute attribute,String value){
    if (attribute != null && attribute.getPropertyIndex() != null) {
        if (StringUtils.isEmpty(value)) {
            value = null;
        }
        if (value == null || (attribute.getOptions() != null && attribute.getOptions().contains(value))) {
            try {
                String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + attribute.getPropertyIndex();
                PropertyUtils.setProperty(this, propertyName, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}


@Column(nullable = false)
public Date getWeekSalesDate(){
    return weekSalesDate;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NotNull
@Column(nullable = false)
public Boolean getIsMarketable(){
    return isMarketable;
}


@Field(store = Store.YES, index = Index.NO)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getMarketPrice(){
    return marketPrice;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_product_specification")
@OrderBy("order asc")
public Set<Specification> getSpecifications(){
    return specifications;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@Column(nullable = false)
public Long getHits(){
    return hits;
}


public void setMonthSalesDate(Date monthSalesDate){
    this.monthSalesDate = monthSalesDate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(precision = 21, scale = 6)
public BigDecimal getCost(){
    return cost;
}


@JsonProperty
@Field(store = Store.YES, index = Index.NO)
@Length(max = 200)
public String getImage(){
    return image;
}


@PreUpdate
public void preUpdate(){
    if (getStock() == null) {
        setAllocatedStock(0);
    }
    if (getTotalScore() != null && getScoreCount() != null && getScoreCount() != 0) {
        setScore((float) getTotalScore() / getScoreCount());
    } else {
        setScore(0F);
    }
}


public void setSpecifications(Set<Specification> specifications){
    this.specifications = specifications;
}


public void setImage(String image){
    this.image = image;
}


public void setName(String name){
    this.name = name;
}


@Transient
public Integer getAvailableStock(){
    Integer availableStock = null;
    if (getStock() != null && getAllocatedStock() != null) {
        availableStock = getStock() - getAllocatedStock();
        if (availableStock < 0) {
            availableStock = 0;
        }
    }
    return availableStock;
}


@Length(max = 200)
public String getStockMemo(){
    return stockMemo;
}


@Field(store = Store.YES, index = Index.NO)
@Min(0)
public Integer getStock(){
    return stock;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@Column(nullable = false)
public Long getSales(){
    return sales;
}


@Column(nullable = false)
public Date getMonthHitsDate(){
    return monthHitsDate;
}


public void setAttributeValue9(String attributeValue9){
    this.attributeValue9 = attributeValue9;
}


public void setAttributeValue8(String attributeValue8){
    this.attributeValue8 = attributeValue8;
}


public void setAttributeValue7(String attributeValue7){
    this.attributeValue7 = attributeValue7;
}


public void setOrderItems(Set<OrderItem> orderItems){
    this.orderItems = orderItems;
}


@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Consultation> getConsultations(){
    return consultations;
}


public void setUnit(String unit){
    this.unit = unit;
}


@Length(max = 200)
public String getSeoTitle(){
    return seoTitle;
}


public void setStock(Integer stock){
    this.stock = stock;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NotNull
@Column(nullable = false)
public Boolean getIsList(){
    return isList;
}


public void setIntroduction(String introduction){
    this.introduction = introduction;
}


public void setSales(Long sales){
    this.sales = sales;
}


public void setWeight(Integer weight){
    this.weight = weight;
}


@Transient
public boolean isValid(Promotion promotion){
    if (promotion == null || !promotion.hasBegun() || promotion.hasEnded() || promotion.getMemberRanks() == null || promotion.getMemberRanks().isEmpty()) {
        return false;
    }
    if (getValidPromotions().contains(promotion)) {
        return true;
    }
    return false;
}


public void setWeekHitsDate(Date weekHitsDate){
    this.weekHitsDate = weekHitsDate;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_product_specification_value")
@OrderBy("specification asc")
public Set<SpecificationValue> getSpecificationValues(){
    return specificationValues;
}


@JsonProperty
@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NumericField
@FieldBridge(impl = BigDecimalNumericFieldBridge.class)
@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getPrice(){
    return price;
}


public void setAttributeValue6(String attributeValue6){
    this.attributeValue6 = attributeValue6;
}


@Field(store = Store.YES, index = Index.NO)
@Column(nullable = false)
public Long getWeekHits(){
    return weekHits;
}


public void setAttributeValue5(String attributeValue5){
    this.attributeValue5 = attributeValue5;
}


public void setAttributeValue4(String attributeValue4){
    this.attributeValue4 = attributeValue4;
}


public void setAttributeValue3(String attributeValue3){
    this.attributeValue3 = attributeValue3;
}


public void setAttributeValue2(String attributeValue2){
    this.attributeValue2 = attributeValue2;
}


public void setAttributeValue1(String attributeValue1){
    this.attributeValue1 = attributeValue1;
}


public void setAttributeValue0(String attributeValue0){
    this.attributeValue0 = attributeValue0;
}


@JsonProperty
@Field(store = Store.YES, index = Index.NO)
@Length(max = 200)
public String getUnit(){
    return unit;
}


public void setStockMemo(String stockMemo){
    this.stockMemo = stockMemo;
}


@ManyToMany(mappedBy = "favoriteProducts", fetch = FetchType.LAZY)
public Set<Member> getFavoriteMembers(){
    return favoriteMembers;
}


@OneToMany(mappedBy = "gift", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
public Set<GiftItem> getGiftItems(){
    return giftItems;
}


public void setProductNotifies(Set<ProductNotify> productNotifies){
    this.productNotifies = productNotifies;
}


@JsonProperty
@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


public void setBrand(Brand brand){
    this.brand = brand;
}


@Valid
@ElementCollection
@CollectionTable(name = "xx_product_product_image")
public List<ProductImage> getProductImages(){
    return productImages;
}


@Field(store = Store.YES, index = Index.NO)
@Min(0)
public Integer getWeight(){
    return weight;
}


@PrePersist
public void prePersist(){
    if (getStock() == null) {
        setAllocatedStock(0);
    }
    setScore(0F);
}


public void setHits(Long hits){
    this.hits = hits;
}


@JsonProperty
@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@Pattern(regexp = "[\\s\\S]*")
@Length(max = 100)
@Column(nullable = false, unique = true, length = 100)
public String getSn(){
    return sn;
}


public void setPrice(BigDecimal price){
    this.price = price;
}


@JsonProperty
@Transient
public String getThumbnail(){
    if (getProductImages() != null && !getProductImages().isEmpty()) {
        return getProductImages().get(0).getThumbnail();
    }
    return null;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NotNull
@Column(nullable = false)
public Boolean getIsTop(){
    return isTop;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@Column(nullable = false)
public Long getScoreCount(){
    return scoreCount;
}


public void setIsGift(Boolean isGift){
    this.isGift = isGift;
}


public void setKeyword(String keyword){
    if (keyword != null) {
        keyword = keyword.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.keyword = keyword;
}


public void setPromotions(Set<Promotion> promotions){
    this.promotions = promotions;
}


public void setWeekSalesDate(Date weekSalesDate){
    this.weekSalesDate = weekSalesDate;
}


public void setReviews(Set<Review> reviews){
    this.reviews = reviews;
}


public void setAttributeValue12(String attributeValue12){
    this.attributeValue12 = attributeValue12;
}


public void setIsTop(Boolean isTop){
    this.isTop = isTop;
}


public void setAttributeValue13(String attributeValue13){
    this.attributeValue13 = attributeValue13;
}


public void setAttributeValue14(String attributeValue14){
    this.attributeValue14 = attributeValue14;
}


public void setAttributeValue15(String attributeValue15){
    this.attributeValue15 = attributeValue15;
}


public void setGiftItems(Set<GiftItem> giftItems){
    this.giftItems = giftItems;
}


@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "xx_product_parameter_value")
public Map<Parameter,String> getParameterValue(){
    return parameterValue;
}


public void setWeekSales(Long weekSales){
    this.weekSales = weekSales;
}


public void setAttributeValue16(String attributeValue16){
    this.attributeValue16 = attributeValue16;
}


public void setSpecificationValues(Set<SpecificationValue> specificationValues){
    this.specificationValues = specificationValues;
}


public void setWeekHits(Long weekHits){
    this.weekHits = weekHits;
}


public void setAttributeValue17(String attributeValue17){
    this.attributeValue17 = attributeValue17;
}


@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
public Set<Promotion> getPromotions(){
    return promotions;
}


public void setIsMarketable(Boolean isMarketable){
    this.isMarketable = isMarketable;
}


public void setAttributeValue18(String attributeValue18){
    this.attributeValue18 = attributeValue18;
}


public void setAttributeValue19(String attributeValue19){
    this.attributeValue19 = attributeValue19;
}


@Length(max = 200)
public String getAttributeValue19(){
    return attributeValue19;
}


@Length(max = 200)
public String getAttributeValue18(){
    return attributeValue18;
}


@Length(max = 200)
public String getAttributeValue17(){
    return attributeValue17;
}


public void setFavoriteMembers(Set<Member> favoriteMembers){
    this.favoriteMembers = favoriteMembers;
}


@Transient
public List<Product> getSiblings(){
    List<Product> siblings = new ArrayList<Product>();
    if (getGoods() != null && getGoods().getProducts() != null) {
        for (Product product : getGoods().getProducts()) {
            if (!this.equals(product)) {
                siblings.add(product);
            }
        }
    }
    return siblings;
}


@Length(max = 200)
public String getAttributeValue16(){
    return attributeValue16;
}


public void setAttributeValue10(String attributeValue10){
    this.attributeValue10 = attributeValue10;
}


public void setAttributeValue11(String attributeValue11){
    this.attributeValue11 = attributeValue11;
}


@Length(max = 200)
public String getSeoKeywords(){
    return seoKeywords;
}


@Length(max = 200)
public String getAttributeValue3(){
    return attributeValue3;
}


@Length(max = 200)
public String getAttributeValue11(){
    return attributeValue11;
}


@Length(max = 200)
public String getAttributeValue4(){
    return attributeValue4;
}


@Length(max = 200)
public String getAttributeValue10(){
    return attributeValue10;
}


public void setProductCategory(ProductCategory productCategory){
    this.productCategory = productCategory;
}


@Length(max = 200)
public String getAttributeValue5(){
    return attributeValue5;
}


@Length(max = 200)
public String getAttributeValue6(){
    return attributeValue6;
}


@Length(max = 200)
public String getAttributeValue7(){
    return attributeValue7;
}


@Length(max = 200)
public String getAttributeValue15(){
    return attributeValue15;
}


@Length(max = 200)
public String getAttributeValue8(){
    return attributeValue8;
}


@Length(max = 200)
public String getAttributeValue14(){
    return attributeValue14;
}


@Length(max = 200)
public String getAttributeValue9(){
    return attributeValue9;
}


@Length(max = 200)
public String getAttributeValue13(){
    return attributeValue13;
}


public void setSeoTitle(String seoTitle){
    this.seoTitle = seoTitle;
}


@Length(max = 200)
public String getAttributeValue12(){
    return attributeValue12;
}


@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Review> getReviews(){
    return reviews;
}


@PreRemove
public void preRemove(){
    Set<Member> favoriteMembers = getFavoriteMembers();
    if (favoriteMembers != null) {
        for (Member favoriteMember : favoriteMembers) {
            favoriteMember.getFavoriteProducts().remove(this);
        }
    }
    Set<Promotion> promotions = getPromotions();
    if (promotions != null) {
        for (Promotion promotion : promotions) {
            promotion.getProducts().remove(this);
        }
    }
    Set<OrderItem> orderItems = getOrderItems();
    if (orderItems != null) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setProduct(null);
        }
    }
}


@Length(max = 200)
public String getAttributeValue0(){
    return attributeValue0;
}


@Length(max = 200)
public String getAttributeValue1(){
    return attributeValue1;
}


@Length(max = 200)
public String getAttributeValue2(){
    return attributeValue2;
}


public void setMarketPrice(BigDecimal marketPrice){
    this.marketPrice = marketPrice;
}


public void setSeoKeywords(String seoKeywords){
    if (seoKeywords != null) {
        seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.seoKeywords = seoKeywords;
}


public void setPoint(Long point){
    this.point = point;
}


@Field(store = Store.YES, index = Index.NO)
@Column(nullable = false)
public Long getMonthHits(){
    return monthHits;
}


@Length(max = 200)
public String getSeoDescription(){
    return seoDescription;
}


@JsonProperty
@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NotNull
@Column(nullable = false)
public Boolean getIsGift(){
    return isGift;
}


public void setTags(Set<Tag> tags){
    this.tags = tags;
}


public void setMonthSales(Long monthSales){
    this.monthSales = monthSales;
}


public void setSn(String sn){
    this.sn = sn;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public void setMonthHits(Long monthHits){
    this.monthHits = monthHits;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Goods getGoods(){
    return goods;
}


public void setParameterValue(Map<Parameter,String> parameterValue){
    this.parameterValue = parameterValue;
}


public void setScoreCount(Long scoreCount){
    this.scoreCount = scoreCount;
}


public void setMonthHitsDate(Date monthHitsDate){
    this.monthHitsDate = monthHitsDate;
}


public void setSeoDescription(String seoDescription){
    this.seoDescription = seoDescription;
}


@ManyToOne(fetch = FetchType.LAZY)
public Brand getBrand(){
    return brand;
}


@Column(nullable = false)
public Date getWeekHitsDate(){
    return weekHitsDate;
}


public void setMemberPrice(Map<MemberRank,BigDecimal> memberPrice){
    this.memberPrice = memberPrice;
}


public void setProductImages(List<ProductImage> productImages){
    this.productImages = productImages;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
@Length(max = 200)
public String getKeyword(){
    return keyword;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public ProductCategory getProductCategory(){
    return productCategory;
}


@Transient
public Boolean getIsOutOfStock(){
    return getStock() != null && getAllocatedStock() != null && getAllocatedStock() >= getStock();
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NumericField
@Column(nullable = false, precision = 12, scale = 6)
public Float getScore(){
    return score;
}


@Field(store = Store.YES, index = Index.NO)
@Column(nullable = false)
public Long getWeekSales(){
    return weekSales;
}


public void setScore(Float score){
    this.score = score;
}


}