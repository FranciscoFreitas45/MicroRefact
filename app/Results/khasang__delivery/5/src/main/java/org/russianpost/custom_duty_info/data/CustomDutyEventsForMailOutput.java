package org.russianpost.custom_duty_info.data;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomDutyEventsForMailOutput", propOrder = { "customDutyEvent" })
public class CustomDutyEventsForMailOutput {

@XmlElement(name = "CustomDutyEvent")
 protected  List<CustomDutyEvent> customDutyEvent;


public List<CustomDutyEvent> getCustomDutyEvent(){
    if (customDutyEvent == null) {
        customDutyEvent = new ArrayList<CustomDutyEvent>();
    }
    return this.customDutyEvent;
}


}