package com.crontab;
 import java.math.BigDecimal;
import java.util.Arrays;
public class JsonOrderNormalSize extends AbstractJsonimplements Comparable<JsonOrderNormalSize>{

 private  String im;

 private  String fund;

 private  String custId;

 private  BigDecimal ccy1NormalSize;

 private  BigDecimal ccy2NormalSize;

 private  String[] custIdArr;


public String getIm(){
    return im;
}


public void setIm(String im){
    this.im = im;
}


public String[] getCustIdArr(){
    return custIdArr;
}


public void setCustIdArr(String[] custIdArr){
    this.custIdArr = Arrays.copyOf(custIdArr, custIdArr.length);
}


public String getFund(){
    return fund;
}


public BigDecimal getCcy1NormalSize(){
    return ccy1NormalSize;
}


public String getCustId(){
    return custId;
}


@Override
public int compareTo(JsonOrderNormalSize o){
    if (this.getIm() != null && o.getIm() != null) {
        int flag = this.getIm().compareTo(o.getIm());
        if (flag == 0 && this.getFund() != null && o.getFund() != null) {
            flag = this.getFund().compareTo(o.getFund());
            if (flag == 0 && this.getCustId() != null && o.getCustId() != null) {
                flag = this.getCustId().compareTo(o.getCustId());
                if (flag == 0 && this.getCurrencyPairGroup().getPricingServiceId() != null && o.getCurrencyPairGroup().getPricingServiceId() != null) {
                    flag = this.getCurrencyPairGroup().getPricingServiceId().compareTo(o.getCurrencyPairGroup().getPricingServiceId());
                    if (flag == 0 && this.getCurrencyPairGroup().getCcy1() != null && o.getCurrencyPairGroup().getCcy1() != null) {
                        flag = this.getCurrencyPairGroup().getCcy1().compareTo(o.getCurrencyPairGroup().getCcy1());
                        if (flag == 0 && this.getCurrencyPairGroup().getCcy2() != null && o.getCurrencyPairGroup().getCcy2() != null) {
                            return this.getCurrencyPairGroup().getCcy2().compareTo(o.getCurrencyPairGroup().getCcy2());
                        }
                        return flag;
                    }
                    return flag;
                }
                return flag;
            }
            return flag;
        }
        return flag;
    } else {
        return 0;
    }
}


public void setFund(String fund){
    this.fund = fund;
}


public void setCustId(String custId){
    this.custId = custId;
}


public BigDecimal getCcy2NormalSize(){
    return ccy2NormalSize;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((ccy1NormalSize == null) ? 0 : ccy1NormalSize.hashCode());
    result = prime * result + ((ccy2NormalSize == null) ? 0 : ccy2NormalSize.hashCode());
    result = prime * result + ((currencyPairGroup == null) ? 0 : currencyPairGroup.hashCode());
    result = prime * result + ((custId == null) ? 0 : custId.hashCode());
    result = prime * result + Arrays.hashCode(custIdArr);
    result = prime * result + ((fund == null) ? 0 : fund.hashCode());
    result = prime * result + ((im == null) ? 0 : im.hashCode());
    result = prime * result + ((lastUpdatedById == null) ? 0 : lastUpdatedById.hashCode());
    result = prime * result + ((lastUpdatedDttm == null) ? 0 : lastUpdatedDttm.hashCode());
    return result;
}


public void setCcy1NormalSize(BigDecimal ccy1NormalSize){
    this.ccy1NormalSize = ccy1NormalSize;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    JsonOrderNormalSize other = (JsonOrderNormalSize) obj;
    if (ccy1NormalSize == null) {
        if (other.ccy1NormalSize != null) {
            return false;
        }
    } else if (!ccy1NormalSize.equals(other.ccy1NormalSize)) {
        return false;
    }
    if (ccy2NormalSize == null) {
        if (other.ccy2NormalSize != null) {
            return false;
        }
    } else if (!ccy2NormalSize.equals(other.ccy2NormalSize)) {
        return false;
    }
    if (currencyPairGroup == null) {
        if (other.currencyPairGroup != null) {
            return false;
        }
    } else if (!currencyPairGroup.equals(other.currencyPairGroup)) {
        return false;
    }
    if (custId == null) {
        if (other.custId != null) {
            return false;
        }
    } else if (!custId.equals(other.custId)) {
        return false;
    }
    if (!Arrays.equals(custIdArr, other.custIdArr)) {
        return false;
    }
    if (fund == null) {
        if (other.fund != null) {
            return false;
        }
    } else if (!fund.equals(other.fund)) {
        return false;
    }
    if (im == null) {
        if (other.im != null) {
            return false;
        }
    } else if (!im.equals(other.im)) {
        return false;
    }
    if (lastUpdatedById == null) {
        if (other.lastUpdatedById != null) {
            return false;
        }
    } else if (!lastUpdatedById.equals(other.lastUpdatedById)) {
        return false;
    }
    if (lastUpdatedDttm == null) {
        if (other.lastUpdatedDttm != null) {
            return false;
        }
    } else if (!lastUpdatedDttm.equals(other.lastUpdatedDttm)) {
        return false;
    }
    return true;
}


public void setCcy2NormalSize(BigDecimal ccy2NormalSize){
    this.ccy2NormalSize = ccy2NormalSize;
}


@Override
public String toString(){
    return "JsonOrderNormalSize [currencyPairGroup=" + currencyPairGroup + ", im=" + im + ", fund=" + fund + ", custId=" + custId + ", ccy1NormalSize=" + ccy1NormalSize + ", ccy2NormalSize=" + ccy2NormalSize + ", lastUpdatedById=" + lastUpdatedById + ", lastUpdatedDttm=" + lastUpdatedDttm + ", custIdArr=" + Arrays.toString(custIdArr) + "]";
}


}