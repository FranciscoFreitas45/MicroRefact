package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "alarms")
public class AlarmReportTempletBean implements Serializable{

 private  long serialVersionUID;

 private  List<AlarmReportTemplet> alarmReportTemplets;


public void setAlarmReportTemplets(List<AlarmReportTemplet> alarmReportTemplets){
    this.alarmReportTemplets = alarmReportTemplets;
}


@XmlElement(name = "alarm")
public List<AlarmReportTemplet> getAlarmReportTemplets(){
    return alarmReportTemplets;
}


}