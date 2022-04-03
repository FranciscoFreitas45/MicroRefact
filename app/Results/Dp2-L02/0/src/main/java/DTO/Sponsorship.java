package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public class Sponsorship extends DomainEntity{

 private  String banner;

 private  String targetURL;

 private  java.lang.Float spentMoney;

 private  Boolean isActivated;

 private  CreditCard creditCard;

 private  Parade parade;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public void setIsActivated(Boolean isActivated){
    this.isActivated = isActivated;
}


@NotNull
public Boolean getIsActivated(){
    return this.isActivated;
}


@ManyToOne(optional = true)
public Parade getParade(){
    return this.parade;
}


@URL
@NotBlank
public String getTargetURL(){
    return this.targetURL;
}


@Valid
@NotNull
public CreditCard getCreditCard(){
    return this.creditCard;
}


@URL
@NotBlank
public String getBanner(){
    return this.banner;
}


@NotNull
public java.lang.Float getSpentMoney(){
    return this.spentMoney;
}


public void setBanner(String banner){
    this.banner = banner;
}


public void setParade(Parade parade){
    this.parade = parade;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParade"))

.queryParam("parade",parade)
;
restTemplate.put(builder.toUriString(),null);
}


}