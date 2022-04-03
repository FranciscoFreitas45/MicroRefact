package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class AlarmTemplet implements Serializable{

 private  long serialVersionUID;

 private  Map<String,AlarmTempletBean> alarmTempletMap;

 private  Map<String,AlarmTempletBean> alarmTempletOidMap;

 private  Map<String,AlarmReportTemplet> alarmReportTempletMap;

 private  AlarmTemplet instance;

/**
 * TODO description here
 */
private AlarmTemplet() {
}
public AlarmTempletBean getAlarmTempletByOid(String oid){
    return alarmTempletMap.get(oid);
}


public void setAlarmTempletMap(Map<String,AlarmTempletBean> alarmTempletMap){
    this.alarmTempletMap = alarmTempletMap;
}


public Map<String,AlarmReportTemplet> getAlarmReportTempletMap(){
    return alarmReportTempletMap;
}


public void addAlarmTemplet(String vendor,AlarmTempletBean alarmTempletBean){
    synchronized (vendor) {
        alarmTempletMap.put(vendor, alarmTempletBean);
    }
}


public Map<String,AlarmTempletBean> getAlarmTempletOidMap(){
    return alarmTempletOidMap;
}


public AlarmTempletBean getAlarmTemplet(String vendor){
    return alarmTempletMap.get(vendor);
}


public Map<String,AlarmTempletBean> getAlarmTempletMap(){
    return alarmTempletMap;
}


public AlarmTemplet getInstance(){
    synchronized (AlarmTemplet.class) {
        if (instance == null) {
            instance = new AlarmTemplet();
        }
        return instance;
    }
}


public void addAlarmReportTemplet(String vendor,AlarmReportTemplet alarmReportTemplet){
    synchronized (vendor) {
        alarmReportTempletMap.put(vendor, alarmReportTemplet);
    }
}


public void setAlarmReportTempletMap(Map<String,AlarmReportTemplet> alarmReportTempletMap){
    this.alarmReportTempletMap = alarmReportTempletMap;
}


public AlarmReportTemplet getAlarmReportTemplet(String vendor){
    return alarmReportTempletMap.get(vendor);
}


public void setAlarmTempletOidMap(Map<String,AlarmTempletBean> alarmTempletOidMap){
    this.alarmTempletOidMap = alarmTempletOidMap;
}


}