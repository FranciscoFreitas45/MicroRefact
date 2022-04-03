package org.russianpost.sms_info.data;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "historyRecord" })
@XmlRootElement(name = "SmsHistoryData")
public class SmsHistoryData {

@XmlElement(nillable = true)
 protected  List<SmsHistoryRecord> historyRecord;


public List<SmsHistoryRecord> getHistoryRecord(){
    if (historyRecord == null) {
        historyRecord = new ArrayList<SmsHistoryRecord>();
    }
    return this.historyRecord;
}


}