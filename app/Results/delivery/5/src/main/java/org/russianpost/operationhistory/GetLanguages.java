package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.AuthorizationHeader;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLanguages", propOrder = { "authorizationHeader" })
public class GetLanguages {

@XmlElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
 protected  AuthorizationHeader authorizationHeader;


public void setAuthorizationHeader(AuthorizationHeader value){
    this.authorizationHeader = value;
}


public AuthorizationHeader getAuthorizationHeader(){
    return authorizationHeader;
}


}