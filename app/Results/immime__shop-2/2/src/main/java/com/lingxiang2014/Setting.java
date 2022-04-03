package com.lingxiang2014;
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


public void setIsSiteEnabled(Boolean isSiteEnabled){
    this.isSiteEnabled = isSiteEnabled;
}


public void setUploadFileExtension(String uploadFileExtension){
    if (uploadFileExtension != null) {
        uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
    }
    this.uploadFileExtension = uploadFileExtension;
}


public void setWithrawMinMoney(BigDecimal withrawMinMoney){
    this.withrawMinMoney = withrawMinMoney;
}


public void setLeaderBonuds(BigDecimal leaderBonuds){
    this.leaderBonuds = leaderBonuds;
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


public void setManagerBonuds(BigDecimal managerBonuds){
    this.managerBonuds = managerBonuds;
}


@NotNull
@Min(1)
@Max(117)
public Integer getUsernameMinLength(){
    return usernameMinLength;
}


public void setUploadImageExtension(String uploadImageExtension){
    if (uploadImageExtension != null) {
        uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
    }
    this.uploadImageExtension = uploadImageExtension;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBdTlcRate(){
    return bdTlcRate;
}


public void setIsXjtlc(Boolean isXjtlc){
    this.isXjtlc = isXjtlc;
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


public void setMediaUploadPath(String mediaUploadPath){
    if (mediaUploadPath != null) {
        if (!mediaUploadPath.startsWith("/")) {
            mediaUploadPath = "/" + mediaUploadPath;
        }
        if (!mediaUploadPath.endsWith("/")) {
            mediaUploadPath += "/";
        }
    }
    this.mediaUploadPath = mediaUploadPath;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayHuzhuBonuds(){
    return todayHuzhuBonuds;
}


public void setIsInvoiceEnabled(Boolean isInvoiceEnabled){
    this.isInvoiceEnabled = isInvoiceEnabled;
}


public void setIsBdtlc(Boolean isBdtlc){
    this.isBdtlc = isBdtlc;
}


public String[] getUploadMediaExtensions(){
    return StringUtils.split(uploadMediaExtension, SEPARATOR);
}


public void setStaticBonuds(BigDecimal staticBonuds){
    this.staticBonuds = staticBonuds;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getHuzhuBonuds(){
    return huzhuBonuds;
}


public void setKefuQQ(String kefuQQ){
    this.kefuQQ = kefuQQ;
}


public void setFutouRate(BigDecimal futouRate){
    this.futouRate = futouRate;
}


public void setFtTxjRate(BigDecimal ftTxjRate){
    this.ftTxjRate = ftTxjRate;
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


public void setManagerBonudsLeve(Integer managerBonudsLeve){
    this.managerBonudsLeve = managerBonudsLeve;
}


public void setSiteName(String siteName){
    this.siteName = siteName;
}


public void setJianDianBonuds(BigDecimal jianDianBonuds){
    this.jianDianBonuds = jianDianBonuds;
}


public void setSmtpFromMail(String smtpFromMail){
    this.smtpFromMail = smtpFromMail;
}


public void setDefaultPassword1(String defaultPassword1){
    this.defaultPassword1 = defaultPassword1;
}


@NotNull
public Boolean getIsDuplicateEmail(){
    return isDuplicateEmail;
}


@Length(max = 200)
public String getUploadMediaExtension(){
    return uploadMediaExtension;
}


public void setRegisterAgreement(String registerAgreement){
    this.registerAgreement = registerAgreement;
}


public void setIsBaodan(Boolean isBaodan){
    this.isBaodan = isBaodan;
}


public void setcRate(BigDecimal cRate){
    this.cRate = cRate;
}


public void setSiteCloseMessage(String siteCloseMessage){
    this.siteCloseMessage = siteCloseMessage;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getXjTlcRate(){
    return xjTlcRate;
}


public void setDefaultPointScale(Double defaultPointScale){
    this.defaultPointScale = defaultPointScale;
}


public void setAccountLockTypes(AccountLockType[] accountLockTypes){
    this.accountLockTypes = accountLockTypes;
}


public void setIsCnzzEnabled(Boolean isCnzzEnabled){
    this.isCnzzEnabled = isCnzzEnabled;
}


public void setXjTlcRate(BigDecimal xjTlcRate){
    this.xjTlcRate = xjTlcRate;
}


public void setMaxSignInMoney(BigDecimal maxSignInMoney){
    this.maxSignInMoney = maxSignInMoney;
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


public void setLcTftRate(BigDecimal lcTftRate){
    this.lcTftRate = lcTftRate;
}


public void setPasswordMinLength(Integer passwordMinLength){
    this.passwordMinLength = passwordMinLength;
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


public void setSmtpUsername(String smtpUsername){
    this.smtpUsername = smtpUsername;
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


public void setUsernameMaxLength(Integer usernameMaxLength){
    this.usernameMaxLength = usernameMaxLength;
}


public void setZero(BigDecimal zero){
    this.zero = zero;
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


public void setTodayDuiPengBonuds(BigDecimal todayDuiPengBonuds){
    this.todayDuiPengBonuds = todayDuiPengBonuds;
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


public void setUploadMaxSize(Integer uploadMaxSize){
    this.uploadMaxSize = uploadMaxSize;
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


public void setLicaiRate(BigDecimal licaiRate){
    this.licaiRate = licaiRate;
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


public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime){
    this.safeKeyExpiryTime = safeKeyExpiryTime;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getLcTxjRate(){
    return lcTxjRate;
}


public void setUploadFlashExtension(String uploadFlashExtension){
    if (uploadFlashExtension != null) {
        uploadFlashExtension = uploadFlashExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
    }
    this.uploadFlashExtension = uploadFlashExtension;
}


public Boolean getIsLctxj(){
    return isLctxj;
}


public void setCookiePath(String cookiePath){
    if (cookiePath != null && !cookiePath.endsWith("/")) {
        cookiePath += "/";
    }
    this.cookiePath = cookiePath;
}


public void setIsBdtxj(Boolean isBdtxj){
    this.isBdtxj = isBdtxj;
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


public void setIsXjtft(Boolean isXjtft){
    this.isXjtft = isXjtft;
}


public void setAccountLockTime(Integer accountLockTime){
    this.accountLockTime = accountLockTime;
}


public void setEmail(String email){
    this.email = email;
}


public void setUploadMediaExtension(String uploadMediaExtension){
    if (uploadMediaExtension != null) {
        uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
    }
    this.uploadMediaExtension = uploadMediaExtension;
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


public void setIsTaxPriceEnabled(Boolean isTaxPriceEnabled){
    this.isTaxPriceEnabled = isTaxPriceEnabled;
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


public void setIsRegisterEnabled(Boolean isRegisterEnabled){
    this.isRegisterEnabled = isRegisterEnabled;
}


public void setSignInMoneyRate(BigDecimal signInMoneyRate){
    this.signInMoneyRate = signInMoneyRate;
}


public void setBaodanRate(BigDecimal baodanRate){
    this.baodanRate = baodanRate;
}


public void setMemberNumberLength(Integer memberNumberLength){
    this.memberNumberLength = memberNumberLength;
}


public void setAccountLockCount(Integer accountLockCount){
    this.accountLockCount = accountLockCount;
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


public void setCookieDomain(String cookieDomain){
    this.cookieDomain = cookieDomain;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


public void setXianjinRate(BigDecimal xianjinRate){
    this.xianjinRate = xianjinRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getXjTftRate(){
    return xjTftRate;
}


public void setSignInType(Integer signInType){
    this.signInType = signInType;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getManagerBonuds(){
    return managerBonuds;
}


public void setBdTlcRate(BigDecimal bdTlcRate){
    this.bdTlcRate = bdTlcRate;
}


public void setLcTbdRate(BigDecimal lcTbdRate){
    this.lcTbdRate = lcTbdRate;
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


public void setImageUploadPath(String imageUploadPath){
    if (imageUploadPath != null) {
        if (!imageUploadPath.startsWith("/")) {
            imageUploadPath = "/" + imageUploadPath;
        }
        if (!imageUploadPath.endsWith("/")) {
            imageUploadPath += "/";
        }
    }
    this.imageUploadPath = imageUploadPath;
}


public void setHuzhuBonuds(BigDecimal huzhuBonuds){
    this.huzhuBonuds = huzhuBonuds;
}


public void setDuiPengBonuds(BigDecimal duiPengBonuds){
    this.duiPengBonuds = duiPengBonuds;
}


public void setFlashUploadPath(String flashUploadPath){
    if (flashUploadPath != null) {
        if (!flashUploadPath.startsWith("/")) {
            flashUploadPath = "/" + flashUploadPath;
        }
        if (!flashUploadPath.endsWith("/")) {
            flashUploadPath += "/";
        }
    }
    this.flashUploadPath = flashUploadPath;
}


public String[] getUploadImageExtensions(){
    return StringUtils.split(uploadImageExtension, SEPARATOR);
}


public void setIsFttxj(Boolean isFttxj){
    this.isFttxj = isFttxj;
}


public void setIsSignIn(Boolean isSignIn){
    this.isSignIn = isSignIn;
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


public void setMinSignInMoney(BigDecimal minSignInMoney){
    this.minSignInMoney = minSignInMoney;
}


@NotNull
@Min(1)
@Max(117)
public Integer getPasswordMinLength(){
    return passwordMinLength;
}


public void setTodayStaticBonuds(BigDecimal todayStaticBonuds){
    this.todayStaticBonuds = todayStaticBonuds;
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


public void setSalesBonuds(BigDecimal salesBonuds){
    this.salesBonuds = salesBonuds;
}


public Boolean getIsFttxj(){
    return isFttxj;
}


@Length(max = 200)
public String getKefuQQ(){
    return kefuQQ;
}


public void setBoundsRate(BigDecimal boundsRate){
    this.boundsRate = boundsRate;
}


@NotNull
@Min(0)
public Integer getAccountLockTime(){
    return accountLockTime;
}


public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled){
    this.isDevelopmentEnabled = isDevelopmentEnabled;
}


public void setCaptchaTypes(CaptchaType[] captchaTypes){
    this.captchaTypes = captchaTypes;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getMinSignInMoney(){
    return minSignInMoney;
}


public void setCnzzPassword(String cnzzPassword){
    this.cnzzPassword = cnzzPassword;
}


@NotNull
public Boolean getIsInvoiceEnabled(){
    return isInvoiceEnabled;
}


public void setbSystemLeave(BigDecimal bSystemLeave){
    this.bSystemLeave = bSystemLeave;
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


public void setIsLicai(Boolean isLicai){
    this.isLicai = isLicai;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getBoundsRate(){
    return boundsRate;
}


public void setTodayHuzhuBonuds(BigDecimal todayHuzhuBonuds){
    this.todayHuzhuBonuds = todayHuzhuBonuds;
}


@NotNull
@Min(0)
@Max(3)
public Integer getPriceScale(){
    return priceScale;
}


public void setIsWithraw(Boolean isWithraw){
    this.isWithraw = isWithraw;
}


public void setAnnounce(String announce){
    this.announce = announce;
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


public void setbRate(BigDecimal bRate){
    this.bRate = bRate;
}


public void setBfTliRate(BigDecimal bfTliRate){
    this.bfTliRate = bfTliRate;
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


public void setPercent(BigDecimal percent){
    this.percent = percent;
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


public void setIsEmailLogin(Boolean isEmailLogin){
    this.isEmailLogin = isEmailLogin;
}


public void setTodaySalesBonuds(BigDecimal todaySalesBonuds){
    this.todaySalesBonuds = todaySalesBonuds;
}


public void setIsXjtbd(Boolean isXjtbd){
    this.isXjtbd = isXjtbd;
}


public void setFileUploadPath(String fileUploadPath){
    if (fileUploadPath != null) {
        if (!fileUploadPath.startsWith("/")) {
            fileUploadPath = "/" + fileUploadPath;
        }
        if (!fileUploadPath.endsWith("/")) {
            fileUploadPath += "/";
        }
    }
    this.fileUploadPath = fileUploadPath;
}


public void setCerttext(String certtext){
    this.certtext = certtext;
}


public void setIsBdtft(Boolean isBdtft){
    this.isBdtft = isBdtft;
}


public void setIsDuplicateEmail(Boolean isDuplicateEmail){
    this.isDuplicateEmail = isDuplicateEmail;
}


public void setTodayJianDianBonuds(BigDecimal todayJianDianBonuds){
    this.todayJianDianBonuds = todayJianDianBonuds;
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


public void setBdTftRate(BigDecimal bdTftRate){
    this.bdTftRate = bdTftRate;
}


public void setTodayManagerBonuds(BigDecimal todayManagerBonuds){
    this.todayManagerBonuds = todayManagerBonuds;
}


@NotEmpty
public String getSiteCloseMessage(){
    return siteCloseMessage;
}


public void setCnzzSiteId(String cnzzSiteId){
    this.cnzzSiteId = cnzzSiteId;
}


public void setIsLctbd(Boolean isLctbd){
    this.isLctbd = isLctbd;
}


public BigDecimal getPercent(){
    return percent;
}


public void setSmtpHost(String smtpHost){
    this.smtpHost = smtpHost;
}


@NotNull
@Min(1)
public Integer getMemberNumberLength(){
    return memberNumberLength;
}


public void setLogo(String logo){
    this.logo = logo;
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


public void setIsXianjin(Boolean isXianjin){
    this.isXianjin = isXianjin;
}


public String[] getIgnoreUrls(){
    return StringUtils.split(ignoreUrl, SEPARATOR);
}


public void setSignInMoney(BigDecimal signInMoney){
    this.signInMoney = signInMoney;
}


@NotEmpty
@Length(max = 200)
public String getCurrencyUnit(){
    return currencyUnit;
}


public void setSmtpPort(Integer smtpPort){
    this.smtpPort = smtpPort;
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


public void setReferer(String referer){
    this.referer = referer;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


public void setPasswordMaxLength(Integer passwordMaxLength){
    this.passwordMaxLength = passwordMaxLength;
}


@NotNull
public Boolean getIsEmailLogin(){
    return isEmailLogin;
}


public void setIsLctxj(Boolean isLctxj){
    this.isLctxj = isLctxj;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayJianDianBonuds(){
    return todayJianDianBonuds;
}


public void setSiteUrl(String siteUrl){
    this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
}


public void setCurrencyUnit(String currencyUnit){
    this.currencyUnit = currencyUnit;
}


public void setSmtpPassword(String smtpPassword){
    this.smtpPassword = smtpPassword;
}


@NotEmpty
@Length(max = 200)
public String getSmtpUsername(){
    return smtpUsername;
}


public void setXjTftRate(BigDecimal xjTftRate){
    this.xjTftRate = xjTftRate;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 27, scale = 12)
public BigDecimal getTodayDuiPengBonuds(){
    return todayDuiPengBonuds;
}


public void setXjTbdRate(BigDecimal xjTbdRate){
    this.xjTbdRate = xjTbdRate;
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


public void setIsFutou(Boolean isFutou){
    this.isFutou = isFutou;
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
}


public void setIsFttbd(Boolean isFttbd){
    this.isFttbd = isFttbd;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setIsOpenReferer(Boolean isOpenReferer){
    this.isOpenReferer = isOpenReferer;
}


public void setLcTxjRate(BigDecimal lcTxjRate){
    this.lcTxjRate = lcTxjRate;
}


@NotNull
@Min(1)
public Integer getAccountLockCount(){
    return accountLockCount;
}


public void setTaxRate(Double taxRate){
    this.taxRate = taxRate;
}


public Boolean getIsXianjin(){
    return isXianjin;
}


public Integer getManagerBonudsLeve(){
    return managerBonudsLeve;
}


public void setBdTxjRate(BigDecimal bdTxjRate){
    this.bdTxjRate = bdTxjRate;
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


public void setFtTbdRate(BigDecimal ftTbdRate){
    this.ftTbdRate = ftTbdRate;
}


public void setDisabledUsername(String disabledUsername){
    if (disabledUsername != null) {
        disabledUsername = disabledUsername.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.disabledUsername = disabledUsername;
}


@NotEmpty
@Length(max = 200)
public String getMediaUploadPath(){
    return mediaUploadPath;
}


public void setIgnoreUrl(String ignoreUrl){
    if (ignoreUrl != null) {
        ignoreUrl = ignoreUrl.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.ignoreUrl = ignoreUrl;
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


public void setaRate(BigDecimal aRate){
    this.aRate = aRate;
}


public void setCurrencySign(String currencySign){
    this.currencySign = currencySign;
}


public void setIsLctft(Boolean isLctft){
    this.isLctft = isLctft;
}


public void setIsBftli(Boolean isBftli){
    this.isBftli = isBftli;
}


public void setDefaultPassword(String defaultPassword){
    this.defaultPassword = defaultPassword;
}


@NotNull
@Min(1)
@Max(117)
public Integer getPasswordMaxLength(){
    return passwordMaxLength;
}


public void setRegisterPoint(Long registerPoint){
    this.registerPoint = registerPoint;
}


public void setAddress(String address){
    this.address = address;
}


public void setMemberNumberPrefix(String memberNumberPrefix){
    this.memberNumberPrefix = memberNumberPrefix;
}


public void setTodayLeaderBonuds(BigDecimal todayLeaderBonuds){
    this.todayLeaderBonuds = todayLeaderBonuds;
}


public Boolean getIsBdtxj(){
    return isBdtxj;
}


@NotNull
public RoundType getPriceRoundType(){
    return priceRoundType;
}


public void setUsernameMinLength(Integer usernameMinLength){
    this.usernameMinLength = usernameMinLength;
}


public String getCnzzPassword(){
    return cnzzPassword;
}


public String[] getDisabledUsernames(){
    return StringUtils.split(disabledUsername, SEPARATOR);
}


public void setPriceRoundType(RoundType priceRoundType){
    this.priceRoundType = priceRoundType;
}


@NotEmpty
@Length(max = 200)
public String getSiteName(){
    return siteName;
}


public void setPriceScale(Integer priceScale){
    this.priceScale = priceScale;
}


}