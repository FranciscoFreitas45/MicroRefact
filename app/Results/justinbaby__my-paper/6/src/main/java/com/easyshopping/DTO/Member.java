package com.easyshopping.DTO;
 import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.easyshopping.entity.MemberAttribute.Type;
import com.easyshopping.interceptor.MemberInterceptor;
import com.easyshopping.util.JsonUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Member extends BaseEntity{

 private  long serialVersionUID;

 public  String PRINCIPAL_ATTRIBUTE_NAME;

 public  String USERNAME_COOKIE_NAME;

 public  int ATTRIBUTE_VALUE_PROPERTY_COUNT;

 public  String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX;

 public  Integer MAX_FAVORITE_COUNT;

 private  String username;

 private  String password;

 private  String email;

 private  Long point;

 private  BigDecimal amount;

 private  BigDecimal balance;

 private  Boolean isEnabled;

 private  Boolean isLocked;

 private  Integer loginFailureCount;

 private  Date lockedDate;

 private  String registerIp;

 private  String loginIp;

 private  Date loginDate;

 private  String name;

 private  Gender gender;

 private  Date birth;

 private  String address;

 private  String zipCode;

 private  String phone;

 private  String mobile;

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

 private  SafeKey safeKey;

 private  Area area;

 private  MemberRank memberRank;

 private  Cart cart;

 private  Set<Order> orders;

 private  Set<Deposit> deposits;

 private  Set<Payment> payments;

 private  Set<CouponCode> couponCodes;

 private  Set<Receiver> receivers;

 private  Set<Review> reviews;

 private  Set<Consultation> consultations;

 private  Set<Product> favoriteProducts;

 private  Set<ProductNotify> productNotifies;

 private  Set<Message> inMessages;

 private  Set<Message> outMessages;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Length(max = 200)
public String getName(){
    return name;
}


@Transient
public Object getAttributeValue(MemberAttribute memberAttribute){
    if (memberAttribute != null) {
        if (memberAttribute.getType() == Type.name) {
            return getName();
        } else if (memberAttribute.getType() == Type.gender) {
            return getGender();
        } else if (memberAttribute.getType() == Type.birth) {
            return getBirth();
        } else if (memberAttribute.getType() == Type.area) {
            return getArea();
        } else if (memberAttribute.getType() == Type.address) {
            return getAddress();
        } else if (memberAttribute.getType() == Type.zipCode) {
            return getZipCode();
        } else if (memberAttribute.getType() == Type.phone) {
            return getPhone();
        } else if (memberAttribute.getType() == Type.mobile) {
            return getMobile();
        } else if (memberAttribute.getType() == Type.checkbox) {
            if (memberAttribute.getPropertyIndex() != null) {
                try {
                    String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                    String propertyValue = (String) PropertyUtils.getProperty(this, propertyName);
                    if (propertyValue != null) {
                        return JsonUtils.toObject(propertyValue, List.class);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (memberAttribute.getPropertyIndex() != null) {
                try {
                    String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                    return (String) PropertyUtils.getProperty(this, propertyName);
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
    return null;
}


@Length(max = 200)
public String getZipCode(){
    return zipCode;
}


public String getLoginIp(){
    return loginIp;
}


@OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Message> getInMessages(){
    return inMessages;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public MemberRank getMemberRank(){
    return memberRank;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Deposit> getDeposits(){
    return deposits;
}


@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getAmount(){
    return amount;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<ProductNotify> getProductNotifies(){
    return productNotifies;
}


@NotNull(groups = Save.class)
@Min(0)
@Column(nullable = false)
public Long getPoint(){
    return point;
}


public Gender getGender(){
    return gender;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Order> getOrders(){
    return orders;
}


@Length(max = 200)
public String getAttributeValue3(){
    return attributeValue3;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("isDefault desc, createDate desc")
public Set<Receiver> getReceivers(){
    return receivers;
}


@Length(max = 200)
public String getAttributeValue4(){
    return attributeValue4;
}


@Length(max = 200)
public String getAttributeValue5(){
    return attributeValue5;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBalance(){
    return balance;
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
public String getAttributeValue8(){
    return attributeValue8;
}


@Length(max = 200)
public String getAttributeValue9(){
    return attributeValue9;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate desc")
public Set<Review> getReviews(){
    return reviews;
}


@NotEmpty
@Email
@Length(max = 200)
@Column(nullable = false)
public String getEmail(){
    return email;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<CouponCode> getCouponCodes(){
    return couponCodes;
}


public Date getBirth(){
    return birth;
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


@Length(max = 200)
public String getPhone(){
    return phone;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_member_favorite_product")
@OrderBy("createDate desc")
public Set<Product> getFavoriteProducts(){
    return favoriteProducts;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getUsername(){
    return username;
}


@Embedded
public SafeKey getSafeKey(){
    return safeKey;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate desc")
public Set<Consultation> getConsultations(){
    return consultations;
}


@Column(nullable = false)
public Boolean getIsLocked(){
    return isLocked;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Message> getOutMessages(){
    return outMessages;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Payment> getPayments(){
    return payments;
}


@Column(nullable = false)
public Integer getLoginFailureCount(){
    return loginFailureCount;
}


public Date getLoginDate(){
    return loginDate;
}


@Column(nullable = false, updatable = false)
public String getRegisterIp(){
    return registerIp;
}


@OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Cart getCart(){
    return cart;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[^\\s&\"<>]+$")
@Column(nullable = false)
public String getPassword(){
    return password;
}


@Length(max = 200)
public String getMobile(){
    return mobile;
}


public Date getLockedDate(){
    return lockedDate;
}


@ManyToOne(fetch = FetchType.LAZY)
public Area getArea(){
    return area;
}


public void setCart(Cart cart){
    this.cart = cart;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCart"))

.queryParam("cart",cart)
;
restTemplate.put(builder.toUriString(),null);
}


}