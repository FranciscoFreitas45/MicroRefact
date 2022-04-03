package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.sms_info.data.SmsHistoryData;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSmsHistoryResponse", propOrder = { "smsHistoryData" })
public class GetSmsHistoryResponse {

@XmlElement(name = "SmsHistoryData", namespace = "http://russianpost.org/sms-info/data")
 protected  SmsHistoryData smsHistoryData;


public void setSmsHistoryData(SmsHistoryData value){
    this.smsHistoryData = value;
}


public SmsHistoryData getSmsHistoryData(){
    return smsHistoryData;
}


}