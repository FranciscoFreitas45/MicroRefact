package forms;
 import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public class FormObjectSponsorshipCreditCard {

 private  int id;

 private  String banner;

 private  String targetURL;

 private  String holderName;

 private  String brandName;

 private  Long number;

 private  Integer expirationMonth;

 private  Integer expirationYear;

 private  Integer cvvCode;

public FormObjectSponsorshipCreditCard() {
    this.id = 0;
}
@NotBlank
public String getBrandName(){
    return this.brandName;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


public void setHolderName(String holderName){
    this.holderName = holderName;
}


@Min(0)
@Max(99)
@NotNull
public Integer getExpirationYear(){
    return this.expirationYear;
}


@Max(12)
@Min(1)
@NotNull
public Integer getExpirationMonth(){
    return this.expirationMonth;
}


public int getId(){
    return this.id;
}


@Min(1)
@Max(999)
@NotNull
public Integer getCvvCode(){
    return this.cvvCode;
}


public void setCvvCode(Integer cvvCode){
    this.cvvCode = cvvCode;
}


public void setNumber(Long number){
    this.number = number;
}


@NotNull
public Long getNumber(){
    return this.number;
}


public void setExpirationYear(Integer expirationYear){
    this.expirationYear = expirationYear;
}


@URL
@NotBlank
public String getTargetURL(){
    return this.targetURL;
}


public void setTargetURL(String targetURL){
    this.targetURL = targetURL;
}


@NotBlank
public String getHolderName(){
    return this.holderName;
}


public void setId(int id){
    this.id = id;
}


public void setExpirationMonth(Integer expirationMonth){
    this.expirationMonth = expirationMonth;
}


@URL
@NotBlank
public String getBanner(){
    return this.banner;
}


public void setBanner(String banner){
    this.banner = banner;
}


}