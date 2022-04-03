package domain;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

 private  String holderName;

 private  String brandName;

 private  Long number;

 private  Integer expirationMonth;

 private  Integer expirationYear;

 private  Integer cvvCode;

public CreditCard() {
}
@NotNull
public Long getNumber(){
    return this.number;
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


public void setExpirationYear(Integer expirationYear){
    this.expirationYear = expirationYear;
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


@NotBlank
public String getHolderName(){
    return this.holderName;
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


public void setExpirationMonth(Integer expirationMonth){
    this.expirationMonth = expirationMonth;
}


public void setNumber(Long number){
    this.number = number;
}


}