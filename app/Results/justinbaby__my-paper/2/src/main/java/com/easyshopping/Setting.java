package com.easyshopping;
 import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Setting implements Serializable{

 private  long serialVersionUID;

 public  String CACHE_NAME;

 public  Integer CACHE_KEY;

 private  String SEPARATOR;

 private  String siteName;

 private  String siteUrl;

 private  String logo;

 private  String hotSearch;

 private  String address;

 private  String phone;

 private  String zipCode;

 private  String email;

 private  String certtext;

 private  Boolean isSiteEnabled;

 private  String siteCloseMessage;

 private  Integer largeProductImageWidth;

 private  Integer largeProductImageHeight;

 private  Integer mediumProductImageWidth;

 private  Integer mediumProductImageHeight;

 private  Integer thumbnailProductImageWidth;

 private  Integer thumbnailProductImageHeight;

 private  String defaultLargeProductImage;

 private  String defaultMediumProductImage;

 private  String defaultThumbnailProductImage;

 private  Integer watermarkAlpha;

 private  String watermarkImage;

 private  WatermarkPosition watermarkPosition;

 private  Integer priceScale;

 private  RoundType priceRoundType;

 private  Boolean isShowMarketPrice;

 private  Double defaultMarketPriceScale;

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

 private  Integer stockAlertCount;

 private  StockAllocationTime stockAllocationTime;

 private  Double defaultPointScale;

 private  Boolean isDevelopmentEnabled;

 private  Boolean isReviewEnabled;

 private  Boolean isReviewCheck;

 private  ReviewAuthority reviewAuthority;

 private  Boolean isConsultationEnabled;

 private  Boolean isConsultationCheck;

 private  ConsultationAuthority consultationAuthority;

 private  Boolean isInvoiceEnabled;

 private  Boolean isTaxPriceEnabled;

 private  Double taxRate;

 private  String cookiePath;

 private  String cookieDomain;

 private  String kuaidi100Key;

 private  Boolean isCnzzEnabled;

 private  String cnzzSiteId;

 private  String cnzzPassword;


@NotNull
@Min(1)
@Max(117)
public Integer getPasswordMinLength(){
    return passwordMinLength;
}


@NotNull
public Boolean getIsReviewEnabled(){
    return isReviewEnabled;
}


@NotNull
public ReviewAuthority getReviewAuthority(){
    return reviewAuthority;
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


public void setDefaultMediumProductImage(String defaultMediumProductImage){
    this.defaultMediumProductImage = defaultMediumProductImage;
}


@Length(max = 200)
public String getHotSearch(){
    return hotSearch;
}


@NotNull
@Min(0)
public Integer getAccountLockTime(){
    return accountLockTime;
}


public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled){
    this.isDevelopmentEnabled = isDevelopmentEnabled;
}


@NotNull
@Min(1)
@Max(117)
public Integer getUsernameMinLength(){
    return usernameMinLength;
}


public void setCaptchaTypes(CaptchaType[] captchaTypes){
    this.captchaTypes = captchaTypes;
}


public void setUploadImageExtension(String uploadImageExtension){
    if (uploadImageExtension != null) {
        uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
    }
    this.uploadImageExtension = uploadImageExtension;
}


public void setThumbnailProductImageWidth(Integer thumbnailProductImageWidth){
    this.thumbnailProductImageWidth = thumbnailProductImageWidth;
}


public void setCnzzPassword(String cnzzPassword){
    this.cnzzPassword = cnzzPassword;
}


public void setStockAlertCount(Integer stockAlertCount){
    this.stockAlertCount = stockAlertCount;
}


@NotNull
public Boolean getIsInvoiceEnabled(){
    return isInvoiceEnabled;
}


public Boolean getIsCnzzEnabled(){
    return isCnzzEnabled;
}


@Length(max = 200)
public String getCerttext(){
    return certtext;
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


public void setIsConsultationEnabled(Boolean isConsultationEnabled){
    this.isConsultationEnabled = isConsultationEnabled;
}


public void setIsInvoiceEnabled(Boolean isInvoiceEnabled){
    this.isInvoiceEnabled = isInvoiceEnabled;
}


@NotNull
@Min(0)
public Integer getSmtpPort(){
    return smtpPort;
}


public String[] getUploadMediaExtensions(){
    return StringUtils.split(uploadMediaExtension, SEPARATOR);
}


@NotNull
public Boolean getIsTaxPriceEnabled(){
    return isTaxPriceEnabled;
}


@NotNull
@Min(1)
public Integer getMediumProductImageWidth(){
    return mediumProductImageWidth;
}


@NotNull
@Min(0)
@Max(3)
public Integer getPriceScale(){
    return priceScale;
}


@NotEmpty
@Length(max = 200)
public String getImageUploadPath(){
    return imageUploadPath;
}


@Length(max = 200)
public String getUploadFlashExtension(){
    return uploadFlashExtension;
}


public String getCnzzSiteId(){
    return cnzzSiteId;
}


@NotNull
@Min(0)
public Integer getUploadMaxSize(){
    return uploadMaxSize;
}


public void setWatermarkPosition(WatermarkPosition watermarkPosition){
    this.watermarkPosition = watermarkPosition;
}


@NotEmpty
@Length(max = 200)
public String getCurrencySign(){
    return currencySign;
}


public void setSiteName(String siteName){
    this.siteName = siteName;
}


public void setStockAllocationTime(StockAllocationTime stockAllocationTime){
    this.stockAllocationTime = stockAllocationTime;
}


@Length(max = 200)
public String getSmtpPassword(){
    return smtpPassword;
}


public void setSmtpFromMail(String smtpFromMail){
    this.smtpFromMail = smtpFromMail;
}


@Length(max = 200)
public String getPhone(){
    return phone;
}


@NotNull
public Boolean getIsDuplicateEmail(){
    return isDuplicateEmail;
}


@Length(max = 200)
public String getUploadImageExtension(){
    return uploadImageExtension;
}


@Length(max = 200)
public String getUploadMediaExtension(){
    return uploadMediaExtension;
}


public void setRegisterAgreement(String registerAgreement){
    this.registerAgreement = registerAgreement;
}


@NotNull
@Min(0)
public Long getRegisterPoint(){
    return registerPoint;
}


@NotNull
public WatermarkPosition getWatermarkPosition(){
    return watermarkPosition;
}


public void setIsEmailLogin(Boolean isEmailLogin){
    this.isEmailLogin = isEmailLogin;
}


@NotNull
@Min(0)
@Max(100)
public Integer getWatermarkAlpha(){
    return watermarkAlpha;
}


public void setSiteCloseMessage(String siteCloseMessage){
    this.siteCloseMessage = siteCloseMessage;
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


@NotNull
public Boolean getIsConsultationEnabled(){
    return isConsultationEnabled;
}


public void setCerttext(String certtext){
    this.certtext = certtext;
}


@NotNull
@Min(1)
public Integer getMediumProductImageHeight(){
    return mediumProductImageHeight;
}


public void setWatermarkAlpha(Integer watermarkAlpha){
    this.watermarkAlpha = watermarkAlpha;
}


public void setDefaultPointScale(Double defaultPointScale){
    this.defaultPointScale = defaultPointScale;
}


public void setIsDuplicateEmail(Boolean isDuplicateEmail){
    this.isDuplicateEmail = isDuplicateEmail;
}


@NotNull
public Boolean getIsShowMarketPrice(){
    return isShowMarketPrice;
}


public void setMediumProductImageWidth(Integer mediumProductImageWidth){
    this.mediumProductImageWidth = mediumProductImageWidth;
}


public void setIsShowMarketPrice(Boolean isShowMarketPrice){
    this.isShowMarketPrice = isShowMarketPrice;
}


public void setAccountLockTypes(AccountLockType[] accountLockTypes){
    this.accountLockTypes = accountLockTypes;
}


@NotEmpty
public String getSiteCloseMessage(){
    return siteCloseMessage;
}


public void setIsCnzzEnabled(Boolean isCnzzEnabled){
    this.isCnzzEnabled = isCnzzEnabled;
}


public void setCnzzSiteId(String cnzzSiteId){
    this.cnzzSiteId = cnzzSiteId;
}


public void setDefaultMarketPriceScale(Double defaultMarketPriceScale){
    this.defaultMarketPriceScale = defaultMarketPriceScale;
}


public void setDefaultLargeProductImage(String defaultLargeProductImage){
    this.defaultLargeProductImage = defaultLargeProductImage;
}


public void setSmtpHost(String smtpHost){
    this.smtpHost = smtpHost;
}


public void setLogo(String logo){
    this.logo = logo;
}


@NotNull
@Min(1)
public Integer getLargeProductImageHeight(){
    return largeProductImageHeight;
}


public void setPasswordMinLength(Integer passwordMinLength){
    this.passwordMinLength = passwordMinLength;
}


public AccountLockType[] getAccountLockTypes(){
    return accountLockTypes;
}


@NotEmpty
@Length(max = 200)
public String getFileUploadPath(){
    return fileUploadPath;
}


public void setSmtpUsername(String smtpUsername){
    this.smtpUsername = smtpUsername;
}


@NotNull
@Min(0)
public Integer getSafeKeyExpiryTime(){
    return safeKeyExpiryTime;
}


public void setIsReviewCheck(Boolean isReviewCheck){
    this.isReviewCheck = isReviewCheck;
}


public void setMediumProductImageHeight(Integer mediumProductImageHeight){
    this.mediumProductImageHeight = mediumProductImageHeight;
}


@NotEmpty
@Length(max = 200)
public String getCurrencyUnit(){
    return currencyUnit;
}


@Length(max = 200)
public String getCookieDomain(){
    return cookieDomain;
}


@NotEmpty
@Length(max = 200)
public String getDefaultLargeProductImage(){
    return defaultLargeProductImage;
}


public void setUsernameMaxLength(Integer usernameMaxLength){
    this.usernameMaxLength = usernameMaxLength;
}


public void setThumbnailProductImageHeight(Integer thumbnailProductImageHeight){
    this.thumbnailProductImageHeight = thumbnailProductImageHeight;
}


public void setSmtpPort(Integer smtpPort){
    this.smtpPort = smtpPort;
}


@NotEmpty
public String getRegisterAgreement(){
    return registerAgreement;
}


public void setReviewAuthority(ReviewAuthority reviewAuthority){
    this.reviewAuthority = reviewAuthority;
}


public void setLargeProductImageWidth(Integer largeProductImageWidth){
    this.largeProductImageWidth = largeProductImageWidth;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


public void setPasswordMaxLength(Integer passwordMaxLength){
    this.passwordMaxLength = passwordMaxLength;
}


@NotNull
public Boolean getIsReviewCheck(){
    return isReviewCheck;
}


@Length(max = 200)
public String getZipCode(){
    return zipCode;
}


@NotNull
public Boolean getIsEmailLogin(){
    return isEmailLogin;
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


public void setUploadMaxSize(Integer uploadMaxSize){
    this.uploadMaxSize = uploadMaxSize;
}


@NotEmpty
@Length(max = 200)
public String getCookiePath(){
    return cookiePath;
}


public void setSiteUrl(String siteUrl){
    this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
}


@NotNull
@Min(1)
@Max(117)
public Integer getUsernameMaxLength(){
    return usernameMaxLength;
}


public void setCurrencyUnit(String currencyUnit){
    this.currencyUnit = currencyUnit;
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


public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime){
    this.safeKeyExpiryTime = safeKeyExpiryTime;
}


public void setSmtpPassword(String smtpPassword){
    this.smtpPassword = smtpPassword;
}


public void setUploadFlashExtension(String uploadFlashExtension){
    if (uploadFlashExtension != null) {
        uploadFlashExtension = uploadFlashExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
    }
    this.uploadFlashExtension = uploadFlashExtension;
}


@NotEmpty
@Length(max = 200)
public String getSmtpUsername(){
    return smtpUsername;
}


public void setIsReviewEnabled(Boolean isReviewEnabled){
    this.isReviewEnabled = isReviewEnabled;
}


public void setCookiePath(String cookiePath){
    if (cookiePath != null && !cookiePath.endsWith("/")) {
        cookiePath += "/";
    }
    this.cookiePath = cookiePath;
}


@Length(max = 200)
public String getKuaidi100Key(){
    return kuaidi100Key;
}


@NotEmpty
@Length(max = 200)
public String getLogo(){
    return logo;
}


public void setKuaidi100Key(String kuaidi100Key){
    this.kuaidi100Key = kuaidi100Key;
}


public void setLargeProductImageHeight(Integer largeProductImageHeight){
    this.largeProductImageHeight = largeProductImageHeight;
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


public void setPhone(String phone){
    this.phone = phone;
}


@NotNull
@Min(1)
public Integer getAccountLockCount(){
    return accountLockCount;
}


@NotEmpty
@Length(max = 200)
public String getFlashUploadPath(){
    return flashUploadPath;
}


public void setTaxRate(Double taxRate){
    this.taxRate = taxRate;
}


public void setAccountLockTime(Integer accountLockTime){
    this.accountLockTime = accountLockTime;
}


public void setConsultationAuthority(ConsultationAuthority consultationAuthority){
    this.consultationAuthority = consultationAuthority;
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


public void setIsTaxPriceEnabled(Boolean isTaxPriceEnabled){
    this.isTaxPriceEnabled = isTaxPriceEnabled;
}


@Email
@Length(max = 200)
public String getEmail(){
    return email;
}


public void setIsRegisterEnabled(Boolean isRegisterEnabled){
    this.isRegisterEnabled = isRegisterEnabled;
}


@NotNull
public ConsultationAuthority getConsultationAuthority(){
    return consultationAuthority;
}


public void setDisabledUsername(String disabledUsername){
    if (disabledUsername != null) {
        disabledUsername = disabledUsername.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.disabledUsername = disabledUsername;
}


public void setIsConsultationCheck(Boolean isConsultationCheck){
    this.isConsultationCheck = isConsultationCheck;
}


public String getWatermarkImage(){
    return watermarkImage;
}


@NotEmpty
@Length(max = 200)
public String getMediaUploadPath(){
    return mediaUploadPath;
}


@NotEmpty
@Length(max = 200)
public String getDefaultThumbnailProductImage(){
    return defaultThumbnailProductImage;
}


public void setAccountLockCount(Integer accountLockCount){
    this.accountLockCount = accountLockCount;
}


@Length(max = 200)
public String getUploadFileExtension(){
    return uploadFileExtension;
}


@NotNull
@Min(1)
public Integer getLargeProductImageWidth(){
    return largeProductImageWidth;
}


@NotNull
@Min(1)
public Integer getThumbnailProductImageWidth(){
    return thumbnailProductImageWidth;
}


@NotEmpty
@Length(max = 200)
public String getDefaultMediumProductImage(){
    return defaultMediumProductImage;
}


@NotNull
@Min(0)
public Integer getStockAlertCount(){
    return stockAlertCount;
}


@NotEmpty
@Length(max = 200)
public String getSmtpHost(){
    return smtpHost;
}


public void setCurrencySign(String currencySign){
    this.currencySign = currencySign;
}


public void setCookieDomain(String cookieDomain){
    this.cookieDomain = cookieDomain;
}


@Length(max = 200)
public String getAddress(){
    return address;
}


public void setWatermarkImage(String watermarkImage){
    this.watermarkImage = watermarkImage;
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


@NotNull
@Min(1)
public Integer getThumbnailProductImageHeight(){
    return thumbnailProductImageHeight;
}


public String[] getHotSearches(){
    return StringUtils.split(hotSearch, SEPARATOR);
}


public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage){
    this.defaultThumbnailProductImage = defaultThumbnailProductImage;
}


@NotNull
public Boolean getIsSiteEnabled(){
    return isSiteEnabled;
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


@NotNull
public RoundType getPriceRoundType(){
    return priceRoundType;
}


public void setUsernameMinLength(Integer usernameMinLength){
    this.usernameMinLength = usernameMinLength;
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


public void setHotSearch(String hotSearch){
    if (hotSearch != null) {
        hotSearch = hotSearch.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.hotSearch = hotSearch;
}


public String[] getUploadImageExtensions(){
    return StringUtils.split(uploadImageExtension, SEPARATOR);
}


@NotNull
public Boolean getIsConsultationCheck(){
    return isConsultationCheck;
}


public String getCnzzPassword(){
    return cnzzPassword;
}


public String[] getDisabledUsernames(){
    return StringUtils.split(disabledUsername, SEPARATOR);
}


@NotNull
public StockAllocationTime getStockAllocationTime(){
    return stockAllocationTime;
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


@NotNull
@Min(0)
@Digits(integer = 3, fraction = 3)
public Double getDefaultMarketPriceScale(){
    return defaultMarketPriceScale;
}


public CaptchaType[] getCaptchaTypes(){
    return captchaTypes;
}


}