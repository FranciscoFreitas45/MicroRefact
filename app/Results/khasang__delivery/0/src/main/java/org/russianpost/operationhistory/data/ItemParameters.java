package org.russianpost.operationhistory.data;
 import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemParameters", propOrder = { "barcode", "internum", "validRuType", "validEnType", "complexItemName", "mailRank", "postMark", "mailType", "mailCtg", "mass", "maxMassRU", "maxMassEN" })
public class ItemParameters {

@XmlElement(name = "Barcode", required = true)
 protected  String barcode;

@XmlElement(name = "Internum")
 protected  String internum;

@XmlElement(name = "ValidRuType")
 protected  boolean validRuType;

@XmlElement(name = "ValidEnType")
 protected  boolean validEnType;

@XmlElement(name = "ComplexItemName", required = true)
 protected  String complexItemName;

@XmlElement(name = "MailRank")
 protected  Rtm02Parameter mailRank;

@XmlElement(name = "PostMark", required = true)
 protected  Rtm02Parameter postMark;

@XmlElement(name = "MailType", required = true)
 protected  Rtm02Parameter mailType;

@XmlElement(name = "MailCtg", required = true)
 protected  Rtm02Parameter mailCtg;

@XmlElement(name = "Mass", required = true)
 protected  BigInteger mass;

@XmlElement(name = "MaxMassRU", required = true)
 protected  BigInteger maxMassRU;

@XmlElement(name = "MaxMassEN", required = true)
 protected  BigInteger maxMassEN;


public String getComplexItemName(){
    return complexItemName;
}


public String getBarcode(){
    return barcode;
}


public void setPostMark(Rtm02Parameter value){
    this.postMark = value;
}


public void setBarcode(String value){
    this.barcode = value;
}


public void setComplexItemName(String value){
    this.complexItemName = value;
}


public boolean isValidEnType(){
    return validEnType;
}


public Rtm02Parameter getMailRank(){
    return mailRank;
}


public BigInteger getMaxMassRU(){
    return maxMassRU;
}


public void setMailType(Rtm02Parameter value){
    this.mailType = value;
}


public BigInteger getMass(){
    return mass;
}


public void setValidEnType(boolean value){
    this.validEnType = value;
}


public BigInteger getMaxMassEN(){
    return maxMassEN;
}


public void setMaxMassEN(BigInteger value){
    this.maxMassEN = value;
}


public boolean isValidRuType(){
    return validRuType;
}


public void setMaxMassRU(BigInteger value){
    this.maxMassRU = value;
}


public void setMailRank(Rtm02Parameter value){
    this.mailRank = value;
}


public Rtm02Parameter getPostMark(){
    return postMark;
}


public void setMailCtg(Rtm02Parameter value){
    this.mailCtg = value;
}


public String getInternum(){
    return internum;
}


public Rtm02Parameter getMailType(){
    return mailType;
}


public void setMass(BigInteger value){
    this.mass = value;
}


public Rtm02Parameter getMailCtg(){
    return mailCtg;
}


public void setInternum(String value){
    this.internum = value;
}


public void setValidRuType(boolean value){
    this.validRuType = value;
}


}