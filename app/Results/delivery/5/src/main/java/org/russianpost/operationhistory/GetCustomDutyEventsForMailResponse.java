package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.custom_duty_info.data.CustomDutyEventsForMailOutput;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCustomDutyEventsForMailResponse", propOrder = { "customDutyEventsForMailOutput" })
public class GetCustomDutyEventsForMailResponse {

@XmlElement(name = "CustomDutyEventsForMailOutput", namespace = "http://www.russianpost.org/custom-duty-info/data")
 protected  CustomDutyEventsForMailOutput customDutyEventsForMailOutput;


public CustomDutyEventsForMailOutput getCustomDutyEventsForMailOutput(){
    return customDutyEventsForMailOutput;
}


public void setCustomDutyEventsForMailOutput(CustomDutyEventsForMailOutput value){
    this.customDutyEventsForMailOutput = value;
}


}