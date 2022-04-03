package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.custom_duty_info.data.CustomDutyEventsForMailInput;
import org.russianpost.operationhistory.data.AuthorizationHeader;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCustomDutyEventsForMail", propOrder = { "authorizationHeader", "customDutyEventsForMailInput" })
public class GetCustomDutyEventsForMail {

@XmlElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
 protected  AuthorizationHeader authorizationHeader;

@XmlElement(name = "CustomDutyEventsForMailInput", namespace = "http://www.russianpost.org/custom-duty-info/data")
 protected  CustomDutyEventsForMailInput customDutyEventsForMailInput;


public void setCustomDutyEventsForMailInput(CustomDutyEventsForMailInput value){
    this.customDutyEventsForMailInput = value;
}


public void setAuthorizationHeader(AuthorizationHeader value){
    this.authorizationHeader = value;
}


public AuthorizationHeader getAuthorizationHeader(){
    return authorizationHeader;
}


public CustomDutyEventsForMailInput getCustomDutyEventsForMailInput(){
    return customDutyEventsForMailInput;
}


}