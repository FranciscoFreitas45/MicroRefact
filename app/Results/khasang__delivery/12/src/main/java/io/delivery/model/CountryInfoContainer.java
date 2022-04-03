package io.delivery.model;
 public class CountryInfoContainer {

 private  String countryCode;

 private  String name;

 private  String currency;

 private  String currencyCode;

public CountryInfoContainer() {
}
public void setCountryCode(String countryCode){
    this.countryCode = countryCode;
}


public void setName(String name){
    this.name = name;
}


public String getCountryCode(){
    return countryCode;
}


public String getCurrency(){
    return currency;
}


public String getCurrencyCode(){
    return currencyCode;
}


public String getName(){
    return name;
}


public void setCurrency(String currency){
    this.currency = currency;
}


public void setCurrencyCode(String currencyCode){
    this.currencyCode = currencyCode;
}


}