package org.russianpost.operationhistory.data;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinanceParameters", propOrder = { "payment", "value", "massRate", "insrRate", "airRate", "rate", "customDuty" })
public class FinanceParameters {

@XmlElement(name = "Payment", required = true)
 protected  BigInteger payment;

@XmlElement(name = "Value", required = true)
 protected  BigInteger value;

@XmlElement(name = "MassRate", required = true)
 protected  BigInteger massRate;

@XmlElement(name = "InsrRate", required = true)
 protected  BigInteger insrRate;

@XmlElement(name = "AirRate", required = true)
 protected  BigInteger airRate;

@XmlElement(name = "Rate", required = true)
 protected  BigInteger rate;

@XmlElement(name = "CustomDuty", required = true)
 protected  BigInteger customDuty;


public void setInsrRate(BigInteger value){
    this.insrRate = value;
}


public BigInteger getMassRate(){
    return massRate;
}


public void setRate(BigInteger value){
    this.rate = value;
}


public BigInteger getCustomDuty(){
    return customDuty;
}


public BigInteger getValue(){
    return value;
}


public void setMassRate(BigInteger value){
    this.massRate = value;
}


public BigInteger getRate(){
    return rate;
}


public void setValue(BigInteger value){
    this.value = value;
}


public BigInteger getInsrRate(){
    return insrRate;
}


public BigInteger getAirRate(){
    return airRate;
}


public BigInteger getPayment(){
    return payment;
}


public void setAirRate(BigInteger value){
    this.airRate = value;
}


public void setPayment(BigInteger value){
    this.payment = value;
}


public void setCustomDuty(BigInteger value){
    this.customDuty = value;
}


}