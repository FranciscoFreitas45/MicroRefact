package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "login", "password" })
@XmlRootElement(name = "AuthorizationHeader")
public class AuthorizationHeader {

@XmlElement(required = true)
 protected  String login;

@XmlElement(required = true)
 protected  String password;

@XmlAttribute(name = "mustUnderstand", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
 protected  String mustUnderstand;


public String getLogin(){
    return login;
}


public void setPassword(String value){
    this.password = value;
}


public String getPassword(){
    return password;
}


public String getMustUnderstand(){
    return mustUnderstand;
}


public void setLogin(String value){
    this.login = value;
}


public void setMustUnderstand(String value){
    this.mustUnderstand = value;
}


}