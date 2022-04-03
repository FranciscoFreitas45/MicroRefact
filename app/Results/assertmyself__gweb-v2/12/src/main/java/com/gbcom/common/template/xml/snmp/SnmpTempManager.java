package com.gbcom.common.template.xml.snmp;
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
public void loadUpgradeReport(){
    Class<?>[] classContext = { FileTransReportTemplate.class };
    upgradeReport = XStreamUtil.fromXML(FileTransReportTemplate.class, UPGRADE_REPORT_PATH, classContext);
    if (upgradeReport == null) {
        LOG.error("LOAD upgradeReport  FILE FAILED");
        throw new Exception();
    }
}


public void init(){
    loadSnmpConfig();
    loadAccessReport();
    loadUpgradeReport();
    loadHeartReport();
    loadL2tpReport();
}


public void loadAccessReport(){
    Class<?>[] classContext = { AccessReportTemplate.class };
    accessReport = XStreamUtil.fromXML(AccessReportTemplate.class, ACCESS_REPORT_PATH, classContext);
    if (accessReport == null) {
        LOG.error("LOAD FILE FAILED");
        throw new Exception();
    }
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


public void loadL2tpReport(){
    Class<?>[] classContext = { L2tpReportTemplate.class };
    l2tpReport = XStreamUtil.fromXML(L2tpReportTemplate.class, L2TP_REPORT_PATH, classContext);
    if (l2tpReport == null) {
        LOG.error("LOAD FILE FAILED");
        throw new Exception();
    }
}


public void loadSnmpConfig(){
    Class<?>[] classContext = { SnmpContextTemplate.class, SnmpConfigTemplate.class };
    snmpContext = XStreamUtil.fromXML(SnmpContextTemplate.class, SNMP_CONFIG_PATH, classContext);
    if (snmpContext == null) {
        LOG.error("LOAD FILE FAILED");
        throw new Exception();
    }
}


public AccessReportTemplate getAccessReportTemplate(){
    return accessReport;
}


public SnmpTempManager getInstance(){
    return instance;
}


public void loadHeartReport(){
    Class<?>[] classContext = { HeartReportTemplate.class };
    heartReport = XStreamUtil.fromXML(HeartReportTemplate.class, HEART_REPORT_PATH, classContext);
    if (heartReport == null) {
        LOG.error("LOAD FILE FAILED");
        throw new Exception();
    }
}


}