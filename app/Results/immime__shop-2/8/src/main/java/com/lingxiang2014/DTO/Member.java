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


public void setPassword(String password){
    this.password = password;
}


@Column(nullable = false)
public Integer getMyPeople(){
    return myPeople;
}


public void setIsLocked(Boolean isLocked){
    this.isLocked = isLocked;
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


public void setRightRemainResults(BigDecimal rightRemainResults){
    this.rightRemainResults = rightRemainResults;
}


@Column(nullable = false)
public Boolean getIsServiceBonuds(){
    return isServiceBonuds;
}


public void setLoginIp(String loginIp){
    this.loginIp = loginIp;
}


public String getLoginIp(){
    return loginIp;
}


public void setIsSalesBonuds(Boolean isSalesBonuds){
    this.isSalesBonuds = isSalesBonuds;
}


public void setLeftMember(Integer leftMember){
    this.leftMember = leftMember;
}


public void setGender(Gender gender){
    this.gender = gender;
}


public Integer getLeftMember(){
    return leftMember;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
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


public void init(){
    setAmount(new BigDecimal(0));
    setBalance(new BigDecimal(0));
    setBalance1(new BigDecimal(0));
    setBalance2(new BigDecimal(0));
    setBalance3(new BigDecimal(0));
    setChildren(new HashSet<Member>());
    setDeposits(null);
    setTodayDuiPengBonuds(new BigDecimal(0));
    setInMessages(null);
    setIsEnabled(true);
    setIsLocked(false);
    setIsLeaf(true);
    setIsStaticDevideBonuds(false);
    setIsOpenPasswordProtections(false);
    setIsDuiPengBonuds(false);
    setIsHuzhuBonuds(false);
    setIsJianDianBonuds(false);
    setIsLeaderBonuds(false);
    setIsSalesBonuds(false);
    setIsServiceBonuds(false);
    setIsSignInBonuds(false);
    setIsStaticBonuds(false);
    setIsComplate(false);
    setLoginFailureCount(0);
    setLockedDate(null);
    setLoginDate(new Date());
    setLeftChildren(null);
    setLoginIp(null);
    setLeftRemainResults(new BigDecimal(0));
    setLeftResults(new BigDecimal(0));
    setLeftMember(0);
    setLeve(null);
    setMidChildren(null);
    setMyPeople(0);
    setOutMessages(null);
    setPasswordProtections(new HashSet<PasswordProtection>());
    setRightChildren(null);
    setRightRemainResults(new BigDecimal(0));
    setRightResults(new BigDecimal(0));
    setRightMember(0);
    setSafeKey(null);
    setStaticDevidedBonuds(new BigDecimal(0));
    setTodayDuiPengBonuds(new BigDecimal(0));
    setTodayResults(new BigDecimal(0));
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


public void setLeve(Integer leve){
    this.leve = leve;
}


@Transient
public void setAttributeValue(MemberAttribute memberAttribute,Object attributeValue){
    if (memberAttribute != null) {
        if (attributeValue instanceof String && StringUtils.isEmpty((String) attributeValue)) {
            attributeValue = null;
        }
        if (memberAttribute.getType() == Type.name && (attributeValue instanceof String || attributeValue == null)) {
            setName((String) attributeValue);
        } else if (memberAttribute.getType() == Type.gender && (attributeValue instanceof Gender || attributeValue == null)) {
            setGender((Gender) attributeValue);
        } else if (memberAttribute.getType() == Type.birth && (attributeValue instanceof Date || attributeValue == null)) {
            setBirth((Date) attributeValue);
        } else if (memberAttribute.getType() == Type.area && (attributeValue instanceof Area || attributeValue == null)) {
            setArea((Area) attributeValue);
        } else if (memberAttribute.getType() == Type.address && (attributeValue instanceof String || attributeValue == null)) {
            setAddress((String) attributeValue);
        } else if (memberAttribute.getType() == Type.zipCode && (attributeValue instanceof String || attributeValue == null)) {
            setZipCode((String) attributeValue);
        } else if (memberAttribute.getType() == Type.phone && (attributeValue instanceof String || attributeValue == null)) {
            setPhone((String) attributeValue);
        } else if (memberAttribute.getType() == Type.mobile && (attributeValue instanceof String || attributeValue == null)) {
            setMobile((String) attributeValue);
        } else if (memberAttribute.getType() == Type.checkbox && (attributeValue instanceof List || attributeValue == null)) {
            if (memberAttribute.getPropertyIndex() != null) {
                if (attributeValue == null || (memberAttribute.getOptions() != null && memberAttribute.getOptions().containsAll((List<?>) attributeValue))) {
                    try {
                        String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                        PropertyUtils.setProperty(this, propertyName, JsonUtils.toJson(attributeValue));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (memberAttribute.getPropertyIndex() != null) {
                try {
                    String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                    PropertyUtils.setProperty(this, propertyName, attributeValue);
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
}


public Gender getGender(){
    return gender;
}


public void setIsLeaderBonuds(Boolean isLeaderBonuds){
    this.isLeaderBonuds = isLeaderBonuds;
}


public void setSafeKey(SafeKey safeKey){
    this.safeKey = safeKey;
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


public void setMobile(String mobile){
    this.mobile = mobile;
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


public void setIsEnabled(Boolean isEnabled){
    this.isEnabled = isEnabled;
}


public Date getBirth(){
    return birth;
}


public void setName(String name){
    this.name = name;
}


@Length(max = 200)
public String getPhone(){
    return phone;
}


public void setLeftRemainResults(BigDecimal leftRemainResults){
    this.leftRemainResults = leftRemainResults;
}


public void setArea(Area area){
    this.area = area;
}


@Column(nullable = false)
public Boolean getIsComplate(){
    return isComplate;
}


public void setLoginFailureCount(Integer loginFailureCount){
    this.loginFailureCount = loginFailureCount;
}


public void setLoginDate(Date loginDate){
    this.loginDate = loginDate;
}


public void setAttributeValue9(String attributeValue9){
    this.attributeValue9 = attributeValue9;
}


public void setAttributeValue8(String attributeValue8){
    this.attributeValue8 = attributeValue8;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getUsername(){
    return username;
}


public void setAttributeValue7(String attributeValue7){
    this.attributeValue7 = attributeValue7;
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


public void setMyPeople(Integer myPeople){
    this.myPeople = myPeople;
}


public void setIsDuiPengBonuds(Boolean isDuiPengBonuds){
    this.isDuiPengBonuds = isDuiPengBonuds;
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


public void setParent(Member parent){
    this.parent = parent;
}


public void setIsLeaf(Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setAmount(BigDecimal amount){
    this.amount = amount;
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


public void setTodayResults(BigDecimal todayResults){
    this.todayResults = todayResults;
}


public void setPasswordProtections(Set<PasswordProtection> passwordProtections){
    this.passwordProtections = passwordProtections;
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


@Transient
public void removeAttributeValue(){
    setName(null);
    setGender(null);
    setBirth(null);
    setArea(null);
    setAddress(null);
    setZipCode(null);
    setPhone(null);
    setMobile(null);
    for (int i = 0; i < ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
        String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + i;
        try {
            PropertyUtils.setProperty(this, propertyName, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}


public void setAttributeValue6(String attributeValue6){
    this.attributeValue6 = attributeValue6;
}


public void setAttributeValue5(String attributeValue5){
    this.attributeValue5 = attributeValue5;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[^\\s&\"<>]+$")
@Column(nullable = false)
public String getPassword(){
    return password;
}


public void setStaticDevidedBonuds(BigDecimal staticDevidedBonuds){
    this.staticDevidedBonuds = staticDevidedBonuds;
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


public void setIsStaticBonuds(Boolean isStaticBonuds){
    this.isStaticBonuds = isStaticBonuds;
}


@Override
public String toString(){
    return "Member [number=" + number + ", username=" + username + ", password=" + password + ", email=" + email + ", point=" + point + ", amount=" + amount + ", balance=" + balance + ", balance1=" + balance1 + ", balance2=" + balance2 + ", balance3=" + balance3 + ", isEnabled=" + isEnabled + ", isLocked=" + isLocked + ", loginFailureCount=" + loginFailureCount + ", lockedDate=" + lockedDate + ", registerIp=" + registerIp + ", loginIp=" + loginIp + ", loginDate=" + loginDate + ", name=" + name + ", gender=" + gender + ", birth=" + birth + ", address=" + address + ", zipCode=" + zipCode + ", phone=" + phone + ", mobile=" + mobile + ", attributeValue0=" + attributeValue0 + ", attributeValue1=" + attributeValue1 + ", attributeValue2=" + attributeValue2 + ", attributeValue3=" + attributeValue3 + ", attributeValue4=" + attributeValue4 + ", attributeValue5=" + attributeValue5 + ", attributeValue6=" + attributeValue6 + ", attributeValue7=" + attributeValue7 + ", attributeValue8=" + attributeValue8 + ", attributeValue9=" + attributeValue9 + ", safeKey=" + safeKey + ", qq=" + qq + ", password2=" + password2 + ", idCard=" + idCard + ", myPeople=" + myPeople + ", isLeaf=" + isLeaf + ", staticDevidedBonuds=" + staticDevidedBonuds + ", staticBonudsRank=" + staticBonudsRank + ", isStaticDevideBonuds=" + isStaticDevideBonuds + ", isSignInBonuds=" + isSignInBonuds + ", isStaticBonuds=" + isStaticBonuds + ", isSalesBonuds=" + isSalesBonuds + ", isDuiPengBonuds=" + isDuiPengBonuds + ", isLeaderBonuds=" + isLeaderBonuds + ", isHuzhuBonuds=" + isHuzhuBonuds + ", isJianDianBonuds=" + isJianDianBonuds + ", isServiceBonuds=" + isServiceBonuds + ", bSystemAccounts=" + bSystemAccounts + ", leftResults=" + leftResults + ", rightResults=" + rightResults + ", leftRemainResults=" + leftRemainResults + ", rightRemainResults=" + rightRemainResults + ", todayResults=" + todayResults + ", todayDuiPengBonuds=" + todayDuiPengBonuds + ", passwordProtections=" + passwordProtections + ", isOpenPasswordProtections=" + isOpenPasswordProtections + ", activeDate=" + activeDate + "]";
}


@Column(nullable = false)
public Boolean getIsStaticBonuds(){
    return isStaticBonuds;
}


@ManyToOne(fetch = FetchType.LAZY)
public Area getArea(){
    return area;
}


public void setBalance2(BigDecimal balance2){
    this.balance2 = balance2;
}


public void setBalance3(BigDecimal balance3){
    this.balance3 = balance3;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLeftResults(){
    return leftResults;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
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


public void setTodayDuiPengBonuds(BigDecimal todayDuiPengBonuds){
    this.todayDuiPengBonuds = todayDuiPengBonuds;
}


public void setLeftResults(BigDecimal leftResults){
    this.leftResults = leftResults;
}


public void setStaticBonudsRank(StaticBonudsRank staticBonudsRank){
    this.staticBonudsRank = staticBonudsRank;
}


public void setLockedDate(Date lockedDate){
    this.lockedDate = lockedDate;
}


public void setBalance1(BigDecimal balance1){
    this.balance1 = balance1;
}


@Column(nullable = false)
public Boolean getIsHuzhuBonuds(){
    return isHuzhuBonuds;
}


@OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<Message> getInMessages(){
    return inMessages;
}


public void setTop(Member top){
    this.top = top;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public MemberRank getMemberRank(){
    return memberRank;
}


public void setRightResults(BigDecimal rightResults){
    this.rightResults = rightResults;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getTop(){
    return top;
}


public void setMemberRank(MemberRank memberRank){
    this.memberRank = memberRank;
}


public void setAllBonuds(BigDecimal allBonuds){
    this.allBonuds = allBonuds;
}


@NotNull(groups = Save.class)
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayDuiPengBonuds(){
    return todayDuiPengBonuds;
}


public void setMidChildren(Member midChildren){
    this.midChildren = midChildren;
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


public void setIsStaticDevideBonuds(Boolean isStaticDevideBonuds){
    this.isStaticDevideBonuds = isStaticDevideBonuds;
}


public void setQq(String qq){
    this.qq = qq;
}


public void setInMessages(Set<Message> inMessages){
    this.inMessages = inMessages;
}


public void setIsServiceBonuds(Boolean isServiceBonuds){
    this.isServiceBonuds = isServiceBonuds;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setIsOpenPasswordProtections(Boolean isOpenPasswordProtections){
    this.isOpenPasswordProtections = isOpenPasswordProtections;
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


public void setEmail(String email){
    this.email = email;
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


public void setRightMember(Integer rightMember){
    this.rightMember = rightMember;
}


@Length(max = 200)
public String getAttributeValue0(){
    return attributeValue0;
}


@Length(max = 200)
public String getAttributeValue1(){
    return attributeValue1;
}


public void setBirth(Date birth){
    this.birth = birth;
}


@Length(max = 200)
public String getAttributeValue2(){
    return attributeValue2;
}


public void setOutMessages(Set<Message> outMessages){
    this.outMessages = outMessages;
}


public void setPoint(Long point){
    this.point = point;
}


public BigDecimal getAllBonuds(){
    return allBonuds;
}


public Date getActiveDate(){
    return activeDate;
}


public void setBanks(Set<Bank> banks){
    this.banks = banks;
}


public String getQq(){
    return qq;
}


@Embedded
public SafeKey getSafeKey(){
    return safeKey;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


@Column(nullable = false)
public Boolean getIsLocked(){
    return isLocked;
}


public void setLeftChildren(Member leftChildren){
    this.leftChildren = leftChildren;
}


public void setDeposits(Set<Deposit> deposits){
    this.deposits = deposits;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


public void setPassword2(String password2){
    this.password2 = password2;
}


public Integer getLeve(){
    return leve;
}


public void setRegisterIp(String registerIp){
    this.registerIp = registerIp;
}


@Column(nullable = false)
public Boolean getIsSignInBonuds(){
    return isSignInBonuds;
}


public void setIsSignInBonuds(Boolean isSignInBonuds){
    this.isSignInBonuds = isSignInBonuds;
}


public void setIsComplate(Boolean isComplate){
    this.isComplate = isComplate;
}


@OneToOne(fetch = FetchType.LAZY)
public Member getMidChildren(){
    return midChildren;
}


public void setUsername(String username){
    this.username = username;
}


public void setAddress(String address){
    this.address = address;
}


public void setIsJianDianBonuds(Boolean isJianDianBonuds){
    this.isJianDianBonuds = isJianDianBonuds;
}


public void setActiveDate(Date activeDate){
    this.activeDate = activeDate;
}


public Integer getRightMember(){
    return rightMember;
}


public Date getLoginDate(){
    return loginDate;
}


public void setbSystemAccounts(Set<BSystemAccount> bSystemAccounts){
    this.bSystemAccounts = bSystemAccounts;
}


@Column(nullable = false, updatable = false)
public String getRegisterIp(){
    return registerIp;
}


public void setNumber(String number){
    this.number = number;
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


public void setRightChildren(Member rightChildren){
    this.rightChildren = rightChildren;
}


public void setIsHuzhuBonuds(Boolean isHuzhuBonuds){
    this.isHuzhuBonuds = isHuzhuBonuds;
}


@Length(max = 200)
public String getMobile(){
    return mobile;
}


public Date getLockedDate(){
    return lockedDate;
}


public void setChildren(Set<Member> children){
    this.children = children;
}


}