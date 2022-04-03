package com.easyshopping.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


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


@Length(max = 200)
public String getHotSearch(){
    return hotSearch;
}


@NotNull
@Min(0)
public Integer getAccountLockTime(){
    return accountLockTime;
}


@NotNull
@Min(1)
@Max(117)
public Integer getUsernameMinLength(){
    return usernameMinLength;
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


@NotEmpty
@Length(max = 200)
public String getCurrencySign(){
    return currencySign;
}


@Length(max = 200)
public String getSmtpPassword(){
    return smtpPassword;
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


@NotNull
@Min(0)
public Long getRegisterPoint(){
    return registerPoint;
}


@NotNull
public WatermarkPosition getWatermarkPosition(){
    return watermarkPosition;
}


@NotNull
@Min(0)
@Max(100)
public Integer getWatermarkAlpha(){
    return watermarkAlpha;
}


@NotNull
public Boolean getIsConsultationEnabled(){
    return isConsultationEnabled;
}


@NotNull
@Min(1)
public Integer getMediumProductImageHeight(){
    return mediumProductImageHeight;
}


@NotNull
public Boolean getIsShowMarketPrice(){
    return isShowMarketPrice;
}


@NotEmpty
public String getSiteCloseMessage(){
    return siteCloseMessage;
}


@NotNull
@Min(1)
public Integer getLargeProductImageHeight(){
    return largeProductImageHeight;
}


public AccountLockType[] getAccountLockTypes(){
    return accountLockTypes;
}


@NotEmpty
@Length(max = 200)
public String getFileUploadPath(){
    return fileUploadPath;
}


@NotNull
@Min(0)
public Integer getSafeKeyExpiryTime(){
    return safeKeyExpiryTime;
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


@NotEmpty
public String getRegisterAgreement(){
    return registerAgreement;
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


@NotEmpty
@Length(max = 200)
public String getSmtpUsername(){
    return smtpUsername;
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


@NotNull
public Boolean getIsDevelopmentEnabled(){
    return isDevelopmentEnabled;
}


@Email
@Length(max = 200)
public String getEmail(){
    return email;
}


@NotNull
public ConsultationAuthority getConsultationAuthority(){
    return consultationAuthority;
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


@Length(max = 200)
public String getAddress(){
    return address;
}


@NotNull
@Min(1)
@Max(117)
public Integer getPasswordMaxLength(){
    return passwordMaxLength;
}


@NotNull
@Min(1)
public Integer getThumbnailProductImageHeight(){
    return thumbnailProductImageHeight;
}


public String[] getHotSearches(){
    return StringUtils.split(hotSearch, SEPARATOR);
}


@NotNull
public Boolean getIsSiteEnabled(){
    return isSiteEnabled;
}


@NotNull
public RoundType getPriceRoundType(){
    return priceRoundType;
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


@NotEmpty
@Length(max = 200)
public String getSiteName(){
    return siteName;
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