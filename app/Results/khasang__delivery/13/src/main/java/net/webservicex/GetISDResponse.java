package net.webservicex;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getISDResult" })
@XmlRootElement(name = "GetISDResponse")
public class GetISDResponse {

@XmlElement(name = "GetISDResult")
 protected  String getISDResult;


public void setGetISDResult(String value){
    this.getISDResult = value;
}


public String getGetISDResult(){
    return getISDResult;
}


}