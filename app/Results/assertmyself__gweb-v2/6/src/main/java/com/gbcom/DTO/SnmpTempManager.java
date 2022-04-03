package com.gbcom.DTO;
 import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
public class SnmpTempManager {

 private  Logger LOG;

 private  String HEART_REPORT_PATH;

 private  String ACCESS_REPORT_PATH;

 private  String SNMP_CONFIG_PATH;

 private  String UPGRADE_REPORT_PATH;

 private  String L2TP_REPORT_PATH;

 private  SnmpTempManager instance;

 private  AccessReportTemplate accessReport;

 private  HeartReportTemplate heartReport;

 private  L2tpReportTemplate l2tpReport;

 private  FileTransReportTemplate upgradeReport;

 private  SnmpContextTemplate snmpContext;

private SnmpTempManager() {
}
public HeartReportTemplate getHeartReportTemplate(){
    return heartReport;
}


public FileTransReportTemplate getUpgradeReportTemplate(){
    return upgradeReport;
}


public SnmpContextTemplate getSnmpContext(){
    return snmpContext;
}


public L2tpReportTemplate getL2tpReportTemplate(){
    return l2tpReport;
}


public SnmpConfigTemplate getDefaultSnmpConfig(){
    return getSnmpContext().getList().get(0);
}


public AccessReportTemplate getAccessReportTemplate(){
    return accessReport;
}


public SnmpTempManager getInstance(){
    return instance;
}


}