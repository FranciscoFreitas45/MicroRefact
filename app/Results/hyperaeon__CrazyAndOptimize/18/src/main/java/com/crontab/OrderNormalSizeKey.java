package com.crontab;
 public class OrderNormalSizeKey {

 private  String pricingServiceId;

 private  String ccy1;

 private  String ccy2;

 private  String fund;


public void setCcy1(String ccy1){
    this.ccy1 = ccy1;
}


public String getCcy2(){
    return ccy2;
}


public String getCcy1(){
    return ccy1;
}


public void setCcy2(String ccy2){
    this.ccy2 = ccy2;
}


public String getFund(){
    return fund;
}


@Override
public int hashCode(){
    StringBuilder sb = new StringBuilder();
    sb.append(pricingServiceId);
    if (ccy1.compareTo(ccy2) < 0) {
        sb.append(ccy1);
        sb.append(ccy2);
    } else {
        sb.append(ccy2);
        sb.append(ccy1);
    }
    sb.append(fund);
    return sb.toString().hashCode();
}


public void setPricingServiceId(String pricingServiceId){
    this.pricingServiceId = pricingServiceId;
}


@Override
public boolean equals(Object obj){
    if (!(obj instanceof OrderNormalSizeKey)) {
        return false;
    }
    OrderNormalSizeKey other = (OrderNormalSizeKey) obj;
    return this.hashCode() == other.hashCode();
}


public void setFund(String fund){
    this.fund = fund;
}


public String getPricingServiceId(){
    return pricingServiceId;
}


}