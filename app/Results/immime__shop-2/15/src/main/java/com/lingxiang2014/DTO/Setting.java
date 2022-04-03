package com.lingxiang2014.DTO;
 import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.sun.istack.internal.NotNull;
public class Setting implements Serializable{

 private  long serialVersionUID;

 public  String CACHE_NAME;

 public  Integer CACHE_KEY;

 private  String SEPARATOR;

 private  String SEPARATOR1;

 private  String siteName;

 private  String siteUrl;

 private  String logo;

 private  String kefuQQ;

 private  String address;

 private  String phone;

 private  String zipCode;

 private  String email;

 private  String certtext;

 private  Boolean isSiteEnabled;

 private  String siteCloseMessage;

 private  Integer priceScale;

 private  RoundType priceRoundType;

 private  Boolean isRegisterEnabled;

 private  Boolean isDuplicateEmail;

 private  String disabledUsername;

 private  Integer usernameMinLength;

 private  Integer usernameMaxLength;

 private  Integer passwordMinLength;

 private  Integer passwordMaxLength;

 private  Long registerPoint;

 private  String registerAgreement;

 private  Boolean isEmailLogin;

 private  CaptchaType[] captchaTypes;

 private  AccountLockType[] accountLockTypes;

 private  Integer accountLockCount;

 private  Integer accountLockTime;

 private  Integer safeKeyExpiryTime;

 private  Integer uploadMaxSize;

 private  String uploadImageExtension;

 private  String uploadFlashExtension;

 private  String uploadMediaExtension;

 private  String uploadFileExtension;

 private  String imageUploadPath;

 private  String flashUploadPath;

 private  String mediaUploadPath;

 private  String fileUploadPath;

 private  String smtpFromMail;

 private  String smtpHost;

 private  Integer smtpPort;

 private  String smtpUsername;

 private  String smtpPassword;

 private  String currencySign;

 private  String currencyUnit;

 private  Double defaultPointScale;

 private  Boolean isDevelopmentEnabled;

 private  Boolean isInvoiceEnabled;

 private  Boolean isTaxPriceEnabled;

 private  Double taxRate;

 private  String cookiePath;

 private  String cookieDomain;

 private  Boolean isCnzzEnabled;

 private  String cnzzSiteId;

 private  String cnzzPassword;

 private  Boolean isSignIn;

 private  Boolean isWithraw;

 private  BigDecimal withrawMinMoney;

 private  Integer signInType;

 private  BigDecimal minSignInMoney;

 private  BigDecimal signInMoneyRate;

 private  BigDecimal maxSignInMoney;

 private  BigDecimal signInMoney;

 private  BigDecimal staticBonuds;

 private  BigDecimal salesBonuds;

 private  BigDecimal duiPengBonuds;

 private  BigDecimal leaderBonuds;

 private  BigDecimal huzhuBonuds;

 private  BigDecimal jianDianBonuds;

 private  BigDecimal managerBonuds;

 private  Integer managerBonudsLeve;

 private  BigDecimal todayStaticBonuds;

 private  BigDecimal todaySalesBonuds;

 private  BigDecimal todayDuiPengBonuds;

 private  BigDecimal todayLeaderBonuds;

 private  BigDecimal todayHuzhuBonuds;

 private  BigDecimal todayJianDianBonuds;

 private  BigDecimal todayManagerBonuds;

 private  BigDecimal boundsRate;

 private  BigDecimal aRate;

 private  BigDecimal bRate;

 private  BigDecimal cRate;

 private  BigDecimal bSystemLeave;

 private  Boolean isXianjin;

 private  Boolean isBaodan;

 private  Boolean isFutou;

 private  Boolean isLicai;

 private  Boolean isXjtbd;

 private  Boolean isXjtft;

 private  Boolean isXjtlc;

 private  Boolean isBdtft;

 private  Boolean isBdtlc;

 private  Boolean isBftli;

 private  Boolean isBdtxj;

 private  Boolean isFttxj;

 private  Boolean isLctxj;

 private  Boolean isFttbd;

 private  Boolean isLctbd;

 private  Boolean isLctft;

 private  BigDecimal xianjinRate;

 private  BigDecimal baodanRate;

 private  BigDecimal futouRate;

 private  BigDecimal licaiRate;

 private  BigDecimal xjTbdRate;

 private  BigDecimal xjTftRate;

 private  BigDecimal xjTlcRate;

 private  BigDecimal bdTftRate;

 private  BigDecimal bdTlcRate;

 private  BigDecimal bfTliRate;

 private  BigDecimal bdTxjRate;

 private  BigDecimal ftTxjRate;

 private  BigDecimal lcTxjRate;

 private  BigDecimal ftTbdRate;

 private  BigDecimal lcTbdRate;

 private  BigDecimal lcTftRate;

 private  String memberNumberPrefix;

 private  Integer memberNumberLength;

 private  String defaultPassword;

 private  String defaultPassword1;

 private  String referer;

 private  Boolean isOpenReferer;

 private  String ignoreUrl;

 private  String announce;

 private  BigDecimal zero;

 private  BigDecimal percent;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getDuiPengBonuds(){
    return duiPengBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getFtTxjRate(){
    return ftTxjRate;
}


@NotNull
public Boolean getIsOpenReferer(){
    return isOpenReferer;
}


public Boolean getIsBaodan(){
    return isBaodan;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayManagerBonuds(){
    return todayManagerBonuds;
}


@NotNull
@Min(1)
@Max(117)
public Integer getUsernameMinLength(){
    return usernameMinLength;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBdTlcRate(){
    return bdTlcRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayLeaderBonuds(){
    return todayLeaderBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getFutouRate(){
    return futouRate;
}


public Boolean getIsCnzzEnabled(){
    return isCnzzEnabled;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getSignInMoney(){
    return signInMoney;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getSalesBonuds(){
    return salesBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayHuzhuBonuds(){
    return todayHuzhuBonuds;
}


public String[] getUploadMediaExtensions(){
    return StringUtils.split(uploadMediaExtension, SEPARATOR);
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getHuzhuBonuds(){
    return huzhuBonuds;
}


@NotEmpty
@Length(max = 200)
public String getImageUploadPath(){
    return imageUploadPath;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getXianjinRate(){
    return xianjinRate;
}


@NotNull
@Min(0)
public Integer getUploadMaxSize(){
    return uploadMaxSize;
}


@NotNull
public Boolean getIsDuplicateEmail(){
    return isDuplicateEmail;
}


@Length(max = 200)
public String getUploadMediaExtension(){
    return uploadMediaExtension;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getXjTlcRate(){
    return xjTlcRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodaySalesBonuds(){
    return todaySalesBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLicaiRate(){
    return licaiRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getMaxSignInMoney(){
    return maxSignInMoney;
}


public Boolean getIsFutou(){
    return isFutou;
}


public Boolean getIsFttbd(){
    return isFttbd;
}


@Length(max = 200)
public String getIgnoreUrl(){
    return ignoreUrl;
}


public Boolean getIsBdtlc(){
    return isBdtlc;
}


@NotNull
@Min(0)
public Integer getSafeKeyExpiryTime(){
    return safeKeyExpiryTime;
}


@Length(max = 200)
public String getCookieDomain(){
    return cookieDomain;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getXjTbdRate(){
    return xjTbdRate;
}


@Length(max = 200)
public String getZipCode(){
    return zipCode;
}


public String[] getUploadFileExtensions(){
    return StringUtils.split(uploadFileExtension, SEPARATOR);
}


@NotEmpty
@Length(max = 200)
public String getSiteUrl(){
    return siteUrl;
}


@NotNull
public Boolean getIsRegisterEnabled(){
    return isRegisterEnabled;
}


public Integer getSignInType(){
    return signInType;
}


public String getReferer(){
    return referer;
}


@NotEmpty
@Length(max = 200)
public String getCookiePath(){
    return cookiePath;
}


@NotNull
@Min(1)
@Max(117)
public Integer getUsernameMaxLength(){
    return usernameMaxLength;
}


@NotNull
@Min(0)
@Digits(integer = 3, fraction = 3)
public Double getTaxRate(){
    return taxRate;
}


public String[] getUploadFlashExtensions(){
    return StringUtils.split(uploadFlashExtension, SEPARATOR);
}


@NotEmpty
@Email
@Length(max = 200)
public String getSmtpFromMail(){
    return smtpFromMail;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getaRate(){
    return aRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLcTftRate(){
    return lcTftRate;
}


public BigDecimal getZero(){
    return zero;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLcTxjRate(){
    return lcTxjRate;
}


public Boolean getIsLctxj(){
    return isLctxj;
}


@NotNull
@Min(0)
@Digits(integer = 3, fraction = 3)
public Double getDefaultPointScale(){
    return defaultPointScale;
}


@Length(max = 200)
public String getDisabledUsername(){
    return disabledUsername;
}


public Boolean getIsBftli(){
    return isBftli;
}


@NotEmpty
@Length(max = 200)
public String getFlashUploadPath(){
    return flashUploadPath;
}


@NotNull
public Boolean getIsDevelopmentEnabled(){
    return isDevelopmentEnabled;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getJianDianBonuds(){
    return jianDianBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getWithrawMinMoney(){
    return withrawMinMoney;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLeaderBonuds(){
    return leaderBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBfTliRate(){
    return bfTliRate;
}


@NotEmpty
@Length(max = 200)
public String getSmtpHost(){
    return smtpHost;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getXjTftRate(){
    return xjTftRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getManagerBonuds(){
    return managerBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayStaticBonuds(){
    return todayStaticBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getSignInMoneyRate(){
    return signInMoneyRate;
}


@NotNull
public Boolean getIsSiteEnabled(){
    return isSiteEnabled;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBdTxjRate(){
    return bdTxjRate;
}


public String[] getUploadImageExtensions(){
    return StringUtils.split(uploadImageExtension, SEPARATOR);
}


public Boolean getIsXjtlc(){
    return isXjtlc;
}


public Boolean getIsLctft(){
    return isLctft;
}


public String getAnnounce(){
    return announce;
}


public CaptchaType[] getCaptchaTypes(){
    return captchaTypes;
}


@NotNull
@Min(1)
@Max(117)
public Integer getPasswordMinLength(){
    return passwordMinLength;
}


public Map<String,String> getkefuQQs(){
    Map<String, String> map = new HashMap<String, String>();
    String[] qqWebNames = StringUtils.split(kefuQQ, SEPARATOR1);
    for (String qqWebName : qqWebNames) {
        String[] qqs = StringUtils.split(qqWebName, SEPARATOR);
        if (qqs != null && qqs.length == 2) {
            map.put(qqs[0], qqs[1]);
        }
    }
    return map;
}


public String getMemberNumberPrefix(){
    return memberNumberPrefix;
}


public Boolean getIsFttxj(){
    return isFttxj;
}


@Length(max = 200)
public String getKefuQQ(){
    return kefuQQ;
}


@NotNull
@Min(0)
public Integer getAccountLockTime(){
    return accountLockTime;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getMinSignInMoney(){
    return minSignInMoney;
}


@NotNull
public Boolean getIsInvoiceEnabled(){
    return isInvoiceEnabled;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getbRate(){
    return bRate;
}


@Length(max = 200)
public String getCerttext(){
    return certtext;
}


@NotNull
@Min(0)
public Integer getSmtpPort(){
    return smtpPort;
}


@NotNull
public Boolean getIsTaxPriceEnabled(){
    return isTaxPriceEnabled;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBoundsRate(){
    return boundsRate;
}


@NotNull
@Min(0)
@Max(3)
public Integer getPriceScale(){
    return priceScale;
}


@Length(max = 200)
public String getUploadFlashExtension(){
    return uploadFlashExtension;
}


public String getCnzzSiteId(){
    return cnzzSiteId;
}


public Boolean getIsLicai(){
    return isLicai;
}


@NotEmpty
@Length(max = 200)
public String getCurrencySign(){
    return currencySign;
}


@Length(max = 200)
public String getSmtpPassword(){
    return smtpPassword;
}


@NotNull
public Boolean getIsSignIn(){
    return isSignIn;
}


@Length(max = 200)
public String getPhone(){
    return phone;
}


@Length(max = 200)
public String getUploadImageExtension(){
    return uploadImageExtension;
}


@NotNull
@Min(0)
public Long getRegisterPoint(){
    return registerPoint;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLcTbdRate(){
    return lcTbdRate;
}


public Boolean getIsLctbd(){
    return isLctbd;
}


@NotEmpty
@Length(max = 200)
public String getDefaultPassword1(){
    return defaultPassword1;
}


public Boolean getIsXjtft(){
    return isXjtft;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBdTftRate(){
    return bdTftRate;
}


@NotEmpty
public String getSiteCloseMessage(){
    return siteCloseMessage;
}


public BigDecimal getPercent(){
    return percent;
}


@NotNull
@Min(1)
public Integer getMemberNumberLength(){
    return memberNumberLength;
}


public AccountLockType[] getAccountLockTypes(){
    return accountLockTypes;
}


@NotEmpty
@Length(max = 200)
public String getFileUploadPath(){
    return fileUploadPath;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getStaticBonuds(){
    return staticBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBaodanRate(){
    return baodanRate;
}


public String[] getIgnoreUrls(){
    return StringUtils.split(ignoreUrl, SEPARATOR);
}


@NotEmpty
@Length(max = 200)
public String getCurrencyUnit(){
    return currencyUnit;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getFtTbdRate(){
    return ftTbdRate;
}


@NotEmpty
public String getRegisterAgreement(){
    return registerAgreement;
}


@NotNull
public Boolean getIsEmailLogin(){
    return isEmailLogin;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayJianDianBonuds(){
    return todayJianDianBonuds;
}


@NotEmpty
@Length(max = 200)
public String getSmtpUsername(){
    return smtpUsername;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayDuiPengBonuds(){
    return todayDuiPengBonuds;
}


@NotEmpty
@Length(max = 200)
public String getLogo(){
    return logo;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getbSystemLeave(){
    return bSystemLeave;
}


@NotNull
@Min(1)
public Integer getAccountLockCount(){
    return accountLockCount;
}


public Boolean getIsXianjin(){
    return isXianjin;
}


public Integer getManagerBonudsLeve(){
    return managerBonudsLeve;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getcRate(){
    return cRate;
}


@Email
@Length(max = 200)
public String getEmail(){
    return email;
}


@NotEmpty
@Length(max = 200)
public String getMediaUploadPath(){
    return mediaUploadPath;
}


@NotEmpty
@Length(max = 200)
public String getDefaultPassword(){
    return defaultPassword;
}


public Boolean getIsXjtbd(){
    return isXjtbd;
}


public Boolean getIsWithraw(){
    return isWithraw;
}


@Length(max = 200)
public String getUploadFileExtension(){
    return uploadFileExtension;
}


public Boolean getIsBdtft(){
    return isBdtft;
}


@NotNull
@Min(1)
@Max(117)
public Integer getPasswordMaxLength(){
    return passwordMaxLength;
}


public Boolean getIsBdtxj(){
    return isBdtxj;
}


@NotNull
public RoundType getPriceRoundType(){
    return priceRoundType;
}


public String getCnzzPassword(){
    return cnzzPassword;
}


public String[] getDisabledUsernames(){
    return StringUtils.split(disabledUsername, SEPARATOR);
}


@NotEmpty
@Length(max = 200)
public String getSiteName(){
    return siteName;
}


public BigDecimal setScale(BigDecimal amount){
    if (amount == null) {
        return null;
    }
    int roundingMode;
    if (getPriceRoundType() == RoundType.roundUp) {
        roundingMode = BigDecimal.ROUND_UP;
    } else if (getPriceRoundType() == RoundType.roundDown) {
        roundingMode = BigDecimal.ROUND_DOWN;
    } else {
        roundingMode = BigDecimal.ROUND_HALF_UP;
    }
    return amount.setScale(getPriceScale(), roundingMode);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setScale"))

.queryParam("amount",amount)
;
BigDecimal aux = restTemplate.getForObject(builder.toUriString(),BigDecimal.class);
return aux;
}


}