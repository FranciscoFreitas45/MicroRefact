package com.gbcom.common.template.xml.guide.bean;
 import com.gbcom.common.template.xml.guide.DeviceInfo;
public class ApUserStatInfo extends DeviceInfo{

 private  long serialVersionUID;

 private  int staID;

 private  String bssid;

 private  String ssid;

 private  int staVlanId;

 private  int staVlanEnable;

 private  String staMacAddress;

 private  String staIp;

 private  String staDevType;

 private  String staConnectTime;

 private  long staUplinkOctets;

 private  long staDownlinkOctets;

 private  int rssi;

 private  String frequency;

 private  int snr;

 private  String txRate;

 private  int errRate;

 private  int assoRate;


public void setStaMacAddress(String staMacAddress){
    this.staMacAddress = staMacAddress;
}


public void setAssoRate(int assoRate){
    this.assoRate = assoRate;
}


public void setTxRate(String txRate){
    this.txRate = txRate;
}


public int getRssi(){
    return rssi;
}


public int getSnr(){
    return snr;
}


public void setStaUplinkOctets(long staUplinkOctets){
    this.staUplinkOctets = staUplinkOctets;
}


public void setFrequency(String frequency){
    this.frequency = frequency;
}


public void setStaID(int staID){
    this.staID = staID;
}


public void setStaConnectTime(String staConnectTime){
    this.staConnectTime = staConnectTime;
}


public void setStaIp(String staIp){
    this.staIp = staIp;
}


public void setStaDownlinkOctets(long staDownlinkOctets){
    this.staDownlinkOctets = staDownlinkOctets;
}


public void setRssi(int rssi){
    this.rssi = rssi;
}


public long getStaDownlinkOctets(){
    return staDownlinkOctets;
}


public String getStaIp(){
    return staIp;
}


public void setBssid(String bssid){
    this.bssid = bssid;
}


public int getAssoRate(){
    return assoRate;
}


public String getStaConnectTime(){
    return staConnectTime;
}


public String getBssid(){
    return bssid;
}


public String getStaDevType(){
    return staDevType;
}


public long getStaUplinkOctets(){
    return staUplinkOctets;
}


public String getStaMacAddress(){
    return staMacAddress;
}


public String getFrequency(){
    return frequency;
}


public void setErrRate(int errRate){
    this.errRate = errRate;
}


public int getStaID(){
    return staID;
}


public void setSsid(String ssid){
    this.ssid = ssid;
}


public void setStaDevType(String staDevType){
    this.staDevType = staDevType;
}


public String getTxRate(){
    return txRate;
}


public String getSsid(){
    return ssid;
}


public int getStaVlanEnable(){
    return staVlanEnable;
}


public void setSnr(int snr){
    this.snr = snr;
}


public int getErrRate(){
    return errRate;
}


public int getStaVlanId(){
    return staVlanId;
}


public void setStaVlanId(int staVlanId){
    this.staVlanId = staVlanId;
}


public void setStaVlanEnable(int staVlanEnable){
    this.staVlanEnable = staVlanEnable;
}


}