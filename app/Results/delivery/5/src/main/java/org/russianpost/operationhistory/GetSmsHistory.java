package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.AuthorizationHeader;
import org.russianpost.sms_info.data.SmsHistoryRequest;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSmsHistory", propOrder = { "authorizationHeader", "smsHistoryRequest" })
public class GetSmsHistory {

@XmlElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
 protected  AuthorizationHeader authorizationHeader;

@XmlElement(name = "SmsHistoryRequest", namespace = "http://russianpost.org/sms-info/data")
 protected  SmsHistoryRequest smsHistoryRequest;


public void setAuthorizationHeader(AuthorizationHeader value){
    this.authorizationHeader = value;
}


public void setSmsHistoryRequest(SmsHistoryRequest value){
    this.smsHistoryRequest = value;
}


public AuthorizationHeader getAuthorizationHeader(){
    return authorizationHeader;
}


public SmsHistoryRequest getSmsHistoryRequest(){
    return smsHistoryRequest;
}


}