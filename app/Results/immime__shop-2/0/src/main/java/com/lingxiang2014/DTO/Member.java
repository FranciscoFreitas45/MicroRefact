package com.lingxiang2014.DTO;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.lingxiang2014.entity.MemberAttribute.Type;
import com.lingxiang2014.interceptor.MemberInterceptor;
import com.lingxiang2014.util.JsonUtils;
public class Member extends BaseEntity{

 private  long serialVersionUID;

 public  String[] NOT_COPY_PROPERTIES;

 public  String PRINCIPAL_ATTRIBUTE_NAME;

 public  String USERNAME_COOKIE_NAME;

 public  int ATTRIBUTE_VALUE_PROPERTY_COUNT;

 public  String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX;

 public  Integer MAX_FAVORITE_COUNT;

 private  String number;

 private  String username;

 private  String password;

 private  String email;

 private  Long point;

 private  BigDecimal amount;

 private  BigDecimal balance;

 private  BigDecimal balance1;

 private  BigDecimal balance2;

 private  BigDecimal balance3;

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

 private  Set<Deposit> deposits;

 private  Set<Message> inMessages;

 private  Set<Message> outMessages;

 private  Member leftChildren;

 private  Member midChildren;

 private  Member rightChildren;

 private  Member parent;

 private  String qq;

 private  String password2;

 private  String idCard;

 private  Set<Bank> banks;

 private  Member top;

 private  Set<Member> children;

 private  Integer myPeople;

 private  Boolean isLeaf;

 private  BigDecimal staticDevidedBonuds;

 private  StaticBonudsRank staticBonudsRank;

 private  Boolean isStaticDevideBonuds;

 private  Boolean isSignInBonuds;

 private  Boolean isStaticBonuds;

 private  Boolean isSalesBonuds;

 private  Boolean isDuiPengBonuds;

 private  Boolean isLeaderBonuds;

 private  Boolean isHuzhuBonuds;

 private  Boolean isJianDianBonuds;

 private  Boolean isServiceBonuds;

 private  Set<BSystemAccount> bSystemAccounts;

 private  BigDecimal leftResults;

 private  BigDecimal rightResults;

 private  BigDecimal leftRemainResults;

 private  BigDecimal rightRemainResults;

 private  Integer leftMember;

 private  Integer rightMember;

 private  BigDecimal todayResults;

 private  BigDecimal todayDuiPengBonuds;

 private  Set<PasswordProtection> passwordProtections;

 private  Boolean isOpenPasswordProtections;

 private  Date activeDate;

 private  Integer leve;

 private  Boolean isComplate;

 private  BigDecimal allBonuds;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Column(nullable = false)
public Integer getMyPeople(){
    return myPeople;
}


@Column(nullable = false)
public Boolean getIsOpenPasswordProtections(){
    return isOpenPasswordProtections;
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


@Column(nullable = false)
public Boolean getIsServiceBonuds(){
    return isServiceBonuds;
}


public String getLoginIp(){
    return loginIp;
}


public Integer getLeftMember(){
    return leftMember;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Deposit> getDeposits(){
    return deposits;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<PasswordProtection> getPasswordProtections(){
    return passwordProtections;
}


@NotNull(groups = Save.class)
@Min(0)
@Column(nullable = false)
public Long getPoint(){
    return point;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getStaticDevidedBonuds(){
    return staticDevidedBonuds;
}


@Column(nullable = false)
public Boolean getIsLeaf(){
    return isLeaf;
}


public String getIdCard(){
    return idCard;
}


public Gender getGender(){
    return gender;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getRightRemainResults(){
    return rightRemainResults;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Bank> getBanks(){
    return banks;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBalance(){
    return balance;
}


@Column(nullable = false)
public Boolean getIsLeaderBonuds(){
    return isLeaderBonuds;
}


public Date getBirth(){
    return birth;
}


@Length(max = 200)
public String getPhone(){
    return phone;
}


@Column(nullable = false)
public Boolean getIsComplate(){
    return isComplate;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getUsername(){
    return username;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getNumber(){
    return number;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getRightResults(){
    return rightResults;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBalance3(){
    return balance3;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBalance2(){
    return balance2;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBalance1(){
    return balance1;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[^\\s&\"<>]+$")
@Column(nullable = false)
public String getPassword2(){
    return password2;
}


@Column(nullable = false)
public Boolean getIsDuiPengBonuds(){
    return isDuiPengBonuds;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getParent(){
    return parent;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLeftRemainResults(){
    return leftRemainResults;
}


@Column(nullable = false)
public Boolean getIsStaticDevideBonuds(){
    return isStaticDevideBonuds;
}


@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Message> getOutMessages(){
    return outMessages;
}


@Column(nullable = false)
public Integer getLoginFailureCount(){
    return loginFailureCount;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[^\\s&\"<>]+$")
@Column(nullable = false)
public String getPassword(){
    return password;
}


@Column(nullable = false)
public Boolean getIsStaticBonuds(){
    return isStaticBonuds;
}


@ManyToOne(fetch = FetchType.LAZY)
public Area getArea(){
    return area;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLeftResults(){
    return leftResults;
}


@Length(max = 200)
public String getName(){
    return name;
}


@Length(max = 200)
public String getZipCode(){
    return zipCode;
}


@ManyToOne
public StaticBonudsRank getStaticBonudsRank(){
    return staticBonudsRank;
}


@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<BSystemAccount> getbSystemAccounts(){
    return bSystemAccounts;
}


@Column(nullable = false)
public Boolean getIsHuzhuBonuds(){
    return isHuzhuBonuds;
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


@ManyToOne(fetch = FetchType.LAZY)
public Member getTop(){
    return top;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayDuiPengBonuds(){
    return todayDuiPengBonuds;
}


@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getAmount(){
    return amount;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayResults(){
    return todayResults;
}


@Length(max = 200)
public String getAttributeValue3(){
    return attributeValue3;
}


@Length(max = 200)
public String getAttributeValue4(){
    return attributeValue4;
}


@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Member> getChildren(){
    return children;
}


@Length(max = 200)
public String getAttributeValue5(){
    return attributeValue5;
}


@OneToOne(fetch = FetchType.LAZY)
public Member getRightChildren(){
    return rightChildren;
}


@Length(max = 200)
public String getAttributeValue6(){
    return attributeValue6;
}


@OneToOne(fetch = FetchType.LAZY)
public Member getLeftChildren(){
    return leftChildren;
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


@NotEmpty
@Email
@Length(max = 200)
@Column(nullable = false)
public String getEmail(){
    return email;
}


@Column(nullable = false)
public Boolean getIsSalesBonuds(){
    return isSalesBonuds;
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


public BigDecimal getAllBonuds(){
    return allBonuds;
}


public Date getActiveDate(){
    return activeDate;
}


public String getQq(){
    return qq;
}


@Embedded
public SafeKey getSafeKey(){
    return safeKey;
}


@Column(nullable = false)
public Boolean getIsLocked(){
    return isLocked;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


public Integer getLeve(){
    return leve;
}


@Column(nullable = false)
public Boolean getIsSignInBonuds(){
    return isSignInBonuds;
}


@OneToOne(fetch = FetchType.LAZY)
public Member getMidChildren(){
    return midChildren;
}


public Integer getRightMember(){
    return rightMember;
}


public Date getLoginDate(){
    return loginDate;
}


@Column(nullable = false, updatable = false)
public String getRegisterIp(){
    return registerIp;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


@Column(nullable = false)
public Boolean getIsJianDianBonuds(){
    return isJianDianBonuds;
}


@Length(max = 200)
public String getMobile(){
    return mobile;
}


public Date getLockedDate(){
    return lockedDate;
}


public void setBalance2(BigDecimal balance2){
    this.balance2 = balance2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBalance2"))

.queryParam("balance2",balance2)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBalance1(BigDecimal balance1){
    this.balance1 = balance1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBalance1"))

.queryParam("balance1",balance1)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBalance"))

.queryParam("balance",balance)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBalance3(BigDecimal balance3){
    this.balance3 = balance3;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBalance3"))

.queryParam("balance3",balance3)
;
restTemplate.put(builder.toUriString(),null);
}


}