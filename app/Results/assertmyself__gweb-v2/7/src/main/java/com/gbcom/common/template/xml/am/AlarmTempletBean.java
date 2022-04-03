package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "alarms")
public class AlarmTempletBean implements Serializable{

 private  long serialVersionUID;

 private  String vendor;

 private  List<AlarmInfoBean> alarmInfoBeans;

 private  Map<Integer,AlarmInfoBean> alarmInfos;

 private  Map<String,AlarmInfoBean> alarmInfoOids;

/**
 * 构造方法
 */
public AlarmTempletBean() {
}
public void setVendor(String vendor){
    this.vendor = vendor;
}


public void setAlarmInfoBeans(List<AlarmInfoBean> alarmInfoBeans){
    this.alarmInfoBeans = alarmInfoBeans;
}


public void addAlarmInfo(int alarmCode,AlarmInfoBean alarmInfoBean){
    if (alarmInfos == null) {
        alarmInfos = new HashMap<Integer, AlarmInfoBean>();
    }
    // synchronized (alarmInfoBeans){
    // alarmInfoBeans.add(alarmInfoBean);
    // }
    synchronized (alarmInfos) {
        alarmInfos.put(alarmCode, alarmInfoBean);
    }
    synchronized (alarmInfoOids) {
        alarmInfoOids.put(alarmInfoBean.getAlarmOid(), alarmInfoBean);
        alarmInfoOids.put(alarmInfoBean.getClearOid(), alarmInfoBean);
    }
}


public AlarmInfoBean getAlarmInfoByOid(String oid){
    return alarmInfoOids.get(oid);
}


@XmlElement(name = "alarmInfo")
public List<AlarmInfoBean> getAlarmInfoBeans(){
    return alarmInfoBeans;
}


@XmlElement(name = "vendor")
public String getVendor(){
    return vendor;
}


public AlarmInfoBean getAlarmInfo(int alarmCode){
    return alarmInfos.get(alarmCode);
}


}