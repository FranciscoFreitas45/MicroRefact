package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "conversionRateResult" })
@XmlRootElement(name = "ConversionRateResponse")
public class ConversionRateResponse {

@XmlElement(name = "ConversionRateResult")
 protected  double conversionRateResult;


public double getConversionRateResult(){
    return conversionRateResult;
}


public void setConversionRateResult(double value){
    this.conversionRateResult = value;
}


}