package com.gbcom.common.template.xml.sys;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("SacConfig")
public class SacConfig {

@XStreamAsAttribute
 protected  String xmlns;

@XStreamAlias("Port")
 private  String port;

@XStreamAlias("Heart")
 private  int heart;

@XStreamAlias("ActThreshold")
 private  int actThreshold;

@XStreamAlias("ClearThreshold")
 private  int clearThreshold;

@XStreamAlias("Number")
 private  int number;

@XStreamAlias("ReportInterval")
 private  int reportInterval;

@XStreamAlias("OffLineThreshold")
 private  int offLineThreshold;

@XStreamAlias("LQInterval")
 private  int lqInterval;

@XStreamAlias("SnmpIp")
 private  String snmpIp;

@XStreamAlias("InnerAlarm")
 private  boolean innerAlarm;

@XStreamAlias("Tr069Switch")
 private  boolean tr069Switch;

@XStreamAlias("MultiDiscover")
 private  boolean multiDiscover;

@XStreamAlias("Ftp")
 private  FtpConfig ftp;

@XStreamAlias("L2tp")
 private  L2tpConfig L2tp;

@XStreamAlias("AutoUpgrade")
 private  boolean autoUpgrade;

@XStreamAlias("AutoConfig")
 private  boolean autoConfig;

@XStreamAlias("TargetVerList")
 private  List<TargetVer> targetVers;

@XStreamAlias("ApSysModelList")
 private  List<ApSysModel> devices;


public void setClearThreshold(int clearThreshold){
    this.clearThreshold = clearThreshold;
}


public void setTargetVers(List<TargetVer> targetVer){
    this.targetVers = targetVer;
}


public boolean isMultiDiscover(){
    return multiDiscover;
}


public void setL2tp(L2tpConfig l2tp){
    L2tp = l2tp;
}


public void setInnerAlarm(boolean innerAlarm){
    this.innerAlarm = innerAlarm;
}


public void setPort(String port){
    this.port = port;
}


public void setSnmpIp(String snmpIp){
    this.snmpIp = snmpIp;
}


public void setTr069Switch(boolean tr069Switch){
    this.tr069Switch = tr069Switch;
}


public void setHeart(int heart){
    this.heart = heart;
}


public int getNumber(){
    return number;
}


public void setAutoConfig(boolean autoConfig){
    this.autoConfig = autoConfig;
}


public List<TargetVer> getTargetVers(){
    return targetVers;
}


public int getHeart(){
    return heart;
}


public void setLqInterval(int lqInterval){
    this.lqInterval = lqInterval;
}


public boolean isAutoConfig(){
    return autoConfig;
}


public void setOffLineThreshold(int offLineThreshold){
    this.offLineThreshold = offLineThreshold;
}


public boolean isAutoUpgrade(){
    return autoUpgrade;
}


public void setAutoUpgrade(boolean autoUpgrade){
    this.autoUpgrade = autoUpgrade;
}


public int getActThreshold(){
    return actThreshold;
}


public void setFtp(FtpConfig ftp){
    this.ftp = ftp;
}


public List<ApSysModel> getDevices(){
    return devices;
}


public FtpConfig getFtp(){
    return ftp;
}


public void setMultiDiscover(boolean multiDiscover){
    this.multiDiscover = multiDiscover;
}


public int getOffLineThreshold(){
    return offLineThreshold;
}


public int getLqInterval(){
    return lqInterval;
}


public void setDevices(List<ApSysModel> devices){
    this.devices = devices;
}


public void setActThreshold(int actThreshold){
    this.actThreshold = actThreshold;
}


public void setReportInterval(int reportInterval){
    this.reportInterval = reportInterval;
}


public void setNumber(int number){
    this.number = number;
}


public String getSnmpIp(){
    return snmpIp;
}


public int getReportInterval(){
    return reportInterval;
}


public boolean isInnerAlarm(){
    return innerAlarm;
}


public String getPort(){
    return port;
}


public L2tpConfig getL2tp(){
    return L2tp;
}


public int getClearThreshold(){
    return clearThreshold;
}


public boolean isTr069Switch(){
    return tr069Switch;
}


}